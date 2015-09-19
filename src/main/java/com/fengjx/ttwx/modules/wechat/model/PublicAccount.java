
package com.fengjx.ttwx.modules.wechat.model;

import com.fengjx.ttwx.common.plugin.db.Mapper;
import com.fengjx.ttwx.common.plugin.db.Model;
import com.fengjx.ttwx.common.plugin.db.Record;
import com.fengjx.ttwx.common.utils.AesUtil;
import com.fengjx.ttwx.modules.wechat.process.utils.WxMpUtil;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.stereotype.Component;

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

    /**
     * 根据userid获得公众账号信息
     *
     * @param userId
     * @return
     */
    public Record getAccountByUserId(String userId) {
        Map<String, Object> attrs = new HashMap();
        attrs.put("sys_user_id", userId);
        return findOne(attrs);
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
    public Record findByTicket(String encryptTicket) {
        Map<String, Object> attrs = new HashMap();
        attrs.put("ticket", AesUtil.decrypt(encryptTicket));
        Record account = findOne(attrs);
        return account;
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
