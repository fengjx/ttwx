package com.fjx.wechat.base.admin.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fjx.wechat.base.admin.entity.MsgTemplateEntity;
import com.fjx.wechat.base.admin.service.MsgTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fjx.common.action.BaseController;
import com.fjx.common.action.MyExecuteCallback;


/**
 * 消息模板
 * @author fengjx xd-fjx@qq.com
 * @date 2014年11月7日
 */
@Controller
@RequestMapping("/admin/template")
public class MsgTemplateController extends BaseController {
	
	@Autowired
	private MsgTemplateService msgTemplateService;
	
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> save(HttpServletRequest request, MsgTemplateEntity msgTemplate){
		msgTemplateService.saveOrUpdate(msgTemplate);
		return retSuccess();
	}
	
}
