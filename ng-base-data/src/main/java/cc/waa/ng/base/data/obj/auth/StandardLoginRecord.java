/*
 * Project:    waa新一代框架的基础模块中的数据访问部分
 * 
 * FileName:   StandardLoginRecord.java
 * CreateTime: 2016-01-14 20:29:05
 */
package cc.waa.ng.base.data.obj.auth;

import cc.waa.ng.base.data.entity.auth.StandardLoginRecordEntity;
import cc.waa.ng.data.obj.TargetEntity;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetEntity(StandardLoginRecordEntity.class)
public interface StandardLoginRecord
	extends LoginRecord
{

	String getLoginAccount();

	void setLoginAccount(String loginAccount);

}
