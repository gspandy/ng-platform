/*
 * Project:    waa新一代框架的基础模块中的数据访问部分
 * 
 * FileName:   ActivitySession.java
 * CreateTime: 2016-03-31 18:03:44
 */
package cc.waa.ng.base.data.obj.auth;

import java.util.Date;

import cc.waa.ng.base.data.entity.auth.ActivitySessionEntity;
import cc.waa.ng.data.obj.TargetEntity;

/**
 * web容器的会话记录
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetEntity(ActivitySessionEntity.class)
public interface ActivitySession
{

	public String getId();

	public void setId(String id);

	public Session getSession();

	public void setSession(Session session);

	public Date getCreateTime();

}
