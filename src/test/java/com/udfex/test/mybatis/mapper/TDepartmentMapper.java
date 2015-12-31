package com.udfex.test.mybatis.mapper;

import com.udfex.framework.mybatis.mapper.MybatisMapper;
import com.udfex.test.mybatis.model.TDepartment;
import com.udfex.test.mybatis.model.TDepartmentExample;
import org.apache.ibatis.annotations.Param;

public interface TDepartmentMapper extends MybatisMapper {
    int countByExample(TDepartmentExample example);

    int deleteByExample(TDepartmentExample example);

    int insertSelective(TDepartment record);

    int updateByExampleSelective(@Param("record") TDepartment record, @Param("example") TDepartmentExample example);
}