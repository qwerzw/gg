package com.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.StandaloneInMemProcessEngineConfiguration;
import org.junit.Test;

public class initDB {
	@org.junit.Test
	public void aDB(){
		ProcessEngineConfiguration cfg=
			ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
		//设置主机
		cfg.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/acti");
		//设置驱动
		cfg.setJdbcDriver("com.mysql.jdbc.Driver");
		//设置账号
		cfg.setJdbcUsername("root");
		//设置密码
		cfg.setJdbcPassword("1357924680");
		
		//策略
		cfg.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		//获取流程引擎
		ProcessEngine processEngine = cfg.buildProcessEngine();
		System.out.println(processEngine);

	}
	@Test
	public void aDB1(){
		ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();
		System.out.println(processEngine);
		
	}
}
