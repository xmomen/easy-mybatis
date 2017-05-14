package com.xmomen.framework.exception;

/**
 * business logic exception
 * Created by tanxinzheng on 16/10/22.
 */
public class BusinessException extends RuntimeException {

    /**
     * Creates a new BusinessException object.
     */
    public BusinessException() {
        super();
    }

    /**
     * Constructs a new invalid parameter exception with the specified detail message.
     * @param message exception message
     */
    public BusinessException(final String message) {
        super(message);
    }

    /**
     * Constructs a new invalid parameter exception with the specified detail message and cause.
     * @param message exception message
     * @param cause exception cause
     */
    public BusinessException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new invalid parameter exception with the specified cause and a
     * detail message of <tt>(cause==null ? null : cause.toString())</tt>
     * (which typically contains the class and detail message of
     * <tt>cause</tt>).
     * @param cause exception cause
     */
    public BusinessException(final Throwable cause) {
        super(cause);
    }
}
