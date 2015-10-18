package com.fengjx.ttwx.common.system.security.shiro.session;

import com.fengjx.ttwx.common.utils.CommonUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;

/**
 * session id 生成器
 *
 * @author fengjx
 * @version 2015-10-17
 */
public class IdGen implements SessionIdGenerator {

	@Override
	public Serializable generateId(Session session) {
		return CommonUtils.getPrimaryKey();
	}
	
}
