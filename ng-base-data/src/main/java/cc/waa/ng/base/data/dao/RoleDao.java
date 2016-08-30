/*
 * Project:    waa新一代框架的基础模块中的数据访问部分
 * 
 * FileName:   RoleDao.java
 * CreateTime: 2015-11-20 15:21:27
 */
package cc.waa.ng.base.data.dao;

import cc.waa.ng.base.data.obj.Role;
import cc.waa.ng.data.dao.RepositoryDefinition;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@RepositoryDefinition(domainClass = Role.class, idClass = String.class)
public interface RoleDao
{

	Role blank();

	Role save(Role user);

	Role findOne(String id);

	void delete(String id);

}
