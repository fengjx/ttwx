package com.fjx.wechat.base.admin.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fjx.wechat.base.admin.service.WechatUserGroupService;
import com.fjx.wechat.base.admin.service.WechatUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fjx.common.action.BaseController;
import com.fjx.common.action.MyExecuteCallback;
import com.fjx.common.framework.system.pagination.Pagination;
import com.fjx.wechat.base.admin.entity.SysUserEntity;
import com.fjx.wechat.base.admin.entity.WechatUserEntity;
import com.fjx.wechat.base.admin.entity.WechatUserGroupEntity;


/**
 * 微信用户管理
 * @author fengjx xd-fjx@qq.com
 * @date 2014年11月2日
 */
@Controller
@RequestMapping("/admin/user")
public class WechatUserAction extends BaseController {
	
	@Autowired
	private WechatUserService wechatUserService;
	@Autowired
	private WechatUserGroupService wechatUserGroupService;
	
	@RequestMapping(value="")
	public String view(HttpServletRequest request){
		return "wechat/admin/user/user";
	}
	
	@RequestMapping(value="/groupList")
	@ResponseBody
	public List<WechatUserGroupEntity> groupList(HttpServletRequest request){
		return wechatUserGroupService.findList();
	}
	
	
	@RequestMapping(value="/userList")
	@ResponseBody
	public Pagination<WechatUserEntity> userList(HttpServletRequest request,WechatUserEntity wechatUser, String group_id){
		SysUserEntity sysUser = getLoginSysUser(request);
		return wechatUserService.pageList(wechatUser,group_id, sysUser.getWechatPublicAccount());
	}


	/**
	 * 保存粉丝分组
	 * @param request
	 * @param userGroup
	 * @return
	 */
	@RequestMapping(value="/saveGroup")
	@ResponseBody
	public Map<String, String> saveGroup(HttpServletRequest request, WechatUserGroupEntity userGroup){
		setErrorMsg(request,"操作失败");
		userGroup.setIn_time(new Date());
		if(StringUtils.isBlank(userGroup.getId())){
			wechatUserGroupService.save(userGroup);
		}else{
			wechatUserGroupService.update(userGroup);
		}
		return retSuccess();
	}

	/**
	 * 删除粉丝分组
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteGroup")
	@ResponseBody
	public Map<String, String> deleteGroup(HttpServletRequest request,String id){
		setErrorMsg(request,"操作失败");
		wechatUserGroupService.delete(id);
		return retSuccess();
	}

	/**
	 * 更新粉丝分组
	 * @param request
	 * @param user_id
	 * @param group_id
	 * @return
	 */
	@RequestMapping(value="/updateUser")
	@ResponseBody
	public Map<String, String> saveUser(HttpServletRequest request,String user_id, String group_id){
		setErrorMsg(request,"操作失败");
		wechatUserService.updateGroup(user_id, group_id);
		return retSuccess();
	}
	

}
