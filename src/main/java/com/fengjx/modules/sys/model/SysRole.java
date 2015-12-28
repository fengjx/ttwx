
package com.fengjx.modules.sys.model;

import com.fengjx.commons.plugin.db.Mapper;
import com.fengjx.commons.plugin.db.Model;
import com.fengjx.commons.plugin.db.Record;
import com.fengjx.commons.utils.CommonUtils;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
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
 * 角色管理
 *
 * @version 2015/10/31
 * @Created by FengJianxin
 * @Email xd-fjx@qq.com
 */
@Mapper(table = "sys_role")
@Component
public class SysRole extends Model {

    /**
     * 通过角色名称查角色
     *
     * @param name
     * @return
     */
    public Record getRoleByName(String name) {
        Map<String, Object> attr = Maps.newHashMap();
        attr.put("name", name);
        return findOne(attr);
    }

    /**
     * 通过角色名称查角色
     *
     * @param code
     * @return
     */
    public Record getRoleByCode(String code) {
        Map<String, Object> attr = Maps.newHashMap();
        attr.put("role_code", code);
        return findOne(attr);
    }

    /**
     * 添加或更新
     *
     * @param record
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOrUpdate(Record record) {
        String roleId = record.getStr("id");
        record.set("update_time", new Date());
        if (StringUtils.isNotBlank(roleId)) {
            update(record);
            deleteMenuRole(roleId);
        } else {
            roleId = CommonUtils.getPrimaryKey();
            record.set("id", roleId);
            insert(record);
        }
        String menuIds = record.getStr("menuIds");
        String[] ids = StringUtils.split(menuIds, ",");
        saveMenuRole(roleId, ids);
    }

    /**
     * 删除角色
     *
     * @param id
     */
    public void deleteRole(String id) {
        deleteMenuRole(id);
        deleteById(id);
    }

    /**
     * 获得授权菜单ID，用,分隔
     *
     * @param roleId
     * @return
     */
    public String getMenuIds(String roleId) {
        return Joiner.on(",").join(getMenuRole(roleId));
    }

    /**
     * 获得授权菜单ID
     *
     * @param roleId
     * @return
     */
    public List<String> getMenuRole(String roleId) {
        StringBuilder sql = new StringBuilder("select menu_id from ");
        sql.append(getTableName(SysRoleMenu.class));
        sql.append(" where role_id = ?");
        return getJdbcTemplate().queryForList(sql.toString(), String.class, roleId);
    }

    /**
     * 删除角色菜单关联
     *
     * @param roleId
     */
    @Transactional(propagation = Propagation.REQUIRED)
    private void deleteMenuRole(String roleId) {
        StringBuilder sql = new StringBuilder("delete from ");
        sql.append(getTableName(SysRoleMenu.class));
        sql.append(" where role_id = ?");
        execute(sql.toString(), roleId);
    }

    /**
     * 保存菜单角色关联
     *
     * @param roleId
     * @param menuIds
     */
    @Transactional(propagation = Propagation.REQUIRED)
    private void saveMenuRole(final String roleId, final String[] menuIds) {
        StringBuilder sql = new StringBuilder("insert into ");
        sql.append(getTableName(SysRoleMenu.class));
        sql.append(" (role_id, menu_id) values (?, ?)");
        batchExecute(sql.toString(), new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setObject(1, roleId);
                preparedStatement.setObject(2, menuIds[i]);
            }

            @Override
            public int getBatchSize() {
                return menuIds.length;
            }
        });
    }

    /**
     * 通过角色ID查询所有用户（包括授权和未授权）
     *
     * @param roleId
     * @return
     */
    public List<Map<String, Object>> roleUsers(String roleId) {
        StringBuilder sql = new StringBuilder(
                "select u.id as \"userId\", u.username, r.role_id \"roleId\" from ");
        sql.append(getTableName(SysUser.class)).append(" u ");
        sql.append("left join ").append(getTableName(SysUserRole.class)).append(" r ");
        sql.append(" on u.id = r.user_id and r.role_id = ?");
        return getJdbcTemplate().queryForList(sql.toString(), roleId);
    }

    /**
     * 保存角色授权
     *
     * @param roleId 角色ID
     * @param userIds 用户ID（多个用“,”分隔）
     */
    public void saveRoleUsers(final String roleId, String userIds) {
        StringBuilder delRoleSql = new StringBuilder("delete from ");
        delRoleSql.append(getTableName(SysUserRole.class));
        delRoleSql.append(" where role_id = ?");
        execute(delRoleSql.toString(), roleId);
        if (StringUtils.isNotBlank(userIds)) {
            StringBuilder sql = new StringBuilder("insert into ");
            sql.append(getTableName(SysUserRole.class));
            sql.append(" (role_id, user_id) values (?, ?)");
            final String[] users = StringUtils.split(userIds, ",");
            batchExecute(sql.toString(), new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement, int i)
                        throws SQLException {
                    preparedStatement.setObject(1, roleId);
                    preparedStatement.setObject(2, users[i]);
                }

                @Override
                public int getBatchSize() {
                    return users.length;
                }
            });
        }
    }

    /**
     * 查询用户角色
     *
     * @param userId
     * @return
     */
    public List<Map<String, Object>> getRolesByUserid(String userId) {
        StringBuilder sql = new StringBuilder("select ");
        sql.append(getColumnsStr("a")).append(", b.user_id");
        sql.append(" from ").append(getTableName()).append("a");
        sql.append(" join ").append(getTableName(SysUserRole.class));
        sql.append(" on b.user_id = a.id").append(" where b.user_id = ? ");
        return findList(sql.toString(), userId);
    }

    /**
     * 获得用户所以角色ID
     *
     * @param userId
     * @return
     */
    public String[] getRoleIdsByUserid(String userId) {
        List<Map<String, Object>> roles = getRolesByUserid(userId);
        String[] arr = new String[roles.size()];
        for (int i = 0; i < roles.size(); i++) {
            arr[i] = (String) (roles.get(i).get("id"));
        }
        return arr;
    }

}
