package com.fjx.wechat.base.web.admin.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.fjx.wechat.base.web.admin.entity.MaterialEntity;
import com.fjx.wechat.base.web.admin.entity.SysUserEntity;
import com.fjx.wechat.base.web.admin.service.MaterialService;



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
	public Map<String, String> addOrUpdate (final HttpServletRequest request, final MaterialEntity material,final String contentsJson) {
		return doResult(new MyExecuteCallback() {
			@Override
			public void execute() throws Exception {
				SysUserEntity sysUser = getLoginSysUser(request);
				material.setSysUser(sysUser);
				List<Map<String,String>> contents = StringUtils.isNotBlank(contentsJson) ? (List<Map<String,String>>) JSONUtil.deserialize(contentsJson) : null;
				materialService.saveOrUpdate(material, contents);
			}
		}, "素材编辑失败");
	}
	
	@RequestMapping(value="/page")
	@ResponseBody
	public Pagination<MaterialEntity> page (final HttpServletRequest request, String msg_type) throws Exception{
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
	public Map<String, String> delete (final String id) throws Exception{
		return doResult(new MyExecuteCallback() {
			@Override
			public void execute() throws Exception {
				materialService.delete(id);
			}
		}, "删除失败");
	}
	
}
