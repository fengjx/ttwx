
package com.fengjx.wechat.base.dao;

import com.fengjx.wechat.base.model.DataDict;

/**
 * 字段管理
 */
public interface DataDictMapper {

    int deleteByPrimaryKey(String id);

    int insert(DataDict record);

    int insertSelective(DataDict record);

    DataDict selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DataDict record);

    int updateByPrimaryKey(DataDict record);
}
