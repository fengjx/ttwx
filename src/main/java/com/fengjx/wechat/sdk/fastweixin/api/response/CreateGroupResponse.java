package com.fengjx.wechat.sdk.fastweixin.api.response;

/**
 * @author peiyu
 */
public class CreateGroupResponse extends BaseResponse {

    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
