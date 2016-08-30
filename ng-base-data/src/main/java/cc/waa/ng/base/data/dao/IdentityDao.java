/*
 * Project:    waa新一代框架的基础模块中的数据访问部分
 * 
 * FileName:   IdentityDao.java
 * CreateTime: 2016-05-09 10:26:29
 */
package cc.waa.ng.base.data.dao;

import cc.waa.ng.base.data.obj.Identity;
import cc.waa.ng.data.dao.RepositoryDefinition;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@RepositoryDefinition(domainClass = Identity.class, idClass = String.class)
public interface IdentityDao
{

	<T extends Identity> T blank(Class<T> type);

	<T extends Identity> T save(T Identity);

	Identity findOne(String id);

}
