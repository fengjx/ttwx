package com.fjx.wechat.base.web.admin.service;

import com.fjx.common.framework.base.service.IBaseAbstractService;
import com.fjx.wechat.base.web.admin.entity.SysUserEntity;


/**
 * 系统注册用户管理
 * @author fengjx xd-fjx@qq.com
 * @version SysUserService.java 2014年9月26日
 */
public interface SysUserService extends IBaseAbstractService<SysUserEntity> {
	
	/**
	 * 用户登录
	 * @param username
	 * @param pwd
	 * @return
	 */
	public SysUserEntity signin(String username, String pwd);
	/**
	 * 用户注册
	 * @param user
	 */
	public void register(SysUserEntity user) throws Exception;
	/**
	 * 验证用户名是否存在
	 * @param username
	 * @return
	 */
	public boolean validUsername(String username);
	/**
	 * 验证邮箱是否存在
	 * @param username
	 * @return
	 */
	public boolean validEmail(String email);
	
	/**
	 * 激活账户
	 * @param ser 加密后的账号ID
	 * @return
	 */
	public boolean activate(String ser);
	
	
}
