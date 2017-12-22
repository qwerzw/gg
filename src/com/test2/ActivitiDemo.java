package com.test2;



import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class ActivitiDemo {
	ProcessEngine processEngine = ProcessEngineConfiguration
			.createProcessEngineConfigurationFromResource("activiti.cfg.xml")
			.buildProcessEngine();
	
	/**
	 * 
	 */
	//发布流程
	@Test
	public void deplay(){
		System.out.println(processEngine);
		Deployment deployment=processEngine.getRepositoryService()
				.createDeployment()
				.addClasspathResource("f/hello.bpmn")
				.addClasspathResource("f/hello.png")
				.name("调皮流程")
				.deploy();
		System.out.println(deployment.getId()+"     "+deployment.getName());
	}
	//启动流程:张三
	@Test
	public void ins(){
		ProcessInstance pi = processEngine.getRuntimeService()
				.startProcessInstanceByKey("hello");
		System.out.println("流程启动id:"+pi.getId());
		System.out.println("流程启动Activityid:"+pi.getActivityId());
	}
	//查看流程：张三
	@Test
	public void chic(){
		//查看人
		String dbz="李四";
		//获取流程引擎对象
		List<Task> list = processEngine.getTaskService()
				.createTaskQuery()
				.taskAssignee(dbz)
				.list();
		//遍历
		for(Task task:list){
			System.out.println("任务id"+task.getId());
			System.out.println("任务name"+task.getName());
		}
	}
	//办理任务
	@Test
	public void blrw(){
		//办理人
		String dbz="302";
		//获取流程引擎对象
		processEngine.getTaskService().complete(dbz);
		System.out.println("办理完成");
	}
	
	// 第二种： 读取压缩包
	public void zip(){
		InputStream in=this.getClass().getClassLoader().getResourceAsStream("");
		//转换zip文件
		ZipInputStream zip=new ZipInputStream(in);
		//发布
		Deployment deployment=processEngine.getRepositoryService()
							.createDeployment()
							.name("")
							.addZipInputStream(zip)
							.deploy();
	}
	//查看流程定义
	@Test
	public void cklcdy (){
		List<ProcessDefinition> list = processEngine.getRepositoryService()
							.createProcessDefinitionQuery()
							.list();
		//遍历
		for (ProcessDefinition p : list) {
			System.out.println("id:"+p.getId()+"name:"+p.getName() );
		}
	}
	//删除流程定义
	@Test
	public void sclcdy (){
		//普通删除(如果有正在执行的流程，就会抛异常)
		processEngine.getRepositoryService()
					.deleteDeployment("");
		//级联删除（定义信息、执行信息、历史信息）
		processEngine.getRepositoryService()
					.deleteDeployment("",true);
	}
	//查看流程状态
	@Test
	public void cklczt (){
		ProcessInstance pi = processEngine.getRuntimeService()
					.createProcessInstanceQuery()
					.processInstanceId("2901")
					.singleResult();
		if (pi!=null) {
			System.out.println(pi.getId()+"流程正在执行！");
		}else{
			System.out.println("流程结束");
		}
	}
	//查看历史流程状态
	@Test
	public void ckls (){
		List<HistoricTaskInstance> list = processEngine.getHistoryService()
					.createHistoricTaskInstanceQuery()
					.taskAssignee("李四")
					.list();
		//遍历
		for (HistoricTaskInstance hi : list) {
			System.out.println("任务id："+hi.getId());
			System.out.println("流程实例id："+hi.getProcessInstanceId());
			System.out.println("任务办理人："+hi.getAssignee());
			System.out.println("执行对象id："+hi.getExecutionId());
		}
		
	}
	
	
	
}
