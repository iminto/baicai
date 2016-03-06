package com.baicai.supple;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 
* @Description: 计划任务，计划任务的方法只能是void类型
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
	@Scheduled(cron = "0 0 1 * * *") 
	public void task1(){
		System.out.println("ScheduleHelp...task1");
	}
	
	/**
	 * 心跳更新。启动时执行一次，之后每隔10秒执行一次
	 */
	@Scheduled(fixedRate = 5000*2)
	public void task2(){
		System.out.println("ScheduleHelp...task2【心跳更新。启动时执行一次，之后每隔10秒执行一次】");
	}

}
