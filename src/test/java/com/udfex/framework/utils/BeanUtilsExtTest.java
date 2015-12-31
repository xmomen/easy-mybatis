package com.udfex.framework.utils;

import com.udfex.test.mybatis.model.TDepartment;
import com.udfex.test.mybatis.model.TEmployee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class BeanUtilsExtTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCopyPropertiesIgnoreDefault() throws Exception {
        TEmployee tEmployee = new TEmployee();
        tEmployee.settDepartmentId(123);
        tEmployee.setRecordVersion(1);
        tEmployee.setCreateDateTime(new Date());
        tEmployee.setCreateTimeZone("SFSF");
        tEmployee.setCreateUserCode("SFSF");
        tEmployee.setUpdateDateTime(new Date());
        tEmployee.setUpdateUserCode("SFSF");
        tEmployee.setUpdateTimeZone("SFSF");
        TDepartment tDepartment = new TDepartment();
        BeanUtilsExt.copyPropertiesIgnoreDefault(tEmployee, tDepartment);
        System.out.println(tDepartment);
    }
}