package com.fjx.wechat.base.admin.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fjx.common.framework.base.service.impl.BaseAbstractService;
import com.fjx.common.framework.system.exception.MyRuntimeException;
import com.fjx.common.framework.system.init.FreeMarkerUtil;
import com.fjx.common.mail.service.MyEmailService;
import com.fjx.common.mail.vo.SendMailVo;
import com.fjx.common.utils.CommonUtils;
import com.fjx.common.utils.MD5Utils;
import com.fjx.common.utils.PasswordUtil;
import com.fjx.wechat.base.constants.FtlFilenameConstants;
import com.fjx.wechat.base.admin.entity.SysUserEntity;
import com.fjx.wechat.config.AppConfig;


/**
 * 系统用户管理
 * @author fengjx xd-fjx@qq.com
 * @version SysUserServiceImpl.java 2014年9月26日
 */
@Service("sysUserService")
public class SysUserServiceImpl extends BaseAbstractService<SysUserEntity> implements SysUserService {
	
	@Autowired
	private MyEmailService emailService;
	
	@Override
	public SysUserEntity signin(String username, String pwd) {
		String hql = "from SysUserEntity where username = ?";
		SysUserEntity sysUser = findOneByHql(hql, username);
		if(null != sysUser && sysUser.getPwd().equals(MD5Utils.md5(pwd))){
			return sysUser;
		}
		return null;
	}

	@Override
	public void register(SysUserEntity user) throws Exception {
		if(validUsername(user.getUsername())){
			throw new MyRuntimeException("用户名已存在");
		}
		if(validEmail(user.getEmail())){
			throw new MyRuntimeException("邮箱已被占用");
		}
		user.setPwd(MD5Utils.md5(user.getPwd()));
		user.setIn_time(new Date());
		user.setIs_valid("0");
		user.setScore(0);
		user.setValid_uid(CommonUtils.getPrimaryKey());
		save(user);
		sendRegisterMail(user);
	}
	
	public void sendRegisterMail(SysUserEntity user) throws Exception{
		SendMailVo mail = new SendMailVo();
		mail.setType(SendMailVo.TYPE_HTML);
		mail.setToUser(user.getEmail());
		mail.setSubject("邮箱验证");
		Map<String, String> root = new HashMap<String, String>();
		root.put("userEmail", user.getEmail());
		root.put("validUrl", AppConfig.DOMAIN_PAGE+"/activate?ser="+PasswordUtil.encode(user.getValid_uid()));
		mail.setContent(FreeMarkerUtil.process(root, FtlFilenameConstants.REGISTER_VALID_MAIN));
		emailService.send(mail);
	}

	@Override
	public boolean validUsername(String username) {
		String hql = "from SysUserEntity u where u.username = ?";
		int total = getCount(hql, true, username);
		return total > 0;
	}

	@Override
	public boolean validEmail(String email) {
		String hql = "from SysUserEntity u where u.email = ?";
		int total = getCount(hql, true, email);
		return total > 0;
	}

	@Override
	public boolean activate(String ser) {
		String uid = PasswordUtil.decode(ser);
		String hql = "from SysUserEntity u where u.valid_uid = ?";
		SysUserEntity user = findOneByHql(hql, uid);
		if(null == user || SysUserEntity.IS_ALIVE.equals(user.getIs_valid())){
			return false;
		}
		user.setIs_valid(SysUserEntity.IS_ALIVE);
		update(user);
		return true;
	}
	
	
}
