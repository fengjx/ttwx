package com.fjx.wechat.base.web.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fjx.common.framework.base.service.impl.BaseAbstractService;
import com.fjx.wechat.base.web.admin.entity.ExtAppEntity;



/**
 * 
 * @author fengjx xd-fjx@qq.com
 * @version ExtAppServiceImpl.java 2014年9月13日
 */
@Service
public class ExtAppServiceImpl extends BaseAbstractService<ExtAppEntity> implements ExtAppService {
	
	
	public List<ExtAppEntity> listByType(String app_type){
		String hql = "from ExtAppEntity e where e.app_type = ?";
		return findListByHql(hql, app_type);
	}
	
}
