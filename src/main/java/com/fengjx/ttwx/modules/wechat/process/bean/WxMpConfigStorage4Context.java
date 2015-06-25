
package com.fengjx.ttwx.modules.wechat.process.bean;

import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;

/**
 * 微信公众平台配置信息，需要在接口请求上下文里调用
 * 
 * @author fengjx.
 * @date：2015/6/21 0021
 */
public class WxMpConfigStorage4Context implements WxMpConfigStorage {

    private static final WxMpConfigStorage4Context INSTANCE = new WxMpConfigStorage4Context();

    private WxMpConfigStorage4Context() {
    }

    public static WxMpConfigStorage4Context getInstance() {
        return INSTANCE;
    }

    @Override
    public String getAccessToken() {
        return WechatContext.getWxMpConfigStorage().getAccessToken();
    }

    @Override
    public boolean isAccessTokenExpired() {
        return WechatContext.getWxMpConfigStorage().isAccessTokenExpired();
    }

    @Override
    public void expireAccessToken() {
        WechatContext.getWxMpConfigStorage().expireAccessToken();
    }

    @Override
    public void updateAccessToken(WxAccessToken wxAccessToken) {
        WechatContext.getWxMpConfigStorage().updateAccessToken(wxAccessToken);
    }

    @Override
    public void updateAccessToken(String s, int i) {
        WechatContext.getWxMpConfigStorage().updateAccessToken(s, i);
    }

    @Override
    public String getJsapiTicket() {
        return WechatContext.getWxMpConfigStorage().getJsapiTicket();
    }

    @Override
    public boolean isJsapiTicketExpired() {
        return WechatContext.getWxMpConfigStorage().isJsapiTicketExpired();
    }

    @Override
    public void expireJsapiTicket() {
        WechatContext.getWxMpConfigStorage().expireJsapiTicket();
    }

    @Override
    public void updateJsapiTicket(String s, int i) {
        WechatContext.getWxMpConfigStorage().updateAccessToken(s, i);
    }

    @Override
    public String getAppId() {
        return WechatContext.getWxMpConfigStorage().getAppId();
    }

    @Override
    public String getSecret() {
        return WechatContext.getWxMpConfigStorage().getSecret();
    }

    @Override
    public String getPartnerId() {
        return WechatContext.getWxMpConfigStorage().getPartnerId();
    }

    @Override
    public String getPartnerKey() {
        return WechatContext.getWxMpConfigStorage().getPartnerKey();
    }

    @Override
    public String getToken() {
        return WechatContext.getWxMpConfigStorage().getToken();
    }

    @Override
    public String getAesKey() {
        return WechatContext.getWxMpConfigStorage().getAesKey();
    }

    @Override
    public long getExpiresTime() {
        return WechatContext.getWxMpConfigStorage().getExpiresTime();
    }

    @Override
    public String getOauth2redirectUri() {
        return WechatContext.getWxMpConfigStorage().getOauth2redirectUri();
    }

    @Override
    public String getHttp_proxy_host() {
        return WechatContext.getWxMpConfigStorage().getHttp_proxy_host();
    }

    @Override
    public int getHttp_proxy_port() {
        return WechatContext.getWxMpConfigStorage().getHttp_proxy_port();
    }

    @Override
    public String getHttp_proxy_username() {
        return WechatContext.getWxMpConfigStorage().getHttp_proxy_username();
    }

    @Override
    public String getHttp_proxy_password() {
        return WechatContext.getWxMpConfigStorage().getHttp_proxy_password();
    }
}
