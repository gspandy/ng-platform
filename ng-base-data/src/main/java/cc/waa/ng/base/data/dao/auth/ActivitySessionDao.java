/*
 * Project:    waa新一代框架的基础模块中的数据访问部分
 * 
 * FileName:   ActivitySessionDao.java
 * CreateTime: 2016-03-31 18:06:12
 */
package cc.waa.ng.base.data.dao.auth;

import java.util.List;

import cc.waa.ng.base.data.obj.auth.ActivitySession;
import cc.waa.ng.base.data.obj.auth.Session;
import cc.waa.ng.data.dao.RepositoryDefinition;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@RepositoryDefinition(domainClass = ActivitySession.class, idClass = String.class)
public interface ActivitySessionDao
{

	ActivitySession findOne(String id);

	ActivitySession blank();

	ActivitySession save(ActivitySession session);

	ActivitySession update(ActivitySession session);

	void delete(String id);

	public List<ActivitySession> findBySession(Session session);

}
