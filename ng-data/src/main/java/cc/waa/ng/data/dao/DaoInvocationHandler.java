/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   DaoInvocationHandler.java
 * CreateTime: 2015-11-20 21:10:25
 */
package cc.waa.ng.data.dao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class DaoInvocationHandler
	implements InvocationHandler
{

	private Class<?> daoType;	// 需要注入dao的类

	public Class<?> getDaoType()
	{
		return this.daoType;
	}

	public void setDaoType(Class<?> daoType)
	{
		this.daoType = daoType;
	}

	private List<QueryInvocation> invocations;

	public void setInvocations(List<QueryInvocation> invocations)
	{
		this.invocations = invocations;
	}

	/* (non-Javadoc)
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
		throws Throwable
	{
		for (QueryInvocation qi : this.invocations)
		{
			InvokeResult ir = qi.invoke(proxy, method, args);

			if (ir.isFailure())
				throw new RuntimeException(ir.getMessage(), ir.getCause());
			else if (ir.isSuccess())
				return ir.getResult();
		}

		throw new RuntimeException("没有找到QueryInvocation的设置");
	}

}
