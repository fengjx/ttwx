package com.fengjx.wechat.sdk.fastweixin.api.request;

import com.fengjx.wechat.sdk.fastweixin.api.entity.Model;
import com.fengjx.wechat.sdk.fastweixin.util.JsonUtil;

/**
 * @author peiyu
 */
public class BaseRequest implements Model {

    public final String toJsonString() {
        return JsonUtil.toJson(this);
    }
}
