package com.fjx.wechat.base.web.admin.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fjx.common.action.BaseController;
import com.fjx.wechat.base.web.admin.entity.DataDictEntity;
import com.fjx.wechat.base.web.admin.service.DataDictService;



/**
 * 数据字典查询
 * @author fengjx xd-fjx@qq.com
 * @date 2014年11月4日
 */
@Controller
@RequestMapping("/dict")
public class DataDictController extends BaseController {
	
	@Autowired
	private DataDictService dataDictService;
	
	@RequestMapping(value="/list")
	@ResponseBody
	public List<DataDictEntity> getList(String group_code){
		return dataDictService.findDictList(group_code);
	}
	
	@RequestMapping(value="/get")
	@ResponseBody
	public DataDictEntity get(String group_code, String dict_value){
		return dataDictService.findDict(group_code, dict_value);
	}
	
}
