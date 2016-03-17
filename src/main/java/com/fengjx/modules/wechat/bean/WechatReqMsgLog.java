
package com.fengjx.modules.wechat.bean;

import com.fengjx.commons.plugin.db.BaseBean;
import com.fengjx.commons.plugin.db.annotation.Mapper;

/**
 * Autu Generated, do not modify this file.
 */
@Mapper(table="wechat_req_msg_log", id = "id")
@SuppressWarnings("serial")
public class WechatReqMsgLog extends BaseBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getId() {
		return get("id");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
	}

	public java.util.Date getCreateTime() {
		return get("create_time");
	}

	public void setEventType(java.lang.String eventType) {
		set("event_type", eventType);
	}

	public java.lang.String getEventType() {
		return get("event_type");
	}

	public void setFromUserName(java.lang.String fromUserName) {
		set("from_user_name", fromUserName);
	}

	public java.lang.String getFromUserName() {
		return get("from_user_name");
	}

	public void setInTime(java.util.Date inTime) {
		set("in_time", inTime);
	}

	public java.util.Date getInTime() {
		return get("in_time");
	}

	public void setMsgId(java.lang.Long msgId) {
		set("msg_id", msgId);
	}

	public java.lang.Long getMsgId() {
		return get("msg_id");
	}

	public void setReqType(java.lang.String reqType) {
		set("req_type", reqType);
	}

	public java.lang.String getReqType() {
		return get("req_type");
	}

	public void setReqXml(java.lang.String reqXml) {
		set("req_xml", reqXml);
	}

	public java.lang.String getReqXml() {
		return get("req_xml");
	}

	public void setRespTime(java.util.Date respTime) {
		set("resp_time", respTime);
	}

	public java.util.Date getRespTime() {
		return get("resp_time");
	}

	public void setRespXml(java.lang.String respXml) {
		set("resp_xml", respXml);
	}

	public java.lang.String getRespXml() {
		return get("resp_xml");
	}

	public void setToUserName(java.lang.String toUserName) {
		set("to_user_name", toUserName);
	}

	public java.lang.String getToUserName() {
		return get("to_user_name");
	}

	public void setPublicAccountId(java.lang.String publicAccountId) {
		set("public_account_id", publicAccountId);
	}

	public java.lang.String getPublicAccountId() {
		return get("public_account_id");
	}

}
