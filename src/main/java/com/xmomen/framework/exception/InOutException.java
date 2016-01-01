package com.xmomen.framework.exception;

/**
 * Created by jengt_000 on 2014/12/23.
 */
public class InOutException extends BaseException {

    /**
     * Creates a new GroupException object.
     */
    public InOutException() {
        super();
    }

    /**
     * @param message
     */
    public InOutException(final String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public InOutException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public InOutException(final Throwable cause) {
        super(cause);
    }
}
