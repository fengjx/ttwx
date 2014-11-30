package com.fjx.wechat.base.admin.service;

import java.util.ArrayList;
import java.util.List;

import com.fjx.common.framework.system.pagination.Pagination;
import com.fjx.wechat.base.admin.entity.ReqMsgLogEntoty;
import com.fjx.wechat.base.admin.entity.WechatPublicAccountEntity;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.fjx.common.framework.base.service.impl.BaseAbstractService;
import com.fjx.common.utils.CommonUtils;


/**
 * 微信消息请求记录
 * @author fengjx xd-fjx@qq.com
 * @date 2014年10月30日
 */
@Service
public class ReqMsgLogServiceImpl extends BaseAbstractService<ReqMsgLogEntoty> implements ReqMsgLogService {


    /**
     * 分页查询
     * @param reqMsgLog 消息记录
     * @param wechatPublicAccount 公众账号
     * @return
     */
    @Override
    public Pagination<ReqMsgLogEntoty> pageList(ReqMsgLogEntoty reqMsgLog, WechatPublicAccountEntity wechatPublicAccount) {
        List<Object> params = new ArrayList<Object>();
    	StringBuilder hql = new StringBuilder();
        hql.append("from ReqMsgLogEntoty l where l.wechatPublicAccount = ?");
        params.add(wechatPublicAccount);
        if(StringUtils.isNotBlank(reqMsgLog.getFrom_user_name())){
            hql.append(" and l.from_user_name = ? ");
            params.add(reqMsgLog.getFrom_user_name());
        }
        if(StringUtils.isNotBlank(reqMsgLog.getReq_type())){
            hql.append(" and l.req_type = ? ");
            params.add(reqMsgLog.getReq_type());
        }
        if(StringUtils.isNotBlank(reqMsgLog.getEvent_type())){
            hql.append(" and l.event_type = ? ");
            params.add(reqMsgLog.getEvent_type());
        }
        if(StringUtils.isNotBlank(reqMsgLog.getStart_time())){
			hql.append(" and l.in_time > ?");
			params.add(CommonUtils.String2Date(reqMsgLog.getStart_time()+" 00:00:00"));
		}
		if(StringUtils.isNotBlank(reqMsgLog.getEnd_time())){
			hql.append(" and l.in_time < ?");
			params.add(CommonUtils.String2Date(reqMsgLog.getEnd_time()+" 23:59:59"));
		}
        hql.append(" order by l.in_time desc");
        return pageByHql(hql.toString(), params);
    }
}
