package com.fjx.wechat.base.admin.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;

import com.fjx.common.bean.ToStringBase;
import com.fjx.common.utils.CommonUtils;

/**
 * 扩展应用表
 * @author fengjx xd-fjx@qq.com
 * @version ExtAppEntity.java 2014年9月13日
 */
@Table(name = "wechat_ext_app")
@Entity
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","supportTypes"})
public class ExtAppEntity extends ToStringBase {

	private static final long serialVersionUID = 8840798984002669943L;

	public static final String TYPE_WEB = "web";
	public static final String TYPE_API = "api";
	public static final String TYPE_RESTFUL = "restful";


	private String id;
	private String name; 			// 
	private String description;		// 描述
	private String app_type; 		// 应用类型，枚举AppType（web、api、restful）
	private String bean_name; 		// spring beanName
	private String method_name;		// 调用方法名
	private String app_url; 		// 应用链接
	private String restful_url; 	// restful远程接口
	private String order_no; 		// 排序字段
	private Date in_time; 			//
	private String group_id; 		// 应用分组ID
	private String is_valid; 		// 是否启用

	private Set<ExtAppSupportTypeEntity> supportTypes;	//插件支持的类型

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getApp_type() {
		return app_type;
	}

	public void setApp_type(String app_type) {
		this.app_type = app_type;
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

	public String getApp_url() {
		return app_url;
	}

	public void setApp_url(String app_url) {
		this.app_url = app_url;
	}
	
	public String getRestful_url() {
		return restful_url;
	}

	public void setRestful_url(String restful_url) {
		this.restful_url = restful_url;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getIn_time() {
		return in_time;
	}

	public void setIn_time(Date in_time) {
		this.in_time = in_time;
	}

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	
	@Column(length=1)
	public String getIs_valid() {
		return is_valid;
	}

	public void setIs_valid(String is_valid) {
		this.is_valid = is_valid;
	}

	@OneToMany(mappedBy = "extApp",fetch=FetchType.LAZY)
	public Set<ExtAppSupportTypeEntity> getSupportTypes() {
		return supportTypes;
	}

	public void setSupportTypes(Set<ExtAppSupportTypeEntity> supportTypes) {
		this.supportTypes = supportTypes;
	}

	@Transient
	public String getStr_in_time() {
		return CommonUtils.date2String(in_time);
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
	public String getSupportMsgTypesStr() {
		String msgTypes = "";
		boolean first = true;
		for(ExtAppSupportTypeEntity type : getSupportTypes()){
			if(StringUtils.isNotBlank(type.getMsg_type())){
				if (first) {
					msgTypes += type.getMsg_type();
					first = false;
				}else{
					msgTypes += ","+type.getMsg_type();
				}
			}
		}
		return msgTypes;
	}
	@Transient
	public String getSupportEventTypesStr() {
		String eventTypes = "";
		boolean first = true;
		for(ExtAppSupportTypeEntity type : getSupportTypes()){
			if(StringUtils.isNotBlank(type.getEvent_type())){
				if (first) {
					eventTypes += type.getEvent_type();
					first = false;
				}else{
					eventTypes += ","+type.getEvent_type();
				}
			}
		}
		return eventTypes;
	}
}
