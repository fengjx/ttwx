
package com.fengjx.modules.wechat.service;

import com.fengjx.commons.ext.qiniu.QiNiuUti;
import com.fengjx.commons.plugin.db.Model;
import com.fengjx.commons.plugin.db.Page;
import com.fengjx.commons.plugin.freemarker.FreemarkerUtil;
import com.fengjx.commons.utils.*;
import com.fengjx.modules.common.constants.AppConfig;
import com.fengjx.modules.wechat.process.sdk.api.WxMpServiceExt;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.bean.WxMpMassGroupMessage;
import me.chanjar.weixin.mp.bean.WxMpMassNews;
import me.chanjar.weixin.mp.bean.WxMpMassOpenIdsMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutNewsMessage;
import me.chanjar.weixin.mp.bean.result.WxMpMassSendResult;
import me.chanjar.weixin.mp.bean.result.WxMpMassUploadResult;
import me.chanjar.weixin.mp.util.xml.XStreamTransformer;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fengjx.modules.wechat.bean.WechatMaterial;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Autu Generated .
 */
@Component
public class WechatMaterialService extends Model<WechatMaterial> {

    private static final Logger LOG = LoggerFactory.getLogger(WechatMaterialService.class);

    private static final String STORE_PREFIX = "/upload/html/material/";

    @Autowired
    private WechatPublicAccountService publicAccountService;

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
                    String targetFileName = StringUtils.isBlank(fileName)
                            ? CommonUtils.getPrimaryKey() : fileName + i;
                    String htmlUrl = STORE_PREFIX + targetFileName + ".html";
                    Map<String, Object> content = contents.get(i);
                    content.put("app_name", AppConfig.APP_NAME);
                    content.put("date", DateUtils.formatDate(now_date, "yyyy-MM-dd"));
                    content.put("email", AppConfig.SUPPORT_EMAIL);
                    // 生成html文件
                    createHtml(targetFileName, htmlUrl, content);
                    String uri = AppConfig.STATIC_DOMAIN + htmlUrl;
                    xml_data = xml_data.replaceAll("\\<Url_" + i + ">(.*?)\\</Url_" + i + ">",
                            "<Url><![CDATA[" + uri + "]]></Url>");
                }
                xml_data.replaceAll("\\<ArticleCount>(.*?)\\</ArticleCount>",
                        "<ArticleCount>" + l + "</ArticleCount>");
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
     * 生产静态文件
     *
     * @param targetFileName
     * @param htmlUrl
     * @param content
     */
    private void createHtml(String targetFileName, String htmlUrl, Map<String, Object> content) {
        if (AppConfig.isQiniu()) {
            try {
                // 创建临时文件，上传完成后删除
                File temp = File.createTempFile(targetFileName, ".temp");
                String path = FreemarkerUtil.createHTML(content, "html/material.ftl",
                        temp.getAbsolutePath());
                QiNiuUti.uploadFile(temp, htmlUrl, true);
                temp.delete();
                LOG.debug("path:" + path);
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }
        } else {
            String htmlPath = AppConfig.STATIC_PATH;
            // 如果不存在则创建文件夹
            FileUtil.makeDirectory(htmlPath + STORE_PREFIX);
            htmlPath = htmlPath + htmlUrl;
            // 通过freemarker生成静态页面，并返回URL
            FreemarkerUtil.createHTML(content, "html/material.ftl", htmlPath);
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
            if (url.startsWith("http://")) {
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

    public void previewMsg(final List<Map<String, Object>> contents, String xmlData, String userId,
            String wxUserId) throws WxErrorException {
        WxMpMassUploadResult uploadResult = uploadMedia(contents, xmlData, userId);
        WxMpServiceExt mpService = (WxMpServiceExt) publicAccountService.getWxMpService(userId);
        WxMpMassOpenIdsMessage massMessage = new WxMpMassOpenIdsMessage();
        massMessage.setMsgType(WxConsts.MASS_MSG_NEWS);
        massMessage.setMediaId(uploadResult.getMediaId());
        massMessage.getToUsers().add(wxUserId);// .add("oaXuSv4_0mXijafuTFuJ6DqeX7Jo");
        WxMpMassSendResult sendResult = mpService.massPreviewMessage(massMessage);
        LOG.debug("send mass message news:" + sendResult);
    }

    private WxMpMassUploadResult uploadMedia(final List<Map<String, Object>> contents,
            String xmlData, String userId) throws WxErrorException {
        WxMpXmlOutNewsMessage outNewsMessage = XStreamTransformer
                .fromXml(WxMpXmlOutNewsMessage.class, xmlData);
        outNewsMessage.setCreateTime(System.currentTimeMillis());

        // 微信服务器交互
        WxMpServiceExt mpService = (WxMpServiceExt) publicAccountService.getWxMpService(userId);
        WxMpMassNews massNews = new WxMpMassNews();

        int i = 0;
        for (WxMpXmlOutNewsMessage.Item item : outNewsMessage.getArticles()) {
            if (contents == null || contents.isEmpty()) {
                WxMpMassNews.WxMpMassNewsArticle art = new WxMpMassNews.WxMpMassNewsArticle();
                // art.setAuthor(AppConfig.APP_NAME);
                art.setShowCoverPic(false);
                // art.setContentSourceUrl(contentSourceUrl);;
                art.setTitle(item.getTitle());
                art.setContent("Test");
                massNews.addArticle(art);
                break;
            }
            Map<String, Object> content = contents.get(i);
            // LOG.debug("content:" + content);
            String picUrl = item.getPicUrl();
            WxMpMassNews.WxMpMassNewsArticle art = new WxMpMassNews.WxMpMassNewsArticle();
            art.setAuthor(AppConfig.APP_NAME);
            art.setShowCoverPic(false);
            // art.setContentSourceUrl(contentSourceUrl);;
            art.setTitle(item.getTitle());
            Object content2 = content.get("content");
            LOG.debug("content2:" + content2);
            art.setContent((String) content2);

            if (picUrl != null) {
                InputStream is;
                WxMediaUploadResult uploadMediaResult = null;
                try {
                    // 上传图片获得 media id
                    is = new URL(picUrl).openStream();
                    uploadMediaResult = mpService.mediaUpload(WxConsts.MEDIA_IMAGE,
                            WxConsts.FILE_JPG, is);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    LogUtil.error(LOG, e.getMessage(), e);
                } catch (IOException e) {
                    LogUtil.error(LOG, e.getMessage(), e);
                } catch (WxErrorException e) {
                    LogUtil.error(LOG, e.getMessage(), e);
                }
                LogUtil.debug(LOG, "uploadImageResult:" + uploadMediaResult);
                if (uploadMediaResult != null && uploadMediaResult.getMediaId() != null) {
                    art.setThumbMediaId(uploadMediaResult.getMediaId());
                }
            }
            massNews.addArticle(art);
            i++;
        }
        // upload news and get media id
        WxMpMassUploadResult uploadResult = mpService.massNewsUpload(massNews);
        return uploadResult;
    }

    public void sendGroupMsg(List<Map<String, Object>> contents, String xmlData, String userId)
            throws WxErrorException {
        WxMpMassUploadResult uploadResult = uploadMedia(contents, xmlData, userId);
        WxMpServiceExt mpService = (WxMpServiceExt) publicAccountService.getWxMpService(userId);
        WxMpMassGroupMessage message = new WxMpMassGroupMessage();
        message.setMsgtype(WxConsts.MASS_MSG_NEWS);
        message.setMediaId(uploadResult.getMediaId());
        WxMpMassSendResult result = mpService.massGroupMessageSend(message);
        LOG.debug(result.toString());
    }

}
