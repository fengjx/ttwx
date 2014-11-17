package com.fjx.wechat.base.admin.service;

import java.util.List;

import com.fjx.common.framework.system.pagination.Pagination;
import org.springframework.stereotype.Service;

import com.fjx.common.framework.base.service.impl.BaseAbstractService;
import com.fjx.wechat.base.admin.entity.ExtAppEntity;



/**
 * 
 * @author fengjx xd-fjx@qq.com
 * @version ExtAppServiceImpl.java 2014年9月13日
 */
@Service
public class ExtAppServiceImpl extends BaseAbstractService<ExtAppEntity> implements ExtAppService {

	/**
	 *
	 * @param app_type 应用类型 web、api、restful
	 * @param msg_type
	 * @param event_type
	 * @return
	 */
	@Override
	public List<ExtAppEntity> listByType(String app_type, String msg_type, String event_type){
		String hql = "from ExtAppEntity e where e.app_type = ?";
		return findListByHql(hql, app_type);
	}


	@Override
	public Pagination<ExtAppEntity> page(ExtAppEntity extApp) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from ExtAppEntity");

		return null;
	}


}
