
package com.fengjx.modules.wechat.bean;

import com.fengjx.commons.plugin.db.BaseBean;
import com.fengjx.commons.plugin.db.annotation.Mapper;

/**
 * Autu Generated, do not modify this file.
 */
@Mapper(table="wechat_menu", id = "id", pid = "parent_id")
@SuppressWarnings("serial")
public class WechatMenu extends BaseBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getId() {
		return get("id");
	}

	public void setInTime(java.util.Date inTime) {
		set("in_time", inTime);
	}

	public java.util.Date getInTime() {
		return get("in_time");
	}

	public void setMenuKey(java.lang.String menuKey) {
		set("menu_key", menuKey);
	}

	public java.lang.String getMenuKey() {
		return get("menu_key");
	}

	public void setMenuLevel(java.lang.Integer menuLevel) {
		set("menu_level", menuLevel);
	}

	public java.lang.Integer getMenuLevel() {
		return get("menu_level");
	}

	public void setName(java.lang.String name) {
		set("name", name);
	}

	public java.lang.String getName() {
		return get("name");
	}

	public void setType(java.lang.String type) {
		set("type", type);
	}

	public java.lang.String getType() {
		return get("type");
	}

	public void setUpdateTime(java.util.Date updateTime) {
		set("update_time", updateTime);
	}

	public java.util.Date getUpdateTime() {
		return get("update_time");
	}

	public void setUrl(java.lang.String url) {
		set("url", url);
	}

	public java.lang.String getUrl() {
		return get("url");
	}

	public void setParentId(java.lang.String parentId) {
		set("parent_id", parentId);
	}

	public java.lang.String getParentId() {
		return get("parent_id");
	}

	public void setUserId(java.lang.String userId) {
		set("user_id", userId);
	}

	public java.lang.String getUserId() {
		return get("user_id");
	}

	public void setOrderNo(java.lang.Integer orderNo) {
		set("order_no", orderNo);
	}

	public java.lang.Integer getOrderNo() {
		return get("order_no");
	}

}
