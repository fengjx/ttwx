package com.fengjx.modules.api.tuling.vo.resp;

/**
 * 酒店类
 * Created by fengjx.
 * Date：2014/12/14 0014
 */
public class HotelBean extends ListBaseBean {

    private static final long serialVersionUID = 7258541588153807410L;

    private String name;
    private String price;
    private String detailurl;
    private String icon;
    private String satisfaction;
    private String count;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public String getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(String satisfaction) {
        this.satisfaction = satisfaction;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
