package com.xmomen.framework.exception;

/**
 * Created by jengt_000 on 2014/12/27.
 */
public class InvalidParameterException extends RuntimeException {

    /**
     * Creates a new InvalidParameterException object.
     */
    public InvalidParameterException() {
        super();
    }

    /**
     * Constructs a new invalid parameter exception with the specified detail message.
     * @param message exception message
     */
    public InvalidParameterException(final String message) {
        super(message);
    }

    /**
     * Constructs a new invalid parameter exception with the specified detail message and cause.
     * @param message exception message
     * @param cause exception cause
     */
    public InvalidParameterException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new invalid parameter exception with the specified cause and a
     * detail message of <tt>(cause==null ? null : cause.toString())</tt>
     * (which typically contains the class and detail message of
     * <tt>cause</tt>).
     * @param cause exception cause
     */
    public InvalidParameterException(final Throwable cause) {
        super(cause);
    }
}
