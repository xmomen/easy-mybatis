package com.udfex.framework.utils;

import junit.framework.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilsExtTest {

    @Test
    public void testRandom() throws Exception {
        int num = 8;
        String result = StringUtilsExt.random(num);
        Assert.assertEquals(num, result.length());
        System.out.println(result);
    }

    @Test
    public void testGetUUID() throws Exception {
        String result = StringUtilsExt.getUUID();
        Assert.assertEquals(32, result.length());
        System.out.println(result);
    }

    @Test
    public void testGetUUID1() throws Exception {
        int num = 16;
        String result = StringUtilsExt.getUUID(num);
        Assert.assertEquals(num, result.length());
        System.out.println(result);
    }

    @Test
    public void testConverToPinyin() throws Exception {

    }
}