package com.xmomen.framework.mybatis.dao;

import com.alibaba.fastjson.JSONObject;
import com.xmomen.demo.entity.TDepartment;
import com.xmomen.demo.entity.TDepartmentExample;
import com.xmomen.demo.entity.TEmployee;
import com.xmomen.demo.entity.TEmployeeExample;
import com.xmomen.framework.exception.InvalidParameterException;
import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import com.xmomen.framework.mybatis.page.PageModel;
import com.xmomen.framework.utils.DateUtils;
import com.xmomen.framework.utils.StringUtilsExt;
import com.xmomen.test.BaseSpringTest;
import com.xmomen.test.ConcurrencyTestUtil;
import junit.framework.Assert;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MybatisDaoTest extends BaseSpringTest {

    TEmployee tEmployee;
    TEmployeeExample tEmployeeExample;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    
    

    @Before
    public void setUp() throws Exception{
        String name = "测试Name";
        Integer age = 18;
        Date birthday = DateUtils.parseDate("1989-02-12");
        tEmployee = new TEmployee();
        tEmployee.setName(name);
        tEmployee.setAge(age);
        tEmployee.setBirthday(birthday);
        tEmployeeExample = new TEmployeeExample();
        tEmployeeExample.createCriteria()
                .andNameEqualTo(name)
                .andNameLike(name+"%")
                .andNameIsNotNull()
                .andAgeLessThan(tEmployee.getAge() + 2)
                .andAgeBetween(tEmployee.getAge() - 2, tEmployee.getAge() + 2)
                .andAgeGreaterThan(tEmployee.getAge() -2)
                .andAgeIsNotNull()
                .andAgeNotBetween(tEmployee.getAge() + 2, tEmployee.getAge() -2)
                .andBirthdayBetween(
                        org.apache.commons.lang.time.DateUtils.addYears(tEmployee.getBirthday(), -10),
                        org.apache.commons.lang.time.DateUtils.addMonths(tEmployee.getBirthday(), 10))
                .andBirthdayEqualTo(tEmployee.getBirthday());
    }

    private static TEmployee createEmployee() throws ParseException {
        String name = "测试Name";
        Integer age = 18;
        Date birthday = DateUtils.parseDate("1989-02-12");
        TEmployee tEmployee = new TEmployee();
        tEmployee.setName(name);
        tEmployee.setAge(age);
        tEmployee.setBirthday(birthday);
        return tEmployee;
    }

    @After
    public void tearDown() throws Exception {
        //单个测试案例执行完之后，引用置空，防止数据污染
        tEmployee = null;
        tEmployeeExample = null;
        expectedException = ExpectedException.none();
    }

    @Test
    public void testGetSqlSessionTemplate() throws Exception {
        SqlSession sqlSession = mybatisDao.getSqlSessionTemplate();
        Assert.assertFalse("sqlSession为空", sqlSession == null);
    }

    @Test
    public void testGetSysdate() throws Exception {
        Date now = new Date();
        Date date = mybatisDao.getSysdate();
        Assert.assertEquals(true, date != null && now.compareTo(date) > 0);
    }

    @Test
    public void testCountByModel() throws Exception {
        String name = String.valueOf(RandomUtils.nextInt());
        int result = 0;
        for (int i = 0; i < 5; i++) {
            TDepartment tDepartment = new TDepartment();
            tDepartment.setName(name);
            int row = mybatisDao.insert(tDepartment);
            result+=row;
        }
        TDepartment tDepartment = new TDepartment();
        tDepartment.setName(name);
        int i = mybatisDao.countByModel(tDepartment);
        Assert.assertEquals(result, i);
    }

    @Test
    public void testCountByExample() throws Exception {
        String prefix = "测试";
        String name = String.valueOf(RandomUtils.nextInt());
        int result = 0;
        for (int i = 0; i < 5; i++) {
            TDepartment tDepartment = new TDepartment();
            tDepartment.setName(prefix+name);
            int row = mybatisDao.insert(tDepartment);
            result+=row;
        }
        TDepartmentExample example = new TDepartmentExample();
        example.createCriteria().andNameLike(prefix+"%");
        int i = mybatisDao.countByExample(example);
        Assert.assertEquals(result, i);
    }

    @Test
    public void testExistByPrimaryKey() throws Exception {
        tEmployee = mybatisDao.insertByModel(tEmployee);
        boolean isExist = mybatisDao.existByPrimaryKey(TEmployee.class, tEmployee.gettEmployeeId());
        Assert.assertEquals(true, isExist);
        boolean isExist2 = mybatisDao.existByPrimaryKey(TEmployee.class, 0);
        Assert.assertEquals(false, isExist2);
    }

    @Test
    public void testExistByModel() throws Exception {
        //正常流程测试
        mybatisDao.insert(tEmployee);
        boolean isExist = mybatisDao.existByModel(tEmployee);
        Assert.assertEquals(true, isExist);
        //异常流程测试
        tEmployee.setName("errorCount");
        boolean isExist2 = mybatisDao.existByModel(tEmployee);
        Assert.assertEquals(false, isExist2);
    }

    @Test
    public void testExistByExample() throws Exception {
        mybatisDao.insert(tEmployee);
        //正常流程测试
        boolean isExist = mybatisDao.existByExample(tEmployeeExample);
        Assert.assertEquals(true, isExist);
        //异常流程测试
        tEmployeeExample.clear();
        tEmployeeExample.createCriteria().andAgeNotEqualTo(tEmployee.getAge());
        boolean isExist2 = mybatisDao.existByExample(tEmployeeExample);
        Assert.assertEquals(false, isExist2);
    }

    @Test
    public void testDeleteByPrimaryKey() throws Exception {
        tEmployee = mybatisDao.insertByModel(tEmployee);
        int i = mybatisDao.deleteByPrimaryKey(TEmployee.class, tEmployee.gettEmployeeId());
        Assert.assertEquals(1 , i);
    }

    @Test
    public void testDeleteByModel() throws Exception {
        tEmployee = mybatisDao.insertByModel(tEmployee);
        int i = mybatisDao.delete(tEmployee);
        Assert.assertEquals(1 , i);
    }

    @Test
    public void testDeleteAllByPrimaryKey() throws Exception {
        int num = 10;
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < num; i++) {
            tEmployee.settEmployeeId(null);
            TEmployee result = mybatisDao.insertByModel(tEmployee);
            list.add(result.gettEmployeeId());
        }
        int i = mybatisDao.deleteAllByPrimaryKey(TEmployee.class, list);
        Assert.assertEquals(list.size() , i);
    }

    @Test
    public void testDeleteAllByModel() throws Exception {
        int num = 10;
        List<TEmployee> list = new ArrayList<TEmployee>();
        for (int i = 0; i < num; i++) {
            tEmployee.settEmployeeId(null);
            list.add(mybatisDao.insertByModel(tEmployee));
        }
        int rowCount = mybatisDao.deleteAllByModel(list);
        Assert.assertEquals(num, rowCount);
    }

    @Test
    public void testDeleteByExample() throws Exception {
        int num = 0;
        for (int i = 0; i < 10; i++) {
            tEmployee.settEmployeeId(null);
            int row = mybatisDao.insert(tEmployee);
            num += row;
        }
        int rowCount = mybatisDao.deleteByExample(tEmployeeExample);
        Assert.assertEquals(num, rowCount);
    }

    @Test
    public void testInsert() throws Exception {
        int row = mybatisDao.insert(tEmployee);
        Assert.assertEquals(1, row);
    }

    @Test
    public void testInsertExpectedException() throws Exception {
        expectedException.expect(InvalidParameterException.class);
        expectedException.expectMessage("Parameter object must be not null");
        mybatisDao.insert(null);
    }

    @Test
    public void testInsertByModel() throws Exception {
        //主键自增插入
        tEmployee = mybatisDao.insertByModel(tEmployee);
        Assert.assertNotNull(tEmployee.gettEmployeeId());
    }

    @Test
    public void testInsertByModel2() throws Exception {
        //主键非自增插入
        Integer primaryKey = 222;
        tEmployee.settEmployeeId(primaryKey);
        tEmployee = mybatisDao.insertByModel(tEmployee);
        Assert.assertEquals(primaryKey, tEmployee.gettEmployeeId());
    }

    @Test
    public void testInsertByModelByConcurrency() throws Exception {
        int concurrency = 30;
        List<Runnable> tasks = new ArrayList<Runnable> (concurrency);
        for(int i = 0; i < concurrency; i++) {
            tasks.add(new Runnable() {
                @Override
                public void run() {
                    TEmployee result = new TEmployee();
                    result.setName(StringUtilsExt.getUUID(8));
                    System.out.println("save: " + JSONObject.toJSONString(result));
                    result = mybatisDao.saveByModel(result);
                    System.out.println("Result: " + JSONObject.toJSONString(result.gettEmployeeId()));
                }
            });
        }
        ConcurrencyTestUtil.assertConcurrent("1024tasks", tasks, 10, concurrency);
    }

    @Test
    public void testSelectByPrimaryKey() throws Exception {
        tEmployee = mybatisDao.insertByModel(tEmployee);
        TEmployee result = mybatisDao.selectByPrimaryKey(TEmployee.class, tEmployee.gettEmployeeId());
        Assert.assertEquals(tEmployee, result);
    }

    @Test
    public void testSelectByModel() throws Exception {
        tEmployee = mybatisDao.insertByModel(tEmployee);
        List<TEmployee> results = mybatisDao.selectByModel(tEmployee);
        Assert.assertEquals(1, results.size());
        Assert.assertEquals(tEmployee.getName(), results.get(0).getName());
    }

    @Test
    public void testSelectOneByModel() throws Exception {
        tEmployee = mybatisDao.insertByModel(tEmployee);
        TEmployee result = mybatisDao.selectOneByModel(tEmployee);
        Assert.assertEquals(tEmployee.getName(), result.getName());
    }

    @Test
    public void testSelectByExample() throws Exception {
        int rowCount = 0;
        for (int i = 0; i < 10; i++) {
            tEmployee.settEmployeeId(null);
            int row = mybatisDao.insert(tEmployee);
            rowCount += row;
        }
        List<TEmployee> results = mybatisDao.selectByExample(tEmployeeExample);
        Assert.assertEquals(rowCount, results.size());
        Assert.assertEquals(tEmployee.getName(), results.get(0).getName());
    }

    @Test
    public void testSelectOneByExample() throws Exception {
        tEmployee = mybatisDao.insertByModel(tEmployee);
        TEmployee result = mybatisDao.selectOneByExample(tEmployeeExample);
        Assert.assertEquals(tEmployee.getName(), result.getName());
    }

    @Test
    public void testSelectPageByModel() throws Exception {
        int total = 0;
        int num = 2;
        int size = 5;
        for (int i = 0; i < 10; i++) {
            TEmployee tEmployee1 = createEmployee();
            int row = mybatisDao.insert(tEmployee1);
            total += row;
        }
        PageModel page = mybatisDao.selectPageByModel(tEmployee, size, num);
        Assert.assertEquals("总页数不正确", total / size, page.getPageInfo().getPages());
        Assert.assertEquals("页码不正确", num, page.getPageInfo().getPageNum());
        Assert.assertEquals("总记录数不正确", total, page.getPageInfo().getTotal());
        Assert.assertEquals("页码大小不正确", size, page.getPageInfo().getPageSize());
        Assert.assertEquals("当前页开始记录数不正确", (num > 0 ? (num - 1) * size : 0), page.getPageInfo().getStartRow());
        Assert.assertEquals("当前页最后一条记录数不正确", num * size, page.getPageInfo().getEndRow());
        Assert.assertEquals("结果集分页数量不正确", size, page.getData().size());
    }

    @Test
    public void testSelectPageByExample() throws Exception {
        int total = 0;
        int num = 2;
        int size = 5;
        for (int i = 0; i < 10; i++) {
            TEmployee tEmployee1 = createEmployee();
            int row = mybatisDao.insert(tEmployee1);
            total += row;
        }
        PageModel page = mybatisDao.selectPageByExample(tEmployeeExample, size, num);
        Assert.assertEquals("总页数不正确", total/size, page.getPageInfo().getPages());
        Assert.assertEquals("页码不正确", num, page.getPageInfo().getPageNum());
        Assert.assertEquals("总记录数不正确", total, page.getPageInfo().getTotal());
        Assert.assertEquals("页码大小不正确", size, page.getPageInfo().getPageSize());
        Assert.assertEquals("当前页开始记录数不正确", (num > 0 ? (num - 1) * size : 0), page.getPageInfo().getStartRow());
        Assert.assertEquals("当前页最后一条记录数不正确", num * size, page.getPageInfo().getEndRow());
        Assert.assertEquals("结果集分页数量不正确", size, page.getData().size());
    }

    @Test
    public void testUpdate() throws Exception {
        tEmployee = mybatisDao.insertByModel(tEmployee);
        String newName = "测试更新成功";
        tEmployee.setName(newName);
        int result = mybatisDao.update(tEmployee);
        Assert.assertEquals(1, result);
    }

    @Test
    public void testUpdateByModel() throws Exception {
        tEmployee = mybatisDao.insertByModel(tEmployee);
        String newName = "测试更新成功";
        tEmployee.setName(newName);
        TEmployee result = mybatisDao.updateByModel(tEmployee);
        Assert.assertEquals(newName, result.getName());
    }

    @Test
    public void testUpdateByExampleSelective() throws Exception {
        // TODO 存在批量更新的情况，需考虑是否可用，
    }

    @Test
    public void testSave() throws Exception {
        tEmployee = mybatisDao.insertByModel(tEmployee);
        String newName = "测试保存成功";
        tEmployee.setName(newName);
        int result = mybatisDao.save(tEmployee);
        Assert.assertEquals(1, result);
    }

    @Test
    public void testSaveByModel() throws Exception {
        tEmployee = mybatisDao.insertByModel(tEmployee);
        String newName = "测试保存成功";
        tEmployee.setName(newName);
        TEmployee result = mybatisDao.saveByModel(tEmployee);
        Assert.assertEquals(newName, result.getName());
    }

    @Test
    public void testSaveAllByModel() throws Exception {
        List<TEmployee> list = new ArrayList<TEmployee>();
        for (int i = 0; i < 10; i++) {
            tEmployee.settEmployeeId(null);
            TEmployee result = mybatisDao.insertByModel(tEmployee);
            list.add(result);
        }
        String newName = "测试更新成功";
        for (int i = 0; i < list.size(); i++) {
            if(i < 4){
                list.get(i).setRowStatus(BaseMybatisModel.ROW_STATE_DELETED);
            }else {
                list.get(i).setName(newName);
            }
        }
        List<TEmployee> results = mybatisDao.saveAllByModel(list);
        Assert.assertEquals(6, results.size());
        Assert.assertEquals(newName, results.get(0).getName());
    }

    @Test
    public void testUpdateOneByExampleSelective() throws Exception {
        tEmployee = mybatisDao.insertByModel(tEmployee);
        /*tEmployee.settEmployeeId(null);
        tEmployee = mybatisDao.insertByModel(tEmployee);*/
        tEmployee.setName("更新成功");
        int i = mybatisDao.updateOneByExampleSelective(tEmployee, tEmployeeExample);
        Assert.assertEquals(1, i);
    }
}