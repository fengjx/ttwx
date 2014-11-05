package com.fjx.wechat.base.vo.security;


/**
 * 微信通用接口凭证
 * 调用获取凭证接口后，微信服务器会返回json格式的数据：{"access_token":"ACCESS_TOKEN","expires_in":7200}
 * @author fengjx
 * @date 2014年1月22日
 */
public class AccessToken {
	
	// 获取到的凭证
	private String token;
	// 凭证有效时间，单位：秒
	private int expiresIn;
	
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	
}
