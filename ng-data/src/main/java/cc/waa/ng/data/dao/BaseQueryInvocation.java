/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   BaseQueryInvocation.java
 * CreateTime: 2016-01-20 20:09:06
 */
package cc.waa.ng.data.dao;

import static cc.waa.ng.data.dao.InvokeResult.failure;
import static cc.waa.ng.data.dao.InvokeResult.success;
import static org.apache.commons.lang3.StringUtils.join;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.commons.lang3.StringUtils;

/**
 * 执行Dao的基础方法（Object所属的方法）
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class BaseQueryInvocation
	implements QueryInvocation
{

	/* (non-Javadoc)
	 * @see cc.waa.ng.data.dao.QueryInvocation#setAssistant(cc.waa.ng.data.dao.DaoAssistant)
	 */
	@Override
	public void setAssistant(DaoAssistant assistant)
	{
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.data.dao.QueryInvocation#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	@Override
	public InvokeResult invoke(Object proxy, Method method, Object[] args)
	{
		try
		{
			DaoInvocationHandler h = (DaoInvocationHandler) Proxy.getInvocationHandler(proxy);

			if (StringUtils.equals("toString", method.getName()))
				return success(join(h.getDaoType().getName(), "@", Integer.toHexString(h.hashCode())));

			return success(method.invoke(h, args));
		}
		catch (Exception e)
		{
			return failure("执行错误", e);
		}
	}

}
