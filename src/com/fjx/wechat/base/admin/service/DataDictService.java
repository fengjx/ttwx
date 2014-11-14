package com.fjx.wechat.base.admin.service;

import java.util.List;

import com.fjx.common.framework.base.service.IBaseAbstractService;
import com.fjx.wechat.base.admin.entity.DataDictEntity;



/**
 * 数据字典
 * @author fengjx xd-fjx@qq.com
 * @version DataDictService.java 2014年9月13日
 */
public interface DataDictService extends IBaseAbstractService<DataDictEntity> {
	
	
	/**
	 * 通过字典组和字典值查询字典信息
	 * @param group_code
	 * @param dict_value
	 * @return
	 */
	public DataDictEntity findDict(String group_code, String dict_value);
	
	/**
	 * 根据字典组，查询字典列表
	 * @param group
	 * @return
	 */
	public List<DataDictEntity> findDictList(String group_code);
	
	
}
