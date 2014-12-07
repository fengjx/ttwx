package com.fjx.wechat.mysdk.exception;

/**
 * 微信API处理异常
 */
public class WechatException extends RuntimeException{

    public WechatException() {
        super();
    }

    public WechatException(String message) {
        super(message);
    }

    public WechatException(String message, Throwable cause) {
        super(message, cause);
    }

    public WechatException(Throwable cause) {
        super(cause);
    }
}
