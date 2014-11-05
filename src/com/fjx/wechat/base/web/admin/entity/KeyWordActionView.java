package com.fjx.wechat.base.web.admin.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fjx.common.bean.ToStringBase;
import com.fjx.common.utils.CommonUtils;


/**
 * 关键字动作查询视图
 * @author fengjx xd-fjx@qq.com
 * @date 2014年10月7日
 */
@Entity
@Table(name = "keyword_action_view")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class KeyWordActionView extends ToStringBase {
	
	private static final long serialVersionUID = -3453111249350855477L;
	
	
	// Fields
	private String id;
	private String req_type;
	private String action_type;
	private String key_word;
	private String material_id;
	private String app_id;
	private Date in_time;
	private String user_id;
	private String beanName;
	private String methodName;
	private String app_name;
	private String xml_data;
	private String msg_type;
	private String dict_name;

	// Constructors

	/** default constructor */
	public KeyWordActionView() {
	}

	/** minimal constructor */
	public KeyWordActionView(String id) {
		this.id = id;
	}

	// Property accessors
	
	@Id
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReq_type() {
		return req_type;
	}

	public void setReq_type(String req_type) {
		this.req_type = req_type;
	}

	public String getAction_type() {
		return action_type;
	}

	public void setAction_type(String action_type) {
		this.action_type = action_type;
	}

	public String getKey_word() {
		return key_word;
	}

	public void setKey_word(String key_word) {
		this.key_word = key_word;
	}

	public String getMaterial_id() {
		return material_id;
	}

	public void setMaterial_id(String material_id) {
		this.material_id = material_id;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getIn_time() {
		return in_time;
	}

	public void setIn_time(Date in_time) {
		this.in_time = in_time;
	}
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	public String getXml_data() {
		return xml_data;
	}

	public void setXml_data(String xml_data) {
		this.xml_data = xml_data;
	}

	public String getMsg_type() {
		return msg_type;
	}

	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}

	public String getDict_name() {
		return dict_name;
	}

	public void setDict_name(String dict_name) {
		this.dict_name = dict_name;
	}
	
	
	@Transient
	public String getStr_in_time() {
		return CommonUtils.date2String(in_time);
	}
}
