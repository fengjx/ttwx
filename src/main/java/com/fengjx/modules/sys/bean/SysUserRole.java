
package com.fengjx.modules.sys.bean;

import com.fengjx.commons.plugin.db.BaseBean;
import com.fengjx.commons.plugin.db.annotation.Mapper;

/**
 * Autu Generated, do not modify this file.
 */
@Mapper(table="sys_user_role", id = "role_id,user_id")
@SuppressWarnings("serial")
public class SysUserRole extends BaseBean {

	public void setUserId(java.lang.String userId) {
		set("user_id", userId);
	}

	public java.lang.String getUserId() {
		return get("user_id");
	}

	public void setRoleId(java.lang.String roleId) {
		set("role_id", roleId);
	}

	public java.lang.String getRoleId() {
		return get("role_id");
	}

}
