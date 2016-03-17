package com.fengjx.modules.api.tuling.vo.resp;

/**
 *
 code	说明
 100000	文本类数据
 200000	网址类数据
 301000	小说
 302000	新闻
 304000	应用、软件、下载
 305000	列车
 306000	航班
 307000	团购
 //308000	优惠（暂无）
 308000	视频、电影、菜谱
 309000	酒店
 310000	彩票
 311000	价格
 312000	餐厅
 40001	key的长度错误（32位）
 40002	请求内容为空
 40003	key错误或帐号未激活
 40004	当天请求次数已用完
 40005	暂不支持该功能
 40006	服务器升级中
 40007	服务器数据格式异常
 50000	机器人设定的“学用户说话”或者“默认回答”
 * Created by fengjx.
 * Date：2014/12/13 0013
 */
public enum ResutlCode {

    TEXT("100000","文本类",TextBean.class),
    LINK("200000","链接类",LinkBean.class),
    NEWS("302000","新闻",NewsBean.class),
    SOFT("304000","应用、软件、下载",SoftBean.class),
    TRAIN("305000","列车",TrainBean.class),
    FLIGHT("306000","航班",FlightBean.class),
    VIDEO("308000","视频、电影、菜谱",VideoBean.class),
    HOTEL("309000","酒店",HotelBean.class),
    PRICE("311000","价格",PriceBean.class),
    ERROR40001("40001","key的长度错误（32位）",null),
    ERROR40002("40002","请求内容为空",null),
    ERROR40003("40003","key错误或帐号未激活",null),
    ERROR40004("40004","当天请求次数已用完",null),
    ERROR40005("40005","暂不支持该功能",null),
    ERROR40007("40007","服务器升级中",null),
    ERROR40006("40006","服务器数据格式异常",null),
    ERROR50000("50000","机器人设定的“学用户说话”或者“默认回答”",null),
    ERROR00000("00000","未知错误”",null)


    ;

    private String code;
    private String text;
    private Class classz;


    ResutlCode(String code, String text,Class classz){
        this.code = code;
        this.text = text;
        this.classz = classz;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Class getClassz() {
        return classz;
    }

    public void setClassz(Class classz) {
        this.classz = classz;
    }
}
