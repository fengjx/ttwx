
package com.fengjx.modules.sys.bean;

import com.fengjx.commons.plugin.db.BaseBean;
import com.fengjx.commons.plugin.db.annotation.Mapper;

/**
 * Autu Generated, do not modify this file.
 */
@Mapper(table="sys_role", id = "id")
@SuppressWarnings("serial")
public class SysRole extends BaseBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getId() {
		return get("id");
	}

	public void setName(java.lang.String name) {
		set("name", name);
	}

	public java.lang.String getName() {
		return get("name");
	}

	public void setRoleCode(java.lang.String roleCode) {
		set("role_code", roleCode);
	}

	public java.lang.String getRoleCode() {
		return get("role_code");
	}

	public void setIsSys(java.lang.String isSys) {
		set("is_sys", isSys);
	}

	public java.lang.String getIsSys() {
		return get("is_sys");
	}

	public void setIsValid(java.lang.String isValid) {
		set("is_valid", isValid);
	}

	public java.lang.String getIsValid() {
		return get("is_valid");
	}

	public void setUpdateTime(java.util.Date updateTime) {
		set("update_time", updateTime);
	}

	public java.util.Date getUpdateTime() {
		return get("update_time");
	}

	public void setRemarks(java.lang.String remarks) {
		set("remarks", remarks);
	}

	public java.lang.String getRemarks() {
		return get("remarks");
	}

}
