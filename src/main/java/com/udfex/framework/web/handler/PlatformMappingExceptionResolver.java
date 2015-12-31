package com.udfex.framework.web.handler;

import com.udfex.framework.exception.BusinessException;
import com.udfex.framework.log.Logger;
import com.udfex.framework.log.LoggerFactory;
import com.udfex.framework.web.exception.ExceptionI18Message;
import com.udfex.framework.web.exception.ValidationParamtersException;
import com.udfex.framework.web.vo.JsonVo;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Jeng on 2015/3/24.
 */
public class PlatformMappingExceptionResolver extends SimpleMappingExceptionResolver {

    static Logger logger = LoggerFactory.getLogger(PlatformMappingExceptionResolver.class);

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request,
                                              HttpServletResponse response,
                                              Object handler,
                                              Exception exception) {
        String viewName = determineViewName(exception, request);
        if (viewName != null) {
            if (!(request.getHeader("accept").indexOf("application/json") > -1 ||
                    (request.getHeader("X-Requested-With") != null &&
                            request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
                // 非异步方式返回
                Integer statusCode = determineStatusCode(request, viewName);
                if (statusCode != null) {
                    applyStatusCodeIfPossible(request, response, statusCode);
                }
                // 跳转到提示页面
                return getModelAndView(viewName, exception, request);
            }
            return ajaxResolveException(request, response, handler, exception);
        }
        return ajaxResolveException(request, response, handler, exception);
    }

    /**
     * ajax请求异常信息返回
     * @param request
     * @param response
     * @param handler
     * @param exception
     * @return
     */
    protected ModelAndView ajaxResolveException(HttpServletRequest request,
                                              HttpServletResponse response,
                                              Object handler,
                                              Exception exception){
        // 判断异常是否为业务异常
        if (exception instanceof BusinessException) {
            // 业务级异常
            logger.debug(exception.getMessage(), exception);
            JsonVo jsonVo = new JsonVo();
            // jsonVo.setMsg(ExceptionI18Message.getLocaleMessage(exception.getMessage())); TODO 暂时无国际化配置，后期添加
            jsonVo.setMsg(exception.getMessage());
            jsonVo.setExceptionMsg(exception.getMessage());
            jsonVo.setStatus(JsonVo.STATUS_BUSINESS_EXCEPTION);
            if(exception instanceof ValidationParamtersException){
                BindingResult bindingResult = ((ValidationParamtersException) exception).getBindingResult();
                if(bindingResult != null){
                    jsonVo.setErrors(bindingResult.getAllErrors());
                }
            }
            return new ModelAndView().addObject(jsonVo);
        }
        if(request.getHeader("accept").indexOf("application/json") > -1){
            logger.error(exception.getMessage(), exception);
            JsonVo jsonVo = new JsonVo();
            jsonVo.setExceptionMsg(exception.getMessage());
            jsonVo.setStatus(JsonVo.STATUS_SYSTEM_EXCEPTION);
            return new ModelAndView().addObject(jsonVo);
        }
        //系统级异常返回默认页面异常
        return null;
    }

}
