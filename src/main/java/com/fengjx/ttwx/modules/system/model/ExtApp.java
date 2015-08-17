
package com.fengjx.ttwx.modules.system.model;

import com.fengjx.ttwx.common.plugin.db.Mapper;
import com.fengjx.ttwx.common.plugin.db.Model;

import com.fengjx.ttwx.common.utils.CommonUtils;
import me.chanjar.weixin.common.api.WxConsts;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ExtAppSupport extAppSupport;

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
        List<Object> params = new ArrayList();
        params.add(app_type);
        StringBuilder sql = new StringBuilder(getSelectSql("e"));

        if (StringUtils.isNotBlank(msg_type)) {
            sql.append(" left join wechat_ext_app_support_type s on s.msg_type = ?");
            params.add(msg_type);
            if (StringUtils.isNotBlank(event_type)) {
                sql.append("and s.event_type = ?");
                params.add(event_type);
            } else {
                sql.append(" and (s.event_type = '' or s.event_type is null)");
            }
        }
        sql.append(" where e.is_valid = '1' ");
        if (StringUtils.isBlank(app_type)) {
            sql.append(" and e.app_type = ?");
        }
        return findList(sql.toString(), params.toArray());
    }

    /**
     * 保存扩展接口（不包含URL应用）
     */
    public void saveExtApi(Map<String,Object> attrs, String[] msgTypes, String[] eventTypes){
        String apiId = (String)attrs.get("id");
        if(StringUtils.isBlank(apiId)){
            apiId = CommonUtils.getPrimaryKey();
            attrs.put("id",apiId);
        }else {
            StringBuilder delSql = new StringBuilder("delete from ");
            delSql.append(extAppSupport.getTableName());
            delSql.append(" where ext_app_id = ?");
            execute(delSql.toString(),apiId);
        }


    }



}
