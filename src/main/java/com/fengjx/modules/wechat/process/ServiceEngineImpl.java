
package com.fengjx.modules.wechat.process;

import com.fengjx.commons.plugin.db.Record;
import com.fengjx.commons.utils.LogUtil;
import com.fengjx.modules.wechat.constants.WechatConst;
import com.fengjx.modules.wechat.process.bean.WechatContext;
import com.fengjx.modules.wechat.process.executor.ServiceExecutorFactory;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 微信消息核心处理类
 * 
 * @author fengjx
 * @date
 */
public class ServiceEngineImpl implements ServiceEngine {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceEngineImpl.class);

    /**
     * 请求服务工厂
     */
    @Autowired
    private ServiceExecutorFactory executorFactory;

    /**
     * 处理微信发来的请求
     * 
     * @return
     */
    @Override
    public String processRequest() {
        // 微信发送的参数
        ServiceExecutor executor;
        Record accountRecord = WechatContext.getInMessageRecord();
        WxMpXmlMessage inMessage = WechatContext.getInMessage();
        WxMpConfigStorage wxMpConfig = WechatContext.getWxMpConfigStorage();
        try {
            executor = executorFactory.getExecutorByName(inMessage);
            if (null == executor) {
                LogUtil.warn(LOG, "未识别到消息动作分发器，此消息不做处理");
                return "";
            }
            // 已激活状态，同时fromUserName与公众号ID不符，视为无效请求
            if (WechatConst.PublicAccount.VALID_STATE_ACTIVATE
                    .equals(accountRecord.getStr("valid_state"))
                    && !inMessage.getToUserName().equals(accountRecord.getStr("account_id"))) {
                LogUtil.warn(LOG, "ToUserName[" + inMessage.getToUserName() + "]无效，返回空不做响应");
                return "";
            }
            WxMpXmlOutMessage outMessage = executor.execute(inMessage, accountRecord, wxMpConfig,
                    WechatContext.getWxsession());
            // 当道请求上下文里，使用aop记录日志需要用到
            WechatContext.setOutMessage(outMessage);
            return processResult(outMessage, inMessage);
        } catch (Exception e) {
            LogUtil.error(LOG, "处理微信请求出现异常", e);
            return ""; // 返回空字符串，微信将不做处理，且不再消息重发
        }
    }

    private String processResult(WxMpXmlOutMessage outMessage, WxMpXmlMessage inMessage) {
        if (null == outMessage) {
            LogUtil.info(LOG, "outMessage is null");
            return "";
        }
        outMessage.setFromUserName(inMessage.getToUserName());
        outMessage.setToUserName(inMessage.getFromUserName());
        String encryptType = WechatContext.getEncryptType();
        if ("raw".equals(encryptType)) {
            LogUtil.info(LOG, "encryptType is raw");
            return outMessage.toXml();
        } else if ("aes".equals(encryptType)) {
            LogUtil.info(LOG, "encryptType is aes");
            return outMessage.toEncryptedXml(WechatContext.getWxMpConfigStorage());
        }
        LogUtil.info(LOG, "encryptType unknown");
        return "";
    }
}
