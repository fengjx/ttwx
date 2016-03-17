
package com.fengjx.commons.web.page.adapter;

import com.fengjx.commons.plugin.db.Page;
import com.fengjx.commons.web.page.AdapterPage;

import java.util.List;

/**
 * bootstrap-table分页数据包装
 *
 * @author fengjx. @date：2015/5/19 0019
 */
public class BootstrapPage<T> extends AdapterPage {

    public static final String ADAPTER_PAGE_NAME = "BootstrapPage";
    private static final long serialVersionUID = 1L;

    public int total;

    public List<T> rows;

    public BootstrapPage() {
    }

    public BootstrapPage(Page<T> page) {
        this.total = page.getTotalRow();
        this.rows = page.getList();
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

}
