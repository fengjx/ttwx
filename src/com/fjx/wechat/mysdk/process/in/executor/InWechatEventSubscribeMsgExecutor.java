
package com.fjx.wechat.mysdk.process.in.executor;

import com.fjx.wechat.base.admin.entity.WechatUserEntity;
import com.fjx.wechat.base.admin.service.WechatUserService;
import com.fjx.wechat.mysdk.beans.req.ReqEventMessage;
import com.fjx.wechat.mysdk.constants.WechatReqEventConstants;
import com.fjx.wechat.mysdk.constants.WechatReqMsgtypeConstants;
import com.fjx.wechat.mysdk.context.WechatContext;
import com.fjx.wechat.mysdk.tools.NameTool;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 用户关注消息处理器
 * 
 * @author fengjx xd-fjx@qq.com
 * @date 2014年9月11日
 */
public class InWechatEventSubscribeMsgExecutor extends InServiceExecutor {

    @Autowired
    private WechatUserService wechatUserService;

    @Override
    public String execute() throws Exception {
        ReqEventMessage eventMessage = new ReqEventMessage(WechatContext.getWechatPostMap());
        logger.info("进入用户关注消息处理器fromUserName=" + eventMessage.getFromUserName());
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
        return NameTool.buildInServiceName(WechatReqMsgtypeConstants.REQ_MSG_TYPE_EVENT,
                WechatReqEventConstants.EVENT_TYPE_SUBSCRIBE);
    }

}
