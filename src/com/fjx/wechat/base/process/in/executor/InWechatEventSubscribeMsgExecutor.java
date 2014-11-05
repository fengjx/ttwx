package com.fjx.wechat.base.process.in.executor;

import com.fjx.wechat.base.constants.WechatReqEventConstants;
import com.fjx.wechat.base.constants.WechatReqMsgtypeConstants;
import com.fjx.wechat.base.context.WechatContext;
import com.fjx.wechat.base.tools.ServiceTool;
import com.fjx.wechat.base.vo.req.ReqEventMessage;


/**
 * 事件消息处理器
 * @author fengjx xd-fjx@qq.com
 * @date 2014年9月11日
 */
public class InWechatEventSubscribeMsgExecutor extends InServiceExecutor {

	@Override
	public String execute() {
		
		ReqEventMessage eventMessage = new ReqEventMessage(WechatContext.getWechatPostMap());
		String event = eventMessage.getEvent();
		
		if(WechatReqEventConstants.EVENT_TYPE_CLICK.equals(event)){
			
		}else if(WechatReqEventConstants.EVENT_TYPE_SUBSCRIBE.equals(event)){
			
		}
		
		return null;
	}

	@Override
	public String getExecutorName() {
		return ServiceTool.buildInServiceName(WechatReqMsgtypeConstants.REQ_MSG_TYPE_EVENT,
				WechatReqEventConstants.EVENT_TYPE_CLICK);
	}

}
