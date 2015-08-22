
package com.fengjx.ttwx.modules.wechat.model;

import com.ext.qiniu.QiNiuUti;
import com.fengjx.ttwx.common.plugin.db.Mapper;
import com.fengjx.ttwx.common.plugin.db.Model;
import com.fengjx.ttwx.common.plugin.db.Page;
import com.fengjx.ttwx.common.plugin.freemarker.FreemarkerUtil;
import com.fengjx.ttwx.common.utils.CommonUtils;
import com.fengjx.ttwx.common.utils.FileUtil;
import com.fengjx.ttwx.common.utils.HttpUtil;
import com.fengjx.ttwx.common.utils.LogUtil;
import com.fengjx.ttwx.modules.common.constants.AppConfig;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.ext.WxMpServiceExt;
import me.chanjar.weixin.mp.bean.WxMpMassNews;
import me.chanjar.weixin.mp.bean.WxMpMassNews.WxMpMassNewsArticle;
import me.chanjar.weixin.mp.bean.WxMpMassOpenIdsMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutNewsMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutNewsMessage.Item;
import me.chanjar.weixin.mp.bean.result.WxMpMassSendResult;
import me.chanjar.weixin.mp.bean.result.WxMpMassUploadResult;
import me.chanjar.weixin.mp.util.xml.XStreamTransformer;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 素材管理
 *
 * @author fengjx.
 * @date：2015/5/28 0028
 */
@Component
@Mapper(table = "wechat_material")
public class Material extends Model {

    private static final Log logger = LogFactory.getLog(Material.class);
    @Autowired
    private PublicAccount publicAccount;

    private static final Logger LOG = LoggerFactory.getLogger(Material.class);

    /**
     * 分页查询
     *
     * @param pageNumber
     * @param pageSize
     * @param type
     * @param userId
     * @return
     */
    public Page<Map<String, Object>> getListPageByType(int pageNumber, int pageSize, String type,
            String userId) {
        StringBuilder sql = new StringBuilder(getSelectSql());
        sql.append(" where msg_type = ? and user_id = ? order by in_time desc");
        return paginate(pageNumber, pageSize, sql.toString(), type, userId);
    }

    /**
     * 新增或者删除
     *
     * @param params
     * @param contents
     * @param userId
     */
    public void saveOrUpdate(Map<String, Object> params, List<Map<String, Object>> contents,
            String userId) {
        if (MapUtils.isEmpty(params)) {
            throw new RuntimeException("添加素材失败，提交的数据为空");
        }
        String fileName = (String) params.get("file_name");
        Date now_date = new Date();
        params.put("in_time", now_date);
        String msgType = (String) params.get("msg_type");
        if (null != msgType && msgType.equals("news")) { // 图文消息
            if (null != contents && contents.size() > 0) {
                String xml_data = (String) params.get("xml_data");
                int l = contents.size();
                for (int i = 0; i < l; i++) {
                    String htmlPath = AppConfig.STATIC_PATH;
                    String primaryKey = StringUtils.isBlank(fileName) ? CommonUtils.getPrimaryKey()
                            : fileName + i;
                    String storePrefix = "/upload/html/material/";
                    String htmlUrl = storePrefix + primaryKey
                            + ".html";

                    Map<String, Object> content = contents.get(i);
                    content.put("app_name", AppConfig.APP_NAME);
                    content.put("date", CommonUtils.date2String(now_date));
                    content.put("email", AppConfig.SUPPORT_EMAIL);

                    if (QiNiuUti.isStoreInQiNiu()) {
                        try {
                            // 创建临时文件，上传完成后删除
                            File temp = File.createTempFile("uphtml", ".temp");
                            String path = FreemarkerUtil.createHTML(content, "html/material.ftl",
									temp.getAbsolutePath());
                            QiNiuUti.uploadFile(temp, htmlUrl, true);
                            temp.delete();

                            logger.debug("path:" + path);
                        } catch (IOException e) {
                            logger.error(e.getMessage(), e);
                        } catch (Exception e) {
                            logger.error(e.getMessage(), e);
                        }

                    } else {
                        // 如果不存在则创建文件夹
                        FileUtil.makeDirectory(htmlPath + storePrefix);
                        htmlPath = htmlPath + htmlUrl;
                        // 通过freemarker生成静态页面，并返回URL
                        FreemarkerUtil.createHTML(content, "html/material.ftl", htmlPath);
                    }
                    String uri = AppConfig.STATIC_DOMAIN + htmlUrl;
                    // 避免出现abc//abc.html的情况
                    if (AppConfig.STATIC_DOMAIN.endsWith("/") && htmlUrl.startsWith("/")) {
                        // uri= AppConfig.STATIC_DOMAIN +
                        // htmlUrl.substring(1,htmlUrl.length());
                    }
                    xml_data = xml_data.replaceAll("\\<Url_" + i + ">(.*?)\\</Url_" + i + ">",
                            "<Url><![CDATA[" + uri + "]]></Url>");
                }
                xml_data.replaceAll("\\<ArticleCount>(.*?)\\</ArticleCount>", "<ArticleCount>" + l
                        + "</ArticleCount>");
                params.put("xml_data", xml_data);
            }
        }
        params.put("user_id", userId);
        if (StringUtils.isBlank((String) params.get("id"))) {
            insert(params);
        } else {
            update(params);
        }
    }

    /**
     * 通过URL读取页面内容
     *
     * @param url
     * @return
     */
    public String loadMaterialContentByUrl(String url) {
        String conten = "页面丢失，请重新编辑！";
        try {
            if (url.startsWith("http://"))
            {
                url = url.substring("http://".length(), url.length());
                url = "http://" + url.replace("//", "/");// 替换url中的双斜杠
            } else if (url.startsWith("https://")) {
                url = url.substring("https://".length(), url.length());
                url = "https://" + url.replace("//", "/");// 替换url中的双斜杠
            }
            conten = HttpUtil.get(url);
            conten = conten.substring(conten.indexOf("<!--###@content@###-->") + 22,
                    conten.lastIndexOf("<!--###@content@###-->"));
        } catch (Exception e) {
            LogUtil.error(LOG, "读取页面失败，url-->" + url, e);
        }
        return conten;
    }

    public void previewMessage(final List<Map<String, Object>> contents, String xmlData,
            String userId, String wxUserId) throws WxErrorException {

        WxMpXmlOutNewsMessage outNewsMessage = XStreamTransformer.fromXml(
                WxMpXmlOutNewsMessage.class, xmlData);
        outNewsMessage.setCreateTime(System.currentTimeMillis());

        // 微信服务器交互

        WxMpServiceExt mpService = (WxMpServiceExt) publicAccount.getWxMpService(userId);
        WxMpMassNews massNews = new WxMpMassNews();

        int i = 0;
        for (Item item : outNewsMessage.getArticles()) {
            if (contents == null || contents.isEmpty()) {
                WxMpMassNewsArticle art = new WxMpMassNewsArticle();
                // art.setAuthor(AppConfig.APP_NAME);
                art.setShowCoverPic(false);
                // art.setContentSourceUrl(contentSourceUrl);;
                art.setTitle(item.getTitle());

                art.setContent("Test");
                massNews.addArticle(art);
                break;
            }
            Map<String, Object> content = contents.get(i);
            // logger.debug("content:" + content);
            String picUrl = item.getPicUrl();
            WxMpMassNewsArticle art = new WxMpMassNewsArticle();
            art.setAuthor(AppConfig.APP_NAME);
            art.setShowCoverPic(false);
            // art.setContentSourceUrl(contentSourceUrl);;
            art.setTitle(item.getTitle());
            Object content2 = content.get("content");
            logger.debug("content2:" + content2);
            art.setContent((String) content2);

            if (picUrl != null) {
                InputStream is;
                WxMediaUploadResult uploadMediaResult = null;
                try {
                    // 上传图片获得 media id
                    is = new URL(picUrl).openStream();
                    uploadMediaResult = mpService.mediaUpload(
                            WxConsts.MEDIA_IMAGE, WxConsts.FILE_JPG, is);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (WxErrorException e) {
                    e.printStackTrace();
                }
                logger.debug("uploadImageResult:" + uploadMediaResult);
                if (uploadMediaResult != null
                        && uploadMediaResult.getMediaId() != null) {
                    art.setThumbMediaId(uploadMediaResult.getMediaId());
                }
            }
            massNews.addArticle(art);
            i++;
        }

        // upload news and get media id
        WxMpMassUploadResult uploadResult = mpService
                .massNewsUpload(massNews);

        WxMpMassOpenIdsMessage massMessage = new WxMpMassOpenIdsMessage();
        massMessage.setMsgType(WxConsts.MASS_MSG_NEWS);
        massMessage.setMediaId(uploadResult.getMediaId());
        massMessage.getToUsers().add(wxUserId);// .add("oaXuSv4_0mXijafuTFuJ6DqeX7Jo");
        WxMpMassSendResult sendResult = mpService.massPreviewMessage(massMessage);
        logger.debug("send mass message news:" + sendResult);
    }

}
