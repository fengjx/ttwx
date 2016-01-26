/*
 * Copyright (c) 2013-2016 http://git.oschina.net/fengjx/ttwx
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.fengjx.commons.web.converter;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.fengjx.commons.plugin.db.Page;
import com.fengjx.commons.web.page.AdapterPage;
import com.fengjx.commons.web.page.PageContext;
import com.fengjx.commons.web.page.adapter.BootstrapPage;
import com.fengjx.commons.web.page.adapter.JqGridPage;
import com.fengjx.modules.common.constants.AppConfig;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;

/**
 * 自定义spring mvc输出解析器
 *
 * @Created by fengjianxin
 * @date 2016/1/20
 */
public class MyFastJsonHttpMessageConverter extends FastJsonHttpMessageConverter {

    private static final String ADAPTER_PAGE_NAME = AppConfig.getConfig("adapterPageName");

    @Override
    protected void writeInternal(Object obj, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        if (PageContext.isAutoConvert() && obj instanceof Page) {
            // 分页适配
            AdapterPage page = getPage((Page) obj);
            super.writeInternal(page, outputMessage);
        } else {
            super.writeInternal(obj, outputMessage);
        }
    }

    @SuppressWarnings("unchecked")
    private AdapterPage getPage(Page page) {
        if (JqGridPage.ADAPTER_PAGE_NAME.equalsIgnoreCase(ADAPTER_PAGE_NAME)) {
            return new JqGridPage<>(page);
        } else if (BootstrapPage.ADAPTER_PAGE_NAME.equalsIgnoreCase(ADAPTER_PAGE_NAME)) {
            return new BootstrapPage<>(page);
        } else {
            throw new RuntimeException("unknown adapterPage type " + ADAPTER_PAGE_NAME);
        }
    }

}
