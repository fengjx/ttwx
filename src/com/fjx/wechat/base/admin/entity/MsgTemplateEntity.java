package com.fjx.wechat.base.admin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fjx.common.bean.ToStringBase;

@Table(name = "wechat_msg_template")
@Entity
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"}) 
public class MsgTemplateEntity extends ToStringBase {

	private static final long serialVersionUID = 5444784184385042550L;

	private String id;
	private String msg_key;
	private String msg_content;
	private String description;
	private Date in_time;
	
	
	@Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMsg_key() {
		return msg_key;
	}

	public void setMsg_key(String msg_key) {
		this.msg_key = msg_key;
	}
	
	@Column(length=4000)
	public String getMsg_content() {
		return msg_content;
	}

	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}

	@Column(length=256)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getIn_time() {
		return in_time;
	}

	public void setIn_time(Date in_time) {
		this.in_time = in_time;
	}
	
}
