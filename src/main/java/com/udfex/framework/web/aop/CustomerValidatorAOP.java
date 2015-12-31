package com.udfex.framework.web.aop;

import com.udfex.framework.log.Logger;
import com.udfex.framework.log.LoggerFactory;
import com.udfex.framework.web.exception.ValidationParamtersException;
import com.udfex.framework.web.vo.JsonVo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.validation.Valid;

/*
 * <p>参数校验AOP</p>
 *
 * @author jeng.tam
 * @version 1.0.0
 * @date 2015-03-24
 */

@Aspect
@Component
public class CustomerValidatorAOP {

    Logger log = LoggerFactory.getLogger(CustomerValidatorAOP.class);

    @Autowired(required = false)
    private Validator validator;

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    private void controllerInvocation() {
    }

    @Around("controllerInvocation()")
    public Object aroundController(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Annotation[] annotationList = method.getAnnotations();
        Annotation[][] argAnnotations = method.getParameterAnnotations();
        String[] argNames = methodSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();

        for (int i = 0; i < args.length; i++) {
            if (hasRequestBodyAndValidAnnotations(argAnnotations[i])) {
                Object ret = validateArg(args[i], argNames[i]);

                if (ret != null) {
                    return ret;
                }
            }
        }

        return joinPoint.proceed(args);
    }

    private boolean hasRequestBodyAndValidAnnotations(Annotation[] annotations) {
        if (annotations.length < 2) {
            return false;
        }
        boolean hasValid = false;
        boolean hasRequestBody = false;
        for (Annotation annotation : annotations) {
            if (Valid.class.isInstance(annotation)) {
                hasValid = true;
            } else if (RequestBody.class.isInstance(annotation)) {
                hasRequestBody = true;
            }
            if (hasValid && hasRequestBody) {
                return true;
            }
        }
        return false;
    }

    private JsonVo validateArg(Object arg, String argName) {
        BindingResult result = getBindingResult(arg, argName);
        validator.validate(arg, result);
        if (result.hasErrors()) {
            throw new ValidationParamtersException(result);
        }
        return null;
    }

    private BindingResult getBindingResult(Object target, String targetName) {
        return new BeanPropertyBindingResult(target, targetName);
    }

}
