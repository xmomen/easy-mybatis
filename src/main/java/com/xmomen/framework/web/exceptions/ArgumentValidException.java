package com.xmomen.framework.web.exceptions;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * Created by Jeng on 15/12/1.
 */
public class ArgumentValidException extends Exception {

    private final BindingResult bindingResult;

    public ArgumentValidException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }

    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder("Validation failed for argument at index ")
                .append(", with ").append(this.bindingResult.getErrorCount()).append(" error(s): ");
        for (ObjectError error : this.bindingResult.getAllErrors()) {
            sb.append("[").append(error).append("] ");
        }
        return sb.toString();
    }
}
