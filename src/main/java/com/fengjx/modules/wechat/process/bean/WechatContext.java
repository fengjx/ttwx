
package com.fengjx.modules.wechat.process.bean;

import com.fengjx.commons.plugin.db.Record;

import org.apache.commons.lang3.StringUtils;

import me.chanjar.weixin.common.session.StandardSessionManager;
import me.chanjar.weixin.common.session.WxSession;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;

/**
 * 微信请求上下文
 *
 * @author fengjx. @date：2015/6/21 0021
 */
public class WechatContext {

    /**
     * 微信session管理器
     */
    private static final WxSessionManager sessionManager = new StandardSessionManager();

    private static ThreadLocal<WxMpConfigStorage> wxMpConfigStorage = new InheritableThreadLocal<>();

    private static ThreadLocal<Record> inMessageRecord = new InheritableThreadLocal<>();

    private static ThreadLocal<String> encryptType = new InheritableThreadLocal<>();

    private static ThreadLocal<WxMpXmlMessage> inMessage = new InheritableThreadLocal<>();

    private static ThreadLocal<WxMpXmlOutMessage> outMessage = new InheritableThreadLocal<>();

    private static ThreadLocal<Long> requestTime = new InheritableThreadLocal<>();

    public static WxMpConfigStorage getWxMpConfigStorage() {
        return wxMpConfigStorage.get();
    }

    public static void setWxMpConfigStorage(WxMpConfigStorage wxMpConfigStorage) {
        WechatContext.wxMpConfigStorage.set(wxMpConfigStorage);
    }

    public static WxMpXmlMessage getInMessage() {
        return inMessage.get();
    }

    public static void setInMessage(WxMpXmlMessage inMessage) {
        WechatContext.inMessage.set(inMessage);
    }

    public static Record getInMessageRecord() {
        return inMessageRecord.get();
    }

    public static void setInMessageRecord(Record inMessageRecord) {
        WechatContext.inMessageRecord.set(inMessageRecord);
    }

    public static WxMpXmlOutMessage getOutMessage() {
        return outMessage.get();
    }

    public static void setOutMessage(WxMpXmlOutMessage outMessage) {
        WechatContext.outMessage.set(outMessage);
    }

    public static String getEncryptType() {
        return encryptType.get();
    }

    public static void setEncryptType(String encryptType) {
        WechatContext.encryptType.set(encryptType);
    }

    public static WxSession getWxsession() {
        if (null == getInMessage() || StringUtils.isBlank(getInMessage().getFromUserName())) {
            throw new RuntimeException("getWxsession error...");
        }
        return sessionManager.getSession(getInMessage().getFromUserName());
    }

    public static Long getRequestTime() {
        Long time = requestTime.get();
        if (null == time) {
            return 0L;
        }
        return time;
    }

    public static void setRequestTime(Long requestTime) {
        WechatContext.requestTime.set(requestTime);
    }

    public static void removeAll() {
        WechatContext.inMessage.remove();
        WechatContext.wxMpConfigStorage.remove();
        WechatContext.inMessageRecord.remove();
        WechatContext.encryptType.remove();
        WechatContext.requestTime.remove();
    }

}
