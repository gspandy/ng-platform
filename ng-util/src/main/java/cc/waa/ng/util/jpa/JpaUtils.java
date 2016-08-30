/*
 * Project:    waa新一代框架的基础模块中的公共工具库
 * 
 * FileName:   JpaUtils.java
 * CreateTime: 2016-01-12 18:11:44
 */
package cc.waa.ng.util.jpa;

import static org.springframework.orm.jpa.EntityManagerFactoryUtils.getTransactionalEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.ManagedType;

/**
 * 基于JPA的实体类常用工具类
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class JpaUtils
{

	private static EntityManagerFactory entityManagerFactory;

	/**
	 * @return the entityManagerFactory
	 */
	public static EntityManagerFactory getEntityManagerFactory()
	{
		return JpaUtils.entityManagerFactory;
	}

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory)
	{
		JpaUtils.entityManagerFactory = entityManagerFactory;
	}

	/**
	 * 获取当前事务范围内的EntityManager对象
	 * 
	 * @return
	 */
	public static EntityManager getCurEntityManager()
	{
		return getTransactionalEntityManager(entityManagerFactory);
	}

	public static <T> ManagedType<T> getManagedType(Class<T> type)
	{
		try
		{
			return entityManagerFactory.getMetamodel().managedType(type);
		}
		catch (Exception e)
		{
			return null;
		}
	}

}
