package com.fjx.wechat.mysdk.vo.resp;

import com.fjx.common.bean.ToStringBase;

/**
 * 消息基类（公众帐号 -> 普通用户）
 * @author fengjx
 * @date 2014年1月19日
 */
public abstract class RespBaseMessage extends ToStringBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5186465349792114253L;
	// 接收方帐号（收到的OpenID）
	private String ToUserName;
	// 开发者微信号
	private String FromUserName;
	// 消息创建时间 （整型）
	private long CreateTime;
	// 消息类型（text/music/news）
	private String MsgType;
	// 位0x0001被标志时，星标刚收到的消息
	private int FuncFlag;
	
	public RespBaseMessage(){
		this.setCreateTime(new Long(0));
		this.setFromUserName("");
		this.setFuncFlag(0);
		this.setMsgType("");
		this.setToUserName("");
	}
	
	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public int getFuncFlag() {
		return FuncFlag;
	}

	public void setFuncFlag(int funcFlag) {
		FuncFlag = funcFlag;
	}
}