
/*
 * Copyright (c) 2013-2016 http://git.oschina.net/fengjx/ttwx
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.fengjx.commons.web.page;

/**
 * @author fengjx. @date：2015/5/10 0010
 */
public class PageContext {

    // 每页显示条数
    private static ThreadLocal<Integer> pageSize = new ThreadLocal<>();
    // 当前页
    private static ThreadLocal<Integer> pageNumber = new ThreadLocal<>();

    public static int getPageSize() {
        Integer _pagesize = pageSize.get();
        if (_pagesize == null) {
            return Integer.MAX_VALUE;
        }
        return _pagesize;
    }

    public static void setPageSize(int _pagesize) {
        pageSize.set(_pagesize);
    }

    public static int getPageNumber() {
        Integer _pageNumber = pageNumber.get();
        if (_pageNumber == null) {
            return 0;
        }
        return _pageNumber;
    }

    public static void setPageNumber(int _pageNumber) {
        pageNumber.set(_pageNumber);
    }

    public static void noPaging() {
        pageNumber.set(0);
        pageSize.set(Integer.MAX_VALUE);
    }

    public static void removeAll() {
        pageNumber.remove();
        pageSize.remove();
    }
}
