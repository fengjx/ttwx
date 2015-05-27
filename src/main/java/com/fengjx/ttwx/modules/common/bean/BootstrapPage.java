
package com.fengjx.ttwx.modules.common.bean;

import com.fengjx.ttwx.common.bean.ToStringBase;
import com.fengjx.ttwx.common.db.Page;
import com.fengjx.ttwx.common.utils.JsonUtil;

import java.util.List;

/**
 * bootstrap-table分页数据包装
 *
 * @author fengjx.
 * @date：2015/5/19 0019
 */
public class BootstrapPage<T> extends ToStringBase {

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

    public String toJson(){
        return JsonUtil.toJson(this);
    }

}
