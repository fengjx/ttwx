
/*
 * Copyright (c) 2013-2016 http://git.oschina.net/fengjx/ttwx
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.fengjx.commons.web.page;

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
