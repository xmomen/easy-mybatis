package com.xmomen.framework.exception;

/**
 * <p>业务服务异常</p>
 * @author Jeng
 * @date 2014年3月30日 下午7:34:35
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BusinessException() {
		super();
	}

	public BusinessException(final String message) {
		super(message);
	}

	public BusinessException(final Throwable cause) {
		super(cause);
	}

	public BusinessException(final String message, final Throwable cause) {
		super(message, cause);
	}
}

