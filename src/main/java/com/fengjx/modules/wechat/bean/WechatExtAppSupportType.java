
package com.fengjx.modules.wechat.bean;

import com.fengjx.commons.plugin.db.BaseBean;
import com.fengjx.commons.plugin.db.annotation.Mapper;

/**
 * Autu Generated, do not modify this file.
 */
@Mapper(table="wechat_ext_app_support_type", id = "id")
@SuppressWarnings("serial")
public class WechatExtAppSupportType extends BaseBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getId() {
		return get("id");
	}

	public void setEventType(java.lang.String eventType) {
		set("event_type", eventType);
	}

	public java.lang.String getEventType() {
		return get("event_type");
	}

	public void setMsgType(java.lang.String msgType) {
		set("msg_type", msgType);
	}

	public java.lang.String getMsgType() {
		return get("msg_type");
	}

	public void setExtAppId(java.lang.String extAppId) {
		set("ext_app_id", extAppId);
	}

	public java.lang.String getExtAppId() {
		return get("ext_app_id");
	}

}
