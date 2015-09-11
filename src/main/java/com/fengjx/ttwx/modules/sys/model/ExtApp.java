
package com.fengjx.ttwx.modules.sys.model;

import com.fengjx.ttwx.common.plugin.db.Mapper;
import com.fengjx.ttwx.common.plugin.db.Model;
import com.fengjx.ttwx.common.plugin.db.ParamHelper;
import com.fengjx.ttwx.common.plugin.db.page.AdapterPage;
import com.fengjx.ttwx.common.utils.CommonUtils;
import com.fengjx.ttwx.common.utils.DateUtils;
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
 * 扩展应用
 *
 * @author fengjx. @date：2015/5/30 0030
 */
@Mapper(table = "wechat_ext_app")
@Component
public class ExtApp extends Model {

    public static class AppType {
        public static final String TYPE_WEB = "web";

        public static final String TYPE_API = "api";

        public static final String TYPE_RESTFUL = "restful";
    }

    @Autowired
    private ExtAppSupport extAppSupport;

    /**
     * @param app_type 应用类型 web、api、restful
     * @param msg_type
     * @param event_type
     * @return
     */
    public List<Map<String, Object>> listByType(String app_type, String msg_type,
            String event_type) {
        if (StringUtils.isBlank(app_type)) {
            throw new IllegalArgumentException("app_type不能为空！");
        }
        List<Object> params = new ArrayList();
        StringBuilder sql = createListSql();
        if (StringUtils.isNotBlank(app_type)) {
            sql.append(" and e.app_type = ?");
            params.add(app_type);
        }
        if (StringUtils.isNotBlank(msg_type)) {
            sql.append("and e.msg_type = ?");
            params.add(msg_type);
        }
        if (StringUtils.isNotBlank(event_type)) {
            sql.append("and s.event_type = ?");
            params.add(event_type);
        }
        sql.append(" order by order_no desc");
        return findList(sql.toString(), params.toArray());
    }

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    public AdapterPage pageList(ParamHelper params) {
        List<Object> qryParams = new ArrayList();
        StringBuilder sql = createListSql();
        if (null != params.get("is_valid")) {
            sql.append(" and a.is_valid = ?");
            qryParams.add(params.get("is_valid"));
        }
        if (StringUtils.isNotBlank(params.getStr("app_type"))) {
            sql.append(" and a.app_type = ?");
            qryParams.add(params.get("app_type"));
        }
        if (StringUtils.isNoneBlank(params.getStr("name"))) {
            sql.append(" and name like CONCAT('%',?,'%')");
            qryParams.add(params.get("name"));
        }
        if (StringUtils.isNoneBlank(params.getStr("start_time"))) {
            sql.append(" and in_time >= ?");
            qryParams.add(DateUtils.parseDate(params.get("start_time")));
        }
        if (StringUtils.isNoneBlank(params.getStr("end_time"))) {
            sql.append(" and in_time <= ?");
            qryParams.add(DateUtils.parseDate(params.get("end_time")));
        }
        if (StringUtils.isNotBlank(params.getStr("msg_type"))) {
            sql.append("and e.msg_type = ?");
            qryParams.add(params.get("msg_type"));
        }
        if (StringUtils.isNotBlank(params.getStr("event_type"))) {
            sql.append("and s.event_type = ?");
            qryParams.add(params.get("event_type"));
        }
        sql.append(" order by in_time desc, order_no desc");
        return paginate(sql.toString(), qryParams.toArray()).convert();
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
    public void saveExtApi(ParamHelper params, String[] msgTypes, String[] eventTypes) {
        String apiId = params.getStr("id");
        if (StringUtils.isBlank(apiId)) {
            apiId = CommonUtils.getPrimaryKey();
            params.set("id", apiId);
            insert(params.getParams());
        } else {
            update(params.getParams());
        }
        if (AppType.TYPE_API.equals(params.getStr("app_type"))) {
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
        insertSql.append(extAppSupport.getTableName());
        insertSql.append("( ").append(extAppSupport.getColumnsStr()).append(" )");
        insertSql.append(" values (?, ?, ?, ?)");
        List<Object[]> batchArgs = new ArrayList();
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
        delSql.append(extAppSupport.getTableName());
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
