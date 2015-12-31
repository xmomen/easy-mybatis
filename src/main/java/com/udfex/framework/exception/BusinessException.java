package com.udfex.framework.exception;

import com.udfex.framework.enums.ErrorCode;

/**
 * <p>业务服务异常</p>
 * @author Jeng
 * @date 2014年3月30日 下午7:34:35
 */
public class BusinessException extends BaseException {

	private static final long serialVersionUID = 1L;

	public BusinessException() {
		super();
	}

	public BusinessException(final String message) {
		super(message);
	}

	public BusinessException(final ErrorCode errorCode) {
		super(errorCode.getErrorCode());
	}

	public BusinessException(final ErrorCode errorCode, final Throwable cause) {
		super(errorCode.getErrorCode(), cause);
	}

	public BusinessException(final Throwable cause) {
		super(cause);
	}

	public BusinessException(final String message, final Throwable cause) {
		super(message, cause);
	}
}

