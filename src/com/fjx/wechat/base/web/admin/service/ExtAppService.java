package com.fjx.wechat.base.web.admin.service;

import java.util.List;

import com.fjx.common.framework.base.service.IBaseAbstractService;
import com.fjx.wechat.base.web.admin.entity.ExtAppEntity;



/**
 * 应用扩展
 * @author fengjx xd-fjx@qq.com
 * @version ExtAppService.java 2014年9月13日
 */
public interface ExtAppService extends IBaseAbstractService<ExtAppEntity> {
	
	/**
	 * 根据类型查询
	 * @param type 应用类型
	 * @return
	 */
	public List<ExtAppEntity> listByType(String app_type);
	
}
