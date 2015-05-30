
package com.fengjx.ttwx.modules.wechat.model;

import com.fengjx.ttwx.common.db.Mapper;
import com.fengjx.ttwx.common.db.Model;
import com.fengjx.ttwx.common.db.Record;
import com.fengjx.ttwx.common.utils.CommonUtils;
import com.fengjx.ttwx.modules.common.bean.BootstrapPage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author fengjx.
 * @date：2015/5/15 0015
 */
@Mapper(table = "wechat_req_msg_log")
@Component
public class ReqMsgLog extends Model {

    @Autowired
    private WechatPublicAccount publicAccount;

    /**
     * 分页查询
     *
     * @param attrs
     * @return
     */
    public BootstrapPage pageList(Map<String, String> attrs, String userId) {
        Record accounRecordt = publicAccount.getPublicAccountByUserid(userId);
        List<Object> params = new ArrayList<Object>();
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
            params.add(CommonUtils.string2Date(attrs.get("start_time") + " 00:00:00"));
        }
        if (StringUtils.isNotBlank(attrs.get("end_time"))) {
            sql.append(" and l.in_time < ?");
            params.add(CommonUtils.string2Date(attrs.get("end_time") + " 23:59:59"));
        }
        sql.append(" order by l.in_time desc");
        return new BootstrapPage(paginate(sql.toString(), params.toArray()));
    }

}
