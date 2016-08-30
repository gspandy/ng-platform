/*
 * Project:    waa新一代框架的演示模块
 * 
 * FileName:   DemoSessionIdChanged.java
 * CreateTime: 2016-04-07 18:51:55
 */
package cc.waa.ng.demo.security;

import static org.slf4j.LoggerFactory.getLogger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;

import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cc.waa.ng.base.data.dao.auth.ActivitySessionDao;
import cc.waa.ng.base.data.obj.auth.ActivitySession;
import cc.waa.ng.base.data.obj.auth.Session;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class DemoSessionIdChanged
	implements HttpSessionIdListener
{

	private static final Logger logger = getLogger(DemoSessionIdChanged.class);



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
			ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(se.getSession().getServletContext());

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
