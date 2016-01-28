
package com.fengjx.modules.wechat.bean;

import com.fengjx.commons.plugin.db.BaseBean;
import com.fengjx.commons.plugin.db.annotation.Mapper;

/**
 * Autu Generated, do not modify this file.
 */
@Mapper(table="wechat_material", id = "id")
@SuppressWarnings("serial")
public class WechatMaterial extends BaseBean {

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

	public void setMsgType(java.lang.String msgType) {
		set("msg_type", msgType);
	}

	public java.lang.String getMsgType() {
		return get("msg_type");
	}

	public void setXmlData(java.lang.String xmlData) {
		set("xml_data", xmlData);
	}

	public java.lang.String getXmlData() {
		return get("xml_data");
	}

	public void setUserId(java.lang.String userId) {
		set("user_id", userId);
	}

	public java.lang.String getUserId() {
		return get("user_id");
	}

}
