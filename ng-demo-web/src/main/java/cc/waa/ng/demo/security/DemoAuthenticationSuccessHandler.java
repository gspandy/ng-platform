/*
 * Project:    waa新一代框架的演示模块
 * 
 * FileName:   DemoAuthenticationSuccessHandler.java
 * CreateTime: 2016-01-27 09:26:22
 */
package cc.waa.ng.demo.security;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import cc.waa.ng.base.data.LoginStatus;
import cc.waa.ng.base.data.dao.auth.LoginRecordDao;
import cc.waa.ng.base.data.dao.auth.SessionDao;
import cc.waa.ng.base.data.obj.auth.Session;
import cc.waa.ng.base.data.obj.auth.StandardLoginRecord;
import cc.waa.ng.util.web.WebUtils;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class DemoAuthenticationSuccessHandler
	extends SavedRequestAwareAuthenticationSuccessHandler
	implements AuthenticationSuccessHandler
{

	private static final Logger logger = getLogger(DemoAuthenticationSuccessHandler.class);



	private SessionDao sessionDao;

	public void setSessionDao(SessionDao sessionDao)
	{
		this.sessionDao = sessionDao;
	}

	private LoginRecordDao loginRecordDao;

	public void setLoginRecordDao(LoginRecordDao loginRecordDao)
	{
		this.loginRecordDao = loginRecordDao;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
		throws IOException, ServletException
	{
		logger.trace("成功登录的后续处理");

		String sessionId = WebUtils.getSessionId(request, response);

		Object details = authentication.getDetails(),
			   principal = authentication.getPrincipal();

		if (!(details instanceof WebAuthenticationDetails) ||
			!(principal instanceof DemoUserDetails))
		{
			logger.error("只支持details为WebAuthenticationDetails和principal为DemoUserDetails的情况");

			return;
		}

//		WebAuthenticationDetails wd = (WebAuthenticationDetails) details;
		DemoUserDetails ud = (DemoUserDetails) principal;

		Session session = this.sessionDao.findOne(sessionId);

		if (session == null)
			throw new RuntimeException("没找到当前会话");

		StandardLoginRecord rec = this.loginRecordDao.blank(StandardLoginRecord.class);
//		rec.setClientIp(wd.getRemoteAddress());
		rec.setLoginAccount(ud.getUsername());
		rec.setLoginStatus(LoginStatus.LOGINED);
		rec.setLoginTime(session.getRefreshTime());
		rec.setSession(session);
		rec.setLoginStatus(LoginStatus.LOGINED);
		rec.setUser(ud.getUser());
//		rec.setUserAgent(request.getHeader("user-agent"));

		rec = this.loginRecordDao.save(rec);

		super.onAuthenticationSuccess(request, response, authentication);
	}

}
