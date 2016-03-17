
package com.fengjx.modules.sys.service;

import com.fengjx.commons.plugin.db.Model;
import com.fengjx.commons.plugin.db.Page;
import com.fengjx.commons.plugin.db.Record;
import com.fengjx.commons.system.exception.MyRuntimeException;
import com.fengjx.commons.utils.AesUtil;
import com.fengjx.commons.utils.CommonUtils;
import com.fengjx.commons.utils.DateUtils;
import com.fengjx.commons.utils.StrUtil;
import com.fengjx.modules.sys.bean.SysUser;
import com.fengjx.modules.sys.entity.SysUserEntity;
import com.fengjx.modules.sys.listener.RegisterEvent;
import com.fengjx.modules.sys.utils.SysUtil;
import com.fengjx.modules.sys.utils.UserUtil;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Autu Generated .
 */
@Component
public class SysUserService extends Model<SysUser> {

    @Autowired
    private ApplicationContext applicationContext;

    public SysUser get(String id) {
        return findById(id);
    }

    public void saveOrUpdate(SysUser record) {
        if (StringUtils.isBlank(record.getStr("id"))) {
            record.set("pwd", "admin");
            register(record);
        } else {
            update(record);
        }
        SysUtil.deleteSysCache();
    }

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    public SysUser getUserByUsername(String username) {
        Map<String, Object> attrs = Maps.newHashMap();
        attrs.put("username", username);
        return findFirst(attrs);
    }

    /**
     * 用户注册
     *
     * @param sysUser
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void register(SysUser sysUser) {
        if (validUsername(sysUser.getStr("username"))) {
            throw new MyRuntimeException("用户名已存在");
        }
        if (validEmail(sysUser.getStr("email"))) {
            throw new MyRuntimeException("邮箱已被占用");
        }
        String salt = StrUtil.randomStr(12);
        sysUser.set("id", CommonUtils.getPrimaryKey());
        sysUser.set("decrypPwd", sysUser.getStr("pwd"));
        sysUser.set("salt", salt);
        sysUser.set("pwd", UserUtil.encryptPwd(sysUser.getStr("pwd"), salt));
        sysUser.set("is_valid", "0");
        sysUser.set("valid_uid", CommonUtils.getPrimaryKey());
        sysUser.set("in_time", new Date());
        save(sysUser);
        // 推送注册消息，监听了RegisterEvent的listener将会收到此消息
        applicationContext.publishEvent(new RegisterEvent(sysUser));
    }

    /**
     * 校验用户名是否存在
     *
     * @param username
     * @return
     */
    public boolean validUsername(String username) {
        StringBuilder sql = new StringBuilder("select count(1) as count from ");
        sql.append(getTableName());
        sql.append(" u where u.username = ?");
        Long total = findOne(sql.toString(), username).getLong("count");
        return total > 0;
    }

    /**
     * 校验邮箱是否存在
     *
     * @param email
     * @return
     */
    public boolean validEmail(String email) {
        StringBuilder sql = new StringBuilder("select count(1) as count from ");
        sql.append(getTableName());
        sql.append(" u where u.email = ?");
        Long total = findOne(sql.toString(), email).getLong("count");
        return total > 0;
    }

    /**
     * 激活账号
     *
     * @param ser
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean activate(String ser) {
        String uid = AesUtil.decrypt(ser);
        Map<String, Object> attrs = Maps.newHashMap();
        attrs.put("valid_uid", uid);
        SysUser sysUser = findFirst(attrs);
        if (sysUser.isEmpty()) {
            throw new MyRuntimeException("无效链接");
        }
        if (SysUserEntity.FREEZE_ALIVE.equals(sysUser.getStr("is_valid"))) {
            throw new MyRuntimeException("账号已被锁定");
        }
        if (SysUserEntity.IS_ALIVE.equals(sysUser.getStr("is_valid"))) {
            return false;
        }
        sysUser.set("is_valid", SysUserEntity.IS_ALIVE);
        update(sysUser);
        return true;
    }

    public Page<Map<String,Object>> pageList(Record record) {
        List<Object> qryParams = new ArrayList<>();
        StringBuilder sql = new StringBuilder(getSelectSql("a"));
        if (null != record.get("is_valid")) {
            sql.append(" and a.is_valid = ?");
            qryParams.add(record.get("is_valid"));
        }
        if (StringUtils.isNoneBlank(record.getStr("username"))) {
            sql.append(" and a.name like CONCAT('%',?,'%')");
            qryParams.add(record.get("username"));
        }
        if (StringUtils.isNoneBlank(record.getStr("email"))) {
            sql.append(" and a.name like CONCAT('%',?,'%')");
            qryParams.add(record.get("email"));
        }
        if (StringUtils.isNoneBlank(record.getStr("start_time"))) {
            sql.append(" and a.in_time >= ?");
            qryParams.add(DateUtils.parseDate(record.get("start_time")));
        }
        if (StringUtils.isNoneBlank(record.getStr("end_time"))) {
            sql.append(" and a.in_time <= ?");
            qryParams.add(DateUtils.parseDate(record.get("end_time")));
        }
        sql.append(" order by in_time desc");
        return paginate(sql.toString(), qryParams.toArray());
    }

}
