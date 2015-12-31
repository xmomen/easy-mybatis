package com.udfex.framework.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jeng on 2015/3/23.
 */
public enum PatternEnums {

    // 邮箱
    EMAIL("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"),
    // 手机号码
    MOBILE_NUMBER("^[1][3-8]\\d{9}$"),
    // 包括15，18位身份证
    ID_CARD("^(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)|(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[Xx])$)$"),
    // 15位身份证号码
    ID_CARD_15("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$"),
    // 18位身份证号码
    ID_CARD_18("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[Xx])$");

    private String regex;

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    PatternEnums(String regex){
        this.regex = regex;
    }

    public boolean check(String value){
        boolean result = false;
        try{
            Pattern pattern = Pattern.compile(this.regex);
            Matcher matcher = pattern.matcher(value);
            result = matcher.matches();
        }catch (Exception e){
            result = false;
        }
        return result;
    }
}
