package com.fjx.wechat.mysdk.api;

import com.fjx.wechat.mysdk.tools.HttpUtil;
import com.fjx.wechat.mysdk.tools.ParaMap;

/**
 * 微信用户相关请求客户端
 * Created by fengjx on 2014/11/15 0015.
 */
public class UserClient extends AbstractClient {

    private String getUserInfo = "https://api.weixin.qq.com/cgi-bin/user/info";
    private String getFollowers = "https://api.weixin.qq.com/cgi-bin/user/get";

    public ApiResult getUserInfo(String openId) {
        AccessToken accessToken = getAccessToken();
        ParaMap pm = ParaMap.create("access_token", accessToken.getAccessToken()).put("openid", openId).put("lang", "zh_CN");
        return proceResult(HttpUtil.get(getUserInfo, pm.getData()));
    }

    public  ApiResult getFollowers(String nextOpenid) {
        AccessToken accessToken = getAccessToken();
        ParaMap pm = ParaMap.create("access_token", accessToken.getAccessToken());
        if (nextOpenid != null){
            pm.put("next_openid", nextOpenid);
        }
        return proceResult(HttpUtil.get(getFollowers, pm.getData()));
    }

    public  ApiResult getFollows() {
        return getFollowers(null);
    }



}
