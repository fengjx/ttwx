
package com.fjx.wechat.base.admin.service;

import com.fjx.common.framework.base.service.impl.BaseAbstractService;
import com.fjx.common.framework.system.pagination.Pagination;
import com.fjx.common.utils.CommonUtils;
import com.fjx.wechat.base.admin.entity.WechatPublicAccountEntity;
import com.fjx.wechat.base.admin.entity.WechatUserEntity;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 微信用户管理
 * 
 * @author fengjx xd-fjx@qq.com
 * @date 2014年11月2日
 */
@Service
public class WechatUserServiceImpl extends BaseAbstractService<WechatUserEntity> implements
        WechatUserService {

    /*
     * (non-Javadoc)
     * @see
     * com.fjx.wechat.base.web.admin.service.WechatUserService#pageList(com.
     * fjx.wechat.base.web.admin.entity.WechatUserEntity,
     * com.fjx.wechat.base.web.admin.entity.WechatPublicAccountEntity)
     */
    @Override
    public Pagination<WechatUserEntity> pageList(WechatUserEntity user, String group_id,
            WechatPublicAccountEntity publicAccoun) {
        StringBuilder hql = new StringBuilder(
                " from WechatUserEntity u where u.publicAccountEntity = ? ");
        List<Object> params = new ArrayList<Object>();
        params.add(publicAccoun);
        if (StringUtils.isNotBlank(user.getOpenid())) {
            hql.append(" and u.openid = ?");
            params.add(user.getOpenid());
        }
        if (StringUtils.isNotBlank(user.getStart_time())) {
            hql.append(" and u.subscribe_time > ?");
            params.add(CommonUtils.string2Date(user.getStart_time() + " 00:00:00"));
        }
        if (StringUtils.isNotBlank(user.getEnd_time())) {
            hql.append(" and u.subscribe_time < ?");
            params.add(CommonUtils.string2Date(user.getEnd_time() + " 23:59:59"));
        }
        if ("".equals(group_id)) { // 空字符串表示查未分组用户
            hql.append(" and (u.wechatUserGroupEntity.id = null or u.wechatUserGroupEntity.id = '') ");
        } else if (group_id != null) {
            hql.append(" and u.wechatUserGroupEntity.id = ?");
            params.add(group_id);
        }
        return pageByHql(hql.toString(), params);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.fjx.wechat.base.web.admin.service.WechatUserService#updateGroup(java
     * .lang.String, java.lang.String)
     */
    @Override
    public void updateGroup(String user_id, String group_id) {
        StringBuilder hql = new StringBuilder(
                "update WechatUserEntity u set u.wechatUserGroupEntity.id = ?");
        hql.append(" where u.id = ?");
        bulkUpdate(hql.toString(), true, group_id, user_id);
    }

}
