package com.fengjx.ttwx.common.plugin.db;

import com.fengjx.ttwx.common.plugin.db.dialect.Dialect;
import com.fengjx.ttwx.common.plugin.db.dialect.MysqlDialect;

/**
 * 数据库配置
 *
 * @author fengjx.
 * @date：2015/5/10 0010
 */
public class Config {

    protected static final Dialect dialect = new MysqlDialect();


}
