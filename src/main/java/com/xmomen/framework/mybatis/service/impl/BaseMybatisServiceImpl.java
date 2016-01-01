package com.xmomen.framework.mybatis.service.impl;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.service.BaseMybatisService;
import com.xmomen.framework.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BaseMybatisServiceImpl extends BaseServiceImpl implements BaseMybatisService {

	@Autowired( required = false)
	@Qualifier( value="mybatisDao")
	public MybatisDao mybatisDao;

}
