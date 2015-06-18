
package com.fengjx.ttwx.modules.wechat.model;

import com.fengjx.ttwx.common.plugin.cache.CacheFactory;
import com.fengjx.ttwx.common.plugin.cache.CacheName;
import com.fengjx.ttwx.common.plugin.cache.SimpleCache;
import com.fengjx.ttwx.common.plugin.db.Mapper;
import com.fengjx.ttwx.common.plugin.db.Model;
import com.fengjx.ttwx.common.plugin.db.Record;
import com.fengjx.ttwx.modules.wechat.bean.MyWxMpConfigStorage;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;

import org.springframework.stereotype.Component;

/**
 * 公众号信息管理
 *
 * @author fengjx.
 * @date：2015/6/17 0017
 */
@Mapper(table = "wechat_public_account")
@Component
public class PublicAccount extends Model {

    /**
     * 根据userid获得公众账号信息
     *
     * @param userId
     * @return
     */
    public Record getAccountByUserId(String userId) {
        StringBuilder sql = new StringBuilder(getSelectSql());
        sql.append(" where sys_user_id = ?");
        return findOne(sql.toString(), userId);
    }

    /**
     * 获得公众号配置
     *
     * @param userId
     * @return
     */
    public WxMpConfigStorage getWxMpConfigStorage(String userId) {
        SimpleCache cache = CacheFactory.getSimpleCache(CacheName.CACHE_NAME_EHCACHE);
        return cache.get("getWxMpConfigStorage_key", () -> {
            MyWxMpConfigStorage config = new MyWxMpConfigStorage();
            Record record = getAccountByUserId(userId);
            // 设置微信公众号的appid
                config.setAppId(record.getStr("app_id"));
                // 设置微信公众号的app corpSecret
                config.setSecret(record.getStr("app_secret"));
                // 设置微信公众号的token
                config.setToken(record.getStr("token"));
                // 设置微信公众号的EncodingAESKey
                config.setAesKey(record.getStr("encodingAESKey"));
                return config;
            });
    }

    /**
     * 获得公众号
     *
     * @param userId
     * @return
     */
    public WxMpService getWxMpService(String userId) {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(getWxMpConfigStorage(userId));
        return wxMpService;
    }

}
