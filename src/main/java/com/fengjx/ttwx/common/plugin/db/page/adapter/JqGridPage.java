
package com.fengjx.ttwx.common.plugin.db.page.adapter;

import com.fengjx.ttwx.common.plugin.db.page.AdapterPage;
import com.fengjx.ttwx.common.plugin.db.page.Page;
import com.fengjx.ttwx.common.plugin.db.page.PageContext;

import java.util.List;

/**
 * jqGrid分页数据适配
 *
 * @Created by FengJianxin on 2015/8/22.
 * @Email xd-fjx@qq.com
 */
public class JqGridPage<T> extends AdapterPage {

    public static final String ADAPTER_PAGE_NAME = "JqGridPage";
    private static final long serialVersionUID = 1L;
    // 总记录数
    public Long records;
    // 总页数
    public int total;
    // 当前页
    public int page;
    // 数据
    public List<T> rows;

    public JqGridPage(Page<T> page) {
        this.total = page.getTotalPage();
        this.rows = page.getList();
        this.records = page.getTotalRow();
        this.page = PageContext.getPageNumber();
    }

    public Long getRecords() {
        return records;
    }

    public void setRecords(Long records) {
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

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

}
