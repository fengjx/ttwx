package com.fengjx.wechat.base.dao;

import com.fengjx.wechat.base.model.Material;
import java.util.List;
import java.util.Map;

public abstract interface MaterialMapper{
  public abstract int deleteByPrimaryKey(String paramString);

  public abstract int insert(Material paramMaterial);

  public abstract int insertSelective(Material paramMaterial);

  public abstract Material selectByPrimaryKey(String paramString);

  public abstract List<Material> selectList(Map<String, String> paramMap);

  public abstract int updateByPrimaryKeySelective(Material paramMaterial);

  public abstract int updateByPrimaryKeyWithBLOBs(Material paramMaterial);

  public abstract int updateByPrimaryKey(Material paramMaterial);
}