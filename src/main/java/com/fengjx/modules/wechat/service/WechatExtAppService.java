
package com.fengjx.modules.wechat.service;

import com.fengjx.commons.plugin.db.Model;
import com.fengjx.commons.plugin.db.Page;
import com.fengjx.commons.plugin.db.Record;
import com.fengjx.commons.utils.CommonUtils;
import com.fengjx.commons.utils.DateUtils;
import com.fengjx.modules.wechat.bean.WechatExtApp;
import me.chanjar.weixin.common.api.WxConsts;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Autu Generated .
 */
@Component
public class WechatExtAppService extends Model<WechatExtApp> {

    public static class AppType {
        public static final String TYPE_WEB = "web";

        public static final String TYPE_API = "api";

        public static final String TYPE_RESTFUL = "restful";
    }

    @Autowired
    private WechatExtAppSupportTypeService extAppSupportService;

    /**
     * 分页查询
     *
     * @param record
     * @return
     */
    public Page<Map<String,Object>> pageList(Record record) {
        List<Object> qryParams = new ArrayList<>();
        StringBuilder sql = createListSql();
        if (null != record.get("is_valid")) {
            sql.append(" and a.is_valid = ?");
            qryParams.add(record.get("is_valid"));
        }
        if (StringUtils.isNotBlank(record.getStr("app_type"))) {
            sql.append(" and a.app_type = ?");
            qryParams.add(record.get("app_type"));
        }
        if (StringUtils.isNoneBlank(record.getStr("name"))) {
            sql.append(" and a.name like CONCAT('%',?,'%')");
            qryParams.add(record.get("name"));
        }
        if (StringUtils.isNoneBlank(record.getStr("start_time"))) {
            sql.append(" and a.in_time >= ?");
            qryParams.add(DateUtils.parseDate(record.get("start_time")));
        }
        if (StringUtils.isNoneBlank(record.getStr("end_time"))) {
            sql.append(" and a.in_time <= ?");
            qryParams.add(DateUtils.parseDate(record.get("end_time")));
        }
        if (StringUtils.isNotBlank(record.getStr("msg_type"))) {
            sql.append(" and e.msg_type = ?");
            qryParams.add(record.get("msg_type"));
        }
        if (StringUtils.isNotBlank(record.getStr("event_type"))) {
            sql.append(" and e.event_type = ?");
            qryParams.add(record.get("event_type"));
        }
        sql.append(" order by in_time desc, order_no desc");
        return paginate(sql.toString(), qryParams.toArray());
    }

    private StringBuilder createListSql() {
        String alias = "a";
        StringBuilder sql = new StringBuilder("select DISTINCT(a.id) d_id,");
        sql.append(getColumnsStr(alias));
        sql.append(
                ",(select GROUP_CONCAT(DISTINCT(msg_type)) msg_types from wechat_ext_app_support_type where ext_app_id = a.id ) msg_types");
        sql.append(
                ",(select GROUP_CONCAT(DISTINCT(event_type)) event_types from wechat_ext_app_support_type where ext_app_id = a.id ) event_types FROM ");
        sql.append(getTableName()).append(" ").append(alias);
        sql.append(" left join wechat_ext_app_support_type e on e.ext_app_id = a.id");
        sql.append(" where 1 = 1 ");
        return sql;
    }

    /**
     * 保存扩展接口（不包含URL应用）
     */
    public void saveExtApi(WechatExtApp extApp, String[] msgTypes, String[] eventTypes) {
        String apiId = extApp.getStr("id");
        if (StringUtils.isBlank(apiId)) {
            apiId = CommonUtils.getPrimaryKey();
            extApp.set("id", apiId);
            insert(extApp);
        } else {
            update(extApp);
        }
        if (AppType.TYPE_API.equals(extApp.getStr("app_type"))) {
            reSaveSupportType(apiId, msgTypes, eventTypes);
        }
    }

    /**
     * 更新接口类型关联
     *
     * @param apiId
     * @param msgTypes
     * @param eventTypes
     */
    @Transactional(propagation = Propagation.REQUIRED)
    private void reSaveSupportType(String apiId, String[] msgTypes, String[] eventTypes) {
        deleteSupportTypeByAppId(apiId);
        StringBuilder insertSql = new StringBuilder("insert into ");
        insertSql.append(extAppSupportService.getTableName());
        insertSql.append("( ").append(extAppSupportService.getColumnsStr()).append(" )");
        insertSql.append(" values (?, ?, ?, ?)");
        List<Object[]> batchArgs = new ArrayList<>();
        Object[] param;
        for (String msgType : msgTypes) {
            if (WxConsts.XML_MSG_EVENT.equals(msgType)) {
                for (String eventType : eventTypes) {
                    param = new Object[4];
                    param[0] = CommonUtils.getPrimaryKey();
                    param[1] = eventType;
                    param[2] = msgType;
                    param[3] = apiId;
                    batchArgs.add(param);
                }
            } else {
                param = new Object[4];
                param[0] = CommonUtils.getPrimaryKey();
                param[1] = null;
                param[2] = msgType;
                param[3] = apiId;
                batchArgs.add(param);
            }
        }
        batchExecute(insertSql.toString(), batchArgs);
    }

    /**
     * 删除api消息类型关联
     *
     * @param appid
     */
    private void deleteSupportTypeByAppId(String appid) {
        StringBuilder delSql = new StringBuilder("delete from ");
        delSql.append(extAppSupportService.getTableName());
        delSql.append(" where ext_app_id = ?");
        execute(delSql.toString(), appid);
    }

    /**
     * 删除应用
     *
     * @param id
     * @param app_type
     */
    public void deleteApp(String id, String app_type) {
        // web应用，没有消息类型关联，不需要删除
        if ("api".equals(app_type) || "restful".equals(app_type)) {
            deleteSupportTypeByAppId(id);
        }
        deleteById(id);
    }

}
