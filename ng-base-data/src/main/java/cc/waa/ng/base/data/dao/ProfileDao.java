/*
 * Project:    waa新一代框架的基础模块中的数据访问部分
 * 
 * FileName:   ProfileDao.java
 * CreateTime: 2016-01-20 11:32:24
 */
package cc.waa.ng.base.data.dao;

import cc.waa.ng.base.data.obj.Profile;
import cc.waa.ng.data.dao.RepositoryDefinition;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@RepositoryDefinition(domainClass = Profile.class, idClass = String.class)
public interface ProfileDao
{

	<T extends Profile> T blank(Class<T> type);

	<T extends Profile> T save(T profile);

}
