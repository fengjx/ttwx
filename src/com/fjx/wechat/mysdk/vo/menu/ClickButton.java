package com.fjx.wechat.mysdk.vo.menu;

import com.fjx.wechat.base.constants.WechatMenuConstants;
import com.fjx.wechat.base.vo.menu.*;

/**
* 普通按钮（子按钮，）
* 子菜单：没有子菜单的菜单项，有可能是二级菜单项，也有可能是不含二级菜单的一级菜单。
* 这类子菜单项一定会包含三个属性：type、name和key
* click：用户点击click类型按钮后，微信服务器会通过消息接口推送【消息类型】为event的结构给开发者（参考消息接口指南），
* 并且带上按钮中开发者填写的key值，开发者可以通过自定义的key值与用户进行交互
* @author fengjx
* @date 2014年1月22日
*/
public class ClickButton extends com.fjx.wechat.base.vo.menu.Button {
	
    private String type = WechatMenuConstants.TYPE_CLICK;
    private String key;
 
    public String getType() {
        return type;
    }
 
    public String getKey() {
        return key;
    }
 
    public void setKey(String key) {
        this.key = key;
    }
}