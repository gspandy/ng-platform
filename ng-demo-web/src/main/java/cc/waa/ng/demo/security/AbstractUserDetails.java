/*
 * Project:    waa新一代框架的演示模块
 * 
 * FileName:   AbstractUserDetails.java
 * CreateTime: 2016-04-01 09:55:36
 */
package cc.waa.ng.demo.security;

import cc.waa.ng.base.data.obj.User;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public abstract class AbstractUserDetails
{

	protected User user;

	/** 默认构建函数（避免Session序列化时出错） */
	public AbstractUserDetails()
	{
		super();
	}

	/**
	 * @param user
	 */
	public AbstractUserDetails(User user)
	{
		super();
	
		this.user = user;
	}

	public User getUser()
	{
		return this.user;
	}

}
