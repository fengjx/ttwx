
package com.fengjx.commons.system.exception;

/**
 * 自定义异常
 *
 * @author fengjx
 */
public class MyRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -5710488447295073398L;

    public MyRuntimeException() {
    }

    public MyRuntimeException(String message) {
        super(message);
    }

    public MyRuntimeException(Throwable throwable) {
        super(throwable);
    }

    public MyRuntimeException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
