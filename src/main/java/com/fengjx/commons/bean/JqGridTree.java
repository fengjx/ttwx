
package com.fengjx.commons.bean;

import com.fengjx.commons.utils.JsonUtil;

import java.util.List;

/**
 * jqgrid 树
 *
 * @Created by fengjianxin
 * @date 2015/10/25
 */
public class JqGridTree<T> extends ToStringBase {

    List<T> rows;

    public int total;

    public int page;

    public JqGridTree(List<T> rows) {
        this.rows = rows;
    }

    public JqGridTree(List<T> rows, int total, int page) {
        this.rows = rows;
        this.total = total;
        this.page = page;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
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

    public String toJson() {
        return JsonUtil.toJson(this);
    }

}
