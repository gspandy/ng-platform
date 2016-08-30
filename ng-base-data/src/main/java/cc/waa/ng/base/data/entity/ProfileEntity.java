/*
 * Project:    waa新一代框架的基础模块中的实体部分
 * 
 * FileName:   ProfileEntity.java
 * CreateTime: 2015-09-18 09:52:43
 */
package cc.waa.ng.base.data.entity;

import java.io.Serializable;
import java.util.Date;

import cc.waa.ng.data.entity.BaseEntity;
import cc.waa.ng.data.entity.LogicalDeleteSupport;

/**
 * 抽象的身份对象
 * 注意：
 *  1、身份对象中的各个属性禁止更新(deleted和deleteTime除外)，这样可以按时间查询
 *     用户历史以来拥有角色(或身份)的情况;
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public abstract class ProfileEntity
	implements LogicalDeleteSupport, BaseEntity, Serializable
{

	/** serialVersionUID */
	private static final long serialVersionUID = -661205808021815748L;



	/** 用户身份实体类对象id */
	private String id;

	/** 身份所属用户对象 */
	private UserEntity user;

	/** 身份对应的角色对象 */
	private RoleEntity role;

	/** 是否已经删除 */
	private boolean deleted;

	/** 创建时间 */
	private Date createTime;

	/** 删除时间 */
	private Date deleteTime;

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

	public RoleEntity getRole()
	{
		return this.role;
	}

	public void setRole(RoleEntity role)
	{
		this.role = role;
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.base.entity.support.LogicalDeleteSupport#isDeleted()
	 */
	@Override
	public boolean isDeleted()
	{
		return this.deleted;
	}

	public void setDeleted(boolean deleted)
	{
		this.deleted = deleted;
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

	/* (non-Javadoc)
	 * @see cc.waa.ng.base.entity.support.LogicalDeleteSupport#getDeleteTime()
	 */
	@Override
	public Date getDeleteTime()
	{
		return this.deleteTime;
	}

	public void setDeleteTime(Date deleteTime)
	{
		this.deleteTime = deleteTime;
	}

}
