
package com.fengjx.ttwx.modules.wechat.model;

import com.fengjx.ttwx.common.plugin.db.Mapper;
import com.fengjx.ttwx.common.plugin.db.Model;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 扩展应用
 *
 * @author fengjx.
 * @date：2015/5/30 0030
 */
@Mapper(table = "wechat_ext_app")
@Component
public class ExtApp extends Model {

    /**
     * @param app_type 应用类型 web、api、restful
     * @param msg_type
     * @param event_type
     * @return
     */
    public List<Map<String, Object>> listByType(String app_type, String msg_type, String event_type) {
        if (StringUtils.isBlank(app_type)) {
            throw new IllegalArgumentException("app_type不能为空！");
        }
        List<Object> params = new ArrayList<Object>();
        params.add(app_type);
        StringBuilder sql = new StringBuilder(getSelectSql("e"));
        if (StringUtils.isNotBlank(msg_type)) {
            sql.append(" inner join wechat_ext_app_support_type s on s.msg_type = ?");
            params.add(msg_type);
            if (StringUtils.isNotBlank(event_type)) {
                sql.append("and s.event_type = ?");
                params.add(event_type);
            } else {
                sql.append(" and (s.event_type = '' or s.event_type is null)");
            }
        }
        sql.append(" where e.app_type = ?");
        return findList(sql.toString(), params.toArray());
    }

}
