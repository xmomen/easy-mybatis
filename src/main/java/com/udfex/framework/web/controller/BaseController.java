package com.udfex.framework.web.controller;


import com.udfex.framework.log.Logger;
import com.udfex.framework.log.LoggerFactory;
import com.udfex.framework.mybatis.dao.MybatisDao;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {

	public Logger log = LoggerFactory.getLogger(BaseController.class);

	@Autowired
	public MybatisDao mybatisDao;

}
