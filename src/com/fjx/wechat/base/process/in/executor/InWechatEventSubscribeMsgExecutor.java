package com.fjx.wechat.base.process.in.executor;

import com.fjx.wechat.base.constants.WechatReqEventConstants;
import com.fjx.wechat.base.constants.WechatReqMsgtypeConstants;
import com.fjx.wechat.base.context.WechatContext;
import com.fjx.wechat.base.tools.ServiceTool;
import com.fjx.wechat.base.vo.req.ReqEventMessage;


/**
 * 用户关注消息处理器
 * @author fengjx xd-fjx@qq.com
 * @date 2014年9月11日
 */
public class InWechatEventSubscribeMsgExecutor extends InServiceExecutor {

	@Override
	public String execute() throws Exception {
		ReqEventMessage eventMessage = new ReqEventMessage(WechatContext.getWechatPostMap());
		logger.info("进入用户关注消息处理器fromUserName="+eventMessage.getFromUserName());
		String event = eventMessage.getEvent();
		return doAction(null, WechatReqMsgtypeConstants.REQ_MSG_TYPE_EVENT, event, null);
	}

	@Override
	public String getExecutorName() {
		return ServiceTool.buildInServiceName(WechatReqMsgtypeConstants.REQ_MSG_TYPE_EVENT,
				WechatReqEventConstants.EVENT_TYPE_SUBSCRIBE);
	}

}
