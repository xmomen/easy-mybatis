package com.udfex.framework.utils;

import org.springframework.beans.BeanUtils;

/**
 * Created by Jeng on 2015/4/18.
 */
public class BeanUtilsExt extends BeanUtils {

    /**
     * 忽略默认审计字段
     * @param source
     * @param target
     */
    public static void copyPropertiesIgnoreDefault(Object source, Object target) {
        BeanUtils.copyProperties(source, target,
                "recordVersion",
                "createUserCode",
                "createTimeZone",
                "createDateTime",
                "updateUserCode",
                "updateDateTime",
                "updateTimeZone");
    }
}
