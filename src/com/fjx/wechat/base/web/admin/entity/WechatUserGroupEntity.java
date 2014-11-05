package com.fjx.wechat.base.web.admin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fjx.common.bean.ToStringBase;
import com.fjx.common.utils.CommonUtils;


/**
 * 
 * @author fengjx xd-fjx@qq.com
 * @date 2014年11月2日
//微信用户分组表 wechat_user_group
--------------------
id       PK          //id
name     String(50)  //name
in_time  Date        //in_time
 */
@Table(name = "wechat_user_group")
@Entity
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"}) 
public class WechatUserGroupEntity extends ToStringBase {

	private static final long serialVersionUID = 8024135770510964259L;
	
	private String id;
	private String name;
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
	@Temporal(TemporalType.TIMESTAMP)
	@OrderBy
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
