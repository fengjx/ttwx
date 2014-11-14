package com.fjx.wechat.mysdk.constants;

import com.fjx.common.utils.GetPropertiesVal;


/**
 * 微信api请求地址
 * @author fengjx
 * @date 2014年9月4日
 */
public class WechatApiConstants {
	
	//微信常量
//	public static final String WECHAT_TOKEN = GetPropertiesVal.getLabel("wechat.myToken");
//    public static final String WECHAT_APPID = GetPropertiesVal.getLabel("wechat.appid");
//    public static final String WECHAT_APPSECRET = GetPropertiesVal.getLabel("wechat.appSecret");
    
    public static final String WECHAT_ACCESS_TOKEN_URL = GetPropertiesVal.getLabel("wechat.access.token.url");
    public static final String WECHAT_MENU_CREATE_URL = GetPropertiesVal.getLabel("wechat.menu.create.url");
    public static final String WECHAT_SCOPE_SNSAPI_BASE = GetPropertiesVal.getLabel("wechat.scope.snsapi_base");
    public static final String WECHAT_SCOPE_SNSAPI_USERINFO = GetPropertiesVal.getLabel("wechat.scope.snsapi_userinfo");
    public static final String WECHAT_OAUTH2_ACCESS_TOKEN_URL = GetPropertiesVal.getLabel("wechat.oauth2.access.token.url");
    public static final String WECHAT_OAUTH2_REFRESH_TOKEN_URL = GetPropertiesVal.getLabel("wechat.oauth2.refresh.token.url");
    public static final String WECHAT_OAUTH2_USERINFO_URL = GetPropertiesVal.getLabel("wechat.oauth2.userinfo.url");
    public static final String WECHAT_USER_GROUPS_URL = GetPropertiesVal.getLabel("wechat.user.groups.url");
	
	public static void main(String[] args) {
		System.out.println(WECHAT_ACCESS_TOKEN_URL);
	}
}
