/*
 * Project:    waa新一代框架的基础模块中的数据访问部分
 * 
 * FileName:   ActivitySessionEntity.java
 * CreateTime: 2016-03-31 17:54:40
 */
package cc.waa.ng.base.data.entity.auth;

import java.io.Serializable;
import java.util.Date;

import cc.waa.ng.base.data.obj.auth.ActivitySession;
import cc.waa.ng.data.entity.BaseEntity;
import cc.waa.ng.data.entity.TargetObject;

/**
 * 跟踪web容器的会话记录（会随着web容器session的销毁而一同销毁）
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetObject(ActivitySession.class)
public class ActivitySessionEntity
	implements BaseEntity, Serializable
{

	/** serialVersionUID */
	private static final long serialVersionUID = -877729265532590267L;



	private String id;

	private SessionEntity session;

	private Date createTime;

	/* (non-Javadoc)
	 * @see cc.waa.ng.data.entity.BaseEntity#getId()
	 */
	@Override
	public String getId()
	{
		return this.id;
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.data.entity.BaseEntity#setId(java.lang.String)
	 */
	@Override
	public void setId(String id)
	{
		this.id = id;
	}

	public SessionEntity getSession()
	{
		return this.session;
	}

	public void setSession(SessionEntity session)
	{
		this.session = session;
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.data.entity.BaseEntity#getCreateTime()
	 */
	@Override
	public Date getCreateTime()
	{
		return this.createTime;
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.data.entity.BaseEntity#setCreateTime(java.util.Date)
	 */
	@Override
	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

}
