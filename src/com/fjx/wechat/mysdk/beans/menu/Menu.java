package com.fjx.wechat.mysdk.beans.menu;

import java.util.ArrayList;
import java.util.List;

import com.fjx.common.bean.ToStringBase;


/**
* 菜单
* 菜单对象包含多个菜单项（最多只能有3个）。
* 这些菜单项即可以是子菜单项（不含二级菜单的一级菜单），也可以是父菜单项（包含二级菜单的菜单项）
* @author fengjx
* @date 2014年1月22日
*/
public class Menu extends ToStringBase {
	
	private static final long serialVersionUID = -6411323038257850413L;
	
	
	List<Button> button;

	public List<Button> getButton() {
		return button;
	}

	public void setButton(List<Button> button) {
		this.button = button;
	}
	
	
	public Menu addButton(Button _button){
		if(null == button){
			button = new ArrayList<Button>();
		}
		button.add(_button);
		return this;
	}
	
//    private Button[] button;
// 
//    public Button[] getButton() {
//        return button;
//    }
// 
//    public void setButton(Button[] button) {
//        this.button = button;
//    }
}