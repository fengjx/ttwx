package com.fjx.wechat.mysdk.api;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fjx.wechat.mysdk.tools.HttpUtil;
import com.fjx.wechat.mysdk.tools.ParaMap;

import java.io.IOException;
import java.util.Map;

/**
 * Created by fengjx on 2014/11/15 0015.
 */
public class AccessTokenClient extends AbstractClient {


    private String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";


    public AccessTokenClient(ApiConfig apiConfig) {
        init(apiConfig);
    }

    public AccessToken getAccessToken() {
        final String appId = apiConfig.getAppId();
        final String appSecret = apiConfig.getAppSecret();
        Map<String, String> queryParas = ParaMap.create("appid", appId).put("secret", appSecret).getData();
        String json = HttpUtil.get(url, queryParas);
        return new AccessToken(json);
    }

}
