/*
 * Project:    waa新一代框架的安全模块
 * 
 * FileName:   NgPasswordEncoder.java
 * CreateTime: 2016-06-16 14:43:30
 */
package cc.waa.ng.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import cc.waa.ng.base.data.dao.UserDao;
import cc.waa.ng.base.data.obj.User;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class NgPasswordEncoder
	implements PasswordEncoder
{

	private UserDao userDao;

	public void setUserDao(UserDao userDao)
	{
		this.userDao = userDao;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.crypto.password.PasswordEncoder#encode(java.lang.CharSequence)
	 */
	@Override
	public String encode(CharSequence rawPassword)
	{
		return rawPassword.toString();
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.crypto.password.PasswordEncoder#matches(java.lang.CharSequence, java.lang.String)
	 */
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword)
	{
		User user;

		if ((user = this.userDao.findOne(encodedPassword)) == null)
			return false;

		return user.getPassHasher().check(rawPassword, user);
	}

}
