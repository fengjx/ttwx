package com.fjx.wechat.mysdk.process.in.executor;

import java.util.Date;

import com.fjx.wechat.base.process.in.executor.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.fjx.wechat.base.constants.WechatReqEventConstants;
import com.fjx.wechat.base.constants.WechatReqMsgtypeConstants;
import com.fjx.wechat.base.context.WechatContext;
import com.fjx.wechat.base.tools.ServiceTool;
import com.fjx.wechat.base.vo.req.ReqEventMessage;
import com.fjx.wechat.base.admin.entity.WechatUserEntity;
import com.fjx.wechat.base.admin.service.WechatUserService;


/**
 * 用户关注消息处理器
 * @author fengjx xd-fjx@qq.com
 * @date 2014年9月11日
 */
public class InWechatEventSubscribeMsgExecutor extends com.fjx.wechat.base.process.in.executor.InServiceExecutor {
	
	@Autowired
	private WechatUserService wechatUserService;
	
	@Override
	public String execute() throws Exception {
		ReqEventMessage eventMessage = new ReqEventMessage(WechatContext.getWechatPostMap());
		logger.info("进入用户关注消息处理器fromUserName="+eventMessage.getFromUserName());
		String event = eventMessage.getEvent();
		
		WechatUserEntity user = new WechatUserEntity();
		user.setOpenid(eventMessage.getFromUserName());
		user.setSubscribe_time(new Date());
		user.setPublicAccountEntity(WechatContext.getPublicAccount());
		wechatUserService.save(user);
		
		return doAction(null, WechatReqMsgtypeConstants.REQ_MSG_TYPE_EVENT, event, null);
	}

	@Override
	public String getExecutorName() {
		return ServiceTool.buildInServiceName(WechatReqMsgtypeConstants.REQ_MSG_TYPE_EVENT,
				WechatReqEventConstants.EVENT_TYPE_SUBSCRIBE);
	}

}
