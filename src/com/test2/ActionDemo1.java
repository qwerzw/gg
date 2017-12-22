package com.test2;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.runtime.ProcessInstance;

public class ActionDemo1 {
	ProcessEngine processEngine = ProcessEngineConfiguration
			.createProcessEngineConfigurationFromResource("activiti.cfg.xml")
			.buildProcessEngine();
	public void ins(){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("userId", "张三");
		ProcessInstance pi = processEngine.getRuntimeService()
				.startProcessInstanceByKey("hello");
		
	}
}
