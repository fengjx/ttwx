package com.fengjx.modules.wechat.process;



/**
 * 微信消息处理核心服务类
 * @author fengjx
 *
 */
public interface ServiceEngine {
	
	/**
	 * 微信核心消息处理方法
	 * @return
	 * @throws Exception
	 */
	String processRequest();
	
}