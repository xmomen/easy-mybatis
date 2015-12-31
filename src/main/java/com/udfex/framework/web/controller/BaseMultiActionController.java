package com.udfex.framework.web.controller;


import com.udfex.framework.log.Logger;
import com.udfex.framework.log.LoggerFactory;
import com.udfex.framework.mybatis.dao.MybatisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class BaseMultiActionController  extends MultiActionController {

	public Logger log = LoggerFactory.getLogger(BaseMultiActionController.class);

	@Autowired(required =  false)
	public MybatisDao mybatisDao;

}
