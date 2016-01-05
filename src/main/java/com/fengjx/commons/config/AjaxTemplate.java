
package com.fengjx.commons.config;

import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

/**
 * ajax消息模板
 *
 * @version 2016/1/5
 * @Created by FengJianxin
 * @Email xd-fjx@qq.com
 */
public final class AjaxTemplate {

    private AjaxTemplate() {
    }

    public static final String statusMsg = "{\"code\":\"%s\",\"msg\":\"%s\"}";

    public static String getMsg(String code, String msg) {
        return String.format(Locale.ENGLISH, statusMsg, code, msg);
    }

    public static String success(String msg) {
        if (StringUtils.isBlank(msg)) {
            msg = "请求成功";
        }
        return getMsg("1", msg);
    }

    public static String success() {
        return success(null);
    }

    public static String fail(String msg) {
        if (StringUtils.isBlank(msg)) {
            msg = "请求失败";
        }
        return getMsg("0", msg);
    }

    public static String fail() {
        return success(null);
    }

}
