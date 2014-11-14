package com.fjx.wechat.mysdk.api;

/**
 * 微信客户端接口
 * Created by fengjx on 2014/11/15 0015.
 */
public abstract class AbstractClient {

    protected ApiConfig apiConfig;

    void init(ApiConfig apiConfig) {
        this.apiConfig = apiConfig;
    }


}
