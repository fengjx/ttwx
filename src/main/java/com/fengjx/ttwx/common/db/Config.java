package com.fengjx.ttwx.common.db;

import com.fengjx.ttwx.common.db.dialect.Dialect;
import com.fengjx.ttwx.common.db.dialect.MysqlDialect;

/**
 * 数据库配置
 *
 * @author fengjx.
 * @date：2015/5/10 0010
 */
public class Config {

    protected static final Dialect dialect = new MysqlDialect();


}
