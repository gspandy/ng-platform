/*
 * Project:    waa新一代框架的基础模块中的实体部分
 * 
 * FileName:   RoleSnapshot.java
 * CreateTime: 2015-09-25 10:38:05
 */
package cc.waa.ng.base.data.entity.snapshot;

import cc.waa.ng.base.data.entity.ProfileEntity;
import cc.waa.ng.base.data.entity.RoleEntity;
import cc.waa.ng.data.entity.snapshot.Snapshot;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class RoleSnapshot
	extends Snapshot<RoleEntity>
{

	/** serialVersionUID */
	private static final long serialVersionUID = 1352931492587541223L;



	private RoleEntity owner;

	/** 角色名称 */
	private String name;

	private String code;

	/** 角色对应的身份类型 */
	private Class<? extends ProfileEntity> profileType;

	/** 对当前角色使用上的说明 */
	private String description;

	/* (non-Javadoc)
	 * @see cc.waa.ng.base.entity.snapshot.Snapshot#getOwner()
	 */
	@Override
	public RoleEntity getOwner()
	{
		return this.owner;
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.base.entity.snapshot.Snapshot#setOwner(java.lang.Object)
	 */
	@Override
	public void setOwner(RoleEntity owner)
	{
		this.owner = owner;
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

}
