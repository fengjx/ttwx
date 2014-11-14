package com.fjx.wechat.mysdk.process.in;



/**
 * 微信消息处理核心服务类
 * @author fengjx
 *
 */
public interface InServiceEngine {
	
	/**
	 * 微信核心消息处理方法
	 * @return
	 * @throws Exception
	 */
	public String processRequest() throws Exception;
	
}