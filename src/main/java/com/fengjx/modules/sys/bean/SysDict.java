
package com.fengjx.modules.sys.bean;

import com.fengjx.commons.plugin.db.BaseBean;
import com.fengjx.commons.plugin.db.Mapper;

/**
 * Autu Generated, do not modify this file.
 */
@Mapper(table="sys_dict", id = "id")
@SuppressWarnings("serial")
public class SysDict extends BaseBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getId() {
		return get("id");
	}

	public void setDictDesc(java.lang.String dictDesc) {
		set("dict_desc", dictDesc);
	}

	public java.lang.String getDictDesc() {
		return get("dict_desc");
	}

	public void setDictName(java.lang.String dictName) {
		set("dict_name", dictName);
	}

	public java.lang.String getDictName() {
		return get("dict_name");
	}

	public void setDictValue(java.lang.String dictValue) {
		set("dict_value", dictValue);
	}

	public java.lang.String getDictValue() {
		return get("dict_value");
	}

	public void setGroupCode(java.lang.String groupCode) {
		set("group_code", groupCode);
	}

	public java.lang.String getGroupCode() {
		return get("group_code");
	}

	public void setInTime(java.lang.String inTime) {
		set("in_time", inTime);
	}

	public java.lang.String getInTime() {
		return get("in_time");
	}

	public void setIsValid(java.lang.Integer isValid) {
		set("is_valid", isValid);
	}

	public java.lang.Integer getIsValid() {
		return get("is_valid");
	}

	public void setOrderNo(java.lang.Integer orderNo) {
		set("order_no", orderNo);
	}

	public java.lang.Integer getOrderNo() {
		return get("order_no");
	}

	public void setParentId(java.lang.String parentId) {
		set("parent_id", parentId);
	}

	public java.lang.String getParentId() {
		return get("parent_id");
	}

	public void setGroupName(java.lang.String groupName) {
		set("group_name", groupName);
	}

	public java.lang.String getGroupName() {
		return get("group_name");
	}

}
