package com.fjx.wechat.mysdk.beans.req;

import java.util.Map;

import com.fjx.common.framework.system.exception.MyRuntimeException;
import com.fjx.wechat.mysdk.constants.WechatReqMsgtypeConstants;


/**
 * 接收文本消息实体
 * @author fengjx
 * @date 2014年9月8日
 */
public class ReqTextMessage extends ReqBaseMessage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1625181297174267569L;
	
	
	// 文本消息内容
	private String content;
	
	
	public ReqTextMessage(Map<String, String> requestMap){
		this.bind(requestMap);
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public void bind(Map<String, String> requestMap) {
		// 消息类型
		String msgType = requestMap.get("MsgType");
		if(!WechatReqMsgtypeConstants.REQ_MSG_TYPE_TEXT.equals(msgType)){
			throw new MyRuntimeException("请求消息不是文本类型");
		}
		// 消息id
		String msgId = requestMap.get("MsgId");
		// 发送方帐号（一个OpenID）
		String fromUserName = requestMap.get("FromUserName");
		// 开发者微信号（公众帐号）
		String toUserName = requestMap.get("ToUserName");
		//消息创建时间
		String createTime = requestMap.get("CreateTime");
		// 文本内容
		String content = requestMap.get("Content");
		
		setContent(content);
		setCreateTime(createTime);
		setFromUserName(fromUserName);
		setMsgId(msgId);
		setMsgType(msgType);
		setToUserName(toUserName);
	}
	
}
