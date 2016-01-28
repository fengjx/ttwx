
package com.fengjx.modules.sys.bean;

import com.fengjx.commons.plugin.db.BaseBean;
import com.fengjx.commons.plugin.db.annotation.Mapper;

/**
 * Autu Generated, do not modify this file.
 */
@Mapper(table="sys_menu", id = "id", pid = "parent_id")
@SuppressWarnings("serial")
public class SysMenu extends BaseBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getId() {
		return get("id");
	}

	public void setParentId(java.lang.String parentId) {
		set("parent_id", parentId);
	}

	public java.lang.String getParentId() {
		return get("parent_id");
	}

	public void setParentIds(java.lang.String parentIds) {
		set("parent_ids", parentIds);
	}

	public java.lang.String getParentIds() {
		return get("parent_ids");
	}

	public void setName(java.lang.String name) {
		set("name", name);
	}

	public java.lang.String getName() {
		return get("name");
	}

	public void setUrl(java.lang.String url) {
		set("url", url);
	}

	public java.lang.String getUrl() {
		return get("url");
	}

	public void setIcon(java.lang.String icon) {
		set("icon", icon);
	}

	public java.lang.String getIcon() {
		return get("icon");
	}

	public void setPermission(java.lang.String permission) {
		set("permission", permission);
	}

	public java.lang.String getPermission() {
		return get("permission");
	}

	public void setLevel(java.lang.Integer level) {
		set("level", level);
	}

	public java.lang.Integer getLevel() {
		return get("level");
	}

	public void setIsShow(java.lang.String isShow) {
		set("is_show", isShow);
	}

	public java.lang.String getIsShow() {
		return get("is_show");
	}

	public void setOrderNo(java.lang.Integer orderNo) {
		set("order_no", orderNo);
	}

	public java.lang.Integer getOrderNo() {
		return get("order_no");
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

	public void setIsValid(java.lang.String isValid) {
		set("is_valid", isValid);
	}

	public java.lang.String getIsValid() {
		return get("is_valid");
	}

}
