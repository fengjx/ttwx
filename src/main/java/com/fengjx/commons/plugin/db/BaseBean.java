
package com.fengjx.commons.plugin.db;

import java.util.Map;

/**
 * @version 2016/1/22
 * @Created by FengJianxin
 * @Email xd-fjx@qq.com
 */
public abstract class BaseBean extends Record implements Ibean{

    private static final long serialVersionUID = -8525287891605547216L;

    public BaseBean() {
    }

    public BaseBean(Map<String, Object> attrs) {
        super.setColumns(attrs);
    }
}
