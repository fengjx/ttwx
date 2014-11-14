package com.fjx.wechat.mysdk.constants;




/**
 * 微信请求事件类型
 * @author fengjx
 * @date 2014年9月4日
 */
public class WechatReqEventConstants {
	
	/**
	 * 事件类型：subscribe(订阅)
	 */
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

	/**
	 * 事件类型：unsubscribe(取消订阅)
	 */
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

	/**
	 * 事件类型：CLICK(自定义菜单点击事件)
	 */
	public static final String EVENT_TYPE_CLICK = "CLICK";
	
	/**
	 * 上报地理位置事件
	 */
	public static final String EVENT_TYPE_LOCATION = "LOCATION";
	
	/**
	 * 二维码扫描事件（如果用户已经关注公众号）
	 */
	public static final String EVENT_TYPE_SCAN = "SCAN";
	
	/**
	 * 点击菜单跳转链接时的事件推送
	 */
	public static final String REQ_MESSAGE_TYPE_VIEW = "VIEW";
	
}
