
package com.fengjx.ttwx.modules.sys.model;

import com.fengjx.ttwx.common.plugin.db.Mapper;
import com.fengjx.ttwx.common.plugin.db.Model;
import com.fengjx.ttwx.common.plugin.db.ParamHelper;
import com.fengjx.ttwx.common.plugin.db.Record;
import com.fengjx.ttwx.common.plugin.db.page.AdapterPage;
import com.fengjx.ttwx.common.system.exception.MyRuntimeException;
import com.fengjx.ttwx.common.utils.AesUtil;
import com.fengjx.ttwx.common.utils.CommonUtils;
import com.fengjx.ttwx.common.utils.DateUtils;
import com.fengjx.ttwx.modules.wechat.entity.SysUserEntity;
import com.fengjx.ttwx.modules.sys.listener.RegisterEvent;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    /**
     * 登录
     *
     * @param username
     * @param pwd
     * @return
     */
    public SysUserEntity signin(String username, String pwd) {
        Map<String, Object> attrs = new HashMap<>();
        attrs.put("username", username);
        Record record = findOne(attrs);
        String md5Hex = DigestUtils.md5Hex(pwd + record.getStr("email"));
        if (null != record && record.getStr("pwd").equalsIgnoreCase(md5Hex)) {
            return record.toBean(SysUserEntity.class);
        }
        return null;
    }

    /**
     * 用户注册
     *
     * @param attrs
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void register(Map<String, Object> attrs) {
        if (validUsername((String) attrs.get("username"))) {
            throw new MyRuntimeException("用户名已存在");
        }
        if (validEmail((String) attrs.get("email"))) {
            throw new MyRuntimeException("邮箱已被占用");
        }
        attrs.put("id", CommonUtils.getPrimaryKey());
        attrs.put("pwd", DigestUtils.md5Hex(((String) attrs.get("pwd") + attrs.get("email"))));
        attrs.put("is_valid", "0");
        attrs.put("valid_uid", CommonUtils.getPrimaryKey());
        attrs.put("in_time", new Date());
        // 默认积分
        if (StringUtils.isBlank((String) attrs.get("score"))) {
            attrs.put("score", 0);
        }
        insert(attrs);
        // 推送注册消息，监听了RegisterEvent的listener将会收到此消息
        applicationContext.publishEvent(new RegisterEvent(attrs));
    }

    /**
     * 校验用户名是否存在
     *
     * @param username
     * @return
     */
    public boolean validUsername(String username) {
        String sql = "select count(1) as count from wechat_sys_user u where u.username = ?";
        Long total = findOne(sql, username).getLong("count");
        return total > 0;
    }

    /**
     * 校验邮箱是否存在
     *
     * @param email
     * @return
     */
    public boolean validEmail(String email) {
        String sql = "select count(1) as count from wechat_sys_user u where u.email = ?";
        Long total = findOne(sql, email).getLong("count");
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
        Map<String, Object> attrs = new HashMap<>();
        attrs.put("valid_uid", uid);
        Record record = findOne(attrs);
        if (null == record || SysUserEntity.IS_ALIVE.equals(record.getStr("is_valid"))) {
            return false;
        }
        record.set("is_valid", SysUserEntity.IS_ALIVE);
        update(record.getColumns());
        return true;
    }

    public AdapterPage pageList(ParamHelper params) {
        List<Object> qryParams = new ArrayList<>();
        StringBuilder sql = new StringBuilder(getSelectSql("a"));
        if (null != params.get("is_valid")) {
            sql.append(" and a.is_valid = ?");
            qryParams.add(params.get("is_valid"));
        }
        if (StringUtils.isNoneBlank(params.getStr("username"))) {
            sql.append(" and a.name like CONCAT('%',?,'%')");
            qryParams.add(params.get("username"));
        }
        if (StringUtils.isNoneBlank(params.getStr("email"))) {
            sql.append(" and a.name like CONCAT('%',?,'%')");
            qryParams.add(params.get("email"));
        }
        if (StringUtils.isNoneBlank(params.getStr("start_time"))) {
            sql.append(" and a.in_time >= ?");
            qryParams.add(DateUtils.parseDate(params.get("start_time")));
        }
        if (StringUtils.isNoneBlank(params.getStr("end_time"))) {
            sql.append(" and a.in_time <= ?");
            qryParams.add(DateUtils.parseDate(params.get("end_time")));
        }
        sql.append(" order by in_time desc");
        return paginate(sql.toString(), qryParams.toArray()).convert();
    }

}
