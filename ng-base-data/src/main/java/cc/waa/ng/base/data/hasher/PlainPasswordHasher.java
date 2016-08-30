/*
 * Project:    waa新一代框架的基础模块中的实体部分
 * 
 * FileName:   PlainPasswordHasher.java
 * CreateTime: 2015-09-17 13:54:17
 */
package cc.waa.ng.base.data.hasher;

import org.apache.commons.lang3.StringUtils;

import cc.waa.ng.base.data.obj.User;

/**
 * 明文密码的密码加密处理
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class PlainPasswordHasher
	implements PasswordHasher
{

	/* (non-Javadoc)
	 * @see cc.waa.ng.base.data.hasher.PasswordHasher#hash(java.lang.CharSequence)
	 */
	@Override
	public String hash(CharSequence rawPassword)
	{
		return rawPassword.toString();
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.base.data.hasher.PasswordHasher#check(java.lang.CharSequence, cc.waa.ng.base.data.obj.User)
	 */
	@Override
	public boolean check(CharSequence rawPassword, User user)
	{
		return StringUtils.equals(rawPassword, user.getPassHash());
	}

}
