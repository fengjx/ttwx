/*
 * Copyright (c) 2013-2016 http://git.oschina.net/fengjx/ttwx
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.fengjx.commons.web.converter;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.fengjx.commons.plugin.db.Page;
import com.fengjx.commons.web.page.AdapterPage;
import com.fengjx.commons.web.page.adapter.BootstrapPage;
import com.fengjx.commons.web.page.adapter.JqGridPage;
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

    private String adapterPageName;

    @Override
    protected void writeInternal(Object obj, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        if (obj != null && obj instanceof Page) {
            Page target = (Page) obj;
            if (target.isConvert()) {
                // 分页适配
                AdapterPage page = getPage(target);
                super.writeInternal(page, outputMessage);
                return;
            }
        }
        super.writeInternal(obj, outputMessage);
    }

    @SuppressWarnings("unchecked")
    private AdapterPage getPage(Page page) {
        if (JqGridPage.ADAPTER_PAGE_NAME.equalsIgnoreCase(getAdapterPageName())) {
            return new JqGridPage<>(page);
        } else if (BootstrapPage.ADAPTER_PAGE_NAME.equalsIgnoreCase(getAdapterPageName())) {
            return new BootstrapPage<>(page);
        } else {
            throw new RuntimeException("unknown adapterPage type " + getAdapterPageName());
        }
    }

    public String getAdapterPageName() {
        return adapterPageName;
    }

    public void setAdapterPageName(String adapterPageName) {
        this.adapterPageName = adapterPageName;
    }
}
