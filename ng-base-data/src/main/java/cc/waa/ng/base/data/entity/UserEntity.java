/*
 * Project:    waa新一代框架的基础模块中的实体部分
 * 
 * FileName:   UserEntity.java
 * CreateTime: 2015-09-17 11:43:47
 */
package cc.waa.ng.base.data.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import cc.waa.ng.base.data.PassHasher;
import cc.waa.ng.base.data.entity.auth.LoginRecordEntity;
import cc.waa.ng.base.data.entity.snapshot.UserSnapshot;
import cc.waa.ng.base.data.obj.User;
import cc.waa.ng.data.entity.BaseEntity;
import cc.waa.ng.data.entity.LogicalDeleteSupport;
import cc.waa.ng.data.entity.TargetObject;
import cc.waa.ng.data.entity.snapshot.Snapshotable;

/**
 * 用户实体类
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetObject(User.class)
@Snapshotable(type = UserSnapshot.class)
public class UserEntity
	implements LogicalDeleteSupport, BaseEntity, Serializable
{

	/** serialVersionUID */
	private static final long serialVersionUID = -8607758991286282949L;



	/** 用户实体类对象id */
	private String id;

	/* 系统账号 */
//	private String account;

	private Set<IdentityEntity> identities;

	/** 用户密码的编码器，初始化为NONE，同时passhash属性为空 */
	private PassHasher passHasher;

	/** 用户密码 */
	private String passHash;

	/**
	 * 具体每个角色对应的身份信息
	 * 由于jpa不支持关联的时候加入条件限制，所以不能用Map<Role, Profile>的形式
	 */
	private Set<ProfileEntity> profiles;

	/** 是否已经删除 */
	private boolean deleted;

	/** 创建时间 */
	private Date createTime;

	/** 删除时间 */
	private Date deleteTime;

	/** 账号是否被锁定 */
	private boolean locked;

	/** 账号的有效期是否已到 */
	private boolean expired;


	/*
	 * 用户登录情况
	 */

	/** 登录次数 */
	private int loginCount;

	/** 首次登录 */
	private LoginRecordEntity firstLogin;

	/** 最近登录 */
	private LoginRecordEntity lastLogin;

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

	public Set<IdentityEntity> getIdentities()
	{
		return this.identities;
	}

	public void setIdentities(Set<IdentityEntity> identities)
	{
		this.identities = identities;
	}

	public PassHasher getPassHasher()
	{
		return this.passHasher;
	}

	public void setPassHasher(PassHasher passHasher)
	{
		this.passHasher = passHasher;
	}

	public String getPassHash()
	{
		return this.passHash;
	}

	public void setPassHash(String passHash)
	{
		this.passHash = passHash;
	}

	public Set<ProfileEntity> getProfiles()
	{
		return this.profiles;
	}

	public void setProfiles(Set<ProfileEntity> profiles)
	{
		this.profiles = profiles;
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

	public boolean isLocked()
	{
		return this.locked;
	}

	public void setLocked(boolean locked)
	{
		this.locked = locked;
	}

	public boolean isExpired()
	{
		return this.expired;
	}

	public void setExpired(boolean expired)
	{
		this.expired = expired;
	}

	public int getLoginCount()
	{
		return this.loginCount;
	}

	public void setLoginCount(int loginCount)
	{
		this.loginCount = loginCount;
	}

	public LoginRecordEntity getFirstLogin()
	{
		return this.firstLogin;
	}

	public void setFirstLogin(LoginRecordEntity firstLogin)
	{
		this.firstLogin = firstLogin;
	}

	public LoginRecordEntity getLastLogin()
	{
		return this.lastLogin;
	}

	public void setLastLogin(LoginRecordEntity lastLogin)
	{
		this.lastLogin = lastLogin;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		if (this.id == null)
			return 0;

		return this.id.hashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this.id == null || !(obj instanceof UserEntity))
			return false;

		return this.id.equals(((UserEntity) obj).id);
	}

}
