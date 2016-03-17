
package com.fengjx.modules.wechat.service;

import com.fengjx.commons.plugin.db.Model;
import com.fengjx.commons.system.exception.MyRuntimeException;
import com.fengjx.modules.wechat.bean.WechatMenu;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxMenu;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Autu Generated .
 */
@Component
public class WechatMenuService extends Model<WechatMenu> {

    @Autowired
    private WechatPublicAccountService publicAccountService;

    public void saveOrUpdate(Map<String, Object> attrs) {
        attrs.put("in_time", new Date());
        if (StringUtils.isNotBlank((String) attrs.get("id"))) {
            StringBuilder sql = new StringBuilder();
            sql.append("update ").append(getTableName());
            sql.append(" set name = ?, in_time = ? where user_id = ? and id = ?");
            execute(sql.toString(), attrs.get("name"), attrs.get("in_time"), attrs.get("user_id"),
                    attrs.get("id"));
        } else {
            insert(attrs);
        }
    }

    /**
     * 删除菜单
     *
     * @param id
     */
    public void deleteMenu(String id) {
        if (!isLeef(id)) {
            StringBuilder sql = new StringBuilder();
            sql.append("delete from ").append(getTableName());
            sql.append(" where parent_id = ?");
            execute(sql.toString(), id);
        }
        deleteById(id);
    }

    /**
     * 查询树形菜单
     *
     * @param userId
     * @return
     */
    public List<Map<String, Object>> treeMenu(String userId) {
        List<Map<String, Object>> list = loadMenuDetailById(null, userId);
        return list;
    }

    /**
     * 递归查询
     *
     * @param id
     * @param userId
     * @return
     */
    private List<Map<String, Object>> loadMenuDetailById(String id, String userId) {
        List<Map<String, Object>> res = null;
        StringBuilder sql = new StringBuilder(
                "select m.id as \"id\", m.in_time as \"in_time\", m.menu_key as \"menu_key\", m.menu_level as \"menu_level\", m.name as \"name\", m.parent_id as \"parent_id\", m.type as \"type\", m.update_time as \"update_time\", m.url as \"url\", m.user_id as \"user_id\",");
        sql.append(" a.id as \"action_id\", a.action_type as \"action_type\", a.in_time as \"action_time\", ");
        sql.append(" b.id as \"app_id\",b.name as \"app_name\", b.description as \"app_description\",");
        sql.append(" c.id as \"material_id\",c.xml_data as \"xml_data\" ");
        sql.append(" from wechat_menu m ");
        sql.append(" left join wechat_resp_msg_action a on m.menu_key = a.key_word ");
        sql.append(" left join wechat_ext_app b on a.app_id = b.id ");
        sql.append(" left join wechat_material c on a.material_id = c.id ");
        sql.append(" where m.user_id = ?");
        if (StringUtils.isBlank(id)) {
            sql.append(" and (m.parent_id is null or m.parent_id = '') order by m.order_no");
            res = findList(sql.toString(), userId);
        } else {
            sql.append(" and m.parent_id = ? order by m.order_no");
            res = findList(sql.toString(), userId, id);
        }
        if (null != res && res.size() > 0) {
            for (int i = 0, l = res.size(); i < l; i++) {
                String _id = res.get(i).get("id") + "";
                // 如果存在子节点（不是叶子节点），则继续递归查询
                if (!isLeef(_id)) {
                    List<Map<String, Object>> tmpList = loadMenuDetailById(_id, userId);
                    res.get(i).put("children", tmpList);
                }
            }
        }
        return res;
    }

    /**
     * 发布菜单
     *
     * @param userId
     */
    public void release(String userId) {
        WxMpService wxMpService = publicAccountService.getWxMpService(userId);
        WxMenu menu = loadMenu(userId);
        try {
            wxMpService.menuCreate(menu);
        } catch (WxErrorException e) {
            WxError error = e.getError();
            throw new MyRuntimeException("菜单发布失败，errcode=" + error.getErrorCode() + " and errmsg="
                    + error.getErrorMsg(), e);
        }
    }

    /**
     * 读取菜单数据
     *
     * @param userId
     * @return
     */
    private WxMenu loadMenu(String userId) {
        List<Map<String, Object>> menuList = loadMenu(null, userId);
        WxMenu menu = new WxMenu();
        if (CollectionUtils.isNotEmpty(menuList)) {
            WxMenu.WxMenuButton button = null;
            for (Map<String, Object> menuMap : menuList) {
                button = new WxMenu.WxMenuButton();
                button.setName((String) menuMap.get("name"));
                @SuppressWarnings("unchecked")
                List<Map<String, Object>> children = (List<Map<String, Object>>) menuMap
                        .get("children");
                if (null != children) {
                    WxMenu.WxMenuButton cButton = null;
                    for (Map<String, Object> cMap : children) {
                        cButton = new WxMenu.WxMenuButton();
                        setButton(cButton, cMap);
                        button.getSubButtons().add(cButton);
                    }
                } else {
                    setButton(button, menuMap);
                }
                menu.getButtons().add(button);
            }
        }
        return menu;
    }

    /**
     * 递归查询菜单
     *
     * @param id
     * @param userId
     * @return
     */
    private List<Map<String, Object>> loadMenu(String id, String userId) {
        List<Map<String, Object>> res = null;
        StringBuilder sql = new StringBuilder(getSelectSql("m"));
        sql.append(" where m.user_id = ?");
        if (StringUtils.isBlank(id)) {
            sql.append(" and (m.parent_id is null or m.parent_id = '') order by m.order_no");
            res = findList(sql.toString(), userId);
        } else {
            sql.append(" and m.parent_id = ? order by m.order_no");
            res = findList(sql.toString(), userId, id);
        }
        if (null != res && res.size() > 0) {
            for (int i = 0, l = res.size(); i < l; i++) {
                String _id = res.get(i).get("id") + "";
                // 如果存在子节点（不是叶子节点），则继续递归查询
                if (!isLeef(_id)) {
                    List<Map<String, Object>> tmpList = loadMenu(_id, userId);
                    res.get(i).put("children", tmpList);
                }
            }
        }
        return res;
    }

    /**
     * 给WxMenuButton赋值
     *
     * @param button
     * @param menuMap
     */
    private void setButton(final WxMenu.WxMenuButton button, Map<String, Object> menuMap) {
        String type = (String) menuMap.get("type");
        String name = (String) menuMap.get("name");
        button.setType(type);
        button.setName(name);
        if (WxConsts.BUTTON_CLICK.equals(type)) {
            button.setKey((String) menuMap.get("menu_key"));
        } else if (WxConsts.BUTTON_VIEW.equals(type)) {
            button.setUrl((String) menuMap.get("url"));
        } else {
            throw new MyRuntimeException("菜单【" + name + "】未设置动作");
        }
    }

    /**
     * 菜单排序
     *
     * @param sortList
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void sort(final List<Map<String, Object>> sortList,final String userId) {
        if (CollectionUtils.isEmpty(sortList)) {
            throw new MyRuntimeException("菜单排序数据为空");
        }
        StringBuilder sql = new StringBuilder();
        sql.append("update ").append(getTableName());
        sql.append(" set order_no = ? where id = ? and user_id = ?");
        batchExecute(sql.toString(), new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                Map<String, Object> attr = sortList.get(i);
                preparedStatement.setObject(1, attr.get("order_no"));
                preparedStatement.setObject(2, attr.get("id"));
                preparedStatement.setObject(3, userId);
            }

            @Override
            public int getBatchSize() {
                return sortList.size();
            }
        });
    }

}
