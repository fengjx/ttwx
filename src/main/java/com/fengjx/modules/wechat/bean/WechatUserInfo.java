
package com.fengjx.modules.wechat.bean;

import com.fengjx.commons.plugin.db.BaseBean;
import com.fengjx.commons.plugin.db.annotation.Mapper;

/**
 * Autu Generated, do not modify this file.
 */
@Mapper(table="wechat_user_info", id = "id")
@SuppressWarnings("serial")
public class WechatUserInfo extends BaseBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getId() {
		return get("id");
	}

	public void setBusiUserId(java.lang.String busiUserId) {
		set("busi_user_id", busiUserId);
	}

	public java.lang.String getBusiUserId() {
		return get("busi_user_id");
	}

	public void setCity(java.lang.String city) {
		set("city", city);
	}

	public java.lang.String getCity() {
		return get("city");
	}

	public void setCountry(java.lang.String country) {
		set("country", country);
	}

	public java.lang.String getCountry() {
		return get("country");
	}

	public void setHeadimgurl(java.lang.String headimgurl) {
		set("headimgurl", headimgurl);
	}

	public java.lang.String getHeadimgurl() {
		return get("headimgurl");
	}

	public void setLanguage(java.lang.String language) {
		set("language", language);
	}

	public java.lang.String getLanguage() {
		return get("language");
	}

	public void setNickname(java.lang.String nickname) {
		set("nickname", nickname);
	}

	public java.lang.String getNickname() {
		return get("nickname");
	}

	public void setOpenid(java.lang.String openid) {
		set("openid", openid);
	}

	public java.lang.String getOpenid() {
		return get("openid");
	}

	public void setProvince(java.lang.String province) {
		set("province", province);
	}

	public java.lang.String getProvince() {
		return get("province");
	}

	public void setSex(java.lang.String sex) {
		set("sex", sex);
	}

	public java.lang.String getSex() {
		return get("sex");
	}

	public void setSubscribe(java.lang.String subscribe) {
		set("subscribe", subscribe);
	}

	public java.lang.String getSubscribe() {
		return get("subscribe");
	}

	public void setUnsubscribeTime(java.util.Date unsubscribeTime) {
		set("unsubscribe_time", unsubscribeTime);
	}

	public java.util.Date getUnsubscribeTime() {
		return get("unsubscribe_time");
	}

	public void setWechatOpenid(java.lang.String wechatOpenid) {
		set("wechat_openid", wechatOpenid);
	}

	public java.lang.String getWechatOpenid() {
		return get("wechat_openid");
	}

	public void setPublicAccountId(java.lang.String publicAccountId) {
		set("public_account_id", publicAccountId);
	}

	public java.lang.String getPublicAccountId() {
		return get("public_account_id");
	}

	public void setGroupId(java.lang.String groupId) {
		set("group_id", groupId);
	}

	public java.lang.String getGroupId() {
		return get("group_id");
	}

	public void setSubscribeTime(java.util.Date subscribeTime) {
		set("subscribe_time", subscribeTime);
	}

	public java.util.Date getSubscribeTime() {
		return get("subscribe_time");
	}

}
