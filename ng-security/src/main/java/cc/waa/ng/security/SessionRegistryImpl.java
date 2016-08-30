/*
 * Project:    waa新一代框架的安全模块
 * 
 * FileName:   SessionRegistryImpl.java
 * CreateTime: 2016-06-16 14:09:05
 */
package cc.waa.ng.security;

import static cc.waa.ng.base.data.LoginStatus.FORCEOUT;
import static cc.waa.ng.base.data.LoginStatus.LOGINED;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.util.Assert.hasText;
import static org.springframework.util.Assert.notNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.util.Assert;

import cc.waa.ng.base.data.LoginStatus;
import cc.waa.ng.base.data.SessionStatus;
import cc.waa.ng.base.data.dao.auth.ActivitySessionDao;
import cc.waa.ng.base.data.dao.auth.LoginRecordDao;
import cc.waa.ng.base.data.obj.User;
import cc.waa.ng.base.data.obj.auth.ActivitySession;
import cc.waa.ng.base.data.obj.auth.LoginRecord;
import cc.waa.ng.base.data.obj.auth.Session;
import cc.waa.ng.base.data.obj.auth.StandardLoginRecord;
import cc.waa.ng.util.lang.DateUtils;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class SessionRegistryImpl
	implements SessionRegistry
{

	private static final Logger logger = getLogger(SessionRegistryImpl.class);

	/** 实现Session结束时的不同状态 */
	private static final ThreadLocal<LoginStatus> loginStatusHolder = new ThreadLocal<LoginStatus>();

	/**
	 * 继承框架内的SessionInformation类，扩展对LoginRecord的操作
	 * 
	 * @author  Kinwaa
	 *
	 * @version 0.0.1
	 * @since   0.0.1
	 */
	private class SessionInformationSupportKickoff
		extends SessionInformation
	{

		/** serialVersionUID */
		private static final long serialVersionUID = -6619393706421918638L;

		/**
		 * @param principal
		 * @param sessionId
		 * @param lastRequest
		 */
		private SessionInformationSupportKickoff(Object principal, String sessionId, Date lastRequest)
		{
			super(principal, sessionId, lastRequest);
		}

		@Override
		public void refreshLastRequest()
		{
			super.refreshLastRequest();

			SessionRegistryImpl.this.refreshLastRequest(getSessionId(), getLastRequest());
		}

		@Override
		public void expireNow()
		{
			super.expireNow();

			SessionRegistryImpl.this.expireNow(getSessionId());
		}

	};



	private LoginRecordDao loginRecordDao;

	public void setLoginRecordDao(LoginRecordDao loginRecordDao)
	{
		this.loginRecordDao = loginRecordDao;
	}

	private ActivitySessionDao activitySessionDao;

	public void setActivitySessionDao(ActivitySessionDao activitySessionDao)
	{
		this.activitySessionDao = activitySessionDao;
	}

	////////////////////////////////////////////////////////////////////////////
	// 供SessionInformationSupportKickoff调用的方法
	//

	/**
	 * 刷新SessionInformation的最后访问时间
	 * 
	 * @param sessionId			web容器的sessionId
	 * @param lastRequest
	 */
	private void refreshLastRequest(String sessionId, Date lastRequest)
	{
		LoginRecord loginRecord = getLoginRecord(sessionId);

		if (loginRecord == null || loginRecord.getLoginStatus() != LOGINED)	// 未登录
			return;

		loginRecord.setLastRequestTime(lastRequest);
		loginRecord.setRequestTimes(loginRecord.getRequestTimes() + 1);
	}

	/**
	 * 标记SessionInformation为失效
	 * 
	 * @param sessionId
	 */
	private void expireNow(String sessionId)
	{
		LoginRecord loginRecord = getLoginRecord(sessionId);

		if (loginRecord == null || loginRecord.getLoginStatus() != LOGINED)	// 未登录
			return;

		loginRecord.setLoginStatus(FORCEOUT);
		loginRecord.setQuitTime(DateUtils.getTransactionDate());

		loginRecord = this.loginRecordDao.update(loginRecord);
	}

	//
	// 供SessionInformationSupportKickoff调用的方法 - 结束
	////////////////////////////////////////////////////////////////////////////

	/**
	 * 根据web容器的sessionId获取相应的登录记录
	 * 
	 * @param sessionId			web容器的sessionId
	 * @return
	 */
	private LoginRecord getLoginRecord(String sessionId)
	{
		ActivitySession activity;
		Session session;

		hasText(sessionId, "SessionId required as per interface contract");

		if ((activity = this.activitySessionDao.findOne(sessionId)) == null ||
			(session = activity.getSession()) == null)
		{
			return null;
		}

		// 如果该Session已经被踢下线，从这里清理Authentication
		if (session.getStatus() == SessionStatus.FORCEOUT)
		{
			logger.trace("强制退出旧的登录状态……");

			SecurityContextHolder.getContext().setAuthentication(null);
		}

		return session.getLoginRecord();
	}

	/**
	 * 根据LoginRecord生成相应的Principal对象
	 * 
	 * @param loginRecord
	 * @return
	 */
	private Object toPrincipal(LoginRecord loginRecord)
	{
		if (loginRecord instanceof StandardLoginRecord)
		{
			User user = loginRecord.getUser();
			String account = ((StandardLoginRecord) loginRecord).getLoginAccount();

			return new NgUserDetails(user, account);
		}

		return null;
	}

	/**
	 * 登录记录转化为SessionInformation
	 * 
	 * @param loginRecord
	 * @param sessionId
	 * @return
	 */
	private SessionInformation toSessionInformation(LoginRecord loginRecord, String sessionId)
	{
		hasText(sessionId, "这里要有web容器的sessionId才行喔");

		logger.trace("检查当前登录记录的状态：{}", loginRecord.getLoginStatus());

		Object principal = toPrincipal(loginRecord);
		Date lastRequest = loginRecord.getLastRequestTime();

		return new SessionInformationSupportKickoff(principal, sessionId, lastRequest);
	}

	/**
	 * 指定状态（或者说退出方式）的removeSessionInformation
	 * 
	 * @param sessionId			web容器的sessionId
	 * @param status
	 */
	public void removeSessionInformation(String sessionId, LoginStatus status)
	{
		hasText(sessionId, "没有sessionId就不能帮你处理");
		notNull(status, "想用这个方法，你就得告诉我LoginStatus");

		loginStatusHolder.set(status);

		removeSessionInformation(sessionId);

		loginStatusHolder.set(null);
	}

	/**
	 * 从principal提取出相应的用户对象
	 * 
	 * @param principal
	 * @return
	 */
	private User pickUser(Object principal)
	{
		if (principal instanceof AbstractUserDetails)
			return ((AbstractUserDetails) principal).getUser();

		return null;
	}

	private LoginRecord chooseLoginRecordType(Object principal)
	{
		if (principal instanceof NgUserDetails)
		{
			StandardLoginRecord rec = this.loginRecordDao.blank(StandardLoginRecord.class);

			rec.setLoginAccount(((NgUserDetails) principal).getUsername());

			return rec;
		}

		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.session.SessionRegistry#getAllPrincipals()
	 */
	@Override
	public List<Object> getAllPrincipals()
	{
		logger.trace("getAllPrincipals()");

		List<Object> principals = new ArrayList<Object>();

		for (LoginRecord rec : this.loginRecordDao.findByLoginStatus(LOGINED))
			if (rec != null)
				principals.add(toPrincipal(rec));

		return principals;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.session.SessionRegistry#getAllSessions(java.lang.Object, boolean)
	 */
	@Override
	public List<SessionInformation> getAllSessions(Object principal, boolean includeExpiredSessions)
	{
		logger.trace("getAllSessions(java.lang.Object, boolean) => ({}, {})", principal, includeExpiredSessions);

		List<SessionInformation> sessions = new ArrayList<SessionInformation>();
		User user = pickUser(principal);
		LoginStatus[] status = includeExpiredSessions ? LoginStatus.values() : new LoginStatus[] { LOGINED };

		if (user == null)
			return Collections.emptyList();

		for (LoginRecord rec : this.loginRecordDao.findByUserLoginStatusIn(user, status))
		{
			if (rec == null)
				continue;

			Session session = rec.getSession();
			List<ActivitySession> activities = session.getActivities();

			if (activities == null || activities.isEmpty())
			{
				logger.error("数据异常，能查到的会话不应该没有web容器会话的关联");

				continue;
			}

			sessions.add(toSessionInformation(rec, activities.get(0).getId()));
		}

		return sessions;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.session.SessionRegistry#getSessionInformation(java.lang.String)
	 */
	@Override
	public SessionInformation getSessionInformation(String sessionId)
	{
		logger.trace("getSessionInformation(java.lang.String) => ({})", sessionId);

		LoginRecord loginRecord = getLoginRecord(sessionId);

		if (loginRecord == null)	// 未登录
			return null;

		return toSessionInformation(loginRecord, sessionId);
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.session.SessionRegistry#refreshLastRequest(java.lang.String)
	 */
	@Override
	public void refreshLastRequest(String sessionId)
	{
		logger.trace("refreshLastRequest(java.lang.String) => ({})", sessionId);

		Assert.hasText(sessionId, "SessionId required as per interface contract");

		SessionInformation info = getSessionInformation(sessionId);

		if (info != null)
			info.refreshLastRequest();
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.session.SessionRegistry#registerNewSession(java.lang.String, java.lang.Object)
	 */
	@Override
	public void registerNewSession(String sessionId, Object principal)
	{
		logger.trace("registerNewSession(java.lang.String, java.lang.Object) => ({}, {})", sessionId, principal);

		hasText(sessionId, "SessionId required as per interface contract");
		notNull(principal, "Principal required as per interface contract");

		if (getSessionInformation(sessionId) != null)
			removeSessionInformation(sessionId, FORCEOUT);

		ActivitySession activity = this.activitySessionDao.findOne(sessionId);
		Session session = activity.getSession();

		LoginRecord loginRecord = chooseLoginRecordType(principal);

		loginRecord.setLoginStatus(LOGINED);
		loginRecord.setLoginTime(DateUtils.getTransactionDate());
		loginRecord.setSession(session);
		loginRecord.setUser(pickUser(principal));

		loginRecord.setLastRequestTime(DateUtils.getTransactionDate());
		loginRecord.setRequestTimes(1);

		loginRecord = this.loginRecordDao.save(loginRecord);
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.session.SessionRegistry#removeSessionInformation(java.lang.String)
	 */
	@Override
	public void removeSessionInformation(String sessionId)
	{
		logger.trace("removeSessionInformation(java.lang.String) => ({})", sessionId);

		LoginRecord loginRecord = getLoginRecord(sessionId);

		if (loginRecord == null || loginRecord.getLoginStatus() != LOGINED)
			return;

		loginRecord.setLoginStatus(loginStatusHolder.get());
		loginRecord.setQuitTime(DateUtils.getTransactionDate());

		loginRecord = this.loginRecordDao.update(loginRecord);
	}

}
