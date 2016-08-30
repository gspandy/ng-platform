/*
 * Project:    waa新一代框架的安全模块
 * 
 * FileName:   SessionIdChanged.java
 * CreateTime: 2016-06-16 11:46:38
 */
package cc.waa.ng.security.web.listener;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.web.context.support.WebApplicationContextUtils.getRequiredWebApplicationContext;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;

import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;

import cc.waa.ng.base.data.dao.auth.ActivitySessionDao;
import cc.waa.ng.base.data.obj.auth.ActivitySession;
import cc.waa.ng.base.data.obj.auth.Session;

/**
 * 当容器的sessionId发生改变时，更换相应的ActivitySession
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class SessionIdChanged
	implements HttpSessionIdListener
{

	private static final Logger logger = getLogger(SessionIdChanged.class);



	private ActivitySessionDao activitySessionDao;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionIdListener#sessionIdChanged(javax.servlet.http.HttpSessionEvent, java.lang.String)
	 */
	@Override
	public void sessionIdChanged(HttpSessionEvent se, String oldSessionId)
	{
		String newSessionId;

		logger.trace("sessionId发生改变，{} => {}", oldSessionId, newSessionId = se.getSession().getId());

		if (this.activitySessionDao == null)
		{
			ApplicationContext context = getRequiredWebApplicationContext(se.getSession().getServletContext());

			this.activitySessionDao = context.getBean(ActivitySessionDao.class);
		}

		ActivitySession oldActivity = this.activitySessionDao.findOne(oldSessionId);
		Session session = oldActivity.getSession();

		this.activitySessionDao.delete(oldSessionId);

		ActivitySession activity = this.activitySessionDao.blank();
		activity.setId(newSessionId);
		activity.setSession(session);

		activity = this.activitySessionDao.save(activity);
	}

}
