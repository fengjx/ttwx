
package com.fengjx.modules.sys.bean;

import com.fengjx.commons.plugin.db.BaseBean;
import com.fengjx.commons.plugin.db.annotation.Mapper;
import com.fengjx.modules.common.constants.AppConfig;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Set;

/**
 * Autu Generated, do not modify this file.
 */
@Mapper(table = "sys_user", id = "id")
@SuppressWarnings("serial")
public class SysUser extends BaseBean {

    public static final String IS_ALIVE = "1"; // 激活
    public static final String NOT_ALIVE = "0"; // 未激活
    public static final String FREEZE_ALIVE = "-1"; // 锁定

    private Set<Map<String, Object>> roles; // 用户拥有的角色列表

    public void setId(java.lang.String id) {
        set("id", id);
    }

    public java.lang.String getId() {
        return get("id");
    }

    public void setEmail(java.lang.String email) {
        set("email", email);
    }

    public java.lang.String getEmail() {
        return get("email");
    }

    public void setInTime(java.util.Date inTime) {
        set("in_time", inTime);
    }

    public java.util.Date getInTime() {
        return get("in_time");
    }

    public void setIsValid(java.lang.String isValid) {
        set("is_valid", isValid);
    }

    public java.lang.String getIsValid() {
        return get("is_valid");
    }

    public void setPhoneNo(java.lang.String phoneNo) {
        set("phone_no", phoneNo);
    }

    public java.lang.String getPhoneNo() {
        return get("phone_no");
    }

    public void setPwd(java.lang.String pwd) {
        set("pwd", pwd);
    }

    public java.lang.String getPwd() {
        return get("pwd");
    }

    public void setSalt(java.lang.String salt) {
        set("salt", salt);
    }

    public java.lang.String getSalt() {
        return get("salt");
    }

    public void setScore(java.lang.Integer score) {
        set("score", score);
    }

    public java.lang.Integer getScore() {
        return get("score");
    }

    public void setUsername(java.lang.String username) {
        set("username", username);
    }

    public java.lang.String getUsername() {
        return get("username");
    }

    public void setValidUid(java.lang.String validUid) {
        set("valid_uid", validUid);
    }

    public java.lang.String getValidUid() {
        return get("valid_uid");
    }

    public void setIsAdmin(java.lang.String isAdmin) {
        set("is_admin", isAdmin);
    }

    public java.lang.String getIsAdmin() {
        return get("is_admin");
    }

    public void setRemarks(java.lang.String remarks) {
        set("remarks", remarks);
    }

    public java.lang.String getRemarks() {
        return get("remarks");
    }

    public Set<Map<String, Object>> getRoles() {
        return roles;
    }

    public void setRoles(Set<Map<String, Object>> roles) {
        this.roles = roles;
    }

    public boolean isAdmin() {
        return !StringUtils.isBlank(getIsAdmin()) && AppConfig.YES.equals(getIsAdmin());
    }

}
