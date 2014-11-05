package com.fjx.wechat.base.vo.req;

import java.util.Map;

import com.fjx.common.bean.ToStringBase;


/**
 * 请求消息基础类
 * @author fengjx
 * @date 2014年9月8日
 */
public abstract class ReqBaseMessage extends ToStringBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4689445658162308164L;

	// 开发者微信号
	private String toUserName;
	// 发送方帐号（一个OpenID）
	private String fromUserName;
	// 消息创建时间 （整型）
	private String createTime;
	// 消息类型（text/music/news）
	private String msgType;
	// 消息id，64位整型 微信服务器在五秒内收不到响应会断掉连接，并且重新发起请求，总共重试三次，关于重试的消息排重，推荐使用msgid排重。
	private String msgId;
	
	//绑定实体参数
	public abstract void bind(Map<String, String> requestMap);
	
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

}
