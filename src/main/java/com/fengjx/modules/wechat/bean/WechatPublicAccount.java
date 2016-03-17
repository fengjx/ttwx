
package com.fengjx.modules.wechat.bean;

import com.fengjx.commons.plugin.db.BaseBean;
import com.fengjx.commons.plugin.db.annotation.Mapper;

/**
 * Autu Generated, do not modify this file.
 */
@Mapper(table="wechat_public_account", id = "id")
@SuppressWarnings("serial")
public class WechatPublicAccount extends BaseBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getId() {
		return get("id");
	}

	public void setAccountId(java.lang.String accountId) {
		set("account_id", accountId);
	}

	public java.lang.String getAccountId() {
		return get("account_id");
	}

	public void setAppId(java.lang.String appId) {
		set("app_id", appId);
	}

	public java.lang.String getAppId() {
		return get("app_id");
	}

	public void setAppSecret(java.lang.String appSecret) {
		set("app_secret", appSecret);
	}

	public java.lang.String getAppSecret() {
		return get("app_secret");
	}

	public void setInTime(java.util.Date inTime) {
		set("in_time", inTime);
	}

	public java.util.Date getInTime() {
		return get("in_time");
	}

	public void setPwd(java.lang.String pwd) {
		set("pwd", pwd);
	}

	public java.lang.String getPwd() {
		return get("pwd");
	}

	public void setTicket(java.lang.String ticket) {
		set("ticket", ticket);
	}

	public java.lang.String getTicket() {
		return get("ticket");
	}

	public void setToken(java.lang.String token) {
		set("token", token);
	}

	public java.lang.String getToken() {
		return get("token");
	}

	public void setUpdateTime(java.util.Date updateTime) {
		set("update_time", updateTime);
	}

	public java.util.Date getUpdateTime() {
		return get("update_time");
	}

	public void setUrl(java.lang.String url) {
		set("url", url);
	}

	public java.lang.String getUrl() {
		return get("url");
	}

	public void setUsername(java.lang.String username) {
		set("username", username);
	}

	public java.lang.String getUsername() {
		return get("username");
	}

	public void setValidCode(java.lang.String validCode) {
		set("valid_code", validCode);
	}

	public java.lang.String getValidCode() {
		return get("valid_code");
	}

	public void setValidState(java.lang.String validState) {
		set("valid_state", validState);
	}

	public java.lang.String getValidState() {
		return get("valid_state");
	}

	public void setSysUserId(java.lang.String sysUserId) {
		set("sys_user_id", sysUserId);
	}

	public java.lang.String getSysUserId() {
		return get("sys_user_id");
	}

	public void setEncodingAESKey(java.lang.String encodingAESKey) {
		set("encodingAESKey", encodingAESKey);
	}

	public java.lang.String getEncodingAESKey() {
		return get("encodingAESKey");
	}

	public void setMerchantId(java.lang.String merchantId) {
		set("merchant_id", merchantId);
	}

	public java.lang.String getMerchantId() {
		return get("merchant_id");
	}

	public void setPayKey(java.lang.String payKey) {
		set("pay_key", payKey);
	}

	public java.lang.String getPayKey() {
		return get("pay_key");
	}

}
