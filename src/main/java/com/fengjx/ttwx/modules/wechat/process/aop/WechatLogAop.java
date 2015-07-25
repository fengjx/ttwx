
package com.fengjx.ttwx.modules.wechat.process.aop;

import com.fengjx.ttwx.common.utils.LogUtil;
import com.fengjx.ttwx.modules.wechat.model.ReqMsgLog;
import com.fengjx.ttwx.modules.wechat.process.bean.WechatContext;

import me.chanjar.weixin.mp.bean.WxMpXmlMessage;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 日志记录切面逻辑 统计微信发送过来的请求
 * 
 * @author fengjx
 * @date 2015-6-24
 */
public class WechatLogAop {

    private static final Logger LOG = LoggerFactory.getLogger(WechatLogAop.class);

    @Autowired
    private ReqMsgLog msgLog;

    public Object addLog(ProceedingJoinPoint joinpoint) {
        LogUtil.info(LOG, "wechat request log addReqLog begin...");
        try {
            // 微信发送的参数
            WxMpXmlMessage inMessage = WechatContext.getInMessage();
            // 消息类型
            String msgType = inMessage.getMsgType();
            Map<String, Object> attrs = new HashMap();
            attrs.put("req_type", msgType);
            attrs.put("event_type", inMessage.getEvent());
            attrs.put("to_user_name", inMessage.getToUserName());
            attrs.put("from_user_name", inMessage.getFromUserName());
            attrs.put("create_time", new Date(inMessage.getCreateTime()));
            attrs.put("msg_id", inMessage.getMsgId());
            attrs.put("in_time", new Date());
            attrs.put("public_account_id", WechatContext.getInMessageRecord().getStr("id"));
            Object res = joinpoint.proceed();
            attrs.put("resp_xml", WechatContext.getOutMessage().toXml());
            attrs.put("resp_time", new Date());
            msgLog.insert(attrs);
            return res;
        } catch (Throwable throwable) {
            LogUtil.error(LOG, "记录微信请求发送数据日志出现异常", throwable);
            return null;
        }
    }
}
