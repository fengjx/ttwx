package com.fjx.wechat.base.web.display.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fjx.common.action.BaseController;




/**
 * 自定义百度编辑器上传控制器
 * @author fengjx xd-fjx@qq.com
 * @date 2014年10月19日
 */
@RequestMapping("/ueditor")
public class MyUeditorUploadController extends BaseController {
	
	
	@RequestMapping(value="/imageUpload")
	@ResponseBody
	public Map<String, String> imageUpload(){
		Map<String, String> res = new HashMap<String, String>();
		
		
		
		
		return res;
	}
	
	
	
}
