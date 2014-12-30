package com.fjx.common.utils;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import com.fjx.common.framework.system.context.MySystemContext;
import com.fjx.common.framework.system.exception.MyRuntimeException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * @author peng
 */
public final class CommonUtils {


    public static final String DATA_FORMAT_ALL = "yyyy-MM-dd HH:mm:ss";
    public static final String DATA_FORMAT_DD = "yyyy-MM-dd";

    /**
     * 生成1-9位随机数
     *
     * @param count 位数
     * @return 返回count位随机数
     */
    public static String getRandomNum(int count) {
        return RandomStringUtils.randomNumeric(count);
    }

    /**
     * 主键生成器，基于时间戳+机器地址的
     *
     * @return 主键
     */
    public static String getPrimaryKey() {
        EthernetAddress nic = EthernetAddress.fromInterface();
        TimeBasedGenerator uuidGenerator = Generators.timeBasedGenerator(nic);
        UUID uuid = uuidGenerator.generate();
        return uuid.toString().replaceAll("-", "").toLowerCase();
    }

    /**
     * @param date
     * @param dateFmtArgs 变长参数 可以不输入 默认为yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2String(Date date, String... dateFmtArgs) {
        if (null == date) {
            return null;
        }
        String dateFmt = DATA_FORMAT_ALL;
        if (dateFmtArgs.length != 0) {
            dateFmt = dateFmtArgs[0];
        }
        DateFormat fmt = new SimpleDateFormat(dateFmt);
        return fmt.format(date);
    }

    /**
     * @param timeMillis  时间戳
     * @param dateFmtArgs 变长参数 可以不输入 默认为yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String timeMillis2DateString(Long timeMillis, String... dateFmtArgs) {
        Date date = timeMillis2Date(timeMillis);
        String dateFmt = DATA_FORMAT_ALL;
        if (dateFmtArgs.length != 0) {
            dateFmt = dateFmtArgs[0];
        }
        DateFormat fmt = new SimpleDateFormat(dateFmt);
        return fmt.format(date);
    }

    /**
     * @param timeMillis  时间戳
     * @param dateFmtArgs 变长参数 可以不输入 默认为yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date timeMillis2Date(Long timeMillis, String... dateFmtArgs) {
        return new Date(timeMillis);
    }


    /**
     * @param date
     * @param dateFmtArgs 变长参数 可以不输入 默认为yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static Date string2Date(String date, String... dateFmtArgs) {
        String dateFmt = DATA_FORMAT_ALL;
        if (dateFmtArgs.length != 0) {
            dateFmt = dateFmtArgs[0];
        }
        DateFormat fmt = new SimpleDateFormat(dateFmt);
        Date res_date = null;
        try {
            res_date = fmt.parse(date);
        } catch (ParseException e) {
            throw new MyRuntimeException(e);
        }
        return res_date;
    }

    /**
     * @param date
     * @return
     * @throws ParseException
     */
    public static java.sql.Date string2SqlDate(String date) {
        return java.sql.Date.valueOf(date);
    }

    /**
     * @param n 随即生成N个大写字母组成的字符串
     * @return
     */
    public static String randomStr(int n) {
        String str = "";
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            char m = (char) (r.nextInt(26) + 65);
            str += m;
        }
        return str;
    }

    /**
     * 获得生成html文件模板路径
     *
     * @param request
     * @return
     */
    public static String getFtlHtmlPath(HttpServletRequest request) {
        String baseUrl = "/WEB-INF/ftl/html";
        try {
            return WebUtils.getRealPath(request.getSession().getServletContext(), "/") + baseUrl;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
    }
}
