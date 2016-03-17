
package com.fengjx.modules.wechat.bean;

import com.fengjx.commons.plugin.db.BaseBean;
import com.fengjx.commons.plugin.db.annotation.Mapper;

/**
 * Autu Generated, do not modify this file.
 */
@Mapper(table="wechat_ext_app", id = "id")
@SuppressWarnings("serial")
public class WechatExtApp extends BaseBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getId() {
		return get("id");
	}

	public void setAppType(java.lang.String appType) {
		set("app_type", appType);
	}

	public java.lang.String getAppType() {
		return get("app_type");
	}

	public void setAppUrl(java.lang.String appUrl) {
		set("app_url", appUrl);
	}

	public java.lang.String getAppUrl() {
		return get("app_url");
	}

	public void setGroupId(java.lang.String groupId) {
		set("group_id", groupId);
	}

	public java.lang.String getGroupId() {
		return get("group_id");
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

	public void setOrderNo(java.lang.String orderNo) {
		set("order_no", orderNo);
	}

	public java.lang.String getOrderNo() {
		return get("order_no");
	}

	public void setBeanName(java.lang.String beanName) {
		set("bean_name", beanName);
	}

	public java.lang.String getBeanName() {
		return get("bean_name");
	}

	public void setMethodName(java.lang.String methodName) {
		set("method_name", methodName);
	}

	public java.lang.String getMethodName() {
		return get("method_name");
	}

	public void setRestfulUrl(java.lang.String restfulUrl) {
		set("restful_url", restfulUrl);
	}

	public java.lang.String getRestfulUrl() {
		return get("restful_url");
	}

	public void setDescription(java.lang.String description) {
		set("description", description);
	}

	public java.lang.String getDescription() {
		return get("description");
	}

	public void setIsValid(java.lang.Boolean isValid) {
		set("is_valid", isValid);
	}

	public java.lang.Boolean getIsValid() {
		return get("is_valid");
	}

}
