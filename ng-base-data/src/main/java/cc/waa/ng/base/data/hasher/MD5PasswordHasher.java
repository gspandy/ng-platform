/*
 * Project:    waa新一代框架的基础模块中的实体部分
 * 
 * FileName:   MD5PasswordHasher.java
 * CreateTime: 2016-01-14 16:19:59
 */
package cc.waa.ng.base.data.hasher;

import static org.apache.commons.codec.digest.DigestUtils.md5Hex;

import org.apache.commons.lang3.StringUtils;

import cc.waa.ng.base.data.obj.User;

/**
 * 用MD5算法处理用户的密码
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class MD5PasswordHasher
	implements PasswordHasher
{

	/* (non-Javadoc)
	 * @see cc.waa.ng.base.data.hasher.PasswordHasher#hash(java.lang.CharSequence)
	 */
	@Override
	public String hash(CharSequence rawPassword)
	{
		// TODO 直接处理CharSequence接口会不会更好
		return md5Hex(rawPassword.toString());
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.base.data.hasher.PasswordHasher#check(java.lang.CharSequence, cc.waa.ng.base.data.obj.User)
	 */
	@Override
	public boolean check(CharSequence rawPassword, User user)
	{
		if (user == null)
			return false;

		return StringUtils.equals(hash(rawPassword), user.getPassHash());
	}

}
