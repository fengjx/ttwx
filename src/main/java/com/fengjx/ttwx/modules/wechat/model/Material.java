
package com.fengjx.ttwx.modules.wechat.model;

import com.fengjx.ttwx.common.plugin.db.Mapper;
import com.fengjx.ttwx.common.plugin.db.Model;
import com.fengjx.ttwx.common.plugin.db.Page;
import com.fengjx.ttwx.common.system.init.FreeMarkerUtil;
import com.fengjx.ttwx.common.utils.CommonUtils;
import com.fengjx.ttwx.common.utils.FileUtil;
import com.fengjx.ttwx.common.utils.HttpUtil;
import com.fengjx.ttwx.common.utils.LogUtil;
import com.fengjx.ttwx.modules.common.constants.AppConfig;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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
        Date now_date = new Date();
        params.put("in_time", now_date);
        String msgType = (String) params.get("msg_type");
        if (null != msgType && msgType.equals("news")) { // 图文消息
            if (null != contents && contents.size() > 0) {
                String xml_data = (String) params.get("xml_data");
                int l = contents.size();
                for (int i = 0; i < l; i++) {
                    String htmlPath = AppConfig.STATIC_PATH;
                    String htmlUrl = "/upload/html/material/" + CommonUtils.getPrimaryKey()
                            + ".html";
                    // 如果不存在则创建文件夹
                    FileUtil.makeDirectory(htmlPath + "/upload/html/material/");
                    htmlPath = htmlPath + htmlUrl;
                    Map<String, Object> content = contents.get(i);
                    content.put("app_name", AppConfig.APP_NAME);
                    content.put("date", CommonUtils.date2String(now_date));
                    // 通过freemarker生成静态页面，并返回URL
                    FreeMarkerUtil.createHTML(content, "html/material.ftl", htmlPath);
                    xml_data = xml_data.replaceAll("\\<Url_" + i + ">(.*?)\\</Url_" + i + ">",
                            "<Url><![CDATA[" + AppConfig.STATIC_DOMAIN + htmlUrl + "]]></Url>");
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
            conten = HttpUtil.get(url);
            conten = conten.substring(conten.indexOf("<!--###@content@###-->") + 22,
                    conten.lastIndexOf("<!--###@content@###-->"));
        } catch (Exception e) {
            LogUtil.error(LOG, "读取页面失败，url-->" + url, e);
        }
        return conten;
    }

}
