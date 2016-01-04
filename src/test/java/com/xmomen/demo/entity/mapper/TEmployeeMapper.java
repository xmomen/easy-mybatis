package com.xmomen.demo.entity.mapper;

import com.xmomen.demo.entity.TEmployee;
import com.xmomen.demo.entity.TEmployeeExample;
import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import org.apache.ibatis.annotations.Param;

public interface TEmployeeMapper extends MybatisMapper {
    int countByExample(TEmployeeExample example);

    int deleteByExample(TEmployeeExample example);

    int insertSelective(TEmployee record);

    int updateByExampleSelective(@Param("record") TEmployee record, @Param("example") TEmployeeExample example);
}