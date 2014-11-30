package com.fjx.wechat.base.admin.entity;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 扩展接口消息类型关联表
 * 定义扩展接口支持的类型
 * @author fengjx xd-fjx@qq.com
 * @date 2014年11月8日
 */
@Entity
@Table(name="wechat_ext_app_support_type")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
public class ExtAppSupportTypeEntity {
	
	private String id;
	private String msg_type;
	private String event_type;
	private ExtAppEntity extApp;
	
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

	@ManyToOne
	@JoinColumn(name="ext_app_id")
	public ExtAppEntity getExtApp() {
		return extApp;
	}

	public void setExtApp(ExtAppEntity extApp) {
		this.extApp = extApp;
	}
}
