/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   EntityAssistant.java
 * CreateTime: 2016-01-26 11:42:54
 */
package cc.waa.ng.data.dao;

import java.lang.reflect.Method;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class EntityAssistant
	implements DaoAssistant
{

	/* (non-Javadoc)
	 * @see cc.waa.ng.data.dao.DaoAssistant#getEntityType(java.lang.reflect.Method)
	 */
	@Override
	public Class<?> getEntityType(Method method)
	{
		Class<?> type = method.getDeclaringClass();

		RepositoryDefinition anno = type.getAnnotation(RepositoryDefinition.class);
		if (anno == null)
			return null;

		return anno.domainClass();
	}

}
