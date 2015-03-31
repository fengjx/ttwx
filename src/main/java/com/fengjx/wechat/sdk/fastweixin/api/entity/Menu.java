package com.fengjx.wechat.sdk.fastweixin.api.entity;

import com.fengjx.wechat.sdk.fastweixin.exception.WeixinException;
import com.fengjx.wechat.sdk.fastweixin.util.JsonUtil;

import java.util.List;

/**
 * 菜单对象，包含所有菜单按钮
 * @author peiyu
 */
public class Menu implements Model {

    /**
     * 一级菜单列表，最多3个
     */
    private List<MenuButton> button;

    public List<MenuButton> getButton() {
        return button;
    }

    public void setButton(List<MenuButton> button) {
        if(null == button || button.size() > 3) {
            throw new WeixinException("主菜单最多3个");
        }
        this.button = button;
    }

    @Override
    public String toJsonString() {
        return JsonUtil.toJson(this);
    }
}
