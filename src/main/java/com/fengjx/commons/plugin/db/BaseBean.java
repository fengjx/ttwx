
package com.fengjx.commons.plugin.db;

import java.util.Map;

/**
 * @version 2016/1/22
 * @Created by FengJianxin
 * @Email xd-fjx@qq.com
 */
public abstract class BaseBean extends Record {

    public BaseBean() {
    }

    public BaseBean(Map<String, Object> attrs) {
        super.setColumns(attrs);
    }
}
