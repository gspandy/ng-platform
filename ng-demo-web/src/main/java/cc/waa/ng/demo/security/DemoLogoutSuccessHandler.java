/*
 * Project:    waa新一代框架的演示模块
 * 
 * FileName:   DemoLogoutSuccessHandler.java
 * CreateTime: 2016-01-27 19:41:27
 */
package cc.waa.ng.demo.security;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import cc.waa.ng.base.data.LoginStatus;
import cc.waa.ng.base.data.dao.auth.LoginRecordDao;
import cc.waa.ng.base.data.obj.auth.LoginRecord;
import cc.waa.ng.util.web.WebUtils;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class DemoLogoutSuccessHandler
	extends SimpleUrlLogoutSuccessHandler
	implements LogoutSuccessHandler
{

	private static final Logger logger = getLogger(DemoLogoutSuccessHandler.class);



	private LoginRecordDao loginRecordDao;

	public void setLoginRecordDao(LoginRecordDao loginRecordDao)
	{
		this.loginRecordDao = loginRecordDao;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.logout.LogoutSuccessHandler#onLogoutSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
		throws IOException, ServletException
	{
		logger.trace("成功退出登录的后续处理");

		String sessionId = WebUtils.getSessionId(request, response);
		LoginRecord rec = this.loginRecordDao.findBySessionIdStatus(sessionId, LoginStatus.LOGINED);

		if (rec == null)
			logger.warn("没找到当前会话的登录记录");

		else
		{
			rec.setQuitTime(new Date());
			rec.setLoginStatus(LoginStatus.AWAY);

			this.loginRecordDao.update(rec);
		}

		logger.trace("用户主动退出登录");

		super.onLogoutSuccess(request, response, authentication);
	}

}
