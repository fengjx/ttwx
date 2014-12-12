package com.fjx.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 自定义GetPropertiesVal返回properties内容
 *
 * @author donglg 2013-03-20
 * @createDate 2013-3-15 上午08:20:00
 */
public final class GetPropertiesVal {

    //该对象不支持实例化
    private GetPropertiesVal() {

    }

    private static ResourceBundle statusRes;

    static {
        statusRes = ResourceBundle.getBundle("config", Locale.getDefault());
    }

    public static String getLabel(String key) {
    	String res = "";
        try {
        	res = new String(statusRes.getString(key).getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
		}
        return res;
    }

    public static String getProTheme(String themeKey) {
        return StringUtils.isBlank(getLabel(themeKey)) ? "default" : getLabel(themeKey);
    }

    public static void main(String[] args) {
        System.out.println(GetPropertiesVal.getProTheme("domain.page"));
    }

}
