/*
 * Project:    waa新一代框架的基础模块中的实体部分
 * 
 * FileName:   NonePasswordHasher.java
 * CreateTime: 2015-09-17 13:43:20
 */
package cc.waa.ng.base.data.hasher;

import cc.waa.ng.base.data.obj.User;

/**
 * 无密码的密码加密处理
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class NonePasswordHasher
	implements PasswordHasher
{

	/* (non-Javadoc)
	 * @see cc.waa.ng.base.data.hasher.PasswordHasher#hash(java.lang.CharSequence)
	 */
	@Override
	public String hash(CharSequence rawPassword)
	{
		return null;
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.base.data.hasher.PasswordHasher#check(java.lang.CharSequence, cc.waa.ng.base.data.obj.User)
	 */
	@Override
	public boolean check(CharSequence rawPassword, User user)
	{
		return false;	// 无密码的用户禁止通过本地账号验证
	}

}
