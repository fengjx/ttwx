package com.fjx.wechat.base.process.in;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.fjx.wechat.base.context.WechatContext;
import com.fjx.wechat.base.process.in.executor.InServiceExecutor;
import com.fjx.wechat.base.process.in.executor.InServiceExecutorFactory;
import com.fjx.wechat.base.tools.MessageUtil;
import com.fjx.wechat.base.web.admin.entity.WechatPublicAccountEntity;

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
	 * @param request
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
					&& !requestMap.get("FromUserName").equals(accountEntity.getAccount_id())) {
				logger.warn("fromUserName["+requestMap.get("FromUserName")+"]无效，返回空不做响应");
				return "";
			}
			respMessage = executor.execute();
			//替换参数
			respMessage = MessageUtil.replaceMsgParam(respMessage, WechatContext.getWechatPostMap());
		} catch (Exception e) {
			logger.error("处理微信请求出现异常", e);
			return "";	//返回空字符串，微信将不做处理，且不再消息重发
		}
		return respMessage;
	}
	
	
}