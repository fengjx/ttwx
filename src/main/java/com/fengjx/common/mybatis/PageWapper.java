
package com.fengjx.common.mybatis;

import com.fengjx.common.bean.ToStringBase;

import java.util.List;

/**
 * 分页对象包装类
 *
 * @author fengjx.
 * @date：2015/4/19 0019
 */
public class PageWapper<T> extends ToStringBase {

    private static final long serialVersionUID = 3675693166325733993L;

    private int total;
    private List<T> rows;

    public PageWapper(List<T> rows) {
        this.rows = rows;
        Page page = PageContext.getPage();
        this.setTotal(page.getTotalPage());
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
