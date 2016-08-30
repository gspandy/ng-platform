/*
 * Project:    waa新一代框架的安全模块
 * 
 * FileName:   SessionRefreshFilter.java
 * CreateTime: 2016-06-16 13:46:03
 */
package cc.waa.ng.security.web;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.split;
import static org.apache.commons.lang3.StringUtils.trim;
import static org.slf4j.LoggerFactory.getLogger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.web.filter.OncePerRequestFilter;

import cc.waa.ng.base.data.SessionStatus;
import cc.waa.ng.base.data.dao.auth.ActivitySessionDao;
import cc.waa.ng.base.data.dao.auth.SessionDao;
import cc.waa.ng.base.data.obj.auth.ActivitySession;
import cc.waa.ng.base.data.obj.auth.Session;
import cc.waa.ng.util.web.WebUtils;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class SessionRefreshFilter
	extends OncePerRequestFilter
	implements Filter
{

	private static final Logger logger = getLogger(SessionRefreshFilter.class);



	private SessionDao sessionDao;

	public void setSessionDao(SessionDao sessionDao)
	{
		this.sessionDao = sessionDao;
	}

	private ActivitySessionDao activitySessionDao;

	public void setActivitySessionDao(ActivitySessionDao activitySessionDao)
	{
		this.activitySessionDao = activitySessionDao;
	}

	/**
	 * 计算ip路由记录
	 * 
	 * @param request
	 * @return
	 */
	private List<String> calIpRoute(HttpServletRequest request)
	{
		final String remoteAddr = request.getRemoteAddr(),
					xForwardFor = request.getHeader("X-Forwarded-For");

		List<String> ipRoute = new ArrayList<String>();

		ipRoute.add(remoteAddr);

		if (isNotEmpty(xForwardFor))
		{
			for (String ip : split(xForwardFor, ','))
			{
				ip = trim(ip);

				if (ipRoute.size() == 1)
					ipRoute.add(ip);
				else
					ipRoute.add(ipRoute.size() - 1, ip);
			}
		}

		return ipRoute;
	}

	/**
	 * 刷新当前会话状态
	 * 
	 * @param request
	 * @param response
	 */
	public void refreshSession(HttpServletRequest request, HttpServletResponse response)
	{
		String sessionId = WebUtils.getSessionId(request, response);
		String webSessionId = request.getSession().getId();
		Session session = this.sessionDao.findOne(sessionId);
		boolean newRec = false;

		if (newRec = (session == null))
		{
			session = this.sessionDao.blank();

			session.setCreateIpRoute(calIpRoute(request));
			session.setId(sessionId);
			session.setStatus(SessionStatus.NORMAL);
			session.setUserAgent(request.getHeader("user-agent"));
		}

		session.setRefreshIpRoute(calIpRoute(request));
		session.setRefreshTime(new Date());
		session.setVisitTimes(session.getVisitTimes() + 1);

		if (newRec)
		{
			session = this.sessionDao.save(session);

			logger.trace("成功创建会话");
		}
		else
		{
			session = this.sessionDao.update(session);

			logger.trace("成功更新会话");
		}

		ActivitySession activity = this.activitySessionDao.findOne(webSessionId);

		if (activity == null)
		{
			activity = this.activitySessionDao.blank();

			activity.setId(webSessionId);
			activity.setSession(session);

			activity = this.activitySessionDao.save(activity);
		}

		else if (!session.equals(activity.getSession()))
		{
			logger.warn("系统的Session和容器的Session的对应关系发生变化");

			activity.setSession(session);

			activity = this.activitySessionDao.update(activity);
		}
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request,
									HttpServletResponse response,
									FilterChain filterChain)
		throws ServletException, IOException
	{
		logger.trace("新请求，刷新会话信息");

		refreshSession(request, response);

		filterChain.doFilter(request, response);
	}

}
