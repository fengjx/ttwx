
package com.fengjx.ttwx.modules.sys.model;

import com.fengjx.ttwx.common.plugin.db.Mapper;
import com.fengjx.ttwx.common.plugin.db.Model;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 系统菜单
 *
 * @version 2015/10/17
 * @Created by FengJianxin
 * @Email xd-fjx@qq.com
 */
@Mapper(table = "sys_menu", pid = "parent_id")
@Component
public class SysMenu extends Model {

    /**
     * 查询树形菜单 treeNode
     *
     * @return
     */
    public List<Map<String, Object>> treeNode() {
        StringBuilder selectSql = new StringBuilder("select a.id, a.parent_id, a.name, a.level from ");
        selectSql.append(getTableName()).append(" a");
        List<Map<String, Object>> list = loadMenuDetailById(null, selectSql.toString());
        return list;
    }

    /**
     * 查询树形菜单 treeTable
     *
     * @return
     */
    public List<Map<String, Object>> treeMenu() {
        List<Map<String, Object>> list = loadMenuDetailById(null, null);
        return list;
    }

    /**
     * 递归查询
     *
     * @param pid
     * @return
     */
    private List<Map<String, Object>> loadMenuDetailById(String pid, String selectSql) {
        List<Map<String, Object>> res = null;
        StringBuilder sql = new StringBuilder(StringUtils.isBlank(selectSql) ? getSelectSql("a")
                : selectSql);
        sql.append(" where 1 = 1 ");
        if (StringUtils.isBlank(pid)) {
            sql.append(" and (a.parent_id is null or a.parent_id = '') order by a.order_no");
            res = findList(sql.toString());
        } else {
            sql.append(" and a.parent_id = ? order by a.order_no");
            res = findList(sql.toString(), pid);
        }
        if (null != res && res.size() > 0) {
            for (int i = 0, l = res.size(); i < l; i++) {
                String _id = res.get(i).get("id") + "";
                // 如果存在子节点（不是叶子节点），则继续递归查询
                if (!isLeef(_id)) {
                    List<Map<String, Object>> tmpList = loadMenuDetailById(_id, selectSql);
                    res.get(i).put("children", tmpList);
                    res.get(i).put("isLeef", false);
                    res.get(i).put("isParent", true);
                }else{
                    res.get(i).put("isLeef", true);
                    res.get(i).put("isParent", false);
                }
            }
        }
        return res;
    }



}
