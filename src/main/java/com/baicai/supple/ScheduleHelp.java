package com.baicai.supple;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.Task;
import org.springframework.scheduling.support.ScheduledMethodRunnable;
import org.springframework.stereotype.Component;

/**
 * 
* @Description: 计划任务，计划任务的方法只能是void类型，需要加上@EnableScheduling才能执行
* 计划任务修改会很频繁，用注解的方式需要重新编译，现改为XML配置
* @author 猪肉有毒 waitfox@qq.com  
* @date 2016年3月3日 下午11:10:03 
* @version V1.0  
* 我只为你回眸一笑，即使不够倾国倾城，我只为你付出此生，换来生再次相守
 */
@Component
@EnableScheduling
public class ScheduleHelp {
	
	/**
	 * 定时计算。每天凌晨 01:00 执行一次 
	 * @return 0
	 */ 
	public void task1(){
		System.out.println("ScheduleHelp...task1");
	}
	
	@Scheduled(cron="0 0 2 * * *")
	public void task3(){
		System.out.println("ScheduleHelp...task1");
	}
	
	/**
	 * 心跳更新。启动时执行一次，之后每隔1000秒执行一次
	 */
	public void task2(){
		System.out.println("ScheduleHelp...task2【心跳更新。启动时执行一次，之后每隔1000秒执行一次】");
	}
	
	@Autowired   
	private ScheduledTaskRegistrar scheduledTaskRegistrar;
	
	public List<Task> getScheduledTasks() {
	    List<Task> result = new ArrayList<Task>();
	    result.addAll(this.scheduledTaskRegistrar.getTriggerTaskList());
	    result.addAll(this.scheduledTaskRegistrar.getCronTaskList());
	    result.addAll(this.scheduledTaskRegistrar.getFixedRateTaskList());
	    result.addAll(this.scheduledTaskRegistrar.getFixedDelayTaskList());
	    return result;
	}
	
	/**
	 * 需要注意的是，只有在XML中定义的task才能被观察到，通过注解配置的是观察不到的
	 * 至少在spring 4.2版本中还没有实现观察注解中的定时任务。
	 * https://jira.spring.io/browse/SPR-12748
	 * @return
	 */
	public List<CronTask> getScheduledCronTasks() {
	    List<CronTask> cronTaskList = this.scheduledTaskRegistrar.getCronTaskList();
	    for (CronTask cronTask : cronTaskList) {
	        System.out.println("cron表达式："+cronTask.getExpression());
	        ScheduledMethodRunnable scheduledMethodRunnable = (ScheduledMethodRunnable) cronTask.getRunnable();
	        System.out.println("执行的方法："+scheduledMethodRunnable.getTarget().getClass().getName()+"@"+scheduledMethodRunnable.getMethod().getName());
	    }
	    return cronTaskList;
	}

}
