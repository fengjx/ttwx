
package com.fengjx.ttwx.modules.wechat.process.bean;

import com.fengjx.ttwx.common.plugin.db.Record;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;

/**
 * 微信请求上下文
 *
 * @author fengjx.
 * @date：2015/6/21 0021
 */
public class WechatContext {

    private static ThreadLocal<WxMpConfigStorage> wxMpConfigStorage = new InheritableThreadLocal();
    private static ThreadLocal<Record> inMessageRecord = new InheritableThreadLocal();
    private static ThreadLocal<String> encryptType = new InheritableThreadLocal();
    private static ThreadLocal<WxMpXmlMessage> inMessage = new InheritableThreadLocal();
    private static ThreadLocal<WxMpXmlOutMessage> outMessage = new InheritableThreadLocal();

    public static WxMpConfigStorage getWxMpConfigStorage() {
        return wxMpConfigStorage.get();
    }

    public static void setWxMpConfigStorage(WxMpConfigStorage wxMpConfigStorage) {
        WechatContext.wxMpConfigStorage.set(wxMpConfigStorage);
    }

    public static WxMpXmlOutMessage getOutMessage() {
        return outMessage.get();
    }

    public static void setOutMessage(WxMpXmlOutMessage outMessage) {
        WechatContext.outMessage.set(outMessage);
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

    public static String getEncryptType() {
        return encryptType.get();
    }

    public static void setEncryptType(String encryptType) {
        WechatContext.encryptType.set(encryptType);
    }

    public static void removeAll() {
        WechatContext.inMessage.remove();
        WechatContext.wxMpConfigStorage.remove();
        WechatContext.outMessage.remove();
        WechatContext.inMessageRecord.remove();
        WechatContext.encryptType.remove();
    }

}
