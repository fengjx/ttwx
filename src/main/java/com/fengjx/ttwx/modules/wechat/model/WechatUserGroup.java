
package com.fengjx.ttwx.modules.wechat.model;

import com.fengjx.ttwx.common.plugin.db.Mapper;
import com.fengjx.ttwx.common.plugin.db.Model;
import com.fengjx.ttwx.common.utils.CommonUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 粉丝分组
 *
 * @author fengjx.
 * @date：2015/6/19 0019
 */
@Mapper(table = "wechat_user_group", id = "id")
@Component
public class WechatUserGroup extends Model {

    /**
     * 根据用户查询粉丝分组
     *
     * @param userId
     * @return
     */
    public List<Map<String, Object>> list(String userId) {
        StringBuilder sql = new StringBuilder(getSelectSql());
        sql.append(" where user_id = ?");
        return findList(sql.toString(), userId);
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
