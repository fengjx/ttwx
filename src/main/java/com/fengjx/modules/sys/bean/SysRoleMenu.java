
package com.fengjx.modules.sys.bean;

import com.fengjx.commons.plugin.db.BaseBean;
import com.fengjx.commons.plugin.db.annotation.Mapper;

/**
 * Autu Generated, do not modify this file.
 */
@Mapper(table="sys_role_menu", id = "menu_id,role_id")
@SuppressWarnings("serial")
public class SysRoleMenu extends BaseBean {

	public void setRoleId(java.lang.String roleId) {
		set("role_id", roleId);
	}

	public java.lang.String getRoleId() {
		return get("role_id");
	}

	public void setMenuId(java.lang.String menuId) {
		set("menu_id", menuId);
	}

	public java.lang.String getMenuId() {
		return get("menu_id");
	}

}
