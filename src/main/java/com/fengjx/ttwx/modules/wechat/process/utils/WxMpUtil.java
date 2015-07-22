
package com.fengjx.ttwx.modules.wechat.process.utils;

import com.fengjx.ttwx.common.plugin.db.Record;
import com.fengjx.ttwx.modules.wechat.process.bean.MyWxMpConfigStorage;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.*;

/**
 * @author fengjx.
 * @date：2015/6/24 0024
 */
public final class WxMpUtil {


    /**
     * 通过数据创建ConfigStorage对象
     *
     * @param accountRecord
     * @return
     */
    public static WxMpConfigStorage buildConfigStorage(Record accountRecord) {
        MyWxMpConfigStorage config = new MyWxMpConfigStorage();
        // 设置微信公众号的appid
        config.setAppId(accountRecord.getStr("app_id"));
        // 设置微信公众号的app corpSecret
        config.setSecret(accountRecord.getStr("app_secret"));
        // 设置微信公众号的token
        config.setToken(accountRecord.getStr("token"));
        // 设置微信公众号的EncodingAESKey
        config.setAesKey(accountRecord.getStr("encodingAESKey"));
        return config;
    }

    /**
     * @param config 公众号配置
     * @return
     */
    public static WxMpService getWxMpServiceByConfig(WxMpConfigStorage config) {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(config);
        return wxMpService;
    }

    /**
     * 根据回复消息内容获得对应实体class
     *
     * @param msgType
     * @return
     */
    public static <T> Class<T> getXmlOutMsgType(String msgType) {
        switch (msgType) {
            case WxConsts.CUSTOM_MSG_TEXT:
                return (Class<T>) WxMpXmlOutTextMessage.class;
            case WxConsts.CUSTOM_MSG_NEWS:
                return (Class<T>) WxMpXmlOutNewsMessage.class;
            case WxConsts.CUSTOM_MSG_IMAGE:
                return (Class<T>) WxMpXmlOutImageMessage.class;
            case WxConsts.CUSTOM_MSG_MUSIC:
                return (Class<T>) WxMpXmlOutMusicMessage.class;
            case WxConsts.CUSTOM_MSG_VIDEO:
                return (Class<T>) WxMpXmlOutVideoMessage.class;
            case WxConsts.CUSTOM_MSG_VOICE:
                return (Class<T>) WxMpXmlOutVoiceMessage.class;
            default:
                throw new RuntimeException("unkonwn msgType");
        }
    }

}
