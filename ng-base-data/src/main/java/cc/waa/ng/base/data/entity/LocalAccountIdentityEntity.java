/*
 * Project:    waa新一代框架的基础模块中的数据访问部分
 * 
 * FileName:   LocalAccountIdentityEntity.java
 * CreateTime: 2016-05-05 18:11:43
 */
package cc.waa.ng.base.data.entity;

import cc.waa.ng.base.data.obj.LocalAccountIdentity;
import cc.waa.ng.data.entity.TargetObject;

/**
 * 本地账号的鉴权标识
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetObject(LocalAccountIdentity.class)
public class LocalAccountIdentityEntity
	extends IdentityEntity
{

	/** serialVersionUID */
	private static final long serialVersionUID = 1910781407143111434L;



	/** 本地账号 */
	private String account;

	public String getAccount()
	{
		return this.account;
	}

	public void setAccount(String account)
	{
		this.account = account;
	}

}
