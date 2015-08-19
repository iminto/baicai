/**
* @Description: TODO
* @author 猪肉有毒 waitfox@qq.com  
* @date 2015年6月8日 上午9:53:09 
* @version V1.0  
* 我只为你回眸一笑，即使不够倾国倾城，我只为你付出此生，换来生再次相守
*/
package com.baicai.core;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
        servletContext.setAttribute("systemx", "");
		
	}

}
