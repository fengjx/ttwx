package com.fjx.wechat.base.web.admin.action;

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
import com.fjx.common.framework.system.pagination.Pagination;
import com.fjx.common.utils.CollectionUtil;
import com.fjx.common.utils.RequestUtil;
import com.fjx.wechat.base.constants.WechatRespMsgtypeConstants;
import com.fjx.wechat.base.web.admin.entity.KeyWordActionView;
import com.fjx.wechat.base.web.admin.entity.MaterialEntity;
import com.fjx.wechat.base.web.admin.entity.RespMsgActionEntity;
import com.fjx.wechat.base.web.admin.entity.SysUserEntity;
import com.fjx.wechat.base.web.admin.entity.WechatMenuEntity;
import com.fjx.wechat.base.web.admin.service.ExtAppService;
import com.fjx.wechat.base.web.admin.service.MaterialService;
import com.fjx.wechat.base.web.admin.service.RespMsgActionService;
import com.fjx.wechat.base.web.admin.service.WechatMenuService;



/**
 * 消息相应规则控制器
 * @author fengjx xd-fjx@qq.com
 * @version RespMsgActionController.java 2014年10月4日
 */
@Controller
@RequestMapping("/admin/action")
public class RespMsgActionController extends BaseController {
	
	@Autowired
	private RespMsgActionService actionService;
	@Autowired
	private WechatMenuService wechatMenuService;
	@Autowired
	private MaterialService materialService;
	@Autowired
	private ExtAppService extAppService;
	
	/**
	 * 关键字回复界面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/keyword")
	public String keyword(HttpServletRequest request){
		return "/wechat/admin/msg_action/keyword_action";
	}
	
	/**
	 * 默认消息回复界面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/default")
	public String defaultAction(HttpServletRequest request){
		return "/wechat/admin/msg_action/default_action";
	}
	
	/**
	 * 粉丝关注回复界面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/subscribe")
	public String subscribe(HttpServletRequest request){
		return "/wechat/admin/msg_action/subscribe_action";
	}
	
	/**
	 * LBS地理位置消息回复
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/lbs")
	public String lbs(HttpServletRequest request){
		return "/wechat/admin/msg_action/lbs_action";
	}
	
	/**
	 * 
	 * @param request
	 * @param actionEntity
	 * @return
	 */
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> save(final HttpServletRequest request, final RespMsgActionEntity actionEntity){
		final Map<String, String> reqMap = RequestUtil.getRequestParams(request);
		final MaterialEntity materialEntity = getMaterial(reqMap);
		final SysUserEntity sysUser = getLoginSysUser(request);
		if(null != materialEntity){
			materialEntity.setSysUser(getLoginSysUser(request));
			actionEntity.setMaterial(materialEntity);
		}
		actionEntity.setExtApp(extAppService.load(reqMap.get("extAppId")));
		actionEntity.setSysUser(sysUser);
		return doResult(new MyExecuteCallback() {
			@Override
			public void execute() throws Exception {
				if(StringUtils.isNotBlank(actionEntity.getId())){
					actionService.updateAction(actionEntity, getWechatMenu(reqMap), materialEntity);
				}else{
					actionService.saveAction(actionEntity, getWechatMenu(reqMap), materialEntity);
				}
			}
		}, "保存失败！");
	}
	
	/**
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/pageList")
	@ResponseBody
	public Pagination<KeyWordActionView> pageList(HttpServletRequest request){
		SysUserEntity sysUser = getLoginSysUser(request);
		Pagination<KeyWordActionView> pagination = actionService.pageMsgAction(RequestUtil.getRequestParams(request),sysUser);
		return pagination;
	}
	
	@RequestMapping(value="load")
	@ResponseBody
	public RespMsgActionEntity loadMsgAction(HttpServletRequest request, String ext_type, String req_type, String event_type, String key_word){
		return actionService.loadMsgAction(ext_type, req_type, event_type, key_word,getLoginSysUser(request));
	}
	
	/**
	 * 从请求参数中获得菜单对象
	 * @param reqMap
	 * @return
	 */
	private WechatMenuEntity getWechatMenu(Map<String, String> reqMap){
		WechatMenuEntity entity = null;
		if(CollectionUtil.isNotEmpty(reqMap) && StringUtils.isNotBlank(reqMap.get("menuId"))){
			entity = wechatMenuService.load(reqMap.get("menuId"));
			entity.setType(reqMap.get("menuType"));
			entity.setUrl(reqMap.get("menuUrl"));
		}
		return entity;
	}
	
	/**
	 * 从请求参数中获得素材对象
	 * @param reqMap
	 * @return
	 */
	private MaterialEntity getMaterial(Map<String, String> reqMap){
		MaterialEntity entity = null;
		if(CollectionUtil.isNotEmpty(reqMap) && StringUtils.isNotBlank(reqMap.get("materiaId"))){
			entity = materialService.load(reqMap.get("materiaId"));
		}
		if(CollectionUtil.isNotEmpty(reqMap) && StringUtils.isNotBlank(reqMap.get("materiaContent"))){
			entity = new MaterialEntity();
			entity.setContent(reqMap.get("materiaContent"));
			entity.setMsg_type(WechatRespMsgtypeConstants.RESP_MESSAGE_TYPE_TEXT);
		}
		return entity;
	}
}
