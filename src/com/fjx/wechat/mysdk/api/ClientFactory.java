package com.fjx.wechat.mysdk.api;


/**
 * 微信客户端工厂
 * Created by fengjx on 2014/11/15 0015.
 */
public class ClientFactory {

    /**
     * 构建获取全局access_token客户端
     * @param appId
     * @param appSecret
     * @return
     */
    public static AccessTokenClient createAccessTokenClient(String appId, String appSecret,String token ){
        ApiConfig apiConfig = new ApiConfig();
        apiConfig.init(appId, appSecret,token);
        return createAccessTokenClient(apiConfig);
    }

    /**
     * 构建获取全局access_token客户端
     * @param apiConfig
     * @return
     */
    public static AccessTokenClient createAccessTokenClient(ApiConfig apiConfig){
        AccessTokenClient accessTokenClient = new AccessTokenClient();
        return accessTokenClient.init(apiConfig);
    }


    /**
     * 构建微信菜单客户端
     * @param appId
     * @param appSecret
     * @return
     */
    public static MenuClient createMenuClient(String appId, String appSecret, String token ){
        ApiConfig apiConfig = new ApiConfig();
        apiConfig.init(appId, appSecret,token);
        return createMenuClient(apiConfig);
    }

    /**
     * 构建微信菜单客户端
     * @param apiConfig
     * @return
     */
    public static MenuClient createMenuClient(ApiConfig apiConfig){
        MenuClient menuClient = new MenuClient();
        return menuClient.init(apiConfig);
    }

    /**
     * 构建用户相关工具类
     * @param appId
     * @param appSecret
     * @return
     */
    public static UserClient createUserClient(String appId, String appSecret,String token ){
        ApiConfig apiConfig = new ApiConfig();
        apiConfig.init(appId, appSecret, token);
        return createUserClient(apiConfig);
    }

    /**
     * 构建用户相关工具类
     * @param apiConfig
     * @return
     */
    public static UserClient createUserClient(ApiConfig apiConfig){
        UserClient client = new UserClient();
        return client.init(apiConfig);
    }


}
