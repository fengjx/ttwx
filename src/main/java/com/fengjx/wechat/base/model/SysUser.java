
package com.fengjx.wechat.base.model;

import com.fengjx.common.bean.ToStringBase;

import java.util.Date;

public class SysUser extends ToStringBase {

    private static final long serialVersionUID = 1653759724677038192L;
    public static final String IS_ALIVE = "1";
    public static final String NOT_ALIVE = "0";
    private String id;
    private String email;
    private Date inTime;
    private String isValid;
    private String phoneNo;
    private String pwd;
    private Integer score;
    private String username;
    private String validUid;

    public String getId()
    {
        return this.id;
    }

    public void setId(String id) {
        this.id = ((id == null) ? null : id.trim());
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = ((email == null) ? null : email.trim());
    }

    public Date getInTime() {
        return this.inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public String getIsValid() {
        return this.isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = ((isValid == null) ? null : isValid.trim());
    }

    public String getPhoneNo() {
        return this.phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = ((phoneNo == null) ? null : phoneNo.trim());
    }

    public String getPwd() {
        return this.pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = ((pwd == null) ? null : pwd.trim());
    }

    public Integer getScore() {
        return this.score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = ((username == null) ? null : username.trim());
    }

    public String getValidUid() {
        return this.validUid;
    }

    public void setValidUid(String validUid) {
        this.validUid = ((validUid == null) ? null : validUid.trim());
    }
}
