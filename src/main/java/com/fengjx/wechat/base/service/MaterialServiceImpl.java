package com.fengjx.wechat.base.service;

import com.fengjx.common.mybatis.PageWapper;
import com.fengjx.common.system.context.MySystemContext;
import com.fengjx.common.system.exception.MyException;
import com.fengjx.common.utils.CommonUtils;
import com.fengjx.common.utils.FileUtil;
import com.fengjx.common.utils.HttpUtil;
import com.fengjx.common.utils.MyFreemarker;
import com.fengjx.wechat.base.dao.MaterialMapper;
import com.fengjx.wechat.base.model.Material;
import com.fengjx.wechat.base.model.SysUser;
import com.fengjx.wechat.config.AppConfig;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialServiceImpl
  implements MaterialService
{

  @Autowired
  private MaterialMapper materialMapper;

  public PageWapper<Material> getListPageByType(String newType, SysUser sysUser)
  {
    Map param = new HashMap();
    param.put("newType", newType);
    param.put("userId", sysUser.getId());
    List materials = this.materialMapper.selectList(param);
    return new PageWapper(materials);
  }

  public void saveOrUpdate(Material material, List<Map<String, Object>> contents)
    throws Exception
  {
    if (null == material) {
      throw new MyException("添加素材失败，提交的数据为空");
    }
    String date_str = CommonUtils.date2String(new Date());
    material.setInTime(new Date());
    String msgType = material.getMsgType();
    if ((null != msgType) && (msgType.equals("news")) && 
      (null != contents) && (contents.size() > 0)) {
      String basePath = CommonUtils.getFtlHtmlPath(MySystemContext.getMyRequest());
      MyFreemarker freemarker = MyFreemarker.getInstance(basePath);
      String xml_data = material.getXmlData();
      int l = contents.size();
      for (int i = 0; i < l; ++i) {
        String htmlPath = AppConfig.STATIC_PATH;
        String htmlUrl = "/upload/html/material/" + CommonUtils.getPrimaryKey() + ".html";

        FileUtil.makeDirectory(htmlPath + "/upload/html/material/");
        htmlPath = htmlPath + htmlUrl;
        Map content = (Map)contents.get(i);
        content.put("date", date_str);

        freemarker.createHTML(content, "material.ftl", htmlPath);
        xml_data = xml_data.replaceAll("\\<Url_" + i + ">(.*?)\\</Url_" + i + ">", "<Url><![CDATA[" + AppConfig.STATIC_DOMAIN + htmlUrl + "]]></Url>");
      }

      xml_data.replaceAll("\\<ArticleCount>(.*?)\\</ArticleCount>", "<ArticleCount>" + l + "</ArticleCount>");

      material.setXmlData(xml_data);
    }

    if (StringUtils.isBlank(material.getId())) {
      material.setId(CommonUtils.getPrimaryKey());
      this.materialMapper.insert(material);
      return;
    }
    this.materialMapper.updateByPrimaryKeySelective(material);
  }

  public String loadMaterialContentByUrl(String url)
    throws Exception
  {
    String conten = "error";
    try {
      String path = AppConfig.STATIC_DOMAIN + url;
      conten = HttpUtil.get(path);
      conten = conten.substring(conten.indexOf("<!--###@content@###-->") + 22, conten.lastIndexOf("<!--###@content@###-->"));
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
    return conten;
  }

  public int deleteByPrimaryKey(String id)
  {
    return this.materialMapper.deleteByPrimaryKey(id);
  }

  public Material selectByPrimaryKey(String id)
  {
    return this.materialMapper.selectByPrimaryKey(id);
  }
}