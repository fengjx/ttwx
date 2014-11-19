package com.fjx.wechat.base.admin.action;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fjx.common.action.BaseController;
import com.fjx.common.action.MyExecuteCallback;
import com.fjx.common.utils.CommonUtils;
import com.fjx.common.utils.PasswordUtil;
import com.fjx.wechat.base.admin.entity.SysUserEntity;
import com.fjx.wechat.base.admin.entity.WechatPublicAccountEntity;
import com.fjx.wechat.base.admin.service.SysUserService;
import com.fjx.wechat.base.admin.service.WechatPublicAccountService;
import com.fjx.wechat.config.AppConfig;



/**
 * 配置授权
 * @author fengjx xd-fjx@qq.com
 * @version SettingController.java 2014年10月5日
 */
@Controller
@RequestMapping("/admin/setting")
public class SettingController extends BaseController {
	
	@Autowired
	private WechatPublicAccountService publicAccountService;
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping(value="")
	public String view (HttpServletRequest request, Map<String, Object> map){
		SysUserEntity sysUser = getLoginSysUser(request);
		WechatPublicAccountEntity accountEntity = publicAccountService.getWechatPublicAccountBySysUserId(sysUser.getId());
		if(null == accountEntity){
			accountEntity = new WechatPublicAccountEntity();
			accountEntity.setSysUser(sysUser);
			String token = CommonUtils.getPrimaryKey();
			String ticket = CommonUtils.getPrimaryKey();
			accountEntity.setValid_code(CommonUtils.getRandomNum(5));
			accountEntity.setValid_state(WechatPublicAccountEntity.VALID_STATE_NONACTIVATED);
			accountEntity.setIn_time(new Date());
			accountEntity.setToken(token);
			accountEntity.setTicket(ticket);
			accountEntity.setUrl(AppConfig.DOMAIN_PAGE+"/wechat/api?ticket="+PasswordUtil.encode(accountEntity.getTicket()));
			publicAccountService.save(accountEntity);
			//刷新session的用户登录信息
			sysUser.setWechatPublicAccount(publicAccountService.load(accountEntity.getId()));
			request.getSession().setAttribute(AppConfig.LOGIN_FLAG, sysUser);
		}
		sysUser.setWechatPublicAccount(accountEntity);
		map.put("wechatAccount", sysUser.getWechatPublicAccount());
		return "wechat/admin/setting/setting";
	}

	/**
	 * 更新 / 保存菜单
	 * @param request
	 * @param accountEntity
	 * @return
	 */
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addOrUpdate (final HttpServletRequest request, final WechatPublicAccountEntity accountEntity) {
		setErrorMsg(request,"授权失败");
		final SysUserEntity sysUser = getLoginSysUser(request);
		accountEntity.setSysUser(sysUser);

		String token = CommonUtils.getPrimaryKey();
		String ticket = CommonUtils.getPrimaryKey();
		accountEntity.setValid_code(CommonUtils.getRandomNum(5));
		accountEntity.setValid_state(WechatPublicAccountEntity.VALID_STATE_NONACTIVATED);
		accountEntity.setIn_time(new Date());
		accountEntity.setToken(token);
		accountEntity.setTicket(ticket);
		accountEntity.setUrl(AppConfig.DOMAIN_PAGE+"/wechat/api?ticket="+PasswordUtil.encode(accountEntity.getTicket()));
		publicAccountService.update(accountEntity);
		//刷新session的用户登录信息
		sysUser.setWechatPublicAccount(publicAccountService.load(accountEntity.getId()));
		request.getSession().setAttribute(AppConfig.LOGIN_FLAG, sysUser);
		return retSuccess();
	}
}
