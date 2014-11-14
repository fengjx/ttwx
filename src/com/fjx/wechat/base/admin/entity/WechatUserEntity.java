package com.fjx.wechat.base.admin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fjx.common.bean.ToStringBase;
import com.fjx.common.utils.CommonUtils;

/**
 * 公众号关注用户
 * @author fengjx xd-fjx@qq.com
 * @date 2014年11月2日 
 * wechat_user_info
微信关注用户表
------------------------------
id                PK          //id
openid            String(100) //普通用户的标识，对当前公众号唯一
subscribe         String(10)  //关注状态（值为0时，代表此用户没有关注）
nickname          String(100) //用户的昵称
sex               String(10)  //用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
city              String(40)  //用户所在城市
country           String(40)  //用户所在国家
province          String(40)  //用户所在省份
language          String(30)  //用户的语言
headimgurl        String(255) //用户头像
subscribe_time    String(40)  //用户关注时间
group_id          String(40)  //用户分组ID
unsubscribe_time  Date        //取消关注时间
wechat_openid     String(100) //公众账号
busi_user_id      String(200) //业务用户表ID
 * 
 * 
 */
@Entity
@Table(name = "wechat_user_info")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler","wechatUserGroupEntity","publicAccountEntity" })
public class WechatUserEntity extends ToStringBase {

	public static final String SES_NULL = "1";
	public static final String SES_MAN = "1";
	public static final String SES_WOMAN = "2";

	private static final long serialVersionUID = 6994609088506674133L;

	private String id;
	private String openid;
	private String subscribe;
	private String nickname;
	private String sex;
	private String city;
	private String country;
	private String province;
	private String language;
	private String headimgurl;
	private Date subscribe_time;
	private Date unsubscribe_time;
	private String busi_user_id;
	
	private WechatUserGroupEntity wechatUserGroupEntity;
	private WechatPublicAccountEntity publicAccountEntity;
	
	private String start_time;
	private String end_time;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public Date getSubscribe_time() {
		return subscribe_time;
	}

	public void setSubscribe_time(Date subscribe_time) {
		this.subscribe_time = subscribe_time;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getUnsubscribe_time() {
		return unsubscribe_time;
	}

	public void setUnsubscribe_time(Date unsubscribe_time) {
		this.unsubscribe_time = unsubscribe_time;
	}

	public String getBusi_user_id() {
		return busi_user_id;
	}

	public void setBusi_user_id(String busi_user_id) {
		this.busi_user_id = busi_user_id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name="group_id")
	@NotFound(action=NotFoundAction.IGNORE)
	public WechatUserGroupEntity getWechatUserGroupEntity() {
		return wechatUserGroupEntity;
	}

	public void setWechatUserGroupEntity(WechatUserGroupEntity wechatUserGroupEntity) {
		this.wechatUserGroupEntity = wechatUserGroupEntity;
	}
	
	@ManyToOne
    @JoinColumn(name = "public_account_id", nullable = false)
	public WechatPublicAccountEntity getPublicAccountEntity() {
		return publicAccountEntity;
	}

	public void setPublicAccountEntity(WechatPublicAccountEntity publicAccountEntity) {
		this.publicAccountEntity = publicAccountEntity;
	}

	@Transient
	public String getStr_subscribe_time() {
		return CommonUtils.date2String(subscribe_time);
	}
	
	@Transient
	public String getStr_unsubscribe_time() {
		return CommonUtils.date2String(unsubscribe_time);
	}
	@Transient
	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	@Transient
	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	
	@Transient
	public String getGroup_id() {
		return wechatUserGroupEntity != null?wechatUserGroupEntity.getId():"";
	}
}
