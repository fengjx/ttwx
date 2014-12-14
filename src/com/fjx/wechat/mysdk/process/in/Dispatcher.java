package com.fjx.wechat.mysdk.process.in;

import java.util.Map;

import com.fjx.wechat.base.admin.entity.WechatPublicAccountEntity;
import com.fjx.wechat.mysdk.constants.WechatReqEventConstants;
import com.fjx.wechat.mysdk.constants.WechatReqMsgtypeConstants;
import com.fjx.wechat.mysdk.context.WechatContext;
import com.fjx.wechat.mysdk.process.in.executor.InWechatValidMsgExecutor;
import com.fjx.wechat.mysdk.tools.NameTool;
import org.apache.log4j.Logger;

/**
 * 业务动作分发器
 * @author fengjx xd-fjx@qq.com
 * @date 2014年9月8日
 */
public class Dispatcher {

	private static final Logger LOGGER = Logger.getLogger(Dispatcher.class);

	/**
	 * 根据业务参数获取业务执行器
	 * @param requestMap
	 * @return
	 */
	public static String getExecutorName(Map<String, String> requestMap){
		// String msgId = requestMap.get("MsgId");
		// // 发送方帐号（一个OpenID）
		// String fromUserName = requestMap.get("FromUserName");
		// // 开发者微信号（公众帐号）
		// String toUserName = requestMap.get("ToUserName");
		// //消息创建时间
		// String createTime = requestMap.get("CreateTime");
		
		// 消息类型
		String msgType = requestMap.get("MsgType");
		WechatPublicAccountEntity accountEntity = WechatContext.getPublicAccount();
		//验证状态
		String valid_state = accountEntity.getValid_state();
		
		if(WechatPublicAccountEntity.VALID_STATE_ACTIVATE.equals(valid_state)){	
			if (WechatReqMsgtypeConstants.REQ_MSG_TYPE_TEXT.equals(msgType)) {// 文本消息
				return NameTool.buildInServiceName(
						WechatReqMsgtypeConstants.REQ_MSG_TYPE_TEXT, null);
			} else if (msgType
					.equals(WechatReqMsgtypeConstants.REQ_MSG_TYPE_LOCATION)) {// 地理位置消息
				return NameTool.buildInServiceName(
						WechatReqMsgtypeConstants.REQ_MSG_TYPE_LOCATION, null);
			} else if (msgType.equals(WechatReqMsgtypeConstants.REQ_MSG_TYPE_IMAGE)) {// 图片消息
				return NameTool.buildInServiceName(
						WechatReqMsgtypeConstants.REQ_MSG_TYPE_IMAGE, null);
			} else if (msgType.equals(WechatReqMsgtypeConstants.REQ_MSG_TYPE_LINK)) {// 链接消息
				return NameTool.buildInServiceName(
						WechatReqMsgtypeConstants.REQ_MSG_TYPE_LINK, null);
			} else if (msgType.equals(WechatReqMsgtypeConstants.REQ_MSG_TYPE_VOICE)) {// 语音消息
				return NameTool.buildInServiceName(
						WechatReqMsgtypeConstants.REQ_MSG_TYPE_VOICE, null);
			} else if (msgType.equals(WechatReqMsgtypeConstants.REQ_MSG_TYPE_VIDEO)) {// 视频消息
				return NameTool.buildInServiceName(
						WechatReqMsgtypeConstants.REQ_MSG_TYPE_VIDEO, null);
			} else if (msgType.equals(WechatReqMsgtypeConstants.REQ_MSG_TYPE_EVENT)) {// 事件消息
				String event = requestMap.get("Event");
				// 自定义菜单事件
				if (null != event
						&& event.equals(WechatReqEventConstants.EVENT_TYPE_CLICK)) {// 菜单点击事件
					return NameTool.buildInServiceName(
							WechatReqMsgtypeConstants.REQ_MSG_TYPE_EVENT,
							WechatReqEventConstants.EVENT_TYPE_CLICK);
				} else if (null != event
						&& event.equals(WechatReqEventConstants.EVENT_TYPE_SUBSCRIBE)) {// 用户订阅事件
					return NameTool.buildInServiceName(
							WechatReqMsgtypeConstants.REQ_MSG_TYPE_EVENT,
							WechatReqEventConstants.EVENT_TYPE_SUBSCRIBE);
				} else if (null != event
						&& event.equals(WechatReqEventConstants.EVENT_TYPE_UNSUBSCRIBE)) {// 用户取消订阅
					return NameTool.buildInServiceName(
							WechatReqMsgtypeConstants.REQ_MSG_TYPE_EVENT,
							WechatReqEventConstants.EVENT_TYPE_UNSUBSCRIBE);
				} else if (null != event
						&& event.equals(WechatReqEventConstants.EVENT_TYPE_SCAN)) {// 二维码扫描事件（已关注用户）处理逻辑
					return NameTool.buildInServiceName(
							WechatReqMsgtypeConstants.REQ_MSG_TYPE_EVENT,
							WechatReqEventConstants.EVENT_TYPE_SCAN);
				}
			}
		}else if(WechatPublicAccountEntity.VALID_STATE_EXCESS.equals(valid_state) && WechatReqMsgtypeConstants.REQ_MSG_TYPE_TEXT.equals(msgType) ){//消息类型是文本，当前账号状态是1，已配置URL到公众平台
			return NameTool.buildInServiceName(InWechatValidMsgExecutor.EXECUTOR_NAME, null);
		}
		LOGGER.warn("接口未配置到公众平台，此消息不做处理");
		return null;
	}
}
