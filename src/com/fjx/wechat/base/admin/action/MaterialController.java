package com.fjx.wechat.base.admin.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fjx.wechat.base.admin.entity.MaterialEntity;
import com.fjx.wechat.base.admin.service.MaterialService;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fjx.common.action.BaseController;
import com.fjx.common.action.MyExecuteCallback;
import com.fjx.common.framework.system.pagination.Pagination;
import com.fjx.wechat.base.admin.entity.SysUserEntity;


/**
 * 素材管理
 * @author fengjx xd-fjx@qq.com
 * @date 2014年9月12日
 */
@Controller
@RequestMapping("/admin/material")
public class MaterialController extends BaseController {
	
	@Autowired
	private MaterialService materialService;
	
	@RequestMapping(value="")
	public String view (HttpServletRequest request){
		return "/wechat/admin/material/material";
	}
	
	@RequestMapping("/multiple")
	public ModelAndView multiple (String id){
		ModelAndView mv = new ModelAndView("/wechat/admin/material/multiple_news");
		mv.addObject("id",id);
		return mv;
	}
	
	@RequestMapping("/single")
	public ModelAndView single (String id){
		ModelAndView mv = new ModelAndView("/wechat/admin/material/single_news");
		mv.addObject("id",id);
		return mv;
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addOrUpdate (HttpServletRequest request, MaterialEntity material, String contentsJson) throws Exception {
		setErrorMsg(request,"素材编辑失败");
		SysUserEntity sysUser = getLoginSysUser(request);
		material.setSysUser(sysUser);
		List<Map<String,String>> contents = StringUtils.isNotBlank(contentsJson) ? (List<Map<String,String>>) JSONUtil.deserialize(contentsJson) : null;
		materialService.saveOrUpdate(material, contents);
		return retSuccess();
	}
	
	@RequestMapping(value="/page")
	@ResponseBody
	public Pagination<MaterialEntity> page (HttpServletRequest request, String msg_type) throws Exception{
		Pagination<MaterialEntity> page = materialService.getListPageByType(msg_type, getLoginSysUser(request));
		return page;
	}
	
	@RequestMapping(value="/load")
	@ResponseBody
	public MaterialEntity load (String id) throws Exception{
		MaterialEntity entity = materialService.load(id);
		return entity;
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	public Map<String, String> delete (HttpServletRequest request, String id) throws Exception{
		setErrorMsg(request,"删除失败");
		materialService.delete(id);
		return retSuccess();
	}
	
}
