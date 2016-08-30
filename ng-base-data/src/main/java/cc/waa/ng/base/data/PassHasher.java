/*
 * Project:    waa新一代框架的基础模块中的实体部分
 * 
 * FileName:   PassHasher.java
 * CreateTime: 2015-09-17 13:21:27
 */
package cc.waa.ng.base.data;

import cc.waa.ng.base.data.hasher.MD5PasswordHasher;
import cc.waa.ng.base.data.hasher.NonePasswordHasher;
import cc.waa.ng.base.data.hasher.PasswordHasher;
import cc.waa.ng.base.data.hasher.PlainPasswordHasher;
import cc.waa.ng.base.data.obj.User;

/**
 * 密码加密器（用户密码的处理方式）
 * 由于可能会出现系统升级等原因，密码的处理方式可能会改变，所以需要记录不同用户当前使用的密码方式
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public enum PassHasher
{

	/** 无密码 */
	NONE(new NonePasswordHasher()),

	/** 明文密码 */
	PLAIN(new PlainPasswordHasher()),

	/** MD5 */
	MD5(new MD5PasswordHasher());

	private PasswordHasher hasher;

	private PassHasher(PasswordHasher hasher)
	{
		this.hasher = hasher;
	}

	public String hash(CharSequence raw)
	{
		return this.hasher.hash(raw);
	}

	public boolean check(CharSequence raw, User user)
	{
		return this.hasher.check(raw, user);
	}

}
