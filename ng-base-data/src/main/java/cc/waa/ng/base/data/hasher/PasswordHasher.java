/*
 * Project:    waa新一代框架的基础模块中的实体部分
 * 
 * FileName:   PasswordHasher.java
 * CreateTime: 2015-09-17 13:37:00
 */
package cc.waa.ng.base.data.hasher;

import cc.waa.ng.base.data.obj.User;

/**
 * 将密码加密处理的接口
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public interface PasswordHasher
{

	/**
	 * 生成密码
	 * 
	 * @param rawPassword
	 * @return
	 */
	String hash(CharSequence rawPassword);

	/**
	 * 验证用户的密码是否正确
	 * 
	 * @param rawPassword
	 * @param user
	 * @return
	 */
	boolean check(CharSequence rawPassword, User user);

}
