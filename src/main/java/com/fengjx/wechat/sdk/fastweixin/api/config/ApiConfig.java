
package com.fengjx.wechat.sdk.fastweixin.api.config;

import com.fengjx.wechat.sdk.fastweixin.api.response.GetTokenResponse;
import com.fengjx.wechat.sdk.fastweixin.util.HttpUtil;
import com.fengjx.wechat.sdk.fastweixin.util.JsonUtil;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * API配置类，项目中请保证其为单例
 * 
 * @author peiyu
 * @since 1.2
 */
public final class ApiConfig {

    private static final Logger LOG = LoggerFactory.getLogger(ApiConfig.class);

    private final String appid;

    private final String secret;

    private String accessToken;

    public static final AtomicBoolean refreshing = new AtomicBoolean(false);

    /**
     * 唯一构造方法，实现同时获取access_token
     * 
     * @param appid 公众号appid
     * @param secret 公众号secret
     */
    public ApiConfig(String appid, String secret) {
        this.appid = appid;
        this.secret = secret;
        init();
    }

    public String getAppid() {
        return appid;
    }

    public String getSecret() {
        return secret;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * 初始化微信配置，即第一次获取access_token
     */
    public void init() {
        LOG.debug("开始第一次初始化access_token........");
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                + this.appid + "&secret=" + this.secret;
        final String appid = this.appid;
        HttpUtil.get(url, null, new HttpUtil.ResponseCallback() {
            @Override
            public void onResponse(int resultCode, String resultJson) {
                if (HttpStatus.SC_OK == resultCode) {
                    GetTokenResponse response = JsonUtil.toBean(resultJson, GetTokenResponse.class);
                    LOG.debug(appid + "获取access_token:{}", response.getAccessToken());
                    ApiConfig.this.accessToken = response.getAccessToken();
                }
            }
        });
    }
}
