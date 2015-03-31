package com.fengjx.wechat.sdk.fastweixin.api.response;

import com.fengjx.wechat.sdk.fastweixin.api.entity.Model;
import com.fengjx.wechat.sdk.fastweixin.util.JsonUtil;

/**
 * 微信API响应报文对象基类
 * @author peiyu
 */
public class BaseResponse implements Model {
    private String errcode;

    private String errmsg;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public final String toJsonString() {
        return JsonUtil.toJson(this);
    }
}
