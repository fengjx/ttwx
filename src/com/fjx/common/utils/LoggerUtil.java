package com.fjx.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * 日志工具类
 * 
 * @author jie.hua@alipay.com
 * @version $Id: LoggerUtil.java, v 0.1 2014-1-8 下午8:29:48 jie.hua Exp $
 */
public class LoggerUtil {

    /**
     * info日志
     * 
     * @param logger
     * @param msg
     */
    public static void info(Logger logger, String message) {

        if (logger.isInfoEnabled()) {
            logger.info(logPrefix() + message);
        }
    }

    /**
     * warn日志
     * 
     * @param logger
     * @param message
     */
    public static void warn(Logger logger, String message) {

        logger.warn(logPrefix() + message);

    }

    /**
     * warn日志
     * 
     * @param logger
     * @param message
     * @param t
     */
    public static void warn(Logger logger, String message, Throwable t) {

        logger.warn(logPrefix() + message, t);

    }

    /**
     * error日志
     * 
     * @param logger
     * @param message
     */
    public static void error(Logger logger, String message) {

        logger.error(logPrefix() + message);
    }

    /**
     * error日志
     * 
     * @param logger
     * @param message
     * @param t
     */
    public static void error(Logger logger, String message, Throwable t) {

        logger.error(logPrefix() + message, t);
    }

    /**
     * 获取调用Logger类的调用类,调用方法,和行数,以及统一上下文ID
     * 
     * <p>
     * 如[LoggerUtil-main-165][343434343434]
     * 
     * @return 返回结果
     */
    public static String getCaller() {
        StackTraceElement stack[] = (new Throwable()).getStackTrace();
        StackTraceElement s = stack[3];

        // 类名
        String className = StringUtils.left(s.getFileName(),
            StringUtils.lastIndexOf(s.getFileName(), "."));

        // 方法名
        String methodName = s.getMethodName();

        // 行数
        int lineNumber = s.getLineNumber();

        return "[" + className + "." + methodName + "(" + lineNumber + ")][threadId="
               + getCurrentThreadId() + "]";
    }

    /**
     * 日志打印时前缀添加
     * @return 返回添加前缀后的结果
     */
    private static String logPrefix() {
        return getCaller() + " --> ";
    }

    /**
     * 获取当前线程ID
     * 
     * @return
     */
    private static long getCurrentThreadId() {

        return Thread.currentThread().getId();

    }
}
