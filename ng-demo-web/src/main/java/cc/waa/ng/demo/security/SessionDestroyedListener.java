/*
 * Project:    waa新一代框架的演示模块
 * 
 * FileName:   SessionDestroyedListener.java
 * CreateTime: 2016-04-26 23:08:40
 */
package cc.waa.ng.demo.security;

import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionDestroyedEvent;

import cc.waa.ng.base.data.LoginStatus;
import cc.waa.ng.base.data.dao.auth.ActivitySessionDao;

/**
 * 把对SessionDestroyedEvent的监听从DemoSessionRegistryImpl移走
 * 因为DemoSessionRegistryImpl加了事务代理之后会拦截两次SessionDestroyedEvent
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class SessionDestroyedListener
	implements ApplicationListener<SessionDestroyedEvent>
{

	private static final Logger logger = getLogger(SessionDestroyedListener.class);



	private DemoSessionRegistryImpl sessionRegistry;

	public void setSessionRegistry(DemoSessionRegistryImpl sessionRegistry)
	{
		this.sessionRegistry = sessionRegistry;
	}

	private ActivitySessionDao activitySessionDao;

	public void setActivitySessionDao(ActivitySessionDao activitySessionDao)
	{
		this.activitySessionDao = activitySessionDao;
	}

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
	 */
	@Override
	public void onApplicationEvent(SessionDestroyedEvent event)
	{
		String sessionId = event.getId();
		LoginStatus status = LoginStatus.AWAY;

		logger.trace("onApplicationEvent(org.springframework.security.core.session.SessionDestroyedEvent.id) => ({})", sessionId);

		// TODO 只通过这个判断就可以区分AWAY和EXPIRED？？
		if (SecurityContextHolder.getContext().getAuthentication() == null)
			status = LoginStatus.EXPIRED;

		this.sessionRegistry.removeSessionInformation(sessionId, status);

		this.activitySessionDao.delete(sessionId);
	}

}
