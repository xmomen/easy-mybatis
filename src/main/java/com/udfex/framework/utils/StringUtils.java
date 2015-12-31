package com.udfex.framework.utils;

import org.springframework.util.Assert;

import java.util.UUID;

/**
 *
 * Created by jengt_000 on 2014/12/23.
 */
@Deprecated
public class StringUtils extends org.apache.commons.lang.StringUtils {

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

}
