package com.xmomen.framework.web.rest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * Created by Jeng on 15/11/29.
 */
@ControllerAdvice
public class RestExceptionHandler {

    private static final Log logger = LogFactory.getLog(RestExceptionHandler.class);

    public static final String PAGE_NOT_FOUND_LOG_CATEGORY = "org.springframework.web.servlet.PageNotFound";
    protected static final Log pageNotFoundLogger = LogFactory.getLog("org.springframework.web.servlet.PageNotFound");

    @ExceptionHandler({NoSuchRequestHandlingMethodException.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            HttpMediaTypeNotAcceptableException.class,
            MissingServletRequestParameterException.class,
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            MethodArgumentNotValidException.class,
            MissingServletRequestPartException.class,
            BindException.class,
            NoHandlerFoundException.class})
    public final ResponseEntity<Object> handleException(Exception ex, HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status;
        if(ex instanceof NoSuchRequestHandlingMethodException) {
            status = HttpStatus.NOT_FOUND;
            return handleNoSuchRequestHandlingMethod((NoSuchRequestHandlingMethodException)ex, headers, status, request);
        } else if(ex instanceof HttpRequestMethodNotSupportedException) {
            status = HttpStatus.METHOD_NOT_ALLOWED;
            return handleHttpRequestMethodNotSupported((HttpRequestMethodNotSupportedException)ex, headers, status, request);
        } else if(ex instanceof HttpMediaTypeNotSupportedException) {
            status = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
            return handleHttpMediaTypeNotSupported((HttpMediaTypeNotSupportedException)ex, headers, status, request);
        } else if(ex instanceof HttpMediaTypeNotAcceptableException) {
            status = HttpStatus.NOT_ACCEPTABLE;
            return handleHttpMediaTypeNotAcceptable((HttpMediaTypeNotAcceptableException)ex, headers, status, request);
        } else if(ex instanceof MissingServletRequestParameterException) {
            status = HttpStatus.BAD_REQUEST;
            return handleMissingServletRequestParameter((MissingServletRequestParameterException)ex, headers, status, request);
        } else if(ex instanceof ServletRequestBindingException) {
            status = HttpStatus.BAD_REQUEST;
            return handleServletRequestBindingException((ServletRequestBindingException)ex, headers, status, request);
        } else if(ex instanceof ConversionNotSupportedException) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleConversionNotSupported((ConversionNotSupportedException)ex, headers, status, request);
        } else if(ex instanceof TypeMismatchException) {
            status = HttpStatus.BAD_REQUEST;
            return handleTypeMismatch((TypeMismatchException)ex, headers, status, request);
        } else if(ex instanceof HttpMessageNotReadableException) {
            status = HttpStatus.BAD_REQUEST;
            return handleHttpMessageNotReadable((HttpMessageNotReadableException)ex, headers, status, request);
        } else if(ex instanceof HttpMessageNotWritableException) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleHttpMessageNotWritable((HttpMessageNotWritableException)ex, headers, status, request);
        } else if(ex instanceof MethodArgumentNotValidException) {
            status = HttpStatus.BAD_REQUEST;
            return handleMethodArgumentNotValid((MethodArgumentNotValidException)ex, headers, status, request);
        } else if(ex instanceof MissingServletRequestPartException) {
            status = HttpStatus.BAD_REQUEST;
            return handleMissingServletRequestPart((MissingServletRequestPartException)ex, headers, status, request);
        } else if(ex instanceof BindException) {
            status = HttpStatus.BAD_REQUEST;
            return handleBindingResult(((BindException) ex).getBindingResult(), (BindException)ex, null, headers, status, request);
        } else if(ex instanceof NoHandlerFoundException) {
            status = HttpStatus.NOT_FOUND;
            return handleNoHandlerFoundException((NoHandlerFoundException)ex, headers, status, request);
        } else {
            logger.warn("Unknown exception type: " + ex.getClass().getName());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex, (Object)null, headers, status, request);
        }
    }

    protected ResponseEntity<Object> handleNoSuchRequestHandlingMethod(NoSuchRequestHandlingMethodException ex, HttpHeaders headers, HttpStatus status, HttpServletRequest request) {
        pageNotFoundLogger.warn(ex.getMessage());
        return handleExceptionInternal(ex, (Object) null, headers, status, request);
    }

    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, HttpServletRequest request) {
        pageNotFoundLogger.warn(ex.getMessage());
        Set supportedMethods = ex.getSupportedHttpMethods();
        if(!supportedMethods.isEmpty()) {
            headers.setAllow(supportedMethods);
        }

        return handleExceptionInternal(ex, (Object) null, headers, status, request);
    }

    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, HttpServletRequest request) {
        List mediaTypes = ex.getSupportedMediaTypes();
        if(!CollectionUtils.isEmpty(mediaTypes)) {
            headers.setAccept(mediaTypes);
        }

        return handleExceptionInternal(ex, (Object) null, headers, status, request);
    }

    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, HttpServletRequest request) {
        return handleExceptionInternal(ex, (Object) null, headers, status, request);
    }

    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, HttpServletRequest request) {
        return handleExceptionInternal(ex, (Object) null, headers, status, request);
    }

    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, HttpServletRequest request) {
        return handleExceptionInternal(ex, (Object) null, headers, status, request);
    }

    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, HttpServletRequest request) {
        return handleExceptionInternal(ex, (Object) null, headers, status, request);
    }

    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, HttpServletRequest request) {
        return handleExceptionInternal(ex, (Object) null, headers, status, request);
    }

    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, HttpServletRequest request) {
        return handleExceptionInternal(ex, (Object) null, headers, status, request);
    }

    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, HttpServletRequest request) {
        return handleExceptionInternal(ex, (Object) null, headers, status, request);
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, HttpServletRequest request) {
//        return handleExceptionInternal(ex, (Object) null, headers, status, request);
        return handleBindingResult(ex.getBindingResult(), ex, null, headers, status, request);
    }

    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, HttpServletRequest request) {
        return handleExceptionInternal(ex, (Object) null, headers, status, request);
    }

    protected ResponseEntity<Object> handleBindingResult(BindingResult bindingResult,Exception ex, Object body, HttpHeaders headers, HttpStatus status, HttpServletRequest request) {
        RestError restError = new RestError(ex, request);
        restError.setStatus(HttpStatus.BAD_REQUEST.value());
        restError.setMessage("非法请求参数，校验请求参数不合法");
        BindingResult result = bindingResult;
        List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();
        List<FieldError> fieldErrorList = new ArrayList<FieldError>();
        for (org.springframework.validation.FieldError fieldError : fieldErrors) {
            FieldError error = new FieldError();
//            error.setMessage(resolveLocalizedErrorMessage(fieldError));//国际化处理
            error.setMessage(fieldError.getDefaultMessage());
            error.setField(fieldError.getField());
            error.setRejectedValue(fieldError.getRejectedValue());
            error.setObjectName(fieldError.getObjectName());
            fieldErrorList.add(error);
        }
        if(!CollectionUtils.isEmpty(fieldErrorList)){
            FieldError fieldError = fieldErrorList.get(0);
            if(StringUtils.trimToNull(fieldError.getField()) != null && StringUtils.trimToNull(fieldError.getMessage()) != null ){
                restError.setMessage(fieldError.getMessage());
            }
        }
        restError.setErrors(fieldErrorList);
        return new ResponseEntity<Object>(restError, HttpStatus.BAD_REQUEST);
    }

    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, HttpServletRequest request) {
        return handleExceptionInternal(ex, (Object) null, headers, status, request);
    }
    @Autowired
    private MessageSource messageSource;

    /**
     * 解析错误信息
     * TODO 国际化错误信息应该异常中处理
     * @param fieldError
     * @return
     */
    private String resolveLocalizedErrorMessage(org.springframework.validation.FieldError fieldError) {
        Locale currentLocale =  LocaleContextHolder.getLocale();
        String localizedErrorMessage = null;
        if(messageSource != null){
            try {
                localizedErrorMessage = messageSource.getMessage(fieldError.getCode(), fieldError.getArguments(), currentLocale);
            }catch (NoSuchMessageException noSuchMessageException){
                logger.debug(noSuchMessageException);
                noSuchMessageException.printStackTrace();
            }
        }
        //If the message was not found, return the most accurate field error code instead.
        //You can remove this check if you prefer to get the default error message.
        if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
            String[] fieldErrorCodes = fieldError.getCodes();
            localizedErrorMessage = fieldErrorCodes[0];
        }

        return localizedErrorMessage;
    }

    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
                                                             HttpHeaders headers, HttpStatus status, HttpServletRequest request) {
        if(HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            WebRequest webRequest = (WebRequest) request;
            webRequest.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        if(WebCommonUtils.isJSON(request)){
            RestError restError = new RestError(ex, request);
            restError.setStatus(status.value());
            return new ResponseEntity(restError, headers, status);
        }

        return new ResponseEntity(body, headers, status);
    }

}
