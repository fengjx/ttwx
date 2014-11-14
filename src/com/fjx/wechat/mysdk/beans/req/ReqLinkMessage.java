package com.fjx.wechat.mysdk.beans.req;

import java.util.Map;

import com.fjx.common.framework.system.exception.MyRuntimeException;
import com.fjx.wechat.mysdk.constants.WechatReqMsgtypeConstants;

/**
 * 接收链接消息实体
 * @author fengjx
 * @date 2014年9月8日
 */
public class ReqLinkMessage extends ReqBaseMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8951544400452605355L;

	// 消息标题
	private String title;
	// 消息描述
	private String description;
	// 消息链接
	private String url;
	
	public ReqLinkMessage(Map<String, String> requestMap){
		this.bind(requestMap);
	}
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public void bind(Map<String, String> requestMap) {
		// 消息类型
		String msgType = requestMap.get("MsgType");
		if(!WechatReqMsgtypeConstants.REQ_MSG_TYPE_LINK.equals(msgType)){
			throw new MyRuntimeException("请求消息不是链接类型");
		}
		// 消息id
		String msgId = requestMap.get("MsgId");
		// 发送方帐号（一个OpenID）
		String fromUserName = requestMap.get("FromUserName");
		// 开发者微信号（公众帐号）
		String toUserName = requestMap.get("ToUserName");
		//消息创建时间
		String createTime = requestMap.get("CreateTime");
		
		String title = requestMap.get("Title");
		String description = requestMap.get("Description");
		String url = requestMap.get("Url");
		
		setCreateTime(createTime);
		setFromUserName(fromUserName);
		setMsgId(msgId);
		setMsgType(msgType);
		setToUserName(toUserName);
		
		setTitle(title);
		setDescription(description);
		setUrl(url);
	}

}
