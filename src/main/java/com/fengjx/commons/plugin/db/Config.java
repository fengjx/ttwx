
package com.fengjx.commons.plugin.db;

import com.fengjx.commons.plugin.db.dialect.Dialect;

/**
 * 数据库配置
 *
 * @author fengjx.
 * @date：2015/5/10 0010
 */
public class Config {

    public static Dialect dialect;

    public static String adapterPageName;

    /**
     * 初始化配置信息
     *
     * @param adapterPageName 分页数据适配名称
     * @param dialect 数据库方言
     */
    protected static void init(String adapterPageName, Dialect dialect) {
        Config.dialect = dialect;
        Config.adapterPageName = adapterPageName;
    }

}
