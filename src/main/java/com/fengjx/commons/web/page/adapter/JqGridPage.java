
/*
 * Copyright (c) 2013-2016 http://git.oschina.net/fengjx/ttwx
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.fengjx.commons.web.page.adapter;

import com.fengjx.commons.plugin.db.Page;
import com.fengjx.commons.web.page.AdapterPage;
import com.fengjx.commons.web.page.PageContext;

import java.util.List;

/**
 * jqGrid分页数据适配
 *
 * @Created by FengJianxin on 2015/8/22.
 * @Email xd-fjx@qq.com
 */
public class JqGridPage extends AdapterPage {

    public static final String ADAPTER_PAGE_NAME = "JqGridPage";

    private static final long serialVersionUID = 1L;

    // 总记录数
    public int records;

    // 总页数
    public int total;

    // 当前页
    public int page;

    // 数据
    public List<Object> rows;

    public JqGridPage(Page<Object> page) {
        this.total = page.getTotalPage();
        this.rows = page.getList();
        this.records = page.getTotalRow();
        this.page = PageContext.getPageNumber();
    }

    public int getRecords() {
        return records;
    }

    public void setRecords(int records) {
        this.records = records;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Object> getRows() {
        return rows;
    }

    public void setRows(List<Object> rows) {
        this.rows = rows;
    }

}
