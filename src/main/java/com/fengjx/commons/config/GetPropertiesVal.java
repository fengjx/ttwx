
/*
 * Copyright (c) 2013-2016 http://git.oschina.net/fengjx/ttwx
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.fengjx.commons.config;

import org.apache.commons.lang3.StringUtils;

/**
 * 自定义GetPropertiesVal返回properties内容
 *
 * @author donglg 2013-03-20
 * @createDate 2013-3-15 上午08:20:00
 */
public final class GetPropertiesVal {

    // 该对象不支持实例化
    private GetPropertiesVal() {
    }

    public static String getLabel(String key) {
        return PropertiesHolder.INSTANCE.getAppConfig().getProperty(key);
    }

    public static String getProTheme(String themeKey) {
        return StringUtils.isBlank(getLabel(themeKey)) ? "default" : getLabel(themeKey);
    }

    public static void main(String[] args) {
        System.out.println(GetPropertiesVal.getProTheme("domain.page"));
    }

}
