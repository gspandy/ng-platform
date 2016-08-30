/*
 * Project:    waa新一代框架的基础模块中的数据访问部分
 * 
 * FileName:   SessionDao.java
 * CreateTime: 2016-01-14 20:49:31
 */
package cc.waa.ng.base.data.dao.auth;

import java.util.Set;

import cc.waa.ng.base.data.obj.User;
import cc.waa.ng.base.data.obj.auth.Session;
import cc.waa.ng.data.dao.RepositoryDefinition;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@RepositoryDefinition(domainClass = Session.class, idClass = String.class)
public interface SessionDao
{

	Session blank();

	Session save(Session session);

	Session findOne(String id);

	Session update(Session session);

	/**
	 * 查询容器中的sessionId对应最近刷新的会话
	 * 
	 * @param sessionId		容器中的sessionId
	 * @return
	 */
	Session findBySessionIdOrderByRefreshTimeDESC(String sessionId);

	/**
	 * 查找用户所有已经登录的会话
	 * 
	 * @param user
	 * @return
	 */
	Set<Session> findByLoginRecordUser(User user);

}
