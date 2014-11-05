package com.fjx.wechat.base.vo.req;

import java.util.Map;

import com.fjx.common.framework.system.exception.MyRuntimeException;
import com.fjx.wechat.base.constants.WechatReqMsgtypeConstants;


/**
 * 接收地理位置消息实体
 * @author fengjx
 * @date 2014年9月8日
 */
public class ReqLocationMessage extends ReqBaseMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2945742328768022196L;
	
	// 地理位置维度
	private String location_X;
	// 地理位置经度
	private String location_Y;
	// 地图缩放大小
	private String scale;
	// 地理位置信息
	private String label;
	
	
	public ReqLocationMessage(Map<String, String> requestMap){
		this.bind(requestMap);
	}
	
	public String getLocation_X() {
		return location_X;
	}

	public void setLocation_X(String location_X) {
		this.location_X = location_X;
	}

	public String getLocation_Y() {
		return location_Y;
	}

	public void setLocation_Y(String location_Y) {
		this.location_Y = location_Y;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public void bind(Map<String, String> requestMap) {
		// 消息类型
		String msgType = requestMap.get("MsgType");
		if(!WechatReqMsgtypeConstants.REQ_MSG_TYPE_LOCATION.equals(msgType)){
			throw new MyRuntimeException("请求消息不是地理位置类型");
		}
		// 消息id
		String msgId = requestMap.get("MsgId");
		// 发送方帐号（一个OpenID）
		String fromUserName = requestMap.get("FromUserName");
		// 开发者微信号（公众帐号）
		String toUserName = requestMap.get("ToUserName");
		//消息创建时间
		String createTime = requestMap.get("CreateTime");
		
		String location_X = requestMap.get("Location_X");
		String location_Y = requestMap.get("Location_Y");
		String scale = requestMap.get("Scale");
		String label = requestMap.get("Label");
		
		setCreateTime(createTime);
		setFromUserName(fromUserName);
		setMsgId(msgId);
		setMsgType(msgType);
		setToUserName(toUserName);		
		
		setLocation_X(location_X);
		setLocation_Y(location_Y);
		setScale(scale);
		setLabel(label);
	}
	
}
