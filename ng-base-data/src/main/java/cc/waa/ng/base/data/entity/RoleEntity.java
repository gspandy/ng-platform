/*
 * Project:    waa新一代框架的基础模块中的实体部分
 * 
 * FileName:   RoleEntity.java
 * CreateTime: 2015-09-18 09:51:13
 */
package cc.waa.ng.base.data.entity;

import java.io.Serializable;
import java.util.Date;

import cc.waa.ng.base.data.entity.snapshot.RoleSnapshot;
import cc.waa.ng.base.data.obj.Role;
import cc.waa.ng.data.entity.BaseEntity;
import cc.waa.ng.data.entity.LogicalDeleteSupport;
import cc.waa.ng.data.entity.TargetObject;
import cc.waa.ng.data.entity.snapshot.Snapshotable;

/**
 * 用户角色实体类
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetObject(Role.class)
@Snapshotable(type = RoleSnapshot.class)
public class RoleEntity
	implements LogicalDeleteSupport, BaseEntity, Serializable
{

	/** serialVersionUID */
	private static final long serialVersionUID = 2252879335874281443L;



	/** 角色实体类对象id */
	private String id;

	/** 角色名称 */
	private String name;

	private String code;

	/** 角色对应的身份类型 */
	private Class<? extends ProfileEntity> profileType;

	/** 对当前角色使用上的说明 */
	private String description;

	/** 是否已经删除 */
	private boolean deleted;

	/** 创建时间 */
	private Date createTime;

	/** 删除时间 */
	private Date deleteTime;

	/* (non-Javadoc)
	 * @see cc.waa.ng.base.entity.support.BaseEntity#getId()
	 */
	@Override
	public String getId()
	{
		return this.id;
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.base.entity.support.BaseEntity#setId(java.lang.String)
	 */
	@Override
	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getCode()
	{
		return this.code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public Class<? extends ProfileEntity> getProfileType()
	{
		return this.profileType;
	}

	public void setProfileType(Class<? extends ProfileEntity> profileType)
	{
		this.profileType = profileType;
	}

	public String getDescription()
	{
		return this.description;
	}

	public void setDescription(String description)
	{
		this.description = description;
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
	 * @see cc.waa.ng.base.entity.support.BaseEntity#getCreateTime()
	 */
	@Override
	public Date getCreateTime()
	{
		return this.createTime;
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.base.entity.support.BaseEntity#setCreateTime(java.util.Date)
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
