package com.udfex.framework.mybatis.service.impl;

import com.udfex.framework.log.Logger;
import com.udfex.framework.log.LoggerFactory;
import com.udfex.framework.mybatis.dao.MybatisDao;
import com.udfex.framework.mybatis.service.BaseMybatisService;
import com.udfex.framework.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BaseMybatisServiceImpl extends BaseServiceImpl implements BaseMybatisService {

	public Logger log = LoggerFactory.getLogger(BaseMybatisServiceImpl.class);
	
	@Autowired( required = false)
	@Qualifier( value="mybatisDao")
	public MybatisDao mybatisDao;

}
