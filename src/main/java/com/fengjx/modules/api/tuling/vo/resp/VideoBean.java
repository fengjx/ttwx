package com.fengjx.modules.api.tuling.vo.resp;

/**
 * 电影、视频、菜谱类
 * Created by fengjx.
 * Date：2014/12/14 0014
 */
public class VideoBean extends ListBaseBean {


    private static final long serialVersionUID = -2399248942164813189L;

    private String name;
    private String info;
    private String detailurl;
    private String icon;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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
