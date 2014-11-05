package com.fjx.wechat.base.web.admin.service;

import com.fjx.common.framework.base.service.IBaseAbstractService;
import com.fjx.wechat.base.web.admin.entity.WechatPublicAccountEntity;



/**
 * 公众账号信息service
 * @author fengjx xd-fjx@qq.com
 * @version WechatPublicAccountService.java 2014年10月5日
 */
public interface WechatPublicAccountService extends IBaseAbstractService<WechatPublicAccountEntity> {
	
	/**
	 * 根据ticket获得公众账号信息
	 * @param ticket
	 * @return
	 */
	public WechatPublicAccountEntity getWechatPublicAccountByTicket(String ticket);
	
}
