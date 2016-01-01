package com.xmomen.framework.exception;

public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 7779099836790360728L;

	/**
	 * Creates a new GroupException object.
	 */
	public BaseException() {
		super();
	}

	/**
	 * @param message
	 */
	public BaseException(final String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BaseException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public BaseException(final Throwable cause) {
		super(cause);
	}
}
