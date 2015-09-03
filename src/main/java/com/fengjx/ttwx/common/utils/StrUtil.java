
package com.fengjx.ttwx.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String工具
 *
 * @Created by FengJianxin on 2015/9/3.
 * @Email xd-fjx@qq.com
 */
public class StrUtil extends StringUtils {


    /**
     * 删除空格、回车、制表符
     *
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

}
