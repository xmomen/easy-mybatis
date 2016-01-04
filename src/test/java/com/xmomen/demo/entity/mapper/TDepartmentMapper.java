package com.xmomen.demo.entity.mapper;

import com.xmomen.demo.entity.TDepartment;
import com.xmomen.demo.entity.TDepartmentExample;
import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import org.apache.ibatis.annotations.Param;

public interface TDepartmentMapper extends MybatisMapper {
    int countByExample(TDepartmentExample example);

    int deleteByExample(TDepartmentExample example);

    int insertSelective(TDepartment record);

    int updateByExampleSelective(@Param("record") TDepartment record, @Param("example") TDepartmentExample example);
}