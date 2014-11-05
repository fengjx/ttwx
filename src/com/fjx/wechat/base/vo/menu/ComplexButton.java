package com.fjx.wechat.base.vo.menu;

import java.util.ArrayList;
import java.util.List;

/**
* 复杂按钮（父按钮）
* 父菜单项：包含有二级菜单项的一级菜单。
* 这类菜单项包含有二个属性：name和sub_button，而sub_button以是一个子菜单项数组
* @author fengjx
* @date 2014年1月22日
*/
public class ComplexButton extends Button {
	
	//子菜单数组
	List<Button> sub_button;

	public List<Button> getSub_button() {
		return sub_button;
	}

	public void setSub_button(List<Button> sub_button) {
		this.sub_button = sub_button;
	}
	
	public ComplexButton addButton(Button button){
		if(null == sub_button){
			sub_button = new ArrayList<Button>();
		}
		sub_button.add(button);
		return this;
	}
	
	
//    private Button[] sub_button;
// 
//    public Button[] getSub_button() {
//        return sub_button;
//    }
// 
//    public void setSub_button(Button[] sub_button) {
//        this.sub_button = sub_button;
//    }
}
