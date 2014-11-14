package com.fjx.wechat.base.display.action;

import java.util.Map;

import com.fjx.wechat.base.admin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fjx.common.action.BaseController;
import com.fjx.common.action.MyExecuteCallback;
import com.fjx.wechat.base.admin.entity.SysUserEntity;


@Controller
public class DisplayController extends BaseController {
	
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping(value={"","index"})
	public String view (){
		return "/wechat/display/index";
	}
	
	@RequestMapping("/contact")
	public String contact (){
		return "/wechat/display/contact";
	}
	
	@RequestMapping("/about")
	public String about (){
		return "/wechat/display/about";
	}
	
	/**
	 * 注册页面跳转
	 * @return
	 */
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String registerView (){
		return "/wechat/display/register";
	}
	
	
	/**
	 * 注册数据提交
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/register", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> register (final SysUserEntity user){
		return doResult(new MyExecuteCallback() {
			@Override
			public void execute() throws Exception {
				sysUserService.register(user);
			}
		}, "注册用户失败！");
	}
	
	/**
	 * 注册用户激活
	 * @param ser
	 * @return
	 */
	@RequestMapping(value="/activate")
	public String activate (String ser){
		if(sysUserService.activate(ser)){
			return "/wechat/display/activate-ok";
		}
		return "/wechat/display/activate-error";
	}
	
}
