
package com.fengjx.wechat.base.model;

import com.fengjx.common.bean.ToStringBase;

import java.util.Date;

public class Material extends ToStringBase {

    private static final long serialVersionUID = 112406899561056879L;
    private String id;
    private Date inTime;
    private String msgType;
    private String userId;
    private String xmlData;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = ((id == null) ? null : id.trim());
    }

    public Date getInTime() {
        return this.inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public String getMsgType() {
        return this.msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = ((msgType == null) ? null : msgType.trim());
    }

    public String getUserId()
    {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = ((userId == null) ? null : userId.trim());
    }

    public String getXmlData()
    {
        return this.xmlData;
    }

    public void setXmlData(String xmlData) {
        this.xmlData = ((xmlData == null) ? null : xmlData.trim());
    }
}
