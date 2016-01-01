package com.xmomen.framework.exception;

/**
 * Created by jengt_000 on 2014/12/27.
 */
public class InvalidResultException extends RuntimeException {

    /**
     * Creates a new GroupException object.
     */
    public InvalidResultException() {
        super();
    }

    /**
     * @param message
     */
    public InvalidResultException(final String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public InvalidResultException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public InvalidResultException(final Throwable cause) {
        super(cause);
    }
}
