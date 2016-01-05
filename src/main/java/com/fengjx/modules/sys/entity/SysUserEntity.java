
package com.fengjx.modules.sys.entity;

import com.fengjx.commons.bean.ToStringBase;
import com.fengjx.modules.common.constants.AppConfig;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * 系统注册用户
 */
public class SysUserEntity extends ToStringBase {

    private static final long serialVersionUID = 7100428337345813905L;

    public static final String IS_ALIVE = "1"; // 激活
    public static final String NOT_ALIVE = "0"; // 未激活
    public static final String FREEZE_ALIVE = "-1"; // 锁定

    private String id;
    private String valid_uid;
    private String username;
    private String pwd;
    private String salt;
    private String email;
    private String phone_no;
    private int score;
    private Date in_time;
    private String is_valid; // 0：无效；1：有效
    private String is_admin; // 是否是超级管理员
    private String remarks; // 备注

    private Set<Map<String, Object>> roles; // 用户拥有的角色列表

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValid_uid() {
        return valid_uid;
    }

    public void setValid_uid(String valid_uid) {
        this.valid_uid = valid_uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getIn_time() {
        return in_time;
    }

    public void setIn_time(Date in_time) {
        this.in_time = in_time;
    }

    public String getIs_valid() {
        return is_valid;
    }

    public void setIs_valid(String is_valid) {
        this.is_valid = is_valid;
    }

    public Set<Map<String, Object>> getRoles() {
        return roles;
    }

    public void setRoles(Set<Map<String, Object>> roles) {
        this.roles = roles;
    }

    public String getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(String is_admin) {
        this.is_admin = is_admin;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public boolean isAdmin() {
        if (StringUtils.isBlank(getIs_admin())) {
            return false;
        }
        return AppConfig.YES.equals(getIs_admin());
    }

}
