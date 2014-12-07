package com.fjx.wechat.mysdk.api;


import com.fjx.wechat.mysdk.tools.NameTool;

/**
 *
 * 微信api配置类
 */
public class ApiConfig {

	private AccessToken accessToken;
	private String appId = null;
	private String appSecret = null;
	private String token;			//公众账号配置的token
	private static String encodingAesKey = null;
	private static boolean messageEncrypt = false;	// 消息加密与否
	
	public void init(AccessToken accessToken) {
		setAccessToken(accessToken);
	}

	public void init(String appId, String appSecret,String token) {
		setAppId(appId);
		setAppSecret(appSecret);
		setToken(token);
	}

	public void init(AccessToken accessToken, String appId, String appSecret,String token) {
		setAccessToken(accessToken);
		setAppId(appId);
		setAppSecret(appSecret);
		setToken(token);
	}

	/**
	 * 根据appId和appSecret生成key
	 * @return
	 */
	public String createKey(){
		return NameTool.createApiConfigKey(this.appId , this.appSecret);
	}

	public AccessToken getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(AccessToken accessToken) {
		this.accessToken = accessToken;
	}


	public  String getAppId() {
		if (appId == null)
			throw new RuntimeException("init ApiConfig.setAppId(...) first");
		return appId;
	}
	
	public  void setAppId(String appId) {
		if (appId == null)
			throw new IllegalArgumentException("appId can not be null");
		this.appId = appId;
	}
	
	public  String getAppSecret() {
		if (appSecret == null)
			throw new RuntimeException("init ApiConfig.setAppSecret(...) first");
		return appSecret;
	}
	
	public  void setAppSecret(String appSecret) {
		if (appSecret == null)
			throw new IllegalArgumentException("appSecret can not be null");
		this.appSecret = appSecret;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		if (token == null)
			throw new IllegalArgumentException("token can not be null");
		this.token = token;
	}

	public static String getEncodingAesKey() {
		return encodingAesKey;
	}

	public static void setEncodingAesKey(String encodingAesKey) {
		if (encodingAesKey == null)
			throw new IllegalArgumentException("encodingAesKey can not be null");
		ApiConfig.encodingAesKey = encodingAesKey;
	}

	public static boolean isMessageEncrypt() {
		return messageEncrypt;
	}

	public static void setMessageEncrypt(boolean messageEncrypt) {
		ApiConfig.messageEncrypt = messageEncrypt;
	}
}




