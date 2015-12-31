package com.udfex.framework.utils;

import org.junit.Assert;
import org.junit.Test;

public class MD5UtilsTest {

    @Test
    public void testGetSalt() throws Exception {
        System.out.println(MD5Utils.getSalt());
    }

    @Test
    public void testEncrypt() throws Exception {
        String salt = MD5Utils.getSalt();
        String password = "123456";
        String encryptPw = MD5Utils.encrypt(password, salt);
        Assert.assertTrue(encryptPw.length()==32 && encryptPw != password);
    }
}