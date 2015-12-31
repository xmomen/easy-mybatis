package com.udfex.framework.enums;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PatternEnumsTest {

    String regex;

    @Before
    public void setUp() throws Exception {
        regex = null;
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testEmailCheck() throws Exception {
        regex = "fsa32f";
        Assert.assertEquals(false, PatternEnums.EMAIL.check(regex));
        regex = "fsa43f@";
        Assert.assertEquals(false, PatternEnums.EMAIL.check(regex));
        regex = "fsa234f@sf";
        Assert.assertEquals(false, PatternEnums.EMAIL.check(regex));
        regex = "fsa324f@ewr.";
        Assert.assertEquals(false, PatternEnums.EMAIL.check(regex));
        regex = "fefew2310@wer23.43f";
        Assert.assertEquals(true, PatternEnums.EMAIL.check(regex));
        regex = "fefew2310@wer23.43f.";
        Assert.assertEquals(false, PatternEnums.EMAIL.check(regex));
        regex = ".fefew.2310@wer23.43f.34";
        Assert.assertEquals(false, PatternEnums.EMAIL.check(regex));
    }

    @Test
    public void testMobileNumberCheck() throws Exception {
        regex = "1521111111e";
        Assert.assertEquals(false, PatternEnums.MOBILE_NUMBER.check(regex));
        regex = "15211111113,";
        Assert.assertEquals(false, PatternEnums.MOBILE_NUMBER.check(regex));
        regex = "1521111111";
        Assert.assertEquals(false, PatternEnums.MOBILE_NUMBER.check(regex));
        regex = "152111155555";
        Assert.assertEquals(false, PatternEnums.MOBILE_NUMBER.check(regex));
        regex = "12211115555";
        Assert.assertEquals(false, PatternEnums.MOBILE_NUMBER.check(regex));
        regex = "10211115555";
        Assert.assertEquals(false, PatternEnums.MOBILE_NUMBER.check(regex));
        regex = "19211115555";
        Assert.assertEquals(false, PatternEnums.MOBILE_NUMBER.check(regex));
        regex = "11211115555";
        Assert.assertEquals(false, PatternEnums.MOBILE_NUMBER.check(regex));
        regex = "18211115555";
        Assert.assertEquals(true, PatternEnums.MOBILE_NUMBER.check(regex));
        regex = "15000084483";
        Assert.assertEquals(true, PatternEnums.MOBILE_NUMBER.check(regex));
    }

    @Test
    public void testIdCardCheck() throws Exception {
        String errorMsg = "身份证格式验证失败";
        String trueRegex = "430224198912122012";
        String falseRegex = "43022419891212201";
        Assert.assertEquals(errorMsg, true, PatternEnums.ID_CARD.check(trueRegex));
        Assert.assertEquals(errorMsg, false, PatternEnums.ID_CARD.check(falseRegex));
    }
}