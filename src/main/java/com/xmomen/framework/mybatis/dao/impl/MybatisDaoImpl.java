package com.xmomen.framework.mybatis.dao.impl;

import java.beans.Introspector;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.framework.mybatis.page.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionException;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.util.Assert;

import com.xmomen.framework.model.BaseModel;
import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.model.BaseMybatisExample;
import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import com.xmomen.framework.mybatis.page.PageInterceptor;
import com.xmomen.framework.mybatis.utils.ModelUtils;
import com.xmomen.framework.support.SpringContextUtil;
import com.xmomen.framework.utils.AssertExt;

public class MybatisDaoImpl extends SqlSessionDaoSupport implements MybatisDao {

    private static final String SQL_MAPPER_BASE_NAMESPACE = "com.xmomen.framework.mybatis.mapper.BaseMybatisMapper.";

    @Override
    public SqlSession getSqlSessionTemplate() {
        return getSqlSession();
    }

    @Override
    public Integer getPrimaryKey() {
        return this.getSqlSessionTemplate().selectOne(SQL_MAPPER_BASE_NAMESPACE + "SELECT_INCREMENT_PK");
    }

    @Override
    public Date getGMTDate() {
        return this.getSqlSessionTemplate().selectOne(SQL_MAPPER_BASE_NAMESPACE + "SELECT_GMT");
    }

    @Override
    public Date getSysdate() {
        // this.getSqlSessionTemplate().selectOne(SQL_MAPPER_BASE_NAMESPACE + "SELECT_SYSDATE");
        return getGMTDate();
    }

    @Override
    public <MODEL extends BaseMybatisModel> int countByModel(MODEL model) {
        AssertExt.isNullParameter(model);
        return getMybatisMapper(model.getClass()).countByModel(model);
    }

    @Override
    public <EXAMPLE extends BaseMybatisExample> int countByExample(EXAMPLE paramExample) {
        AssertExt.isNullParameter(paramExample);
        return getMybatisMapperByExample(paramExample.getClass()).countByExample(paramExample);
    }

    /**
     * 统计匹配的总记录数
     *
     * @param mapperId
     * @return
     */
    @Override
    public int count(String mapperId) {
        return count(mapperId, null);
    }

    /**
     * 统计匹配的总记录数
     *
     * @param mapperId
     * @param object
     * @return
     */
    @Override
    public int count(String mapperId, Object object) {
        Integer integer = getSqlSessionTemplate().selectOne(mapperId, object);
        if(integer == null){
            throw new SqlSessionException("Error:  Sql statement is only support select count/sum from tablename. Please check sql statement");
        }
        return integer;
    }

    @Override
    public <MODEL extends BaseMybatisModel> boolean existByPrimaryKey(@Param("modelClass") Class<MODEL> paramClass, @Param("primaryKey") Serializable paramSerializable) {
        AssertExt.isNullParameter(paramSerializable);
        int rowCount = getMybatisMapper(paramClass).existByPrimaryKey(paramClass,paramSerializable);
        return rowCount == 1 ? true : false;
    }

    @Override
    public <MODEL extends BaseMybatisModel> boolean existByModel(MODEL model) {
        int resultCount = countByModel(model);
        return resultCount > 0 ? true : false;
    }

    @Override
    public <MODEL extends BaseMybatisExample> boolean existByExample(MODEL paramExample) {
        int resultCount = countByExample(paramExample);
        return resultCount > 0 ? true : false;
    }

    @Override
    public boolean exist(String mapperId) {
        return exist(mapperId, null);
    }

    @Override
    public boolean exist(String mapperId, Object object) {
        List result = getSqlSessionTemplate().selectList(mapperId, object);
        if(result != null && result.size() > 0){
            return true;
        }
        return false;
    }

    @Override
    public <MODEL extends BaseMybatisModel> int deleteByPrimaryKey(@Param("modelClass") Class<MODEL> paramClass, @Param("primaryKey") Serializable primaryKey) {
        int rowCount = 0;
        AssertExt.isNullParameter(primaryKey);
        boolean isExist = existByPrimaryKey(paramClass, primaryKey);
        if(isExist){
            rowCount = getMybatisMapper(paramClass).deleteByPrimaryKey(paramClass, primaryKey);
            AssertExt.isInvalidResult(rowCount != 1, "Expected 1 lines affected result to be returned by deleteByPrimaryKey(), but found: " + rowCount + " line affected");
        }
        return rowCount;
    }

    @Override
    public <MODEL extends BaseMybatisModel> int delete(MODEL model) {
        int rowCount = 0;
        AssertExt.isNullParameter(model);
        boolean isExist = existByModel(model);
        if(isExist){
            rowCount = getMybatisMapper(model.getClass()).delete(model);
            AssertExt.isInvalidResult(rowCount != 1, "Expected 1 lines affected result to be returned by delete(), but found: " + rowCount + " line affected");
        }
        return rowCount;
    }

    @Override
    public <MODEL extends BaseMybatisModel> int deleteAllByPrimaryKey(Class<MODEL> paramClass, Collection<? extends Serializable> ids) {
        int row = 0;
        for (Serializable id : ids){
            deleteByPrimaryKey(paramClass,id);
            row++;
        }
        return row;
    }

    @Override
    public <MODEL extends BaseMybatisModel> int deleteAllByModel(Collection<MODEL> models) {
        int row = 0;
        for (Iterator iterator = models.iterator(); iterator.hasNext(); ) {
            BaseMybatisModel model = (BaseMybatisModel)iterator.next();
            delete(model);
            row++;
        }
        return row;
    }

    @Override
    public <EXAMPLE extends BaseMybatisExample> int deleteByExample(EXAMPLE paramExample) {
        AssertExt.isNullParameter(paramExample);
        return getMybatisMapperByExample(paramExample.getClass()).deleteByExample(paramExample);
    }

    @Override
    public <MODEL extends BaseMybatisModel> int insert(MODEL model) {
        AssertExt.isNullParameter(model);
        try {
            // insert CREATE_DATE_TIME values
            String createDateTimeColumnName = ModelUtils.getColumnName(model.getClass(), BaseModel.FIELD_CREATE_DATE_TIME);
            if(createDateTimeColumnName != null){
                Date date = (Date) ModelUtils.getFieldValueByFieldName(model, ModelUtils.camelName(BaseModel.FIELD_CREATE_DATE_TIME));
                if(date == null){
                    Method write = ModelUtils.getWriteMethodByFieldName(model.getClass(), ModelUtils.camelName(BaseModel.FIELD_CREATE_DATE_TIME));
                    Date nowDate = getSysdate();
                    try {
                        write.invoke(model, nowDate);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
            String recVersionColumnName = ModelUtils.getRecVersionColumnName(model.getClass());
            if(recVersionColumnName != null){
                // insert RECORD_VERSION values
                Integer recordVersion = (Integer) ModelUtils.getFieldValueByFieldName(model, ModelUtils.camelName(recVersionColumnName));
                if(recordVersion == null){
                    Method write = ModelUtils.getWriteMethodByFieldName(model.getClass(), ModelUtils.camelName(recVersionColumnName));
                    Integer zeroRecordVersion = 0;
                    try {
                        write.invoke(model, zeroRecordVersion);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return getMybatisMapper(model.getClass()).insertSelective(model);
    }

    @Override
    public <MODEL extends BaseMybatisModel> MODEL insertByModel(MODEL model) {
        int rowCount = insert(model);

        AssertExt.isInvalidResult(rowCount != 1, "Expected 1 lines affected result to be returned by insertByModel(), but found: " + rowCount + " line affected");

        Serializable primaryKey = ModelUtils.getPrimaryKeyValue(model);
        if (null == primaryKey) {
            primaryKey = getPrimaryKey();
        }
        return (MODEL) selectByPrimaryKey(model.getClass(), primaryKey);
        //return model;
    }

    @Override
    public <MODEL extends BaseMybatisModel> MODEL selectByPrimaryKey(@Param("modelClass") Class<MODEL> paramClass, @Param("primaryKey") Serializable primaryKey) {
        AssertExt.isNullParameter(primaryKey);
        return getMybatisMapper(paramClass).selectByPrimaryKey(paramClass,primaryKey);
    }

    @Override
    public <MODEL extends BaseMybatisModel> List<MODEL> selectByModel(MODEL model) {
        AssertExt.isNullParameter(model);
        return getMybatisMapper(model.getClass()).selectByModel(model);
    }

    @Override
    public <MODEL extends BaseMybatisModel> MODEL selectOneByModel(MODEL model) {
        List<MODEL> resultList = selectByModel(model);
        if(0 == resultList.size()){
            return null;
        }
        AssertExt.isInvalidResult(1 < resultList.size(), "Expected one result (or null) to be returned by selectOneByModel(), but found: " + resultList.size());
        return (MODEL) AssertExt.isTrueLoose(1 == resultList.size(), resultList.get(0));
    }

    @Override
    public <MODEL extends BaseMybatisModel, EXAMPLE extends BaseMybatisExample> List<MODEL> selectByExample(EXAMPLE paramExample) {
        AssertExt.isNullParameter(paramExample);
        return getMybatisMapperByExample(paramExample.getClass()).selectByExample(paramExample);
    }

    @Override
    public <MODEL extends BaseMybatisModel, EXAMPLE extends BaseMybatisExample> MODEL selectOneByExample(EXAMPLE paramExample) {
        List<MODEL> resultList = selectByExample(paramExample);

        AssertExt.isInvalidResult(1 < resultList.size(), "Expected one result (or null) to be returned by selectOneByExample(), but found: " + resultList.size());
        if(resultList.size() == 0){
            return null;
        }
        return (MODEL) AssertExt.isTrueLoose(1 == resultList.size(), resultList.get(0));
    }

    @Override
    public <MODEL extends BaseMybatisModel> Page<MODEL> selectPageByModel(MODEL model, Integer pageSize, Integer pageNum) {
        AssertExt.isInvalidParameter(null == model, "Parameter model is require and can't support null object to be selectPageByModel(), but found: " + model);
        AssertExt.isInvalidParameter(null == pageSize, "Parameter pageSize is require and can't support null object to be selectPageByModel(), but found parameter pageSize: " + pageSize);
        AssertExt.isInvalidParameter(null == pageNum, "Parameter pageNum is require and can't support null object to be selectPageByModel(), but found parameter pageNum: " + pageNum);
        AssertExt.isInvalidParameter(0 >= pageSize || 0 >= pageNum,
                "Parameter pageSize and pageNum value must greater than 0 to be selectPageByModel()," +
                        " but found parameter pageSize: " + pageSize + ", pageNum: " + pageNum);
        PageInterceptor.startPage(pageNum, pageSize);
        selectByModel(model);
        return PageInterceptor.endPage();
    }

    @Override
    public <MODEL extends BaseMybatisModel, EXAMPLE extends BaseMybatisExample> Page<MODEL> selectPageByExample(EXAMPLE example, Integer pageSize, Integer pageNum) {
        AssertExt.isInvalidParameter(null == example,
                "Parameter model is require and can't support null object to be selectPageByExample(), but found: " + example);
        AssertExt.isInvalidParameter(null == pageSize,
                "Parameter pageSize is require and can't support null object to be selectPageByExample(), but found parameter pageSize: " + pageSize);
        AssertExt.isInvalidParameter(null == pageNum,
                "Parameter pageNum is require and can't support null object to be selectPageByExample(), but found parameter pageNum: " + pageNum);
        AssertExt.isInvalidParameter(0 >= pageSize || 0 >= pageNum,
                "Parameter pageInfo of pageSize and pageNum value must greater than 0 to be selectPageByModel()," +
                        " but found parameter pageInfo of pageSize: " + pageSize + ", pageNum: " + pageNum);
        PageInterceptor.startPage(pageNum, pageSize);
        selectByExample(example);
        return PageInterceptor.endPage();
    }

    @Override
    public Page<?> selectPage(String mapperId, Object object, Integer pageSize, Integer pageNum) {
        AssertExt.isInvalidParameter(null == object,
                "Parameter model is require and can't support null object to be selectPage(), but found: " + object);
        AssertExt.isInvalidParameter(null == pageSize,
                "Parameter pageSize is require and can't support null object to be selectPage(), but found parameter pageSize: " + pageSize);
        AssertExt.isInvalidParameter(null == pageNum,
                "Parameter pageNum is require and can't support null object to be selectPage(), but found parameter pageNum: " + pageNum);
        AssertExt.isInvalidParameter(0 >= pageSize || 0 >= pageNum,
                "Parameter pageInfo of pageSize and pageNum value must greater than 0 to be selectPageByModel()," +
                        " but found parameter pageInfo of pageSize: " + pageSize + ", pageNum: " + pageNum);
        PageInterceptor.startPage(pageNum, pageSize);
        getSqlSessionTemplate().selectList(mapperId, object);
        return PageInterceptor.endPage();
    }

    @Override
    public <MODEL extends BaseMybatisModel> int update(MODEL model) {
        AssertExt.isNullParameter(model);
        Serializable primaryKey = ModelUtils.getPrimaryKeyValue(model);
        boolean isExist = existByPrimaryKey(model.getClass(), primaryKey);
        AssertExt.isInvalidResult(!isExist, "Row was deleted by another transaction (or unsaved-value mapping was incorrect)");
        try {
            String updateDateTimeColumnName = ModelUtils.getColumnName(model.getClass(), BaseModel.FIELD_UPDATE_DATE_TIME);
            if(updateDateTimeColumnName != null){
                // insert CREATE_DATE_TIME values
                Date date = (Date) ModelUtils.getFieldValueByFieldName(model, ModelUtils.camelName(BaseModel.FIELD_UPDATE_DATE_TIME));
                if(date == null){
                    Method write = ModelUtils.getWriteMethodByFieldName(model.getClass(), ModelUtils.camelName(BaseModel.FIELD_UPDATE_DATE_TIME));
                    Date nowDate = getSysdate();
                    try {
                        write.invoke(model, nowDate);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        int updateCount = getMybatisMapper(model.getClass()).update(model);
        AssertExt.isInvalidResult(1 > updateCount,  "Row was updated by another transaction (or unsaved-value mapping was incorrect)");
		return (Integer) AssertExt.isTrueLoose(1 == updateCount, updateCount);
    }

    @Override
    public <MODEL extends BaseMybatisModel> MODEL updateByModel(MODEL model) {
        update(model);
        Integer primaryKey = (Integer) ModelUtils.getPrimaryKeyValue(model);
        return (MODEL) selectByPrimaryKey(model.getClass(),primaryKey);
    }

    /**
     * 根据example条件将model更新到数据库中（此操作很可能会导致批量更新的情况出现，建议在完善之前废弃）
     *
     * @param model
     * @param example
     * @return
     */
    @Override
    public <MODEL extends BaseMybatisModel, EXAMPLE extends BaseMybatisExample> int updateByExampleSelective(@Param("record") MODEL model, @Param("example") EXAMPLE paramExample) {
        AssertExt.isNullParameter(model);
        AssertExt.isNullParameter(paramExample);
        return getMybatisMapperByExample(paramExample.getClass()).updateByExampleSelective(model, paramExample);
    }

    @Override
    public <MODEL extends BaseMybatisModel, EXAMPLE extends BaseMybatisExample> int updateOneByExampleSelective(@Param("record") MODEL model, @Param("example") EXAMPLE paramExample) {
        AssertExt.isNullParameter(model);
        AssertExt.isNullParameter(paramExample);
        int count = countByExample(paramExample);
        AssertExt.isInvalidParameter(count == 0, "Row was updated or delete by another transaction (or unsaved-value mapping was incorrect)");
        AssertExt.isInvalidParameter(count > 1, "Expected one result to be returned by updateOneByExampleSelective(), but found: " + count);
        return updateByExampleSelective(model, paramExample);
    }

    @Override
    public <MODEL extends BaseMybatisModel> int save(MODEL model) {
        AssertExt.isNullParameter(model);
        if (BaseModel.ROW_STATE_DELETED.equals(model.getRowStatus())) {
            return delete(model);
        }
        Serializable idValue = ModelUtils.getPrimaryKeyValue(model);
        if(idValue == null){
            return insert(model);
        }
        boolean isExists = existByPrimaryKey(model.getClass(), idValue);
        if (isExists) {
            return update(model);
        }
        return insert(model);
    }

    @Override
    public <MODEL extends BaseMybatisModel> MODEL saveByModel(MODEL model) {
        save(model);
        Integer primaryKey = (Integer) ModelUtils.getPrimaryKeyValue(model);
        return (MODEL) selectByPrimaryKey(model.getClass(), primaryKey);
    }

    @Override
    public <MODEL extends BaseMybatisModel> List<MODEL> saveAllByModel(Collection<MODEL> models) {
        AssertExt.isNullParameter(models);
        List<MODEL> modelsToDelete = new ArrayList<MODEL>();
        List<MODEL> modelsToMerge = new ArrayList<MODEL>();
        List<MODEL> result = new ArrayList<MODEL>();
        for (Iterator iterator = models.iterator(); iterator.hasNext(); ) {
            BaseMybatisModel model = (BaseMybatisModel)iterator.next();
            if (BaseModel.ROW_STATE_DELETED.equals(model.getRowStatus())){
                modelsToDelete.add((MODEL)model);
            }else {
                modelsToMerge.add((MODEL)model);
            }
        }
        deleteAllByModel(modelsToDelete);
        for (BaseMybatisModel model : modelsToMerge) {
            Serializable idValue = ModelUtils.getPrimaryKeyValue(model);
            boolean isExists = false;
            if(idValue != null){
                isExists = existByPrimaryKey(model.getClass(), idValue);
            }
            if (isExists) { //存在此记录则进行更新操作
                result.add((MODEL) updateByModel(model));
            } else { //若不存在则进行新增操作
                result.add((MODEL) insertByModel(model));
            }
        }
        return result;
    }

    /**
     * 根据model类获取相应mapper
     * @param modelClass
     * @param <MODEL>
     * @return
     */
    private <MODEL extends BaseMybatisModel> MybatisMapper getMybatisMapper(Class<MODEL> modelClass) {
        String modelName = modelClass.getSimpleName();
        String mapperName = Introspector.decapitalize(modelName) + "Mapper";
        Object objMapper = null;
		if (SpringContextUtil.containsBean(mapperName)) {
			objMapper = SpringContextUtil.getBean(mapperName);
		}else {
			String superModelName = modelClass.getSuperclass().getSimpleName();
			mapperName = Introspector.decapitalize(superModelName) + "Mapper";
			objMapper = SpringContextUtil.getBean(mapperName);
		}
        Assert.notNull(objMapper, "mapper " + mapperName + "extends MybatisMapper not exist");
        Assert.isInstanceOf(MybatisMapper.class, objMapper, "mapper " + mapperName + "not extends MybatisMapper");
        return (MybatisMapper) objMapper;
    }

    /**
     * 根据example类获取相应mapper
     * @param exampleClass
     * @param <Model>
     * @return
     */
    private <MODEL extends BaseMybatisExample> MybatisMapper getMybatisMapperByExample(Class<MODEL> exampleClass) {
        String exampleClassSimpleName = exampleClass.getSimpleName();
        int nameLength = exampleClassSimpleName.length();
        String modelName = exampleClassSimpleName.substring(0, nameLength - 7);
        String mapperName = Introspector.decapitalize(modelName) + "Mapper";
        Object objMapper = SpringContextUtil.getBean(mapperName);
        Assert.notNull(objMapper, "mapper " + mapperName + "extends MybatisMapper not exist");
        Assert.isInstanceOf(MybatisMapper.class, objMapper, "mapper " + mapperName + "not extends MybatisMapper");
        return (MybatisMapper) objMapper;
    }

}
