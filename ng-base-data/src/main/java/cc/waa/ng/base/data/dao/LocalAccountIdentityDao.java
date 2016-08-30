/*
 * Project:    waa新一代框架的基础模块中的数据访问部分
 * 
 * FileName:   LocalAccountIdentityDao.java
 * CreateTime: 2016-05-06 11:37:49
 */
package cc.waa.ng.base.data.dao;

import cc.waa.ng.base.data.obj.LocalAccountIdentity;
import cc.waa.ng.data.dao.RepositoryDefinition;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@RepositoryDefinition(domainClass = LocalAccountIdentity.class, idClass = String.class)
public interface LocalAccountIdentityDao
{

	LocalAccountIdentity findByAccount(String account);

}
