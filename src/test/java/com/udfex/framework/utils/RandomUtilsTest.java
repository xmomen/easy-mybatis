package com.udfex.framework.utils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RandomUtilsTest {

    int length = 0;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        length = 0;
    }

    @Test
    public void testGenerateNumber() throws Exception {
        length = 6;
        String num = String.valueOf(RandomUtils.generateNumber(length));
        Assert.assertEquals(length, num.length());
        System.out.println(num);
    }

    @Test
    public void testGenerateString() throws Exception {
        length = 15;
        String num = String.valueOf(RandomUtils.generateString(length));
        Assert.assertEquals(length, num.length());
        System.out.println(num);
    }

    @Test
    public void testGenerateMixString() throws Exception {
        length = 15;
        String num = String.valueOf(RandomUtils.generateMixString(length));
        Assert.assertEquals(length, num.length());
        System.out.println(num);
    }

    @Test
    public void testGenerateLowerString() throws Exception {
        length = 15;
        String num = String.valueOf(RandomUtils.generateLowerString(length));
        Assert.assertEquals(length, num.length());
        System.out.println(num);
    }

    @Test
    public void testGenerateUpperString() throws Exception {
        length = 15;
        String num = String.valueOf(RandomUtils.generateUpperString(length));
        Assert.assertEquals(length, num.length());
        System.out.println(num);
    }

}