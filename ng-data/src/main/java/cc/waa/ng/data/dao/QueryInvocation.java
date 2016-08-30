/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   QueryInvocation.java
 * CreateTime: 2016-01-20 18:50:46
 */
package cc.waa.ng.data.dao;

import java.lang.reflect.Method;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public interface QueryInvocation
{

	/**
	 * @param proxy
	 * @param method
	 * @param args
	 * @return
	 */
	InvokeResult invoke(Object proxy, Method method, Object[] args);

	void setAssistant(DaoAssistant assistant);

}
