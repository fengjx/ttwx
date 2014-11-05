package com.fjx.common.bean;

import java.util.Date;

import com.fjx.common.utils.CommonUtils;


/**
 * 自定义日期类型
 * @author fengjx xd-fjx@qq.com
 * @date 2014年10月7日
 */
public class MyDate extends Date {

	private static final long serialVersionUID = -5826038329195388375L;

	@Override
	public String toString() {
		return CommonUtils.date2String(this);
	}
	
	
	
	
}
