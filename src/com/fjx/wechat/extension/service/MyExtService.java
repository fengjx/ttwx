package com.fjx.wechat.extension.service;

import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fjx.wechat.base.constants.WechatReqMsgtypeConstants;
import com.fjx.wechat.base.constants.WechatRespMsgtypeConstants;
import com.fjx.wechat.base.context.WechatContext;
import com.fjx.wechat.base.tools.MessageUtil;
import com.fjx.wechat.base.vo.resp.RespTextMessage;
import com.fjx.wechat.extension.api.restful.WeatherServiceApi;

/**
 * 
 * @author fengjx xd-fjx@qq.com
 * @date 2014年11月8日
 */
public class MyExtService {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	
	/**
	 * 天气查询
	 * @return
	 */
	public String weatherInfo(){
		String respMessage = null;
		try {
			Map<String, String> requestMap = WechatContext.getWechatPostMap();

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			RespTextMessage textMessage = new RespTextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(WechatRespMsgtypeConstants.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);
			
			logger.info("消息类型："+msgType);
			//接收地理位置请求
			if(WechatReqMsgtypeConstants.REQ_MSG_TYPE_LOCATION.equals(msgType)){
				// 接收用户发送的文本消息内容
				String Location_X = requestMap.get("Location_X");
				String Location_Y = requestMap.get("Location_Y");
				String label = requestMap.get("Label");
				
				String keyWord = Location_Y+","+Location_X;
                logger.info("天气查询："+keyWord+", label="+label);
                if ("".equals(keyWord)) {
                	//返回使用指南
                    textMessage.setContent(WeatherServiceApi.getWeatherUsage());  
                } else {  
                    textMessage.setContent(WeatherServiceApi.queryhWeather(keyWord));  
                }
                respMessage = MessageUtil.textMessageToXml(textMessage);
			}
		} catch (Exception e) {
			logger.error("执行自定义文本消息扩展处理发生异常",e);
		}
		return respMessage;
	}
	
	
}
