/*
 * Project:    waa新一代框架的演示模块
 * 
 * FileName:   SessionAutoDestoryListener.java
 * CreateTime: 2016-04-06 11:28:44
 */
package cc.waa.ng.demo.security;

import static org.slf4j.LoggerFactory.getLogger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;

/**
 * 实现自动销毁Session的监听器
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class SessionAutoDestoryListener
	implements ServletContextListener, HttpSessionListener
{

	private static final Logger logger = getLogger(SessionAutoDestoryListener.class);

//	private static final String KEY = "cc.waa.key.sessionsInApplication";



	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event)
	{
		logger.debug("应用程序关闭，这里负责销毁所有Session");
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0)
	{
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionCreated(HttpSessionEvent event)
	{
		logger.trace("有新的session建立了，{}", event.getSession().getId());
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent event)
	{
		logger.trace("有session被销毁了，不过这里不会处理，{}", event.getSession().getId());
	}

}
