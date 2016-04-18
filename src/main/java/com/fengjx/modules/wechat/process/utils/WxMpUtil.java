
package com.fengjx.modules.wechat.process.utils;

import com.fengjx.commons.plugin.db.Record;
import com.fengjx.modules.wechat.bean.WechatPublicAccount;
import com.fengjx.modules.wechat.process.bean.MyWxMpConfigStorage;
import com.fengjx.modules.wechat.process.sdk.api.WxMpServiceExt;
import com.fengjx.modules.wechat.process.sdk.api.WxMpServiceImplExt;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.*;

/**
 * @author fengjx. @date：2015/6/24 0024
 */
public final class WxMpUtil {

    /**
     * 通过数据创建ConfigStorage对象
     *
     * @param account
     * @return
     */
    public static WxMpConfigStorage buildConfigStorage(WechatPublicAccount account) {
        if (null == account || account.isEmpty()) {
            return null;
        }
        MyWxMpConfigStorage config = new MyWxMpConfigStorage();
        // 设置微信公众号的appid
        config.setAppId(account.getAppId());
        // 设置微信公众号的app corpSecret
        config.setSecret(account.getAppSecret());
        // 设置微信公众号的token
        config.setToken(account.getToken());
        // 设置微信公众号的EncodingAESKey
        config.setAesKey(account.getEncodingAESKey());
        // 设置微信支付商户ID
        config.setPartnerId(account.getMerchantId());
        // 设置微信支付api秘钥
        config.setPartnerKey(account.getPayKey());
        return config;
    }

    /**
     * 通过数据创建ConfigStorage对象
     *
     * @param accountRecord
     * @return
     */
    public static WxMpConfigStorage buildConfigStorage(Record accountRecord) {
        if (null == accountRecord || accountRecord.isEmpty()) {
            return null;
        }
        MyWxMpConfigStorage config = new MyWxMpConfigStorage();
        // 设置微信公众号的appid
        config.setAppId(accountRecord.getStr("app_id"));
        // 设置微信公众号的app corpSecret
        config.setSecret(accountRecord.getStr("app_secret"));
        // 设置微信公众号的token
        config.setToken(accountRecord.getStr("token"));
        // 设置微信公众号的EncodingAESKey
        config.setAesKey(accountRecord.getStr("encodingAESKey"));
        // 设置微信支付商户ID
        config.setPartnerId(accountRecord.getStr("merchant_id"));
        // 设置微信支付api秘钥
        config.setPartnerKey(accountRecord.getStr("pay_key"));
        return config;
    }

    /**
     * @param config 公众号配置
     * @return
     */
    public static WxMpService getWxMpServiceByConfig(WxMpConfigStorage config) {
        WxMpServiceExt wxMpService = new WxMpServiceImplExt();
        wxMpService.setWxMpConfigStorage(config);
        return wxMpService;
    }

    /**
     * @param account 公众号配置
     * @return
     */
    public static WxMpService getWxMpService(WechatPublicAccount account) {
        WxMpServiceExt wxMpService = new WxMpServiceImplExt();
        wxMpService.setWxMpConfigStorage(buildConfigStorage(account));
        return wxMpService;
    }

    /**
     * 根据回复消息内容获得对应实体class
     *
     * @param msgType
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Class<?> getXmlOutMsgType(String msgType) {
        switch (msgType) {
            case WxConsts.CUSTOM_MSG_TEXT:
                return WxMpXmlOutTextMessage.class;
            case WxConsts.CUSTOM_MSG_NEWS:
                return WxMpXmlOutNewsMessage.class;
            case WxConsts.CUSTOM_MSG_IMAGE:
                return WxMpXmlOutImageMessage.class;
            case WxConsts.CUSTOM_MSG_MUSIC:
                return WxMpXmlOutMusicMessage.class;
            case WxConsts.CUSTOM_MSG_VIDEO:
                return WxMpXmlOutVideoMessage.class;
            case WxConsts.CUSTOM_MSG_VOICE:
                return WxMpXmlOutVoiceMessage.class;
            default:
                throw new RuntimeException("unkonwn msgType");
        }
    }

}
