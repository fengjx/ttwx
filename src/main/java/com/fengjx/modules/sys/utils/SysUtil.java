/*
 * Copyright (c) 2013-2016 http://git.oschina.net/fengjx/ttwx
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.fengjx.modules.sys.utils;

import com.fengjx.commons.plugin.cache.ehcache.EhCacheUtil;
import com.fengjx.modules.common.constants.AppConfig;

/**
 * @Created by fengjianxin
 * @date 2016/1/11
 */
public final class SysUtil {

    /**
     * 删除系统缓存
     */
    public static void deleteSysCache() {
        EhCacheUtil.removeAll(AppConfig.EhcacheName.SYS_CACHE);
        UserUtil.removeCache(UserUtil.CACHE_MENU_LIST);
    }

}
