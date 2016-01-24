
package com.fengjx.modules.wechat.service;

import com.fengjx.commons.plugin.db.Model;
import com.fengjx.commons.plugin.db.Page;
import com.fengjx.commons.plugin.db.Record;
import com.fengjx.commons.utils.DateUtils;
import com.fengjx.modules.wechat.bean.WechatReqMsgLog;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Autu Generated .
 */
@Component
public class WechatReqMsgLogService extends Model<WechatReqMsgLog> {

    @Autowired
    private WechatPublicAccountService publicAccountService;

    /**
     * 分页查询
     *
     * @param attrs
     * @return
     */
    public Page<Map<String, Object>> pageList(Map<String, String> attrs, String userId) {
        Record accounRecordt = publicAccountService.getAccountByUserId(userId);
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder(getSelectSql());
        sql.append(" l where l.public_account_id = ?");
        params.add(accounRecordt.getStr("id"));
        if (StringUtils.isNotBlank(attrs.get("from_user_name"))) {
            sql.append(" and l.from_user_name = ? ");
            params.add(attrs.get("from_user_name"));
        }
        if (StringUtils.isNotBlank(attrs.get("req_type"))) {
            sql.append(" and l.req_type = ? ");
            params.add(attrs.get("req_type"));
        }
        if (StringUtils.isNotBlank(attrs.get("event_type"))) {
            sql.append(" and l.event_type = ? ");
            params.add(attrs.get("event_type"));
        }
        if (StringUtils.isNotBlank(attrs.get("start_time"))) {
            sql.append(" and l.in_time > ?");
            params.add(DateUtils.parseDate(attrs.get("start_time") + " 00:00:00"));
        }
        if (StringUtils.isNotBlank(attrs.get("end_time"))) {
            sql.append(" and l.in_time < ?");
            params.add(DateUtils.parseDate(attrs.get("end_time") + " 23:59:59"));
        }
        sql.append(" order by l.in_time desc");
        return paginate(sql.toString(), params.toArray());
    }

}
