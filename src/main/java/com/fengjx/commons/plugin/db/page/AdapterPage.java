
package com.fengjx.commons.plugin.db.page;

import com.fengjx.commons.bean.ToStringBase;
import com.fengjx.commons.utils.JsonUtil;

/**
 * 分页抽象类
 *
 * @Created by FengJianxin on 2015/8/22.
 * @Email xd-fjx@qq.com
 */
public abstract class AdapterPage extends ToStringBase {


    public String toJson() {
        return JsonUtil.toJson(this);
    }

}
