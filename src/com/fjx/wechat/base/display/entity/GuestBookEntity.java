package com.fjx.wechat.base.display.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fjx.common.bean.ToStringBase;
import com.fjx.common.utils.CommonUtils;

/**
 * 用户留言
 * 
 * @author fengjx xd-fjx@qq.com
 * @version GuestBook.java 2014年9月28日
 */
@Table(name = "portal_guestbook")
@Entity
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"}) 
public class GuestBookEntity extends ToStringBase {

	private static final long serialVersionUID = 3594076564523558995L;

	private String id;
	private String name;
	private String email;
	private String qq;
	private String msg;
	private Date in_time;
	
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(length=1000)
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getIn_time() {
		return in_time;
	}

	public void setIn_time(Date in_time) {
		this.in_time = in_time;
	}
	
	@Transient
	public String getStr_in_time() {
		return CommonUtils.date2String(in_time);
	}
	
}
