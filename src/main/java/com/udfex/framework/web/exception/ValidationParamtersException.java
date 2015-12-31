package com.udfex.framework.web.exception;

import com.udfex.framework.exception.BusinessException;
import org.springframework.validation.BindingResult;

/**
 * Created by Jeng on 2015/3/23.
 */
public class ValidationParamtersException extends BusinessException {

    private BindingResult bindingResult;

    public BindingResult getBindingResult() {
        return bindingResult;
    }

    public void setBindingResult(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    /**
     * @param bindingResult
     */
    public ValidationParamtersException(BindingResult bindingResult) {
        super(bindingResult.getFieldError().getDefaultMessage());
        this.bindingResult = bindingResult;
    }
}
