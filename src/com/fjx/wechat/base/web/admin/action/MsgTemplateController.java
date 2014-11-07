package com.fjx.wechat.base.web.admin.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fjx.common.action.BaseController;
import com.fjx.common.action.MyExecuteCallback;
import com.fjx.wechat.base.web.admin.entity.MsgTemplateEntity;
import com.fjx.wechat.base.web.admin.service.MsgTemplateService;



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
	public Map<String, String> save(HttpServletRequest request, final MsgTemplateEntity msgTemplate){
		return doResult(new MyExecuteCallback() {
			@Override
			public void execute() throws Exception {
				msgTemplateService.saveOrUpdate(msgTemplate);
			}
		}, null);
	}
	
}
