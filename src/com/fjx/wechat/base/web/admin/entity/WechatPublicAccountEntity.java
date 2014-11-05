package com.fjx.wechat.base.web.admin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fjx.common.bean.ToStringBase;
import com.fjx.common.utils.CommonUtils;

/**
 * 公众账号信息表
 * @author fengjx xd-fjx@qq.com
 * @version WechatPublicAccounts.java 2014年10月5日
 */
@Table(name = "wechat_public_account")
@Entity
public class WechatPublicAccountEntity extends ToStringBase {

	private static final long serialVersionUID = -3469572054536337933L;
	
	public static final String VALID_STATE_NONACTIVATED = "0";		//0：未激活
	public static final String VALID_STATE_EXCESS = "1";			//1：已配置到公众平台
	public static final String VALID_STATE_ACTIVATE = "2";			//2：已通过客户端校验验证码
	
	
	private String id;
	private String account_id;
	private String url;
	private String token;
	private String ticket;
	private String app_id;
	private String app_secret;
	private String valid_state;		//首次激活（0：未激活；1：已配置到公众平台；2：已通过客户端校验验证码）
	private String valid_code;		//验证码
	private Date in_time;
	private Date update_time;
	private String username;
	private String pwd;
	
	private SysUserEntity sysUser;
	
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
	
	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	@Column(unique=true)
	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getApp_id() {
		return app_id;
	}
	
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public String getApp_secret() {
		return app_secret;
	}

	public void setApp_secret(String app_secret) {
		this.app_secret = app_secret;
	}
	
	public String getValid_state() {
		return valid_state;
	}

	public void setValid_state(String valid_state) {
		this.valid_state = valid_state;
	}
	
	public String getValid_code() {
		return valid_code;
	}

	public void setValid_code(String valid_code) {
		this.valid_code = valid_code;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable=false)
	public Date getIn_time() {
		return in_time;
	}

	public void setIn_time(Date in_time) {
		this.in_time = in_time;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	@Column(updatable=false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(updatable=false)
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	@OneToOne
	@JoinColumn(name="sys_user_id")
	public SysUserEntity getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUserEntity sysUser) {
		this.sysUser = sysUser;
	}
	
	@Transient
	public String getStr_in_time() {
		return CommonUtils.date2String(in_time);
	}
}
