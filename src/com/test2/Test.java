package com.test2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

public class Test {

	
	public static void qd() {
		 //流程引擎
		ProcessEngine processEngine=ProcessEngineConfiguration
				.createProcessEngineConfigurationFromResource("activiti.cfg.xml")
				.buildProcessEngine();
		System.out.println(processEngine);
		//启动流程
		//传参数
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("u1", "李白");//提交
		map.put("u2", "杜甫");//审批
		map.put("u3", "李世民");//终审
		//启动流程
		ProcessInstance pi=processEngine.getRuntimeService()
          .startProcessInstanceByKey("leave",map);
		//流程启动id 32701
		System.out.println("流程启动id:"+pi.getId());
		System.out.println("流程ActivityId:"+pi.getActivityId());
	}
	
	public static void szcs(){
		ProcessEngine processEngine=ProcessEngineConfiguration
				.createProcessEngineConfigurationFromResource("activiti.cfg.xml")
				.buildProcessEngine();
		System.out.println(processEngine);
		//流程代办 32707
		//查看人
		String dbz="李白";
//		获取任务列表
		TaskService service=processEngine.getTaskService();
		Task task=service.createTaskQuery()
				        .taskAssignee(dbz)
				        .singleResult();
		//设置变量  2 约会 now
//		service.setVariable(task.getId(), "请假原因", "约会");
//		service.setVariable(task.getId(), "天数", 2);
//		service.setVariable(task.getId(), "开始时间", new Date());
		Map<String, Object> var=new HashMap<String, Object>();
		var.put("请假原因", "约会");
		var.put("天数", 2);
		var.put("开始时间", "2018-01-01");
		service.setVariables(task.getId(), var);
		
//		Map<String, Object> map=service.getVariables(task.getId());
//		Set<String> set=map.keySet();
//		for (String key : set) {
//			System.out.println("key "+key+" value:"+map.get(key));
//		}
//		service.setVariable(task.getId(), "批复1", "约会约会约会");
//		//遍历
	    System.out.println("任务id:"+task.getId());
	    System.out.println("任务名称:"+task.getName());
	}
	
	public static void tj(){
		ProcessEngine processEngine=ProcessEngineConfiguration
				.createProcessEngineConfigurationFromResource("activiti.cfg.xml")
				.buildProcessEngine();
		System.out.println(processEngine);
	    //办理任务
	    processEngine.getTaskService().complete("92702");
	}

	public static void ckcs(){
		ProcessEngine processEngine=ProcessEngineConfiguration
				.createProcessEngineConfigurationFromResource("activiti.cfg.xml")
				.buildProcessEngine();
		System.out.println(processEngine);
		//流程代办 32707
		//查看人
		String dbz="李世民";
//		获取任务列表
		TaskService service=processEngine.getTaskService();
		Task task=service.createTaskQuery()
				        .taskAssignee(dbz)
				        .singleResult();
		
		Map<String, Object> map=service.getVariables(task.getId());
		Set<String> set=map.keySet();
		for (String key : set) {
			System.out.println("key "+key+" value:"+map.get(key));
		}
		service.setVariable(task.getId(), "批复2", "约会约会约会");
//		//遍历
	    System.out.println("任务id:"+task.getId());
	    System.out.println("任务名称:"+task.getName());

	}

}
