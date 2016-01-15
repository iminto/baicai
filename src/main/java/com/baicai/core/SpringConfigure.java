package com.baicai.core;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class SpringConfigure implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	// ApplicationContext容器初始化或者刷新时触发该事件
	public void onApplicationEvent(ContextRefreshedEvent eve) {
		if (eve.getApplicationContext().getParent() == null) {
			System.out.println("Spring容器加载");
		}
	}

}
