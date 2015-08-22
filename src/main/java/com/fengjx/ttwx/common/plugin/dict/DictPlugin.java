package com.fengjx.ttwx.common.plugin.dict;

import com.fengjx.ttwx.common.plugin.IPlugin;

/**
 * Created by FengJianxin on 2015/8/22.
 * Email xd-fjx@qq.com
 */
public class DictPlugin implements IPlugin {

    private DictService service;

    @Override
    public void start() {
        DictUtil.init(service);
    }

    public DictService getService() {
        return service;
    }

    public void setService(DictService service) {
        this.service = service;
    }
}
