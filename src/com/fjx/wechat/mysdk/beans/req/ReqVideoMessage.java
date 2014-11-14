package com.fjx.wechat.mysdk.beans.req;

import java.util.Map;

import com.fjx.common.framework.system.exception.MyRuntimeException;
import com.fjx.wechat.mysdk.constants.WechatReqMsgtypeConstants;


/**
 * 接收视频消息实体
 * @author fengjx
 * @date 2014年9月8日
 */
public class ReqVideoMessage extends ReqBaseMessage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1665589721195299064L;
	
	// 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
	private String mediaId;
	// 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
	private String thumbMediaId;
	
	public ReqVideoMessage(Map<String, String> requestMap){
		this.bind(requestMap);
	}
	
	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	@Override
	public void bind(Map<String, String> requestMap) {
		// 消息类型
		String msgType = requestMap.get("MsgType");
		if(!WechatReqMsgtypeConstants.REQ_MSG_TYPE_VIDEO.equals(msgType)){
			throw new MyRuntimeException("请求消息不是视频类型");
		}
		// 消息id
		String msgId = requestMap.get("MsgId");
		// 发送方帐号（一个OpenID）
		String fromUserName = requestMap.get("FromUserName");
		// 开发者微信号（公众帐号）
		String toUserName = requestMap.get("ToUserName");
		//消息创建时间
		String createTime = requestMap.get("CreateTime");
		
		String mediaId = requestMap.get("MediaId");
		String thumbMediaId = requestMap.get("ThumbMediaId");
		
		setCreateTime(createTime);
		setFromUserName(fromUserName);
		setMsgId(msgId);
		setMsgType(msgType);
		setToUserName(toUserName);		
		
		setMediaId(mediaId);
		setThumbMediaId(thumbMediaId);
	}
	
}
