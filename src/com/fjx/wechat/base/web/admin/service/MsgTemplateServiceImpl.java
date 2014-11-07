package com.fjx.wechat.base.web.admin.service;

import org.springframework.stereotype.Service;

import com.fjx.common.framework.base.service.impl.BaseAbstractService;
import com.fjx.wechat.base.web.admin.entity.MsgTemplateEntity;

/**
 * 消息模板
 * @author fengjx xd-fjx@qq.com
 * @date 2014年11月7日
 */
@Service
public class MsgTemplateServiceImpl extends BaseAbstractService<MsgTemplateEntity> implements MsgTemplateService {

	@Override
	public MsgTemplateEntity getMsgTemplateByKey(String key) {
		String hql = " from MsgTemplateEntity m where m.msg_key = ?";
		return findOneByHql(hql, key);
	}

}
