package com.fengjx.modules.api.tuling.vo.resp;


/**
 *
 * 新闻类实体
 * Created by fengjx.
 * Date：2014/12/13 0013
 */
public class NewsBean extends ListBaseBean {

    private static final long serialVersionUID = 1764527947233403113L;

    private String article;
    private String source;
    private String detailurl;
    private String icon;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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
