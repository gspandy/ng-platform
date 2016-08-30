/*
 * Project:    waa新一代框架的基础模块中的数据访问部分
 *
 * FileName:   UserDao.java
 * CreateTime: 2015-09-17 17:02:38
 */
package cc.waa.ng.base.data.dao;

import java.util.List;

import cc.waa.ng.base.data.SessionStatus;
import cc.waa.ng.base.data.obj.User;
import cc.waa.ng.data.dao.RepositoryDefinition;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@RepositoryDefinition(domainClass = User.class, idClass = String.class)
public interface UserDao
{

	User findOne(String id);

	User blank();

	User save(User user);

	List<User> findByLastLoginSessionStatus(SessionStatus status);

}
