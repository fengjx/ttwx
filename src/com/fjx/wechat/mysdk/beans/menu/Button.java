package com.fjx.wechat.mysdk.beans.menu;



/**
* 按钮的基类
* 所有一级菜单、二级菜单都共有一个相同的属性name
* @author fengjx
* @date 2014年1月22日
*/
public class Button {
	
    private String name;
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
}
