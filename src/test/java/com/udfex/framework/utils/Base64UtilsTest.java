package com.udfex.framework.utils;

import org.junit.Test;
import sun.misc.BASE64Encoder;

import static org.junit.Assert.*;

public class Base64UtilsTest {

    @Test
    public void testEncoder() throws Exception {
        String s = StringUtils.getUUID(32);
        System.out.println("加密前：" + s);
        String ds = Base64Utils.encoder(s);
        System.out.println("加密后：" + ds);
        System.out.println("解密后：" + Base64Utils.decoder(ds));
    }

    @Test
    public void testEncoder1() throws Exception {
        String s = StringUtils.getUUID(32);
        System.out.println("加密前：" + s);
        String ds = Base64Utils.encoder(s, 5);
        System.out.println("加密后：" + ds);
        System.out.println("解密后：" + Base64Utils.decoder(ds, 5));
    }

    @Test
    public void testDecoder() throws Exception {

    }

    @Test
    public void testDecoder1() throws Exception {

    }
}