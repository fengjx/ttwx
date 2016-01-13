
package com.fengjx.modules.sys.model;

import com.fengjx.commons.plugin.db.Mapper;
import com.fengjx.commons.plugin.db.Model;
import com.fengjx.commons.plugin.db.Record;
import com.fengjx.commons.plugin.db.page.AdapterPage;
import com.fengjx.commons.system.exception.MyRuntimeException;
import com.fengjx.commons.utils.AesUtil;
import com.fengjx.commons.utils.CommonUtils;
import com.fengjx.commons.utils.DateUtils;
import com.fengjx.commons.utils.StrUtil;
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
 * 系统用户管理
 *
 * @author fengjx. @date：2015/5/6 0006
 */
@Component
@Mapper(table = "sys_user", id = "id")
public class SysUser extends Model {

    @Autowired
    private ApplicationContext applicationContext;

    public SysUserEntity get(String id) {
        return findById(id).toBean(SysUserEntity.class);
    }

    public void saveOrUpdate(Record record) {
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
    public Record getUserByUsername(String username) {
        Map<String, Object> attrs = Maps.newHashMap();
        attrs.put("username", username);
        return findOne(attrs);
    }

    /**
     * 登录
     *
     * @param username
     * @param pwd
     * @return
     */
    public SysUserEntity signin(String username, String pwd) {
        Record record = getUserByUsername(username);
        String md5Hex = UserUtil.encryptPwd(pwd, record.getStr("salt"));
        if (!record.isEmpty() && record.getStr("pwd").equalsIgnoreCase(md5Hex)) {
            return record.toBean(SysUserEntity.class);
        }
        return null;
    }

    /**
     * 用户注册
     *
     * @param record
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void register(Record record) {
        if (validUsername(record.getStr("username"))) {
            throw new MyRuntimeException("用户名已存在");
        }
        if (validEmail(record.getStr("email"))) {
            throw new MyRuntimeException("邮箱已被占用");
        }
        String salt = StrUtil.randomStr(12);
        record.set("id", CommonUtils.getPrimaryKey());
        record.set("decrypPwd", record.getStr("pwd"));
        record.set("salt", salt);
        record.set("pwd", UserUtil.encryptPwd(record.getStr("pwd"), salt));
        record.set("is_valid", "0");
        record.set("valid_uid", CommonUtils.getPrimaryKey());
        record.set("in_time", new Date());
        insert(record);
        // 推送注册消息，监听了RegisterEvent的listener将会收到此消息
        applicationContext.publishEvent(new RegisterEvent(record));
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
        Record record = findOne(attrs);
        if (record.isEmpty()) {
            throw new MyRuntimeException("无效链接");
        }
        if (SysUserEntity.FREEZE_ALIVE.equals(record.getStr("is_valid"))) {
            throw new MyRuntimeException("账号已被锁定");
        }
        if (SysUserEntity.IS_ALIVE.equals(record.getStr("is_valid"))) {
            return false;
        }
        record.set("is_valid", SysUserEntity.IS_ALIVE);
        update(record);
        return true;
    }

    public AdapterPage pageList(Record record) {
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
        return paginate(sql.toString(), qryParams.toArray()).convert();
    }

}
