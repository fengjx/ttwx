package com.fjx.wechat.base.web.admin.entity;

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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fjx.common.bean.ToStringBase;
import com.fjx.common.utils.CommonUtils;

/**
 * 消息动作规则表
 * @author fengjx xd-fjx@qq.com
 * @date 2014年9月9日
 */
@Entity
@Table(name="wechat_resp_msg_action",
	uniqueConstraints = {@UniqueConstraint(columnNames={"key_word", "user_id"})}
)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","sysUser"}) 
public class RespMsgActionEntity extends ToStringBase {

	private static final long serialVersionUID = 3782192152284007383L;
	public static final String ACTION_TYPE_MATERIAL = "material";
	public static final String ACTION_TYPE_API = "api";
	

	private String id;				
	private String ext_type;		//自定义类型扩展（如默认消息类型）
	private String req_type;		//请求类型
	private String event_type;		//事件类型
	private String key_word;		//关键字/key
	private String action_type;		//动作响应数据源(素材、接口)
	private MaterialEntity material;//若action_type=api，则为空
	private ExtAppEntity extApp;	//若action_type=material，则为空
	private Date in_time;			//编辑时间
	
	private SysUserEntity sysUser;		//归属用户
	
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

	public String getExt_type() {
		return ext_type;
	}

	public void setExt_type(String ext_type) {
		this.ext_type = ext_type;
	}

	public String getReq_type() {
		return req_type;
	}

	public void setReq_type(String req_type) {
		this.req_type = req_type;
	}

	public String getEvent_type() {
		return event_type;
	}

	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}
	
	public String getKey_word() {
		return key_word;
	}

	public void setKey_word(String key_word) {
		this.key_word = key_word;
	}

	public String getAction_type() {
		return action_type;
	}

	public void setAction_type(String action_type) {
		this.action_type = action_type;
	}
	
	@ManyToOne
	@JoinColumn(name="material_id")
	public MaterialEntity getMaterial() {
		return material;
	}

	public void setMaterial(MaterialEntity material) {
		this.material = material;
	}
	
	@ManyToOne
	@JoinColumn(name="app_id")
	public ExtAppEntity getExtApp() {
		return extApp;
	}

	public void setExtApp(ExtAppEntity extApp) {
		this.extApp = extApp;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getIn_time() {
		return in_time;
	}

	public void setIn_time(Date in_time) {
		this.in_time = in_time;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
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
