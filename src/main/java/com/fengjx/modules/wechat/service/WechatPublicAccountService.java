
package com.fengjx.modules.wechat.service;

import com.fengjx.commons.plugin.cache.ehcache.EhCacheUtil;
import com.fengjx.commons.plugin.db.Model;
import com.fengjx.commons.plugin.db.Record;
import com.fengjx.commons.system.exception.MyRuntimeException;
import com.fengjx.commons.utils.AesUtil;
import com.fengjx.commons.utils.CommonUtils;
import com.fengjx.commons.utils.StrUtil;
import com.fengjx.modules.common.constants.AppConfig;
import com.fengjx.modules.wechat.bean.WechatPublicAccount;
import com.fengjx.modules.wechat.constants.WechatConst;
import com.fengjx.modules.wechat.process.utils.WxMpUtil;
import com.google.common.collect.Maps;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Autu Generated .
 */
@Component
public class WechatPublicAccountService extends Model<WechatPublicAccount> {

    /**
     * 根据userid获得公众账号信息
     *
     * @param userId
     * @return
     */
    public WechatPublicAccount getAccountByUserId(String userId) {
        return EhCacheUtil.get(AppConfig.EhcacheName.WECHAT_PUBLIC_ACCOUNT, userId, () -> {
            Map<String, Object> attrs = Maps.newHashMap();
            attrs.put("sys_user_id", userId);
            WechatPublicAccount publicAccount = findFirst(attrs);
            buildCache(publicAccount);
            return publicAccount;
        });
    }

    /**
     * 根据公众号原始ID获得公众账号信息
     *
     * @param accountId
     * @return
     */
    public WechatPublicAccount getAccountByAccountId(String accountId) {
        return EhCacheUtil.get(AppConfig.EhcacheName.WECHAT_PUBLIC_ACCOUNT, accountId, () -> {
            Map<String, Object> attrs = Maps.newHashMap();
            attrs.put("account_id", accountId);
            return findFirst(attrs);
        });

    }

    /**
     * 更新公众号信息设置
     *
     * @param publicAccount
     * @param userId
     * @return
     */
    public WechatPublicAccount updateAccount(WechatPublicAccount publicAccount, String userId) {
        validAccount(publicAccount.getId(), userId);
        update(publicAccount);
        removeCache();
        return findById(publicAccount.getId());
    }

    private void validAccount(String accountId, String userId) {
        String sql = "select count(1) from " + getTableName() + " where id = ? and sys_user_id = ?";
        int count = getCount(sql, accountId, userId);
        if (count != 1) {
            throw new MyRuntimeException("操作失败，数据错误！");
        }
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
        removeCache();
        return findById(id);
    }

    /**
     * 重新赋值
     *
     * @param id
     * @param userId
     * @return
     */
    public Map<String, Object> resetAttrs(String id, String userId) {
        Map<String, Object> attrs = new HashMap<>();
        attrs.put("sys_user_id", userId);
        String token = CommonUtils.getPrimaryKey();
        String ticket = CommonUtils.getPrimaryKey();
        attrs.put("id", id);
        attrs.put("in_time", new Date());
        attrs.put("token", token);
        attrs.put("ticket", ticket);
        attrs.put("url", AppConfig.API_PATH + "?ticket=" + AesUtil.encrypt(ticket));
        attrs.put("valid_code", StrUtil.getRandomNum(5));
        attrs.put("valid_state", WechatConst.PublicAccount.VALID_STATE_NONACTIVATED);
        return attrs;
    }

    /**
     * 根据userId获得公众号配置
     *
     * @param userId
     * @return
     */
    public WxMpConfigStorage getWxMpConfigStorageByUserId(String userId) {
        Record record = getAccountByUserId(userId);
        return WxMpUtil.buildConfigStorage(record);
    }

    /**
     * 根据ID获得公众号配置
     *
     * @param encryptTicket 加密后的ticket
     * @return
     */
    public WechatPublicAccount findByTicket(String encryptTicket) {
        return EhCacheUtil.get(AppConfig.EhcacheName.WECHAT_PUBLIC_ACCOUNT, encryptTicket, () -> {
            Map<String, Object> attrs = new HashMap<>();
            attrs.put("ticket", AesUtil.decrypt(encryptTicket));
            WechatPublicAccount publicAccount = findFirst(attrs);
            buildCache(publicAccount);
            return publicAccount;
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
     * 通过公众号ID，获得公众号接口
     *
     * @param accountId
     * @return
     */
    public WxMpService getWxMpServiceByAccountId(String accountId) {
        return WxMpUtil.getWxMpService(getAccountByAccountId(accountId));
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

    /**
     * 删除公众号缓存
     */
    private void removeCache() {
        EhCacheUtil.removeAll(AppConfig.EhcacheName.WECHAT_PUBLIC_ACCOUNT);
    }

    /**
     * 添加缓存
     *
     * @param publicAccount
     */
    private void buildCache(WechatPublicAccount publicAccount) {
        EhCacheUtil.put(AppConfig.EhcacheName.WECHAT_PUBLIC_ACCOUNT, publicAccount.getId(),
                publicAccount);
        EhCacheUtil.put(AppConfig.EhcacheName.WECHAT_PUBLIC_ACCOUNT, publicAccount.getTicket(),
                publicAccount);
        EhCacheUtil.put(AppConfig.EhcacheName.WECHAT_PUBLIC_ACCOUNT, publicAccount.getAccountId(),
                publicAccount);
        EhCacheUtil.put(AppConfig.EhcacheName.WECHAT_PUBLIC_ACCOUNT, publicAccount.getSysUserId(),
                publicAccount);
    }

}
