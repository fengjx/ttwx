
package com.fengjx.wechat.base.dao;

import com.fengjx.wechat.base.model.DataDict;

public abstract interface DataDictMapper{

    public abstract int deleteByPrimaryKey(String paramString);

    public abstract int insert(DataDict paramDataDict);

    public abstract int insertSelective(DataDict paramDataDict);

    public abstract DataDict selectByPrimaryKey(String paramString);

    public abstract int updateByPrimaryKeySelective(DataDict paramDataDict);

    public abstract int updateByPrimaryKey(DataDict paramDataDict);
}
