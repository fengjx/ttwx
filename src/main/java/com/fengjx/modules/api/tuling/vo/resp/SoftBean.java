package com.fengjx.modules.api.tuling.vo.resp;

/**
 * 软件类
 * Created by fengjx.
 * Date：2014/12/14 0014
 */
public class SoftBean extends ListBaseBean {


    private static final long serialVersionUID = -6926889016809938420L;

    private String name;
    private String count;
    private String detailurl;
    private String icon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getDetailurl() {
        return detailurl;
    }

    public void setDetailurl(String detailurl) {
        this.detailurl = detailurl;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
