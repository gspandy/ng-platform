/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   DomainAssistant.java
 * CreateTime: 2016-01-26 11:40:50
 */
package cc.waa.ng.data.dao;

import java.lang.reflect.Method;

import cc.waa.ng.data.obj.TargetEntity;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class DomainAssistant
	implements DaoAssistant
{

	/* (non-Javadoc)
	 * @see cc.waa.ng.data.dao.EntityAssistant#getEntityType(java.lang.reflect.Method)
	 */
	@Override
	public Class<?> getEntityType(Method method)
	{
		Class<?> type = method.getDeclaringClass();

		RepositoryDefinition anno = type.getAnnotation(RepositoryDefinition.class);
		if (anno == null)
			return null;

		Class<?> domainType = anno.domainClass();
		TargetEntity anno2 = domainType.getAnnotation(TargetEntity.class);
		if (anno2 == null)
			return null;

		return anno2.value();
	}

}
