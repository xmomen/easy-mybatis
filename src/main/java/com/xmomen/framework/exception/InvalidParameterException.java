package com.xmomen.framework.exception;

/**
 * Created by jengt_000 on 2014/12/27.
 */
public class InvalidParameterException extends RuntimeException {

    /**
     * Creates a new GroupException object.
     */
    public InvalidParameterException() {
        super();
    }

    /**
     * @param message
     */
    public InvalidParameterException(final String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public InvalidParameterException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public InvalidParameterException(final Throwable cause) {
        super(cause);
    }
}
