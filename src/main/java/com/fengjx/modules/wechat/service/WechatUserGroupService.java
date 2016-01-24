
package com.fengjx.modules.wechat.service;

import com.fengjx.commons.plugin.db.Model;
import com.fengjx.commons.utils.CommonUtils;
import com.fengjx.modules.wechat.bean.WechatUserGroup;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Autu Generated .
 */
@Component
public class WechatUserGroupService extends Model<WechatUserGroup> {

    /**
     * 根据用户查询粉丝分组
     *
     * @param userId
     * @return
     */
    public List<Map<String, Object>> list(String userId) {
        String sql = getSelectSql() + " where user_id = ?";
        return findList(sql, userId);
    }

    public void insertOrUpdate(Map<String, Object> attrs) {
        if (StringUtils.isBlank((String) attrs.get("id"))) {
            attrs.put("id", CommonUtils.getPrimaryKey());
            insert(attrs);
        } else {
            update(attrs);
        }
    }

}
