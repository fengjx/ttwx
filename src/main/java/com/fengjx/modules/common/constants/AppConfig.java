
package com.fengjx.modules.common.constants;

import com.fengjx.commons.config.GetPropertiesVal;
import org.apache.commons.lang3.StringUtils;

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

    public static final String APP_ENCODED = GetPropertiesVal.getLabel("app.encoding");

    public static final String TEST_FALG = GetPropertiesVal.getLabel("test_flag");

    // 用户登录信息在session中的key
    public static final String LOGIN_FLAG = "sys_user_login_key";

    public static final String REQUEST_ERROE_MSG_KEY = GetPropertiesVal.getLabel("http.error.key");

    public static final String REQUEST_FLAG_AJAX = "ajax";

    public static final String APP_NAME = GetPropertiesVal.getLabel("app_name");

    public static final String KEYWORDS = GetPropertiesVal.getLabel("keywords");

    public static final String DESCRIPTION = GetPropertiesVal.getLabel("description");

    public static final String DOMAIN_PAGE = GetPropertiesVal.getLabel("domain.page");

    public static final String RESOURCE_URL = GetPropertiesVal.getLabel("resource.url");

    public static final String STORAGE_TYPE = GetPropertiesVal.getLabel("storage.type");

    public static final String STATIC_DOMAIN = GetPropertiesVal.getLabel("staticDomain");

    public static final String STATIC_PATH = GetPropertiesVal.getLabel("staticPath");

    public static final String SUPPORT_EMAIL = GetPropertiesVal.getLabel("mail.username");

    public static final String STATIC_FILE = GetPropertiesVal.getLabel("web.staticFile");

    public static final String ADMIN_PATH = GetPropertiesVal.getLabel("adminPath");

    public static final String API_PATH = GetPropertiesVal.getLabel("apiPath");

    public static final String ADAPTER_PAGE_NAME = GetPropertiesVal.getLabel("adapterPageName");

    public static final String UEDITOR_SAVEPATH = GetPropertiesVal.getLabel("ueditor.savePath");
    public static final String UEDITOR_FILENAMEFORMAT = GetPropertiesVal
            .getLabel("ueditor.fileNameFormat");

    /**
     * 是/否
     */
    public static final String YES = "1";

    public static final String NO = "0";

    /**
     * 对/错
     */
    public static final String TRUE = "true";
    public static final String FALSE = "false";

    /**
     * @return true:测试环境
     */
    public static boolean isTest() {
        return YES.equals(AppConfig.TEST_FALG);
    }

    /**
     * 是否使用七牛云存储
     *
     * @return
     */
    public static boolean isQiniu() {
        return "qiniu".equals(STORAGE_TYPE);
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

    /**
     * 获得静态文件后缀名数组
     *
     * @return
     */
    public static String[] getStaticFiles() {
        return StringUtils.split(STATIC_FILE, ",");
    }

    /**
     * ehcache名称
     */
    public static class EhcacheName {
        public static final String DEFAULT_CACHE = "defaultCache";

        public static final String SYS_CACHE = "sysCache";

        public static final String DICT_CACHE = "dictCache";

        public static final String WECHAT_ACTION_CACHE = "wechatActionCache";

        public static final String WECHAT_KEYWORD_ACTION_CACHE = "wechatKeywordActionCache";

        public static final String WECHAT_PUBLIC_ACCOUNT = "wechatPublicAccount";
    }

}
