package com.xmomen.test;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Created by jengt_000 on 2015/1/1.
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring-core.xml"})
@TransactionConfiguration(defaultRollback = true)
public class BaseSpringTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    public MybatisDao mybatisDao;

    @Test
    public void testStart(){
        Assert.assertEquals("初始化MybatisDao", true,mybatisDao != null);
    }
}
