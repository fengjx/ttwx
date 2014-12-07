package com.fjx.wechat.mysdk.process.in.executor;

import com.fjx.wechat.base.admin.entity.RespMsgActionEntity;
import com.fjx.wechat.config.MsgTemplateConstants;
import com.fjx.wechat.mysdk.constants.WechatReqMsgtypeConstants;
import com.fjx.wechat.mysdk.context.WechatContext;
import com.fjx.wechat.mysdk.process.ext.TextExtService;
import com.fjx.wechat.mysdk.tools.NameTool;
import com.fjx.wechat.mysdk.beans.req.ReqTextMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 文本消息处理器
 * @author fengjx xd-fjx@qq.com
 * @date 2014年9月11日
 */
public class InWechatTextMsgExecutor extends InServiceExecutor {

	@Autowired
	private TextExtService textExtService;
	
	@Override
	public String execute() throws Exception {
		ReqTextMessage textMessage = new ReqTextMessage(WechatContext.getWechatPostMap());
		logger.info("进入文本消息处理器fromUserName="+textMessage.getFromUserName());
		RespMsgActionEntity actionEntity = msgActionService.loadMsgAction(null,WechatReqMsgtypeConstants.REQ_MSG_TYPE_TEXT, null,textMessage.getContent(), WechatContext.getPublicAccount().getSysUser());
		//没有找到匹配规则
		if(null == actionEntity){
			String res = textExtService.execute();
			if(StringUtils.isNotBlank(res)){	//如果有数据则直接返回
				return res;
			}
			//返回默认回复消息
			actionEntity = msgActionService.loadMsgAction(MsgTemplateConstants.WECHAT_DEFAULT_MSG, null, null, null, WechatContext.getPublicAccount().getSysUser());
		}
		return doAction(actionEntity);
	}

	@Override
	public String getExecutorName() {
		return NameTool.buildInServiceName(WechatReqMsgtypeConstants.REQ_MSG_TYPE_TEXT, null);
	}

}
