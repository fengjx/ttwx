package com.fjx.wechat.base.web.admin.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fjx.common.action.BaseController;




@Controller
public class CommonController extends BaseController {
	
	@RequestMapping("/admin")
	public String view (){
		return "/wechat/admin/main";
	}
	
	@RequestMapping("/admin2")
	public String view2 (){
		return "/wechat/admin/main2";
	}
	
	@RequestMapping("/admin/center")
	public String center (){
		return "/wechat/admin/layout/center";
	}
	
	@RequestMapping("/admin/east")
	public String east (){
		return "/wechat/admin/layout/east";
	}
	
	@RequestMapping("/admin/north")
	public String north (){
		return "/wechat/admin/layout/north";
	}
	
	@RequestMapping("/admin/south")
	public String south (){
		return "/wechat/admin/layout/south";
	}
	
	@RequestMapping("/admin/west")
	public String west (){
		return "/wechat/admin/layout/west";
	}
	
}
