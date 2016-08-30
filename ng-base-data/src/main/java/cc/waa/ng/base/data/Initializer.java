/*
 * Project:    waa新一代框架的基础模块中的数据访问部分
 * 
 * FileName:   Initializer.java
 * CreateTime: 2016-01-30 13:39:38
 */
package cc.waa.ng.base.data;

import static cc.waa.ng.base.data.PassHasher.MD5;

import org.springframework.context.ApplicationContext;

import cc.waa.ng.base.data.dao.IdentityDao;
import cc.waa.ng.base.data.dao.ProfileDao;
import cc.waa.ng.base.data.dao.RoleDao;
import cc.waa.ng.base.data.dao.UserDao;
import cc.waa.ng.base.data.obj.AdminProfile;
import cc.waa.ng.base.data.obj.LocalAccountIdentity;
import cc.waa.ng.base.data.obj.Role;
import cc.waa.ng.base.data.obj.User;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class Initializer
{

	public static void init(ApplicationContext context)
	{
		UserDao userDao = context.getBean(UserDao.class);
		RoleDao roleDao = context.getBean(RoleDao.class);
		ProfileDao profileDao = context.getBean(ProfileDao.class);
		IdentityDao identityDao = context.getBean(IdentityDao.class);

		User user = userDao.blank();
		user.setExpired(false);
		user.setLocked(false);
		user.setPassHash(MD5.hash("leung"));
		user.setPassHasher(MD5);
		user = userDao.save(user);

		LocalAccountIdentity lai = identityDao.blank(LocalAccountIdentity.class);
		lai.setUser(user);
		lai.setAccount("kinwaa");
		lai.setEnabled(true);
		lai = identityDao.save(lai);

		Role role = roleDao.blank();
		role.setCode("ADMIN");
		role.setName("系统管理员");
		role.setProfileType(AdminProfile.class);
		role = roleDao.save(role);

		AdminProfile profile = profileDao.blank(AdminProfile.class);
		profile.setRole(role);
		profile.setUser(user);
		profile = profileDao.save(profile);
	}

}
