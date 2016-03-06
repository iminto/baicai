package org.springframework.web.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 
 * @author piaohailin
 * 
 */
public class LogbackConfigListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
    	WebLogbackConfigurer.initLogging(event.getServletContext());
    }

    public void contextDestroyed(ServletContextEvent event) {
    	WebLogbackConfigurer.shutdownLogging(event.getServletContext());
    }
}
