package com.fjx.wechat.mysdk.process.in;

import java.util.Map;

import com.fjx.wechat.mysdk.context.WechatContext;
import com.fjx.wechat.mysdk.process.in.executor.InServiceExecutor;
import com.fjx.wechat.mysdk.process.in.executor.InServiceExecutorFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.fjx.wechat.base.admin.entity.WechatPublicAccountEntity;

/**
 * 微信消息核心处理类
 * @author fengjx
 * @date 
 */
public class InServiceEngineImpl implements InServiceEngine {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	/**
     * 请求服务工厂
     */
	@Autowired
    private InServiceExecutorFactory inServiceExecutorFactory;
	
	/**
	 * 处理微信发来的请求
	 * @return
	 */
	@Override
	public String processRequest() {
		String respMessage = "";
		Map<String, String>	requestMap = WechatContext.getWechatPostMap();
		WechatPublicAccountEntity accountEntity = WechatContext.getPublicAccount();
		//微信发送的参数
		InServiceExecutor executor;
		try {
			executor = inServiceExecutorFactory.getExecutorByName(requestMap);
			if(null == executor){
				logger.warn("未识别到消息动作分发器，此消息不做处理");
				return "";
			}
			//已激活状态，同时fromUserName与公众号ID不符，视为无效请求
			if (WechatPublicAccountEntity.VALID_STATE_ACTIVATE.equals(accountEntity.getValid_state())
					&& !requestMap.get("ToUserName").equals(accountEntity.getAccount_id())) {
				logger.warn("ToUserName["+requestMap.get("ToUserName")+"]无效，返回空不做响应");
				return "";
			}
			respMessage = executor.execute();
		} catch (Exception e) {
			logger.error("处理微信请求出现异常", e);
			return "";	//返回空字符串，微信将不做处理，且不再消息重发
		}
		return respMessage;
	}
	
	
}