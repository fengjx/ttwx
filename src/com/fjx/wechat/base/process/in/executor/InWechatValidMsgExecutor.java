package com.fjx.wechat.base.process.in.executor;


import org.springframework.beans.factory.annotation.Autowired;

import com.fjx.wechat.base.constants.MsgTemplateConstants;
import com.fjx.wechat.base.context.WechatContext;
import com.fjx.wechat.base.tools.ServiceTool;
import com.fjx.wechat.base.vo.req.ReqTextMessage;
import com.fjx.wechat.base.web.admin.entity.WechatPublicAccountEntity;
import com.fjx.wechat.base.web.admin.service.WechatPublicAccountService;

/**
 * 验证码消息处理器
 * @author fengjx xd-fjx@qq.com
 * @date 2014年9月11日
 */
public class InWechatValidMsgExecutor extends InServiceExecutor {
	
	public static final String EXECUTOR_NAME = "inWechatValidMsgExecutor";
	
	@Autowired
	private WechatPublicAccountService publicAccountService;
	
	@Override
	public String execute() {
		ReqTextMessage textMessage = new ReqTextMessage(WechatContext.getWechatPostMap());
		logger.info("进入验证消息处理器fromUserName="+textMessage.getFromUserName());
		
		WechatPublicAccountEntity accountEntity = WechatContext.getPublicAccount();
		String valid_code = accountEntity.getValid_code();
		//文字消息与验证码相同
		if(valid_code.equals(textMessage.getContent())){
			//更新账号状态为激活
			accountEntity.setValid_state(WechatPublicAccountEntity.VALID_STATE_ACTIVATE);
			accountEntity.setAccount_id(textMessage.getFromUserName());
			wechatPublicAccountService.update(accountEntity);
			return msgTemplateService.getMsgTemplateByKey(MsgTemplateConstants.API_VALID_SUCCESS).getMsg_content();
		}
		return msgTemplateService.getMsgTemplateByKey(MsgTemplateConstants.API_VALID_FAIL).getMsg_content();
	}

	@Override
	public String getExecutorName() {
		return ServiceTool.buildInServiceName(EXECUTOR_NAME, null);
	}

}
