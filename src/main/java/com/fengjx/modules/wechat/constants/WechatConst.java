
package com.fengjx.modules.wechat.constants;

/**
 * 上次常量
 *
 * @author FengJianxin
 * @version 2015-05-15
 */
public interface WechatConst {

    class PublicAccount {
        public static final String VALID_STATE_NONACTIVATED = "0"; // 0：未激活
        public static final String VALID_STATE_EXCESS = "1"; // 1：已配置到公众平台
        public static final String VALID_STATE_ACTIVATE = "2"; // 2：已通过客户端校验验证码
    }

}
