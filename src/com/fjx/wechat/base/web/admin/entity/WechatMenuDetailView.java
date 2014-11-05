package com.fjx.wechat.base.web.admin.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fjx.common.bean.ToStringBase;
import com.fjx.common.utils.CommonUtils;

/**
 * 
 * @author fengjx xd-fjx@qq.com
 * @date 2014年10月7日
 */
@Entity
@Table(name = "wechat_menu_detail_view")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class WechatMenuDetailView extends ToStringBase {

	private static final long serialVersionUID = 1188493901821890963L;

	private String id;
	private Date in_time;
	private String menu_key;
	private Integer menu_level;
	private String name;
	private String parent_id;
	private String type;
	private Date update_time;
	private String url;
	private String user_id;
	private String action_id;
	private String action_type;
	private Date action_time;
	private String app_id;
	private String app_name;
	private String app_description;
	private String material_id;
	private String xml_data;

	// Constructors

	/** default constructor */
	public WechatMenuDetailView() {
	}

	/** minimal constructor */
	public WechatMenuDetailView(String id) {
		this.id = id;
	}

	@Id
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getIn_time() {
		return in_time;
	}

	public void setIn_time(Date in_time) {
		this.in_time = in_time;
	}

	public String getMenu_key() {
		return menu_key;
	}

	public void setMenu_key(String menu_key) {
		this.menu_key = menu_key;
	}

	public Integer getMenu_level() {
		return menu_level;
	}

	public void setMenu_level(Integer menu_level) {
		this.menu_level = menu_level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getAction_id() {
		return action_id;
	}

	public void setAction_id(String action_id) {
		this.action_id = action_id;
	}

	public String getAction_type() {
		return action_type;
	}

	public void setAction_type(String action_type) {
		this.action_type = action_type;
	}

	public Date getAction_time() {
		return action_time;
	}

	public void setAction_time(Date action_time) {
		this.action_time = action_time;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	public String getApp_description() {
		return app_description;
	}

	public void setApp_description(String app_description) {
		this.app_description = app_description;
	}

	public String getMaterial_id() {
		return material_id;
	}

	public void setMaterial_id(String material_id) {
		this.material_id = material_id;
	}

	public String getXml_data() {
		return xml_data;
	}

	public void setXml_data(String xml_data) {
		this.xml_data = xml_data;
	}

	@Transient
	public String getStr_in_time() {
		return CommonUtils.date2String(in_time);
	}
	
	@Transient
	public String getStr_update_time() {
		return CommonUtils.date2String(update_time);
	}

	@Transient
	public String getStr_action_time() {
		return CommonUtils.date2String(action_time);
	}
}