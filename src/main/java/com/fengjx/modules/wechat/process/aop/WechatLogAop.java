
package com.fengjx.modules.wechat.process.aop;

import com.fengjx.commons.utils.LogUtil;
import com.fengjx.modules.wechat.bean.WechatReqMsgLog;
import com.fengjx.modules.wechat.process.bean.WechatContext;
import com.fengjx.modules.wechat.service.WechatReqMsgLogService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
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
    private WechatReqMsgLogService msgLogService;

    public Object addLog(ProceedingJoinPoint joinpoint) {
        LogUtil.info(LOG, "wechat request log addReqLog begin...");
        try {
            // 微信发送的参数
            WxMpXmlMessage inMessage = WechatContext.getInMessage();
            // 消息类型
            String msgType = inMessage.getMsgType();
            WechatReqMsgLog log = new WechatReqMsgLog();
            Map<String, Object> attrs = new HashMap<>();
            log.setReqType(msgType);
            log.setEventType(inMessage.getEvent());
            log.setToUserName(inMessage.getToUserName());
            log.setFromUserName(inMessage.getFromUserName());
            log.setCreateTime(new Date(inMessage.getCreateTime()));
            log.setMsgId(inMessage.getMsgId());
            log.setInTime(new Date());
            log.setPublicAccountId(WechatContext.getInMessageRecord().getStr("id"));
            Object res = joinpoint.proceed();
            WxMpXmlOutMessage outMessage = WechatContext.getOutMessage();
            log.setRespXml(outMessage == null ? "" : outMessage.toXml());
            log.setRespTime(new Date());
            msgLogService.insert(attrs);
            return res;
        } catch (Throwable throwable) {
            LogUtil.error(LOG, "记录微信请求发送数据日志出现异常", throwable);
            return null;
        }
    }
}
