/*
 * Project:    waa新一代框架的基础模块中的数据访问部分
 * 
 * FileName:   IdentityEntity.java
 * CreateTime: 2016-05-05 17:45:01
 */
package cc.waa.ng.base.data.entity;

import java.io.Serializable;
import java.util.Date;

import cc.waa.ng.data.entity.BaseEntity;

/**
 * 鉴权标识（用于定位用，通常某一种类型的Identity只会定位到一个用户）
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public abstract class IdentityEntity
	implements BaseEntity, Serializable
{

	/** serialVersionUID */
	private static final long serialVersionUID = -2732075888576631770L;



	private String id;

	/** 所属用户 */
	private UserEntity user;

	/** 标识当前鉴权标识是否生效，部分鉴权标识可能只是占用未完成验证的流程 */
	private boolean enabled;

	/** 创建时间 */
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

	public UserEntity getUser()
	{
		return this.user;
	}

	public void setUser(UserEntity user)
	{
		this.user = user;
	}

	public boolean isEnabled()
	{
		return this.enabled;
	}

	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
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
