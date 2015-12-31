package com.udfex.framework.exception;

/**
 * Created by jengt_000 on 2014/12/27.
 */
public class SystemException extends RuntimeException {

    /**
     * Creates a new GroupException object.
     */
    public SystemException() {
        super();
    }

    /**
     * @param message
     */
    public SystemException(final String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public SystemException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public SystemException(final Throwable cause) {
        super(cause);
    }
}
