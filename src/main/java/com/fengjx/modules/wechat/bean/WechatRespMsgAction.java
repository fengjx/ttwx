
package com.fengjx.modules.wechat.bean;

import com.fengjx.commons.plugin.db.BaseBean;
import com.fengjx.commons.plugin.db.annotation.Mapper;

/**
 * Autu Generated, do not modify this file.
 */
@Mapper(table="wechat_resp_msg_action", id = "id")
@SuppressWarnings("serial")
public class WechatRespMsgAction extends BaseBean {

	public static final String ACTION_TYPE_MATERIAL = "material";

	public static final String ACTION_TYPE_API = "api";

	public static final String FUZZY_EXACT = "1"; // 完全匹配

	public static final String FUZZY_CONTAIN = "2"; // 包含匹配

	public static final String FUZZY_START = "3"; // 关键字开始

	public static final String FUZZY_END = "4"; // 关键字结束

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getId() {
		return get("id");
	}

	public void setActionType(java.lang.String actionType) {
		set("action_type", actionType);
	}

	public java.lang.String getActionType() {
		return get("action_type");
	}

	public void setEventType(java.lang.String eventType) {
		set("event_type", eventType);
	}

	public java.lang.String getEventType() {
		return get("event_type");
	}

	public void setExtType(java.lang.String extType) {
		set("ext_type", extType);
	}

	public java.lang.String getExtType() {
		return get("ext_type");
	}

	public void setInTime(java.util.Date inTime) {
		set("in_time", inTime);
	}

	public java.util.Date getInTime() {
		return get("in_time");
	}

	public void setKeyWord(java.lang.String keyWord) {
		set("key_word", keyWord);
	}

	public java.lang.String getKeyWord() {
		return get("key_word");
	}

	public void setFuzzy(java.lang.Integer fuzzy) {
		set("fuzzy", fuzzy);
	}

	public java.lang.Integer getFuzzy() {
		return get("fuzzy");
	}

	public void setReqType(java.lang.String reqType) {
		set("req_type", reqType);
	}

	public java.lang.String getReqType() {
		return get("req_type");
	}

	public void setAppId(java.lang.String appId) {
		set("app_id", appId);
	}

	public java.lang.String getAppId() {
		return get("app_id");
	}

	public void setMaterialId(java.lang.String materialId) {
		set("material_id", materialId);
	}

	public java.lang.String getMaterialId() {
		return get("material_id");
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
