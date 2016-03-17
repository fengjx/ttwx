
package com.fengjx.modules.api.tuling.vo.req;

import com.fengjx.commons.bean.ToStringBase;

/**
 * 请求参数
 参数	是否必须	   编码	    长度	     示例	                                       说明
 key	必须	       utf-8	32	     1ca80891c02eb2edb736b8ce41591426	           开发者先注册帐号，激活之后即可获得
 info	必须        utf-8	1~30	 打招呼“你好”，查天气“北京今天天气”	           请求内容
 userid	上下文必须   utf-8	1~32	 eb2edb736	                                   此userid针对开发者自己的每一个用户
 loc	非必须	   utf-8	1~30	 北京中关村	位置信息
 lon	非必须	   utf-8	 	     东经116.234632（小数点后保留6位），需要写为116234632	经度信息
 lat	非必须	   utf-8	 	     北纬40.234632（小数点后保留6位），需要写为40234632	纬度信息
 * Created by fengjx.
 * Date：2014/12/13 0013
 */
public class RequestBean extends ToStringBase {

    private String key;
    private String info;
    private String userid;
    private String loc;
    private String lon;
    private String lat;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
