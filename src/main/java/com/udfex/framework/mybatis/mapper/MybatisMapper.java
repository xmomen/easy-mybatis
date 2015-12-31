package com.udfex.framework.mybatis.mapper;

import com.udfex.framework.mybatis.model.BaseMybatisExample;
import com.udfex.framework.mybatis.model.BaseMybatisModel;
import com.udfex.framework.mybatis.provider.BaseMapperProvider;
import org.apache.ibatis.annotations.*;

import java.io.Serializable;
import java.util.List;

public interface MybatisMapper extends BaseSuperMapper {

    @SelectProvider(type = BaseMapperProvider.class, method = "countByModel")
    @ResultType(value = Integer.class)
    public <Model extends BaseMybatisModel> int countByModel(Model paramModel);

    public <Example extends BaseMybatisExample> int countByExample(Example paramExample);

    @SelectProvider(type = BaseMapperProvider.class, method = "existByPrimaryKey")
    @ResultType(value = Integer.class)
    public <Model extends BaseMybatisModel> int existByPrimaryKey(
            @Param(BaseMybatisModel.PARAMETER_MODEL_CLASS) Class<Model> paramClass,
            @Param(BaseMybatisModel.PRIMARY_KEY) Serializable paramSerializable);

    @DeleteProvider(type = BaseMapperProvider.class, method = "deleteByPrimaryKey")
    public <Model extends BaseMybatisModel> int deleteByPrimaryKey(
            @Param(BaseMybatisModel.PARAMETER_MODEL_CLASS) Class<Model> paramClass,
            @Param(BaseMybatisModel.PRIMARY_KEY) Serializable paramSerializable);

    @DeleteProvider(type = BaseMapperProvider.class, method = "delete")
    public <Model extends BaseMybatisModel> int delete(Model paramModel);

    public <Example extends BaseMybatisExample> int deleteByExample(Example paramExample);

    public <Model extends BaseMybatisModel> int insertSelective(Model paramModel);

//    @InsertProvider(type = BaseMapperProvider.class, method = "insert")
//    public <Model extends BaseMybatisModel> int insert(Model paramModel);

    @SelectProvider(type = BaseMapperProvider.class, method = "selectByPrimaryKey")
    @ResultMap(value = "BaseResultMap")
    public <Model extends BaseMybatisModel> Model selectByPrimaryKey(
            @Param(BaseMybatisModel.PARAMETER_MODEL_CLASS) Class<Model> paramClass,
            @Param(BaseMybatisModel.PRIMARY_KEY) Serializable paramSerializable);

    @SelectProvider(type = BaseMapperProvider.class, method = "selectByModel")
    @ResultMap(value = "BaseResultMap")
    public <Model extends BaseMybatisModel> List<Model> selectByModel(Model paramModel);

    public <Model extends BaseMybatisModel, Example extends BaseMybatisExample> List<Model> selectByExample(Example paramExample);

    @UpdateProvider(type = BaseMapperProvider.class, method = "update")
    public <Model extends BaseMybatisModel> int update(Model paramModel);

    public <Model extends BaseMybatisModel, Example extends BaseMybatisExample> int updateByExampleSelective(
            @Param("record") Model paramModel,
            @Param("example") Example paramExample);

}
