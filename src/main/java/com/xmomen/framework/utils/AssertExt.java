package com.xmomen.framework.utils;

import com.xmomen.framework.exception.InvalidParameterException;
import com.xmomen.framework.exception.InvalidResultException;
import org.springframework.util.Assert;

/**
 * Created by jengt_000 on 2015/1/16.
 */
public class AssertExt extends Assert {

    /**
     * 预期一个无效的结果集，抛出{@code InvalidResultException}
     * @param expression
     * @param message
     */
    public static void isInvalidResult(boolean expression, String message) {
        if (expression) {
            throw new InvalidResultException(message);
        }
    }

    /**
     * 预期一个无效的结果集，抛出默认信息
     * @param expression
     */
    public static void isInvalidResult(boolean expression) {
        isInvalidResult(expression, "This is a invalid Result");
    }

    /**
     * 预期一个无效的参数，抛出{@code InvalidParameterException}
     * @param expression
     * @param message
     */
    public static void isInvalidParameter(boolean expression, String message) {
        if (expression) {
            throw new InvalidParameterException(message);
        }
    }

    /**
     * 预期一个无效的参数，抛出默认信息
     * @param expression
     */
    public static void isInvalidParameter(boolean expression) {
        isInvalidParameter(expression, "This is a invalid parameter object");
    }

    /**
     * 预期一个null的参数对象，抛出{@code InvalidParameterException}
     * @param object
     * @param message
     */
    public static void isNullParameter(Object object, String message) {
        if (null == object) {
            throw new InvalidParameterException(message);
        }
    }

    /**
     * 预期一个null的参数对象，抛出默认信息
     * @param object
     */
    public static void isNullParameter(Object object) {
        isNullParameter(object, "Parameter object must be not null");
    }

    /**
     * 松散的表达式判断，正确则返回对象，错误则返回null
     * @param expression
     * @param object
     * @return
     */
    public static Object isTrueLoose(boolean expression, Object object) {
        if(expression){
            return object;
        }
        return null;
    }

    /**
     * 严格的表达式判断，正确则返回对象，错误则抛出{@code InvalidResultException}
     * @param expression
     * @param object
     * @return
     */
    public static Object isTrueStrict(boolean expression, Object object) {
        return isTrueStrict(expression, object, "This is a invalid Result");
    }

    /**
     * 严格的表达式判断，正确则返回对象，错误则抛出{@code InvalidResultException}
     * @param expression
     * @param object
     * @return
     */
    public static Object isTrueStrict(boolean expression, Object object, String message) {
        if(expression){
            return object;
        }
        throw new InvalidResultException(message);
    }

}
