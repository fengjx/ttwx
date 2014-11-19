package com.fjx.wechat.base.admin.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fjx.wechat.base.admin.entity.WechatMenuEntity;
import com.fjx.wechat.base.admin.service.WechatMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fjx.common.action.BaseController;
import com.fjx.common.action.MyExecuteCallback;
import com.fjx.wechat.base.admin.entity.SysUserEntity;


/**
 * 素材管理
 * @author fengjx xd-fjx@qq.com
 * @date 2014年9月12日
 */
@Controller
@RequestMapping("/admin/menu")
public class WechatMenuController extends BaseController {
	
	@Autowired
	private WechatMenuService wechatMenuService;
	
	/**
	 * 菜单管理界面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="")
	public String view (HttpServletRequest request){
		return "/wechat/admin/menu/menu";
	}
	
	/**
	 * 更新 / 保存菜单
	 * @param request
	 * @param menu
	 * @return
	 */
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addOrUpdate (HttpServletRequest request, WechatMenuEntity menu, final String parent_id) {
		setErrorMsg(request,"菜单编辑失败");
		SysUserEntity sysUser = getLoginSysUser(request);
		if(StringUtils.isBlank(menu.getId())){
			wechatMenuService.saveMenu(menu, parent_id, sysUser);
		}else{
			wechatMenuService.updateMenu(menu, parent_id, sysUser);
		}
		return retSuccess();
	}
	
	/**
	 * 加载树形菜单
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/load")
	@ResponseBody
	public List<Map<String, Object>> load (HttpServletRequest request){
		List<Map<String, Object>> tree = wechatMenuService.treeMenu(getLoginSysUser(request));
		return tree;
	}

	/**
	 * 删除菜单
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public Map<String, String> delete (HttpServletRequest request, String id) throws Exception{
		setErrorMsg(request,"菜单删除失败");
		wechatMenuService.delete(id);
		return retSuccess();
	}


	/**
	 * 发布菜单
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/release")
	@ResponseBody
	public Map<String, String> release (HttpServletRequest request) throws Exception{
		SysUserEntity sysUser = getLoginSysUser(request);
		wechatMenuService.release(sysUser);
		return retSuccess();
	}
}
