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

public class ContextLoaderListener implements ServletContextListener{

	public void contextDestroyed(ServletContextEvent event) {
		
	}

	public void contextInitialized(ServletContextEvent event) {
		System.out.println("容器加载完成..contextInitialized");
		ServletContext servletContext = event.getServletContext();
        servletContext.setAttribute("systemx", "");
        BeetlGroupUtilConfiguration config= (BeetlGroupUtilConfiguration) SpringUtils.getApplicationContext().getBean("beetlConfig");
        GroupTemplate group = config.getGroupTemplate();//此处可处理模板全局变量
        Map<String,Object> shared = new HashMap<String,Object>();
        String path=BaseTool.getServer()+servletContext.getContextPath()+"/";
        shared.put("path", path);
        group.setSharedVars(shared);
	}

}
