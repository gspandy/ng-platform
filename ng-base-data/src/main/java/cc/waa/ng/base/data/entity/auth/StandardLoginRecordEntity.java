/*
 * Project:    waa新一代框架的基础模块中的实体部分
 * 
 * FileName:   StandardLoginRecordEntity.java
 * CreateTime: 2015-09-22 14:40:30
 */
package cc.waa.ng.base.data.entity.auth;

import cc.waa.ng.base.data.obj.auth.StandardLoginRecord;
import cc.waa.ng.data.entity.TargetObject;

/**
 * 网站本地登录，通过本地账号和密码进行的
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetObject(StandardLoginRecord.class)
public class StandardLoginRecordEntity
	extends LoginRecordEntity
{

	/** serialVersionUID */
	private static final long serialVersionUID = 921234914452705276L;



	/** 记录用户登录时用的账号 */
	private String loginAccount;

	public String getLoginAccount()
	{
		return this.loginAccount;
	}

	public void setLoginAccount(String loginAccount)
	{
		this.loginAccount = loginAccount;
	}

}
