package com.udfex.framework.exception;

/**
 * Created by jengt_000 on 2015/1/17.
 */
public enum ExceptionEnum {
    BASE_EXCEPTION,
    SYSTEM_EXCEPTION;

    public class ExceptionCode {
        public static final int BASE_EXCEPTION_CODE = 10000;
        public static final int SYSTEM_EXCEPTION_CODE = 10001;
        public static final int INVALID_PARAMETER_EXCEPTION_CODE = 10022;
        public static final int INVALID_RESULT_EXCEPTION_CODE = 10023;
        public static final int BUSINESS_EXCEPTION_CODE = 10050;
        public static final int IN_OUT_EXCEPTION_CODE = 10020;
    }
}
