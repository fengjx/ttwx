package com.fjx.wechat.base.admin.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fjx.common.framework.system.pagination.Pagination;
import com.fjx.wechat.base.admin.entity.DataDictEntity;
import com.fjx.wechat.base.admin.service.DataDictService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fjx.common.action.BaseController;

import javax.servlet.http.HttpServletRequest;


/**
 * 数据字典查询
 * @author fengjx xd-fjx@qq.com
 * @date 2014年11月4日
 */
@Controller
public class DataDictController extends BaseController {
	
	@Autowired
	private DataDictService dataDictService;

	@RequestMapping(value={"/admin/dict"})
	public String view(HttpServletRequest request) {
		return "/wechat/admin/sys/dict";
	}

	@RequestMapping(value="/dict/list")
	@ResponseBody
	public List<DataDictEntity> getList(String group_code){
		return dataDictService.findDictList(group_code);
	}

	@RequestMapping(value="/dict/pageList")
	@ResponseBody
	public Pagination<DataDictEntity> getPageList(String group_code){
		return dataDictService.pageList(group_code);
	}

	@RequestMapping(value="/dict/get")
	@ResponseBody
	public DataDictEntity get(String group_code, String dict_value){
		return dataDictService.findDict(group_code, dict_value);
	}

	@RequestMapping(value="/dict/group")
	@ResponseBody
	public List<Map<String, String>> getGroup(){
		return dataDictService.findDictGroup();
	}

	@RequestMapping(value="/admin/dict/save")
	@ResponseBody
	public Map<String, String> save(HttpServletRequest request, DataDictEntity dict){
		String id = dict.getId();
		dict.setIn_time(new Date());
		if (StringUtils.isBlank(id)) {
			dataDictService.save(dict);
		}else {
			dataDictService.update(dict);
		}
		return retSuccess();
	}

	@RequestMapping(value="/admin/dict/delete")
	@ResponseBody
	public Map<String, String> delete(HttpServletRequest request, String id){
		dataDictService.delete(id);
		return retSuccess();
	}

}
