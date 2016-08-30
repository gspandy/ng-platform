/*
 * Project:    waa新一代框架的基础模块中的实体部分
 * 
 * FileName:   UserSnapshot.java
 * CreateTime: 2015-09-23 23:04:19
 */
package cc.waa.ng.base.data.entity.snapshot;

import java.util.Date;

import cc.waa.ng.base.data.PassHasher;
import cc.waa.ng.base.data.entity.UserEntity;
import cc.waa.ng.data.entity.snapshot.NestedProperty;
import cc.waa.ng.data.entity.snapshot.Snapshot;

/**
 * 用户实体类快照
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class UserSnapshot
	extends Snapshot<UserEntity>
{

	/** serialVersionUID */
	private static final long serialVersionUID = 3216279874527010007L;



	private UserEntity owner;

	private PassHasher passHasher;

	private String passHash;

	/** 账号是否被锁定 */
	private boolean locked;

	/** 账号的有效期是否已到 */
	private boolean expired;

	private int loginCount;

	/** 首次登录时间（记录owner.firstLogin.loginTime） */
	@NestedProperty("firstLogin.loginTime")
	private Date firstLoginTime;

	/** 最近登录时间（记录owner.lastLogin.loginTime） */
	@NestedProperty("lastLogin.loginTime")
	private Date lastLoginTime;

	/* (non-Javadoc)
	 * @see cc.waa.ng.base.entity.snapshot.Snapshot#getOwner()
	 */
	@Override
	public UserEntity getOwner()
	{
		return this.owner;
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.base.entity.snapshot.Snapshot#setOwner(java.lang.Object)
	 */
	@Override
	public void setOwner(UserEntity owner)
	{
		this.owner = owner;
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

	public Date getFirstLoginTime()
	{
		return this.firstLoginTime;
	}

	public void setFirstLoginTime(Date firstLoginTime)
	{
		this.firstLoginTime = firstLoginTime;
	}

	public Date getLastLoginTime()
	{
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime)
	{
		this.lastLoginTime = lastLoginTime;
	}

}
