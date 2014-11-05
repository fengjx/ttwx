package com.fjx.wechat.base.web.display.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fjx.common.action.BaseController;
import com.fjx.common.action.MyExecuteCallback;
import com.fjx.wechat.base.web.display.entity.GuestBookEntity;
import com.fjx.wechat.base.web.display.service.GuestBookService;


/**
 * 
 * @author fengjx xd-fjx@qq.com
 * @date 2014年11月4日
 */
@Controller
@RequestMapping("/contact")
public class ContactConyroller extends BaseController {
	
	
	@Autowired
	private GuestBookService guestBookService;
	
	@RequestMapping(value="/guestbook/save", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> save(HttpServletRequest request, final GuestBookEntity guestBook){
		Map<String, String> resMap = new HashMap<String, String>();
		if(StringUtils.isBlank(guestBook.getName())){
			resMap.put("code", "0");
			resMap.put("msg", "姓名不能为空");
			return resMap;
		}
		if(StringUtils.isBlank(guestBook.getEmail())){
			resMap.put("code", "0");
			resMap.put("msg", "邮箱不能为空");
			return resMap;
		}
		if(StringUtils.isBlank(guestBook.getMsg())){
			resMap.put("code", "0");
			resMap.put("msg", "发送信息不能为空");
			return resMap;
		}
		return doResult(new MyExecuteCallback() {
			@Override
			public void execute() throws Exception {
				guestBook.setIn_time(new Date());
				guestBookService.save(guestBook);
			}
		}, "提交失败");
	}
	
	
	
}
