package com.udfex.test.mybatis.mapper;

import com.udfex.framework.mybatis.mapper.MybatisMapper;
import com.udfex.test.mybatis.model.TEmployee;
import com.udfex.test.mybatis.model.TEmployeeExample;
import org.apache.ibatis.annotations.Param;

public interface TEmployeeMapper extends MybatisMapper {
    int countByExample(TEmployeeExample example);

    int deleteByExample(TEmployeeExample example);

    int insertSelective(TEmployee record);

    int updateByExampleSelective(@Param("record") TEmployee record, @Param("example") TEmployeeExample example);
}