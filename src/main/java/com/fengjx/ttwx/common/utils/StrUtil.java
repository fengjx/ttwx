
package com.fengjx.ttwx.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String工具
 *
 * @Created by FengJianxin on 2015/9/3.
 * @Email xd-fjx@qq.com
 */
public class StrUtil extends StringUtils {

    private static final String CHARSET_NAME = "UTF-8";

    /**
     * 删除字符串里的空格、回车、制表符
     *
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 删除字符串里的换行符
     *
     * @param str
     * @return
     */
    public static String replaceLineFeed(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\r|\n|\r\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }


    /**
     * 统计字符串个数
     *
     * @param srcStr
     * @param findStr
     * @return
     */
    public static int countStr(String srcStr, String findStr) {
        return countStr(srcStr, findStr, true);
    }

    /**
     * 统计字符串个数
     *
     * @param srcStr
     * @param findStr
     * @param ignoreCase true：忽略大小写
     * @return
     */
    public static int countStr(String srcStr, String findStr, boolean ignoreCase) {
        if (ignoreCase) {
            findStr = findStr.toLowerCase();
            srcStr = srcStr.toLowerCase();
        }
        Pattern pattern = Pattern.compile(findStr.toLowerCase());
        Matcher matcher = pattern.matcher(srcStr);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    /**
     * 转换为字节数组
     * @param str
     * @return
     */
    public static byte[] getBytes(String str){
        if (str != null){
            try {
                return str.getBytes(CHARSET_NAME);
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        }else{
            return null;
        }
    }


    public static void main(String[] args) {
        String ss = "aaagdfgdasda";
        System.out.println(countStr(ss, "a"));
    }
}
