/*
 * Project:    waa新一代框架的基础模块中的实体部分
 * 
 * FileName:   LoginRecordEntity.java
 * CreateTime: 2015-09-19 14:56:48
 */
package cc.waa.ng.base.data.entity.auth;

import java.io.Serializable;
import java.util.Date;

import cc.waa.ng.base.data.LoginStatus;
import cc.waa.ng.base.data.entity.UserEntity;
import cc.waa.ng.base.data.obj.auth.LoginRecord;
import cc.waa.ng.data.entity.BaseEntity;
import cc.waa.ng.data.entity.TargetObject;

/**
 * 登录记录
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetObject(LoginRecord.class)
public abstract class LoginRecordEntity
	implements BaseEntity, Serializable
{

	/** serialVersionUID */
	private static final long serialVersionUID = -8682785340597399027L;



	private String id;

	private UserEntity user;

	private SessionEntity session;

	private LoginStatus loginStatus;

	private Date loginTime;

	private long requestTimes;

	private Date lastRequestTime;

	private Date quitTime;

	private Date createTime;

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

	public UserEntity getUser()
	{
		return this.user;
	}

	public void setUser(UserEntity user)
	{
		this.user = user;
	}

	public SessionEntity getSession()
	{
		return this.session;
	}

	public void setSession(SessionEntity session)
	{
		this.session = session;
	}

	public LoginStatus getLoginStatus()
	{
		return this.loginStatus;
	}

	public void setLoginStatus(LoginStatus status)
	{
		this.loginStatus = status;
	}

	public Date getLoginTime()
	{
		return this.loginTime;
	}

	public void setLoginTime(Date loginTime)
	{
		this.loginTime = loginTime;
	}

	public long getRequestTimes()
	{
		return this.requestTimes;
	}

	public void setRequestTimes(long requestTimes)
	{
		this.requestTimes = requestTimes;
	}

	public Date getLastRequestTime()
	{
		return this.lastRequestTime;
	}

	public void setLastRequestTime(Date lastRequestTime)
	{
		this.lastRequestTime = lastRequestTime;
	}

	public Date getQuitTime()
	{
		return this.quitTime;
	}

	public void setQuitTime(Date quitTime)
	{
		this.quitTime = quitTime;
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

}
