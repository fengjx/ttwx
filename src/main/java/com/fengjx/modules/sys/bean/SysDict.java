
package com.fengjx.modules.sys.bean;

import com.fengjx.commons.plugin.db.BaseBean;
import com.fengjx.commons.plugin.db.annotation.Mapper;

/**
 * Autu Generated, do not modify this file.
 */
@Mapper(table="sys_dict", id = "id")
@SuppressWarnings("serial")
public class SysDict extends BaseBean {

	public void setId(String id) {
		set("id", id);
	}

	public String getId() {
		return get("id");
	}

	public void setDictDesc(String dictDesc) {
		set("dict_desc", dictDesc);
	}

	public String getDictDesc() {
		return get("dict_desc");
	}

	public void setDictName(String dictName) {
		set("dict_name", dictName);
	}

	public String getDictName() {
		return get("dict_name");
	}

	public void setDictValue(String dictValue) {
		set("dict_value", dictValue);
	}

	public String getDictValue() {
		return get("dict_value");
	}

	public void setGroupCode(String groupCode) {
		set("group_code", groupCode);
	}

	public String getGroupCode() {
		return get("group_code");
	}

	public void setInTime(java.util.Date inTime) {
		set("in_time", inTime);
	}

	public java.util.Date getInTime() {
		return get("in_time");
	}

	public void setIsValid(Integer isValid) {
		set("is_valid", isValid);
	}

	public Integer getIsValid() {
		return get("is_valid");
	}

	public void setOrderNo(Integer orderNo) {
		set("order_no", orderNo);
	}

	public Integer getOrderNo() {
		return get("order_no");
	}

	public void setParentId(String parentId) {
		set("parent_id", parentId);
	}

	public String getParentId() {
		return get("parent_id");
	}

	public void setGroupName(String groupName) {
		set("group_name", groupName);
	}

	public String getGroupName() {
		return get("group_name");
	}

}
