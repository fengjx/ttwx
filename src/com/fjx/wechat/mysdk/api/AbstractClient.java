package com.fjx.wechat.mysdk.api;

import com.fjx.common.utils.BeanUtil;
import com.fjx.wechat.mysdk.context.ApiConfigContext;

/**
 * 微信客户端接口
 * Created by fengjx on 2014/11/15 0015.
 */
public abstract class AbstractClient {

    protected ApiConfig apiConfig;

    protected <T extends AbstractClient> T init(ApiConfig apiConfig) {
        ApiConfig temp = ApiConfigContext.getApiConfigByKey(apiConfig.createKey());
        //缓存中没有相关配置
        if(BeanUtil.isNull(temp)){
            this.apiConfig = apiConfig;
        }else{
            //配置缓存里的配置
            this.apiConfig = temp;
        }
        return (T)this;
    }

    /**
     * 获得全局AccessToken
     * @return
     */
    protected AccessToken getAccessToken(){
        AccessToken accessToken = apiConfig.getAccessToken();
        if(null == accessToken || !accessToken.isAvailable()){
            AccessTokenClient accessTokenClient = ClientFactory.createAccessTokenClient(apiConfig);
            accessToken = accessTokenClient.getAccessToken();
            apiConfig.setAccessToken(accessToken);
            //将配置缓存起来
            ApiConfigContext.put(apiConfig);
        }
        return accessToken;
    }

    /**
     * 处理api返回结果
     * @param jsonResult
     * @return
     */
    protected ApiResult proceResult(String jsonResult){
        ApiResult apiResult = new ApiResult(jsonResult);
        //40001	获取access_token时AppSecret错误，或者access_token无效
        if("40001".equals(apiResult.getErrorCode())){
            //把无效的apiconfig从缓存里删除，下次重新调API时会重新获取
            ApiConfigContext.remove(apiConfig.createKey());
        }
        return apiResult;
    }

}
