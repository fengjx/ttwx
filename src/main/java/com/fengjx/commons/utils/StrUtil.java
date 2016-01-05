
package com.fengjx.commons.utils;

import com.google.common.base.Joiner;
import org.apache.commons.lang3.RandomStringUtils;
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
            Pattern p = Pattern.compile("\\r|\n|\r\n");
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

    /**
     * 将数组数据转成字符串，并用分隔符隔开
     *
     * @param src
     * @param separator
     * @return
     */
    public static String join(Object[] src, String separator){
        if(null == src || src.length == 0){
            return "";
        }
        return Joiner.on(separator).join(src);
    }

    /**
     * 生成指定长度的字母和数字的随机组合字符串
     *
     * @param count 位数
     * @return
     */
    public static String randomStr(int count){
        return RandomStringUtils.randomAlphanumeric(count);
    }

    /**
     * 生成随机数字
     *
     * @param count 位数
     * @return 返回count位随机数
     */
    public static String getRandomNum(int count) {
        return RandomStringUtils.randomNumeric(count);
    }

    public static void main(String[] args) {
        String ss = "asas asas assasas\n asasas";
        System.out.println(replaceLineFeed(ss));
    }
}
