
package com.fengjx.commons.plugin.db;

/**
 * @author fengjx.
 * @date：2015/5/9 0009
 */
public class MyDbException extends RuntimeException {

    public MyDbException() {
    }

    public MyDbException(String message) {
        super(message);
    }

    public MyDbException(Throwable throwable) {
        super(throwable);
    }

    public MyDbException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
