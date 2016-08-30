/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   JPQLQueryInvocation.java
 * CreateTime: 2016-01-21 09:49:02
 */
package cc.waa.ng.data.dao;

import java.lang.reflect.Method;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class JPQLQueryInvocation
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
		// TODO Auto-generated method stub
		return InvokeResult.skip();
	}

}
