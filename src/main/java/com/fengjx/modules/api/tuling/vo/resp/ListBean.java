package com.fengjx.modules.api.tuling.vo.resp;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by fengjx.
 * Date：2014/12/13 0013
 */
public class ListBean extends BaseRespBean {

    private static final long serialVersionUID = 3786858182944920284L;

    private List<ListBaseBean> list = new ArrayList<ListBaseBean>();

    public ListBean() {
    }

    public List<ListBaseBean> getList() {
        return list;
    }

    public void setList(List<ListBaseBean> list) {
        this.list = list;
    }

    public void add(ListBaseBean bean){
        this.list.add(bean);
    }
    public void addAll(List<ListBaseBean> list){
        this.list.addAll(list);
    }
}
