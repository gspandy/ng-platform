/*
 * Project:    waa新一代框架的基础模块中的数据访问部分
 * 
 * FileName:   LocalAccountIdentity.java
 * CreateTime: 2016-05-06 11:32:53
 */
package cc.waa.ng.base.data.obj;

import cc.waa.ng.base.data.entity.LocalAccountIdentityEntity;
import cc.waa.ng.data.obj.TargetEntity;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetEntity(LocalAccountIdentityEntity.class)
public interface LocalAccountIdentity
	extends Identity
{

	String getAccount();

	void setAccount(String account);

}
