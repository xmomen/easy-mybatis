package com.xmomen.framework.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import java.util.Random;
import java.util.UUID;

/**
 * Created by Jeng on 2015/4/16.
 */
public class StringUtilsExt extends StringUtils {

    /**
     * 生成32位UUID
     *
     * @return
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        return str.replaceAll("-", "");
    }

    /**
     * 生成指定位数UUID
     * @param length
     * @return
     */
    public static String getUUID(int length) {
        Assert.isTrue(length > 0 && length <= 32, "length must be > 0 and <= 32");
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString().replaceAll("-", "");
        return str.substring(0,length);
    }

    /**
     * 生成指定位数的数字
     * @param len
     * @return
     */
    public static String random(int len) {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < len; i++) {
            sb.append(new Random().nextInt(10));
        }
        return sb.toString();
    }

}
