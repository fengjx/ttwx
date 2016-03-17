
package com.fengjx.modules.wechat.bean;

import com.fengjx.commons.plugin.db.BaseBean;
import com.fengjx.commons.plugin.db.annotation.Mapper;

/**
 * Autu Generated, do not modify this file.
 */
@Mapper(table="wechat_user_group", id = "id")
@SuppressWarnings("serial")
public class WechatUserGroup extends BaseBean {

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

	public void setName(java.lang.String name) {
		set("name", name);
	}

	public java.lang.String getName() {
		return get("name");
	}

	public void setUserId(java.lang.String userId) {
		set("user_id", userId);
	}

	public java.lang.String getUserId() {
		return get("user_id");
	}

}
