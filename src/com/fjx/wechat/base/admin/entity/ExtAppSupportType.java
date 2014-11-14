package com.fjx.wechat.base.admin.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



/**
 * 扩展接口消息类型关联表
 * 定义扩展接口支持的类型
 * @author fengjx xd-fjx@qq.com
 * @date 2014年11月8日
 */
@Table(name="wechat_ext_app_support_type")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","extApps"}) 
public class ExtAppSupportType {
	
	private String id;
	private String msg_type;
	private String event_type;
	private Set<ExtAppEntity> extApps;
	
	
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
	public String getMsg_type() {
		return msg_type;
	}
	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}
	public String getEvent_type() {
		return event_type;
	}
	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}
	
	@OneToMany
	@JoinColumn(name="ext_app_id")
	public Set<ExtAppEntity> getExtApps() {
		return extApps;
	}
	public void setExtApps(Set<ExtAppEntity> extApps) {
		this.extApps = extApps;
	}
}
