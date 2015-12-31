package com.udfex.framework.utils;

import org.junit.Assert;
import org.junit.Test;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class StringUtilsTest {

    @Test
    public void testGetUUID() throws Exception {
        String uuid = StringUtils.getUUID();
        Assert.assertTrue(uuid.length() == 32 && uuid.matches("^[a-z0-9]+$"));
    }

    @Test
    public void testGetUUID1() throws Exception {
        int num = 18;
        String uuid = StringUtils.getUUID(num).toUpperCase();
        Assert.assertTrue(uuid.length() == num);
    }

    @Test
    public void testEncode() throws Exception {
        String old = StringUtils.getUUID(32);
        System.out.println(old);
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String string = base64Encoder.encode(old.getBytes());
        System.out.println(string);
        byte[] s = new BASE64Decoder().decodeBuffer(string);
        System.out.println(new String(s));
    }
}