
package com.fengjx.commons.plugin.db;

import com.fengjx.commons.plugin.db.dialect.Dialect;

/**
 * 数据库配置
 *
 * @version 2016/1/23
 * @Created by FengJianxin
 * @Email xd-fjx@qq.com
 */
public class Config {

    // 数据库方言
    public static Dialect dialect;
    // 主键生成器
    public static IdGenerator idGenerator = new IdDefaultGen();
    // 是否自动生成ID
    public static boolean autoId = true;

    protected static void init(Dialect dialect) {
        Config.dialect = dialect;
    }

    protected static void init(Dialect dialect, IdGenerator idGenerator) {
        Config.dialect = dialect;
        Config.idGenerator = idGenerator;
    }

    protected static void init(Dialect dialect, IdGenerator idGenerator,
            boolean autoId) {
        Config.dialect = dialect;
        Config.idGenerator = idGenerator;
        Config.autoId = autoId;
    }
}
