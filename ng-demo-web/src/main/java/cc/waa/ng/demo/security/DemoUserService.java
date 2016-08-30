/*
 * Project:    waa新一代框架的演示模块
 * 
 * FileName:   DemoUserService.java
 * CreateTime: 2016-01-20 18:30:05
 */
package cc.waa.ng.demo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import cc.waa.ng.base.data.dao.LocalAccountIdentityDao;
import cc.waa.ng.base.data.obj.LocalAccountIdentity;
import cc.waa.ng.base.data.obj.User;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class DemoUserService
	implements UserDetailsService
{

	private LocalAccountIdentityDao identityDao;

	public void setIdentityDao(LocalAccountIdentityDao identityDao)
	{
		this.identityDao = identityDao;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(final String username)
		throws UsernameNotFoundException
	{
		User user;
		LocalAccountIdentity lai = this.identityDao.findByAccount(username);

		if (lai == null || (user = lai.getUser()) == null)
			throw new UsernameNotFoundException(username);

		return new DemoUserDetails(user, username);
	}

}
