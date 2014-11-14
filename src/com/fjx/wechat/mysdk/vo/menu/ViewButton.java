package com.fjx.wechat.mysdk.vo.menu;

import com.fjx.wechat.base.constants.WechatMenuConstants;
import com.fjx.wechat.base.vo.menu.*;

/**
* 普通按钮（子按钮，视图跳转）
* 子菜单：没有子菜单的菜单项，有可能是二级菜单项，也有可能是不含二级菜单的一级菜单。
* 这类子菜单项一定会包含三个属性：type、name和view
* view：用户点击view类型按钮后，微信客户端将会打开开发者在按钮中填写的url值	（即网页链接），达到打开网页的目的，
* 建议与网页授权获取用户基本信息接口结合，获得用户的登入个人信息。
* @author fengjx
* @date 2014年1月22日
*/
public class ViewButton extends com.fjx.wechat.base.vo.menu.Button {
	
    private String type = WechatMenuConstants.TYPE_VIEW;
    private String url;
 
    public String getType() {
        return type;
    }
 
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
    
}