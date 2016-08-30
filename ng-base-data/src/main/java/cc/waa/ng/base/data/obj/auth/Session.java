/*
 * Project:    waa新一代框架的基础模块中的数据访问部分
 * 
 * FileName:   Session.java
 * CreateTime: 2016-01-14 18:36:03
 */
package cc.waa.ng.base.data.obj.auth;

import java.util.Date;
import java.util.List;

import cc.waa.ng.base.data.SessionStatus;
import cc.waa.ng.base.data.entity.auth.SessionEntity;
import cc.waa.ng.data.obj.TargetEntity;

/**
 * 系统自身的会话记录
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetEntity(SessionEntity.class)
public interface Session
{

	String getId();

	void setId(String id);

	List<ActivitySession> getActivities();

	void setActivities(List<ActivitySession> activities);

	SessionStatus getStatus();

	void setStatus(SessionStatus status);

	String getUserAgent();

	void setUserAgent(String userAgent);

	List<String> getCreateIpRoute();

	void setCreateIpRoute(List<String> createIpRoute);

	Date getCreateTime();

	List<String> getRefreshIpRoute();

	void setRefreshIpRoute(List<String> refreshIpRoute);

	Date getRefreshTime();

	void setRefreshTime(Date refreshTime);

	LoginRecord getLoginRecord();

	void setLoginRecord(LoginRecord loginRecord);

	long getVisitTimes();

	void setVisitTimes(long visitTimes);

	long getLoginTimes();

	void setLoginTimes(long loginTimes);

}
