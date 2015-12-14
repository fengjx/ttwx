
package com.fengjx.commons.system.exception;

/**
 * ValidateException support short circuit implementation.
 */
public class ValidateException extends MyRuntimeException {
    private static final long serialVersionUID = 20920496215941871L;

	public ValidateException() {
	}

	public ValidateException(String errorMsg) {
		super(errorMsg);
	}

}
