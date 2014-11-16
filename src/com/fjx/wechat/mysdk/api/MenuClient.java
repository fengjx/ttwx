package com.fjx.wechat.mysdk.api;

import com.fjx.common.utils.JsonUtil;
import com.fjx.wechat.mysdk.beans.menu.Menu;
import com.fjx.wechat.mysdk.tools.HttpUtil;

/**
 * 菜单相关请求客户端
 * Created by fengjx on 2014/11/15 0015.
 */
public class MenuClient extends AbstractClient {


    private static String getMenu = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=";
    private static String createMenu = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";


    /**
     * 查询菜单
     */
    public ApiResult getMenu() {
        AccessToken accessToken = apiConfig.getAccessToken();
        if(null == accessToken || !accessToken.isAvailable()){
            AccessTokenClient accessTokenClient = ClientFactory.createAccessTokenClient(apiConfig);
            accessToken = accessTokenClient.getAccessToken();
        }
        String jsonResult = HttpUtil.get(getMenu + accessToken.getAccessToken());
        return new ApiResult(jsonResult);
    }

    /**
     * 创建菜单
     */
    public ApiResult createMenu(String jsonStr) {
        AccessToken accessToken = apiConfig.getAccessToken();
        if(null == accessToken || !accessToken.isAvailable()){
            AccessTokenClient accessTokenClient = ClientFactory.createAccessTokenClient(apiConfig);
            accessToken = accessTokenClient.getAccessToken();
        }
        String jsonResult = HttpUtil.post(createMenu + accessToken.getAccessToken(), jsonStr);
        return new ApiResult(jsonResult);
    }

    /**
     * 创建菜单
     */
    public ApiResult createMenu(Menu menu) {
        AccessToken accessToken = apiConfig.getAccessToken();
        if(null == accessToken || !accessToken.isAvailable()){
            AccessTokenClient accessTokenClient = ClientFactory.createAccessTokenClient(apiConfig);
            accessToken = accessTokenClient.getAccessToken();
        }
        String jsonStr = JsonUtil.getJson(menu);
        String jsonResult = HttpUtil.post(createMenu + accessToken.getAccessToken(), jsonStr);
        return new ApiResult(jsonResult);
    }

}
