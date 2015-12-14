
package com.fengjx.modules.wechat.model;

import com.fengjx.commons.plugin.cache.IDataLoader;
import com.fengjx.commons.plugin.cache.SimpleCache;
import com.fengjx.commons.plugin.db.Mapper;
import com.fengjx.commons.plugin.db.Model;
import com.fengjx.commons.plugin.db.Record;
import com.fengjx.commons.utils.AesUtil;
import com.fengjx.commons.utils.CommonUtils;
import com.fengjx.modules.common.constants.AppConfig;
import com.fengjx.modules.wechat.process.utils.WxMpUtil;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 公众号信息管理
 *
 * @author fengjx. @date：2015/6/17 0017
 */
@Mapper(table = "wechat_public_account")
@Component
public class PublicAccount extends Model {

    public static final String VALID_STATE_NONACTIVATED = "0"; // 0：未激活

    public static final String VALID_STATE_EXCESS = "1"; // 1：已配置到公众平台

    public static final String VALID_STATE_ACTIVATE = "2"; // 2：已通过客户端校验验证码

    @Autowired
    private SimpleCache simpleCache;

    /**
     * 根据userid获得公众账号信息
     *
     * @param userId
     * @return
     */
    public Record getAccountByUserId(String userId) {
        Map<String, Object> attrs = new HashMap<>();
        attrs.put("sys_user_id", userId);
        return findOne(attrs);
    }

    /**
     * 更新公众号信息设置
     *
     * @param attrs
     * @param userId
     * @return
     */
    public Record updateAccount(Map<String, Object> attrs, String userId) {
        attrs.put("sys_user_id", userId);
        update(attrs);
        Record record = findById(attrs.get(getPrimaryKey()));
        simpleCache.remove(record.getStr("ticket"));
        return record;
    }

    /**
     * 重置公众号配置
     *
     * @param id
     * @param userId
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Record reset(String id, String userId) {
        Map<String, Object> attrs = resetAttrs(id, userId);
        update(attrs);
        Record record = findById(id);
        simpleCache.remove(record.getStr("ticket"));
        return record;
    }

    /**
     * 重新赋值
     *
     * @param id
     * @param userId
     * @return
     */
    public static Map<String, Object> resetAttrs(String id, String userId) {
        Map<String, Object> attrs = new HashMap<>();
        attrs.put("sys_user_id", userId);
        String token = CommonUtils.getPrimaryKey();
        String ticket = CommonUtils.getPrimaryKey();
        attrs.put("id", id);
        attrs.put("in_time", new Date());
        attrs.put("token", token);
        attrs.put("ticket", ticket);
        attrs.put("url",
                AppConfig.DOMAIN_PAGE + AppConfig.API_PATH + "?ticket=" + AesUtil.encrypt(ticket));
        attrs.put("valid_code", CommonUtils.getRandomNum(5));
        attrs.put("valid_state", PublicAccount.VALID_STATE_NONACTIVATED);
        return attrs;
    }

    /**
     * 根据userId获得公众号配置
     *
     * @param userId
     * @return
     */
    public WxMpConfigStorage getWxMpConfigStorageByUserId(final String userId) {
        Record record = getAccountByUserId(userId);
        return WxMpUtil.buildConfigStorage(record);
    }

    /**
     * 根据ID获得公众号配置
     *
     * @param encryptTicket 加密后的ticket
     * @return
     */
    public Record findByTicket(final String encryptTicket) {
        return simpleCache.get(AesUtil.decrypt(encryptTicket), new IDataLoader<Record>() {
            @Override
            public Record load() {
                Map<String, Object> attrs = new HashMap<>();
                attrs.put("ticket", AesUtil.decrypt(encryptTicket));
                Record account = findOne(attrs);
                return account;
            }
        });
    }

    /**
     * 获得公众号配置
     *
     * @param ticket
     * @return
     */
    public WxMpConfigStorage getWxMpConfigStorageByTicket(String ticket) {
        return WxMpUtil.buildConfigStorage(findByTicket(ticket));
    }

    /**
     * 获得公众号
     *
     * @param userId
     * @return
     */
    public WxMpService getWxMpService(String userId) {
        return WxMpUtil.getWxMpServiceByConfig(getWxMpConfigStorageByUserId(userId));
    }

    /**
     * 通过ticket获得WxMpService
     *
     * @param ticket
     * @return
     */
    public WxMpService getWxMpServiceByTicket(String ticket) {
        return WxMpUtil.getWxMpServiceByConfig(getWxMpConfigStorageByTicket(ticket));
    }

}
