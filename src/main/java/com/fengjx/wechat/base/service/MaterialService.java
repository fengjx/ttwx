package com.fengjx.wechat.base.service;

import com.fengjx.common.mybatis.PageWapper;
import com.fengjx.wechat.base.model.Material;
import com.fengjx.wechat.base.model.SysUser;
import java.util.List;
import java.util.Map;

public abstract interface MaterialService
{
  public abstract PageWapper<Material> getListPageByType(String paramString, SysUser paramSysUser);

  public abstract void saveOrUpdate(Material paramMaterial, List<Map<String, Object>> paramList)
    throws Exception;

  public abstract String loadMaterialContentByUrl(String paramString)
    throws Exception;

  public abstract int deleteByPrimaryKey(String paramString);

  public abstract Material selectByPrimaryKey(String paramString);
}