
package com.fjx.wechat.base.admin.service;

import com.fjx.common.framework.base.service.impl.BaseAbstractService;
import com.fjx.common.framework.system.context.MySystemContext;
import com.fjx.common.framework.system.exception.MyException;
import com.fjx.common.framework.system.exception.MyRuntimeException;
import com.fjx.common.framework.system.pagination.Pagination;
import com.fjx.common.utils.CommonUtils;
import com.fjx.common.utils.FileUtil;
import com.fjx.common.utils.MyFreemarker;
import com.fjx.wechat.base.admin.entity.MaterialEntity;
import com.fjx.wechat.base.admin.entity.SysUserEntity;
import com.fjx.wechat.config.AppConfig;
import com.fjx.wechat.mysdk.tools.HttpUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 素材管理
 * 
 * @author fengjx xd-fjx@qq.com
 * @date 2014年9月12日
 */
@Service("materialService")
public class MaterialServiceImpl extends BaseAbstractService<MaterialEntity> implements
        MaterialService {

    @Override
    public Pagination<MaterialEntity> getListPageByType(String type, SysUserEntity sysUser) {
        String hql = "from MaterialEntity m where m.msg_type = ? and m.sysUser = ? order by m.in_time desc ";
        return pageByHql(hql, type, sysUser);
    }

    @Override
    public void saveOrUpdate(MaterialEntity material, List<Map<String, String>> contents)
            throws Exception {
        if (null == material) {
            throw new MyException("添加素材失败，提交的数据为空");
        }
        String date_str = CommonUtils.date2String(new Date());
        material.setIn_time(new Date());
        String msgType = material.getMsg_type();
        if (null != msgType && msgType.equals("news")) { // 图文消息
            if (null != contents && contents.size() > 0) {
                String basePath = CommonUtils.getFtlHtmlPath(MySystemContext.getMyRequest());
                MyFreemarker freemarker = MyFreemarker.getInstance(basePath);
                String xml_data = material.getXml_data();
                int l = contents.size();
                for (int i = 0; i < l; i++) {
                    String htmlPath = AppConfig.STATIC_PATH;
                    String htmlUrl = "/upload/html/material/" + CommonUtils.getPrimaryKey()
                            + ".html";
                    // 如果不存在则创建文件夹
                    FileUtil.makeDirectory(htmlPath + "/upload/html/material/");
                    htmlPath = htmlPath + htmlUrl;
                    Map<String, String> content = contents.get(i);
                    content.put("date", date_str);
                    // 通过freemarker生成静态页面，并返回URL
                    freemarker.createHTML(content, "material.ftl", htmlPath);
                    xml_data = xml_data.replaceAll("\\<Url_" + i + ">(.*?)\\</Url_" + i + ">",
                            "<Url><![CDATA[" + AppConfig.STATIC_DOMAIN + htmlUrl + "]]></Url>");
                }
                xml_data.replaceAll("\\<ArticleCount>(.*?)\\</ArticleCount>", "<ArticleCount>" + l
                        + "</ArticleCount>");
                material.setXml_data(xml_data);
            }
        }
        if (StringUtils.isBlank(material.getId())) {
            save(material);
            return;
        }
        update(material);
    }

    @Override
    public String loadMaterialContentByUrl(String url) {
        String conten = "error";
        try {
            String path = AppConfig.STATIC_DOMAIN + url;
            conten = HttpUtil.get(path);
            conten = conten.substring(conten.indexOf("<!--###@content@###-->") + 22,
                    conten.lastIndexOf("<!--###@content@###-->"));
        } catch (Exception e) {
            throw new MyRuntimeException(e);
        }
        return conten;
    }

}
