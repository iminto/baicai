/**
* @Description: 设置全局变量
* @author 猪肉有毒 waitfox@qq.com  
* @date 2015年6月8日 上午9:53:09 
* @version V1.0  
* 我只为你回眸一笑，即使不够倾国倾城，我只为你付出此生，换来生再次相守
*/
package com.baicai.core;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.beetl.core.GroupTemplate;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;

import com.baicai.util.PropertiesTool;

public class ContextLoaderListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
        servletContext.setAttribute("systemx", "");
        BeetlGroupUtilConfiguration config= (BeetlGroupUtilConfiguration) SpringUtils.getApplicationContext().getBean("beetlConfig");
        GroupTemplate group = config.getGroupTemplate();//此处可处理模板全局变量
        Map<String,Object> shared = new HashMap<String,Object>();
        shared.put("version", "0.01");
        shared.put("path", PropertiesTool.get("system", "BASEURL"));
        group.setSharedVars(shared);
        System.out.println(group.getSharedVars());
	}

}
