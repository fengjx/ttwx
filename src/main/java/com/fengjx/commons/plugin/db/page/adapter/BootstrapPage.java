
package com.fengjx.commons.plugin.db.page.adapter;

import com.fengjx.commons.plugin.db.page.AdapterPage;
import com.fengjx.commons.plugin.db.page.Page;

import java.util.List;

/**
 * bootstrap-table分页数据包装
 *
 * @author fengjx.
 * @date：2015/5/19 0019
 */
public class BootstrapPage<T> extends AdapterPage {

    public static final String ADAPTER_PAGE_NAME = "BootstrapPage";
    private static final long serialVersionUID = 1L;

    public Long total;

    public List<T> rows;

    public BootstrapPage() {
    }

    public BootstrapPage(Page<T> page) {
        this.total = page.getTotalRow();
        this.rows = page.getList();
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

}
