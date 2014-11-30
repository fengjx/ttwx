package com.fjx.wechat.base.admin.service;

import java.util.List;
import java.util.Map;

import com.fjx.common.framework.system.pagination.Pagination;
import com.fjx.wechat.base.admin.entity.DataDictEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.fjx.common.framework.base.service.impl.BaseAbstractService;


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
		if(StringUtils.isBlank(group_code)){
			throw new IllegalArgumentException("group_code不能为空");
		}
		String hql = " from DataDictEntity d where d.group_code = ? order by d.order_num";
		return findListByHql(hql, group_code);
	}

	@Override
	public List<Map<String, String>> findDictGroup() {
		String hql = " select distinct new map (d.group_code as group_code, d.group_name as group_name) from DataDictEntity d ";
		return findListByHql(hql);
	}

	/**
	 * 分页查询
	 * @param group_code
	 * @return
	 */
	@Override
	public Pagination<DataDictEntity> pageList(String group_code) {
		String hql = " from DataDictEntity d where d.group_code = ? order by d.group_code, d.order_num";
		if(StringUtils.isBlank(group_code)){
			hql = " from DataDictEntity d order by d.group_code, d.order_num";
			return pageByHql(hql);
		}
		return pageByHql(hql, group_code);
	}


}
