package com.fjx.wechat.mysdk.process.in.executor;

import com.fjx.wechat.mysdk.constants.WechatReqMsgtypeConstants;
import com.fjx.wechat.mysdk.context.WechatContext;
import com.fjx.wechat.mysdk.tools.ServiceTool;
import com.fjx.wechat.mysdk.beans.req.ReqTextMessage;

/**
 * 文本消息处理器
 * @author fengjx xd-fjx@qq.com
 * @date 2014年9月11日
 */
public class InWechatTextMsgExecutor extends InServiceExecutor {
	
	
	@Override
	public String execute() throws Exception {
		ReqTextMessage textMessage = new ReqTextMessage(WechatContext.getWechatPostMap());
		logger.info("进入文本消息处理器fromUserName="+textMessage.getFromUserName());
		return doAction(null, WechatReqMsgtypeConstants.REQ_MSG_TYPE_TEXT, null, textMessage.getContent());
	}

	@Override
	public String getExecutorName() {
		return ServiceTool.buildInServiceName(WechatReqMsgtypeConstants.REQ_MSG_TYPE_TEXT, null);
	}

}
