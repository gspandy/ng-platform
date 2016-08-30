/*
 * Project:    waa新一代框架的基础模块中的数据访问部分
 * 
 * FileName:   LoginRecord.java
 * CreateTime: 2016-01-14 18:32:27
 */
package cc.waa.ng.base.data.obj.auth;

import java.util.Date;

import cc.waa.ng.base.data.LoginStatus;
import cc.waa.ng.base.data.entity.auth.LoginRecordEntity;
import cc.waa.ng.base.data.obj.User;
import cc.waa.ng.data.obj.TargetEntity;

/**
 * 登录记录的业务对象
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetEntity(LoginRecordEntity.class)
public interface LoginRecord
{

	String getId();

	User getUser();

	void setUser(User user);

	Session getSession();

	void setSession(Session session);

	LoginStatus getLoginStatus();

	void setLoginStatus(LoginStatus loginStatus);

	Date getLoginTime();

	void setLoginTime(Date loginTime);

	long getRequestTimes();

	void setRequestTimes(long requestTimes);

	Date getLastRequestTime();

	void setLastRequestTime(Date lastRequestTime);

	Date getQuitTime();

	void setQuitTime(Date quitTime);

	Date getCreateTime();

}
