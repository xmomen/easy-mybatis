package com.udfex.framework.utils;

import java.math.BigDecimal;

/**
 * Created by Jeng on 2015/6/18.
 */
public class BigDecimalUtils {

    public static boolean isMoreThanZero(BigDecimal decimal) {
        if(decimal != null) {
            return decimal.compareTo(BigDecimal.ZERO) > 0;
        }
        return false;
    }

    public static boolean isMoreAndEqualZero(BigDecimal decimal) {
        if(decimal != null) {
            return decimal.compareTo(BigDecimal.ZERO) >= 0;
        }
        return false;
    }

    public static boolean isZero(BigDecimal decimal) {
        if(decimal != null) {
            return decimal.compareTo(BigDecimal.ZERO) == 0;
        }
        return false;
    }

    public static Integer compare(BigDecimal decimal, Integer i) {
        if(decimal != null && i != null) {
            return decimal.compareTo(new BigDecimal(i));
        }
        return null;
    }

    public static Integer compare(BigDecimal decimal, Double d) {
        if(decimal != null && d != null) {
            return decimal.compareTo(new BigDecimal(d));
        }
        return null;
    }
}
