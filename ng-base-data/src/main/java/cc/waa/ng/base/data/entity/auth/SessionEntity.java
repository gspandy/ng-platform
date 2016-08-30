/*
 * Project:    waa新一代框架的基础模块中的实体部分
 * 
 * FileName:   SessionEntity.java
 * CreateTime: 2015-09-22 14:19:53
 */
package cc.waa.ng.base.data.entity.auth;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import cc.waa.ng.base.data.SessionStatus;
import cc.waa.ng.base.data.obj.auth.Session;
import cc.waa.ng.data.entity.BaseEntity;
import cc.waa.ng.data.entity.TargetObject;

/**
 * 跟踪一个客户端全过程的会话记录（只要不手动清除cookie，该记录将会一直存在并更新）
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetObject(Session.class)
public class SessionEntity
	implements BaseEntity, Serializable
{

	/** serialVersionUID */
	private static final long serialVersionUID = 2867840346270100959L;



	private String id;

//	private String sessionId;	// 这个是容器的sessionId
	private List<ActivitySessionEntity> activities;

	private SessionStatus status;

	private String userAgent;

	/** 
	 * 创建该session的ip路由记录
	 * 
	 * 排在第一位的ip是直接从request.getRemoteAddr()获取的（最可信的）
	 * 后面的ip是代理服务器转发的x-requet.....记录
	 */
	private List<String> createIpRoute;

	private Date createTime;

	/**
	 * 最新刷新session的ip路由记录
	 * 
	 * 说明参照createIpRoute
	 */
	private List<String> refreshIpRoute;

	private Date refreshTime;

	/** 对应的登录记录，当且仅当status为LOGINED时必须要设置loginRecord（不为空） */
	private LoginRecordEntity loginRecord;

	private long visitTimes;

	private long loginTimes;

	/* (non-Javadoc)
	 * @see cc.waa.ng.util.entity.BaseEntity#getId()
	 */
	@Override
	public String getId()
	{
		return this.id;
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.util.entity.BaseEntity#setId(java.lang.String)
	 */
	@Override
	public void setId(String id)
	{
		this.id = id;
	}

	public List<ActivitySessionEntity> getActivities()
	{
		return this.activities;
	}

	public void setActivities(List<ActivitySessionEntity> activities)
	{
		this.activities = activities;
	}

	public SessionStatus getStatus()
	{
		return this.status;
	}

	public void setStatus(SessionStatus status)
	{
		this.status = status;
	}

	public String getUserAgent()
	{
		return this.userAgent;
	}

	public void setUserAgent(String userAgent)
	{
		this.userAgent = userAgent;
	}

	public List<String> getCreateIpRoute()
	{
		return this.createIpRoute;
	}

	public void setCreateIpRoute(List<String> createIpRoute)
	{
		this.createIpRoute = createIpRoute;
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.util.entity.BaseEntity#getCreateTime()
	 */
	@Override
	public Date getCreateTime()
	{
		return this.createTime;
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.util.entity.BaseEntity#setCreateTime(java.util.Date)
	 */
	@Override
	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public List<String> getRefreshIpRoute()
	{
		return this.refreshIpRoute;
	}

	public void setRefreshIpRoute(List<String> refreshIpRoute)
	{
		this.refreshIpRoute = refreshIpRoute;
	}

	public Date getRefreshTime()
	{
		return this.refreshTime;
	}

	public void setRefreshTime(Date refreshTime)
	{
		this.refreshTime = refreshTime;
	}

	public LoginRecordEntity getLoginRecord()
	{
		return this.loginRecord;
	}

	public void setLoginRecord(LoginRecordEntity loginRecord)
	{
		this.loginRecord = loginRecord;
	}

	public long getVisitTimes()
	{
		return this.visitTimes;
	}

	public void setVisitTimes(long visitTimes)
	{
		this.visitTimes = visitTimes;
	}

	public long getLoginTimes()
	{
		return this.loginTimes;
	}

	public void setLoginTimes(long loginTimes)
	{
		this.loginTimes = loginTimes;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (!(obj instanceof SessionEntity))
			return false;

		return compareTo((SessionEntity) obj) == 0;
	}

}
