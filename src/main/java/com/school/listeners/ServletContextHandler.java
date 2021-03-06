package com.school.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;
import com.school.util.ApplicationManager;

/**
 * ServletContextHandler receives notification events about ServletContext
 * lifecycle changes. Loads initial parameters from xml file and connects to
 * database
 * 
 * @author Branislav
 *
 */
public class ServletContextHandler implements ServletContextListener {

	private final static Logger logger = Logger.getLogger(ServletContextHandler.class);

	/**
	 * Gets notified by ServletContextEvent that the web application initialization
	 * process is starting. Creates SpringJDBCTemplate Spring Bean from
	 * ApplicationManager and set SpringJDBCTemplate instance object as
	 * ServletContext attribute.
	 * 
	 * @param sce
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {

		logger.info("Context initialized");

		try {
			logger.warn("Initializing Database Connection");
			ApplicationManager.getSpringAppContext().getBean("studentJDBCTemplate");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * Gets notified by ServletContextEvent that the ServletContext is about to be
	 * shut down.
	 * 
	 * @param sce
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("Destroying Context"); 
		ApplicationManager.closeSpringApplicationContext();
	}

}
