
package com.fengjx.modules.wechat.bean;

import com.fengjx.commons.plugin.db.BaseBean;
import com.fengjx.commons.plugin.db.annotation.Mapper;

/**
 * Autu Generated, do not modify this file.
 */
@Mapper(table="wechat_msg_template", id = "id")
@SuppressWarnings("serial")
public class WechatMsgTemplate extends BaseBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getId() {
		return get("id");
	}

	public void setDescription(java.lang.String description) {
		set("description", description);
	}

	public java.lang.String getDescription() {
		return get("description");
	}

	public void setInTime(java.util.Date inTime) {
		set("in_time", inTime);
	}

	public java.util.Date getInTime() {
		return get("in_time");
	}

	public void setMsgContent(java.lang.String msgContent) {
		set("msg_content", msgContent);
	}

	public java.lang.String getMsgContent() {
		return get("msg_content");
	}

	public void setMsgKey(java.lang.String msgKey) {
		set("msg_key", msgKey);
	}

	public java.lang.String getMsgKey() {
		return get("msg_key");
	}

}
