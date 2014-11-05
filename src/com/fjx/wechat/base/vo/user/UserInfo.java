package com.fjx.wechat.base.vo.user;

import com.fjx.common.bean.ToStringBase;

/**
 * 用户信息
 * @author fengjx
 * 2014年3月24日
 */
public class UserInfo extends ToStringBase {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6112459661407545539L;
	
	
	private String openid;		//用户的唯一标识
	private String nickname;	//用户昵称
	private String sex;			//用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	private String province;	//用户个人资料填写的省份
	private String city;		//普通用户个人资料填写的城市
	private String country;		//国家，如中国为CN
	private String headimgurl;	//用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
	private String[] privilege;	//用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
	private String language;
	
	private String scope;				//授权方式snsapi_base / snsapi_userinfo
	
	
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String[] getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String[] privilege) {
		this.privilege = privilege;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	
}
