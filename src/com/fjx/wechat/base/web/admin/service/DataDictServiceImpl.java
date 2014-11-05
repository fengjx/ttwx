package com.fjx.wechat.base.web.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fjx.common.framework.base.service.impl.BaseAbstractService;
import com.fjx.wechat.base.web.admin.entity.DataDictEntity;



/**
 * 数据字典
 * @author fengjx xd-fjx@qq.com
 * @version DataDictServiceImpl.java 2014年9月13日
 */
@Service
public class DataDictServiceImpl extends BaseAbstractService<DataDictEntity> implements DataDictService {

	@Override
	public DataDictEntity findDict(String group_code, String dict_value) {
		String hql = " from DataDictEntity d where d.group_code = ? and d.dict_value = ?";
		return findOneByHql(hql, group_code, dict_value);
	}

	@Override
	public List<DataDictEntity> findDictList(String group_code) {
		String hql = " from DataDictEntity d where d.group_code = ?";
		return findListByHql(hql, group_code);
	}
	
	
	
}
