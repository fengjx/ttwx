
package com.fengjx.ttwx.modules.wechat.model;

import com.fengjx.ttwx.common.plugin.db.Mapper;
import com.fengjx.ttwx.common.plugin.db.Model;
import com.fengjx.ttwx.common.plugin.db.Record;
import com.fengjx.ttwx.common.system.exception.MyRuntimeException;
import com.fengjx.ttwx.common.utils.CommonUtils;
import com.fengjx.ttwx.modules.common.bean.BootstrapPage;

import me.chanjar.weixin.common.api.WxConsts;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 消息动作规则
 *
 * @author fengjx.
 * @date：2015/6/5 0005
 */
@Mapper(table = "wechat_resp_msg_action")
@Component
public class RespMsgAction extends Model {

    private static final Logger LOG = LoggerFactory.getLogger(RespMsgAction.class);

    public static final String ACTION_TYPE_MATERIAL = "material";
    public static final String ACTION_TYPE_API = "api";

    /**
     * 保存消息响应规则
     *
     * @param actionMap
     * @param menuMap
     * @param materialMap
     */
    public void saveAction(Map<String, Object> actionMap, Map<String, Object> menuMap,
            Map<String, Object> materialMap) {
        // 菜单参数为空，表示不是菜单动作
        if (MapUtils.isNotEmpty(menuMap)) {
            saveMenuAction(actionMap, menuMap, materialMap);
        } else {
            saveMsgAction(actionMap, materialMap);
        }
    }

    /**
     * 更新消息响应规则
     *
     * @param actionMap
     * @param menuMap
     * @param materialMap
     */
    public void updateAction(Map<String, Object> actionMap, Map<String, Object> menuMap,
            Map<String, Object> materialMap) {
        String action_id = (String) actionMap.get("id");
        if (StringUtils.isNotBlank(action_id)) {
            deleteMsgActionById(action_id);
        }
        saveAction(actionMap, menuMap, materialMap);
    }

    /**
     * 分页查询
     *
     * @params params 查询参数
     * @param userId 所属用户
     * @return
     */
    public BootstrapPage pageMsgAction(Map<String, String> params, String userId) {
        List<Object> parameters = new ArrayList();
        StringBuilder sql = new StringBuilder(detailSql());
        sql.append(" where a.user_id = ? ");
        parameters.add(userId);
        if (StringUtils.isNotBlank(params.get("req_type"))) {
            sql.append(" and a.req_type = ?");
            parameters.add(params.get("req_type"));
        }
        if (StringUtils.isNotBlank(params.get("event_type"))) {
            sql.append(" and a.event_type = ?");
            parameters.add(params.get("event_type"));
        }
        if (StringUtils.isNotBlank(params.get("action_type"))) {
            sql.append(" and a.action_type = ?");
            parameters.add(params.get("action_type"));
        }
        if (StringUtils.isNotBlank(params.get("key_word"))) {
            sql.append(" and a.key_word like ?");
            parameters.add("%" + params.get("key_word") + "%");
        }
        if (StringUtils.isNotBlank(params.get("start_time"))) {
            sql.append(" and a.in_time >= ?");
            parameters.add(CommonUtils.string2Date(params.get("start_time").trim() + " 00:00:00"));
        }
        if (StringUtils.isNotBlank(params.get("end_time"))) {
            sql.append(" and a.in_time < ?");
            parameters.add(CommonUtils.string2Date(params.get("end_time").trim() + " 23:59:59"));
        }
        sql.append(" order by a.in_time desc");
        return new BootstrapPage(paginate(sql.toString(), parameters.toArray()));
    }

    /**
     * 根据ID读取规则详情
     *
     * @param id
     * @param userId
     * @return
     */
    public Record loadDetailById(String id, String userId) {
        StringBuilder sql = new StringBuilder(detailSql());
        sql.append(" where a.id = ? and a.user_id = ? ");
        return findOne(sql.toString(), id, userId);
    }

    /**
     * 查询唯一的消息响应规则
     *
     * @param ext_type
     * @param req_type
     * @param event_type
     * @param key_word
     * @param userId
     * @return
     */
    public Record loadMsgAction(String ext_type, String req_type, String event_type,
            String key_word, String userId) {
        List<Object> parameters = new ArrayList();
        StringBuilder sql = new StringBuilder(detailSql());
        sql.append(" where a.user_id = ? ");
        parameters.add(userId);
        if (StringUtils.isNotBlank(ext_type)) {
            sql.append(" and a.ext_type = ?");
            parameters.add(ext_type);
        }
        if (StringUtils.isNotBlank(req_type)) {
            sql.append(" and a.req_type = ?");
            parameters.add(req_type);
        }
        if (StringUtils.isNotBlank(event_type)) {
            sql.append(" and a.event_type = ?");
            parameters.add(event_type);
        }
        if (StringUtils.isNotBlank(key_word)) {
            sql.append(" and a.key_word = ?");
            parameters.add(key_word);
        }
        return findOne(sql.toString(), parameters.toArray());
    }

    /**
     * 根据id批量删除消息规则
     *
     * @param ids
     */
    public void deleteMsgActionById(String ids) {
        if (StringUtils.isBlank(ids)) {
            throw new MyRuntimeException("ID为空，删除消息动作失败");
        }
        String _ids[] = ids.split(",");
        List<Object> parameters = new ArrayList();
        StringBuilder sql = new StringBuilder("delete from ");
        sql.append(getTableName());
        if (null != _ids && _ids.length > 1) {
            sql.append(" where id in (");
            for (int i = 0, l = _ids.length; i < l; i++) {
                sql.append("?");
                if (i != l - 1) {
                    sql.append(", ");
                }
                parameters.add(_ids[i]);
            }
            sql.append(")");
        } else {
            sql.append(" where id = ?");
            parameters.add(ids);
        }
        execute(sql.toString(), parameters.toArray());
    }

    /**
     * 根据关键字删除规则
     *
     * @param key_word
     * @param userId
     */
    public void deleteMsgActionByKey(String key_word, String userId) {
        StringBuilder sql = new StringBuilder("delete from ");
        sql.append(getTableName()).append(" where key_word = ? and user_id = ?");
        execute(sql.toString(), key_word, userId);
    }

    /**
     * 保存菜单动作
     *
     * @param actionMap
     * @param menuMap
     * @param materialMap
     */
    private void saveMenuAction(Map<String, Object> actionMap, Map<String, Object> menuMap,
            Map<String, Object> materialMap) {
        Date now = new Date();
        actionMap.put("in_time", now);
        String menuId = (String) menuMap.get("id");
        menuMap.put("update_time", now);
        String menuType = (String) menuMap.get("type");
        // 菜单类型为click
        if (menuType.equals(WxConsts.BUTTON_CLICK)) {
            menuMap.put("menu_key", "key_" + menuId);
            menuMap.put("url", "");
            // 请求关键字 or 菜单点击key
            actionMap.put("key_word", "key_" + menuId);
            // 消息响应类型
            String action_type = (String) actionMap.get("action_type");
            if (ACTION_TYPE_MATERIAL.equals(action_type)) { // 从素材读取数据
                // 消息响应类型
                String resp_type = (String) materialMap.get("msg_type");
                // 消息回复类型是文字，则先将文字信息保存到素材表
                if (WxConsts.XML_MSG_TEXT.equals(resp_type)) {
                    materialMap.put("id", CommonUtils.getPrimaryKey());
                    materialMap.put("in_time", now);
                    insert(Material.class, materialMap);
                }
            }
            actionMap.put("material_id", materialMap.get("id"));
            insert(actionMap);
        } else if (menuType.equals(WxConsts.BUTTON_VIEW)) {
            menuMap.put("menu_key", null);
        }
        update(WechatMenu.class, menuMap);
    }

    /**
     * 保存其他（除菜单外）消息动作
     *
     * @param actionMap
     * @param materialMap
     */
    private void saveMsgAction(Map<String, Object> actionMap,
            Map<String, Object> materialMap) {
        // 保存前先判断改消息规则是否存在，某种规则必须确保唯一
        if (isExist(actionMap)) {
            throw new MyRuntimeException("相同的消息规则已经存在");
        }
        Date now = new Date();
        actionMap.put("in_time", now);
        // 消息响应类型
        String action_type = (String) actionMap.get("action_type");
        if (ACTION_TYPE_MATERIAL.equals(action_type)) { // 从素材读取数据
            // 消息响应类型
            String resp_type = (String) materialMap.get("msg_type");
            // 消息回复类型是文字，则先将文字信息保存到素材表
            if (WxConsts.XML_MSG_TEXT.equals(resp_type)) {
                materialMap.put("id", CommonUtils.getPrimaryKey());
                materialMap.put("in_time", now);
                insert(Material.class, materialMap);
                actionMap.put("material_id", materialMap.get("id"));
            }
        }
        insert(actionMap);
    }

    /**
     * 判断消息规则是否存在
     *
     * @param attrs
     * @return
     */
    private boolean isExist(Map<String, Object> attrs) {
        Record record = loadMsgAction((String) attrs.get("ext_type"),
                (String) attrs.get("req_type"),
                (String) attrs.get("event_type"), (String) attrs.get("key_word"),
                (String) attrs.get("user_id"));
        return !record.isEmpty();
    }

    /**
     * 规则详情查询sql
     * 
     * @return
     */
    private String detailSql() {
        StringBuffer sql = new
                StringBuffer(
                        "select a.id as id, a.req_type as req_type, a.action_type as action_type, a.key_word as key_word, a.material_id as material_id, a.app_id as app_id, a.in_time as in_time,");
        sql.append(" b.bean_name as bean_name, b.name as app_name,");
        sql.append(" c.xml_data as xml_data, c.msg_type as msg_type,");
        sql.append(" d.dict_name as dict_name");
        sql.append(" from wechat_resp_msg_action a");
        sql.append(" left join wechat_ext_app b ");
        sql.append(" on a.app_id = b.id");
        sql.append(" left join wechat_material c");
        sql.append(" on a.material_id = c.id");
        sql.append(" left join wechat_data_dict d");
        sql.append(" on a.action_type = d.dict_value and d.group_code = 'action_type'");
        return sql.toString();
    }

}
