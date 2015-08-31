
package com.fengjx.ttwx.modules.common.constants;

import com.fengjx.ttwx.common.utils.GetPropertiesVal;

/**
 * 全局配置常量
 * 
 * @author fengjx xd-fjx@qq.com
 * @version AppConfig.java 2014年9月16日
 */
public final class AppConfig {
    // 该对象不支持实例化
    private AppConfig() {
    }

    public static final String TEST_FALG = GetPropertiesVal.getLabel("test_flag");
    // 用户登录信息在session中的key
    public static final String LOGIN_FLAG = "sys_user_login_key";
    public static final String REQUEST_ERROE_MSG_KEY = "errorMsg";
    public static final String REQUEST_FLAG_AJAX = "ajax";

    public static final String APP_NAME = GetPropertiesVal.getLabel("app_name");
    public static final String KEYWORDS = GetPropertiesVal.getLabel("keywords");
    public static final String DESCRIPTION = GetPropertiesVal.getLabel("description");
    public static final String DOMAIN_PAGE = GetPropertiesVal.getLabel("domain.page");
    public static final String RESOURCE_URL = GetPropertiesVal.getLabel("resource.url");
    public static final String STATIC_DOMAIN = GetPropertiesVal.getLabel("staticDomain");
    public static final String STATIC_PATH = GetPropertiesVal.getLabel("staticPath");
    public static final String SUPPORT_EMAIL = GetPropertiesVal.getLabel("mail.username");
    public static final String ADMIN_PATH = GetPropertiesVal.getLabel("adminPath");
    public static final String ADAPTER_PAGE_NAME = GetPropertiesVal.getLabel("adapterPageName");

    /**
     * 是/否
     */
    public static final String YES = "1";
    public static final String NO = "0";

    /**
     * @return true:测试环境
     */
    public static boolean isTest() {
        return YES.equals(AppConfig.TEST_FALG);
    }

    /**
     * 根据参数获得配置
     *
     * @param key
     * @return
     */
    public static String getConfig(String key) {
        return GetPropertiesVal.getLabel(key);
    }

    /**
     * 获取常量
     * 
     * @see ${fns:getConst('YES')}
     */
    public static Object getConst(String field) {
        try {
            return AppConfig.class.getField(field).get(null);
        } catch (Exception e) {
            // 异常代表无配置，这里什么也不做
        }
        return null;
    }

}
