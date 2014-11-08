package com.fjx.wechat.base.web.admin.entity;

import java.util.Date;
import com.fjx.common.bean.ToStringBase;
import com.fjx.common.utils.CommonUtils;


/**
 * 关键字动作查询视图
 * @author fengjx xd-fjx@qq.com
 * @date 2014年10月7日
 */
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
	private String bean_name;
	private String method_name;
	private String app_name;
	private String xml_data;
	private String msg_type;
	private String dict_name;
	
	// Constructors

	/** default constructor */
	public KeyWordActionView() {
	}
	
	public KeyWordActionView(String id, String req_type, String action_type,String key_word, Date in_time,
			String app_id, String bean_name, String method_name,
			String app_name,String material_id, String xml_data, String msg_type, String dict_name) {
		super();
		this.id = id;
		this.req_type = req_type;
		this.action_type = action_type;
		this.key_word = key_word;
		this.material_id = material_id;
		this.app_id = app_id;
		this.in_time = in_time;
		this.bean_name = bean_name;
		this.method_name = method_name;
		this.app_name = app_name;
		this.xml_data = xml_data;
		this.msg_type = msg_type;
		this.dict_name = dict_name;
	}


	/** minimal constructor */
	public KeyWordActionView(String id) {
		this.id = id;
	}

	// Property accessors
	
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

	public String getBean_name() {
		return bean_name;
	}

	public void setBean_name(String bean_name) {
		this.bean_name = bean_name;
	}

	public String getMethod_name() {
		return method_name;
	}

	public void setMethod_name(String method_name) {
		this.method_name = method_name;
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
	
	
	public String getStr_in_time() {
		return CommonUtils.date2String(in_time);
	}

}
