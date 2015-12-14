
package com.fengjx.commons.plugin.db.page;

import com.fengjx.commons.plugin.db.Config;
import com.fengjx.commons.plugin.db.page.adapter.BootstrapPage;
import com.fengjx.commons.plugin.db.page.adapter.JqGridPage;
import com.fengjx.commons.utils.JsonUtil;

import java.io.Serializable;
import java.util.List;

/**
 * 分页实体
 * 
 * @param <T>
 */
public class Page<T> implements Serializable {

    private static final long serialVersionUID = -5395997221963176643L;

    private List<T> list; // list result of this page
    private int pageNumber; // page number
    private int pageSize; // result amount of this page
    private int totalPage; // total page
    private Long totalRow; // total row

    /**
     * Constructor.
     * 
     * @param list the list of paginate result
     * @param pageNumber the page number
     * @param pageSize the page size
     * @param totalPage the total page of paginate
     * @param totalRow the total row of paginate
     */
    public Page(List<T> list, int pageNumber, int pageSize, int totalPage, Long totalRow) {
        this.list = list;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.totalRow = totalRow;
    }

    /**
     * Return list of this page.
     */
    public List<T> getList() {
        return list;
    }

    /**
     * Return page number.
     */
    public int getPageNumber() {
        return pageNumber;
    }

    /**
     * Return page size.
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Return total page.
     */
    public int getTotalPage() {
        return totalPage;
    }

    /**
     * Return total row.
     */
    public Long getTotalRow() {
        return totalRow;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    /**
     * 分页数据转换
     *
     * @param adapterPageName
     * @return
     */
    public AdapterPage convert(String adapterPageName) {
        if (JqGridPage.ADAPTER_PAGE_NAME.equalsIgnoreCase(adapterPageName)) {
            return new JqGridPage<>(this);
        } else if (BootstrapPage.ADAPTER_PAGE_NAME.equalsIgnoreCase(adapterPageName)) {
            return new BootstrapPage<>(this);
        } else {
            throw new RuntimeException("unknown adapterPage type " + adapterPageName);
        }
    }

    public AdapterPage convert() {
        return convert(Config.adapterPageName);
    }

}
