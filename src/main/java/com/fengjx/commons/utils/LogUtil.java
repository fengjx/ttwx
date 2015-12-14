
package com.fengjx.commons.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

/**
 * 日志工具类
 * 
 * @author jie.hua@alipay.com
 * @version $Id: LoggerUtil.java, v 0.1 2014-1-8 下午8:29:48 jie.hua Exp $
 */
public final class LogUtil {

    /**
     * info日志
     *
     * @param logger
     * @param message
     */
    public static void debug(Logger logger, String message, Object... arguments) {
        if (logger.isInfoEnabled()) {
            FormattingTuple ft = MessageFormatter.arrayFormat(message, arguments);
            logger.debug(logPrefix(ft.getMessage()), ft.getThrowable());
        }
    }

    /**
     * info日志
     * 
     * @param logger
     * @param message
     */
    public static void info(Logger logger, String message, Object... arguments) {
        if (logger.isInfoEnabled()) {
            FormattingTuple ft = MessageFormatter.arrayFormat(message, arguments);
            logger.info(logPrefix(ft.getMessage()), ft.getThrowable());
        }
    }

    /**
     * warn日志
     * 
     * @param logger
     * @param message
     */
    public static void warn(Logger logger, String message, Object... arguments) {
        if (logger.isWarnEnabled()) {
            FormattingTuple ft = MessageFormatter.arrayFormat(message, arguments);
            logger.warn(logPrefix(ft.getMessage()), ft.getThrowable());
        }
    }

    /**
     * error日志
     * 
     * @param logger
     * @param message
     */
    public static void error(Logger logger, String message, Object... arguments) {
        if (logger.isErrorEnabled()) {
            FormattingTuple ft = MessageFormatter.arrayFormat(message, arguments);
            logger.error(logPrefix(ft.getMessage()), ft.getThrowable());
        }
    }

    /**
     * 获取调用Logger类的调用类,调用方法,和行数,以及统一上下文ID
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
     * 
     * @return 返回添加前缀后的结果
     */
    private static String logPrefix(String msg) {
        StringBuilder logMsg = new StringBuilder(getCaller());
        logMsg.append("\n+++++++++++++++++++++++++++++++++++++++++++++++\n");
        // 删除日志里的换行符
        logMsg.append(StrUtil.replaceLineFeed(msg));
        logMsg.append("\n+++++++++++++++++++++++++++++++++++++++++++++++");
        return logMsg.toString();
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
