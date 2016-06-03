package com.xmomen.framework.web.rest;

import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.framework.web.exceptions.NotFoundResourcesException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Jeng on 15/11/29.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Log logger = LogFactory
            .getLog(GlobalExceptionHandler.class);

    /**
     * 未找到相应资源
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(NotFoundResourcesException.class)
    public ResponseEntity<RestError> handleNotFoundException(NotFoundResourcesException ex, HttpServletRequest request) {
        RestError restError = new RestError(ex, request);
        restError.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<RestError>(restError, HttpStatus.NOT_FOUND);
    }

    MessageSource messageSource;

    /**
     * 非法请求参数校验异常
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(ArgumentValidException.class)
    public ResponseEntity processValidationError(ArgumentValidException ex, HttpServletRequest request) {
        RestError restError = new RestError(ex, request);
        restError.setStatus(HttpStatus.BAD_REQUEST.value());
        restError.setMessage("非法请求参数，校验请求参数不合法");
        BindingResult result = ex.getBindingResult();
        List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();
        List<FieldError> fieldErrorList = new ArrayList<FieldError>();
        for (org.springframework.validation.FieldError fieldError : fieldErrors) {
            FieldError error = new FieldError();
            error.setMessage(fieldError.getDefaultMessage());
            error.setField(fieldError.getField());
            error.setRejectedValue(fieldError.getRejectedValue());
            error.setObjectName(fieldError.getObjectName());
            fieldErrorList.add(error);
        }
        if(!CollectionUtils.isEmpty(fieldErrorList)){
            FieldError fieldError = fieldErrorList.get(0);
            if(StringUtils.trimToNull(fieldError.getField()) != null && StringUtils.trimToNull(fieldError.getMessage()) != null ){
                restError.setMessage(fieldError.getField()+fieldError.getMessage());
            }
        }
        restError.setErrors(fieldErrorList);
        return new ResponseEntity<RestError>(restError, HttpStatus.BAD_REQUEST);
    }

    /**
     * 解析错误信息
     * TODO 国际化错误信息应该异常中处理
     * @param fieldError
     * @return
     */
    private String resolveLocalizedErrorMessage(org.springframework.validation.FieldError fieldError) {
        Locale currentLocale =  LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);

        //If the message was not found, return the most accurate field error code instead.
        //You can remove this check if you prefer to get the default error message.
        if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
            String[] fieldErrorCodes = fieldError.getCodes();
            localizedErrorMessage = fieldErrorCodes[0];
        }

        return localizedErrorMessage;
    }

}
