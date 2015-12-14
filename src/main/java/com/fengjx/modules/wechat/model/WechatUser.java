
package com.fengjx.modules.wechat.model;

import com.fengjx.commons.plugin.db.Mapper;
import com.fengjx.commons.plugin.db.Model;
import com.fengjx.commons.plugin.db.page.AdapterPage;
import com.fengjx.commons.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 粉丝管理
 *
 * @author fengjx. @date：2015/6/19 0019
 */
@Mapper(table = "wechat_user_info", id = "id")
@Component
public class WechatUser extends Model {

    /**
     * 分页查询
     *
     * @param attrs
     * @param userId
     * @return
     */
    public AdapterPage pageList(Map<String, String> attrs, String userId) {
        StringBuilder sql = new StringBuilder(getSelectSql("u"));
        sql.append(" where u.public_account_id = (select id from ");
        sql.append(getTableName(PublicAccount.class));
        sql.append(" where sys_user_id = ?) ");
        List<Object> params = new ArrayList<>();
        params.add(userId);
        if (StringUtils.isNotBlank(attrs.get("openid"))) {
            sql.append(" and u.openid = ?");
            params.add(attrs.get("openid"));
        }
        if (StringUtils.isNotBlank(attrs.get("start_time"))) {
            sql.append(" and u.subscribe_time > ?");
            params.add(DateUtils.parseDate(attrs.get("start_time") + " 00:00:00"));
        }
        if (StringUtils.isNotBlank(attrs.get("end_time"))) {
            sql.append(" and u.subscribe_time < ?");
            params.add(DateUtils.parseDate(attrs.get("end_time") + " 23:59:59"));
        }
        if ("".equals(attrs.get("group_id"))) { // 空字符串表示查未分组用户
            sql.append(" and (u.group_id = null or u.group_id = '') ");
        } else if (attrs.get("group_id") != null) {
            sql.append(" and u.group_id = ?");
            params.add(attrs.get("group_id"));
        }
        return paginate(sql.toString(), params.toArray()).convert();
    }

}
