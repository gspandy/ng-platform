/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   DataRepositoryFactoryBean.java
 * CreateTime: 2015-11-20 20:57:58
 */
package cc.waa.ng.data.dao;

import java.lang.reflect.Proxy;
import java.util.List;

import org.springframework.beans.factory.FactoryBean;

/**
 * 负责创建dao对象的工厂类
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class DataRepositoryFactoryBean<T>
	implements FactoryBean<T>
{

	private Class<T> daoType;	// 需要注入dao的类

	public void setDaoType(Class<T> daoType)
	{
		this.daoType = daoType;
	}

	private List<QueryInvocation> invocations;

	public void setInvocations(List<QueryInvocation> invocations)
	{
		this.invocations = invocations;
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.FactoryBean#getObject()
	 */
	@Override
	public T getObject()
		throws Exception
	{
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Class<?>[] interfaces = new Class<?>[] { this.daoType };
		DaoInvocationHandler h = new DaoInvocationHandler();

		h.setDaoType(this.daoType);
		h.setInvocations(this.invocations);

		return this.daoType.cast(Proxy.newProxyInstance(loader, interfaces, h));
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.FactoryBean#getObjectType()
	 */
	@Override
	public Class<?> getObjectType()
	{
		return this.daoType;
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.FactoryBean#isSingleton()
	 */
	@Override
	public boolean isSingleton()
	{
		return true;
	}

}
