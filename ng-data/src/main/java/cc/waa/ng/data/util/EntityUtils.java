/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   EntityUtils.java
 * CreateTime: 2016-01-20 10:26:21
 */
package cc.waa.ng.data.util;

import java.lang.reflect.Proxy;

import cc.waa.ng.data.entity.TargetObject;
import cc.waa.ng.data.obj.ObjectInvocationHandler;
import cc.waa.ng.data.obj.mapper.Map2SetMethodMapper;
import cc.waa.ng.data.obj.mapper.SameMethodMapper;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class EntityUtils
{

	/**
	 * 针对有设置@TargetObject的实体类进行动态代理类的封装
	 * 
	 * @param entity
	 * @return
	 */
	public static Object entityWrapper(Object entity)
	{
		TargetObject anno = null;

		if (entity == null)
			return null;

		if ((anno = entity.getClass().getAnnotation(TargetObject.class)) == null)
			return entity;

		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		ObjectInvocationHandler handler = new ObjectInvocationHandler(entity);

		handler.addMethodMapper(new Map2SetMethodMapper());
		handler.addMethodMapper(new SameMethodMapper());

		return Proxy.newProxyInstance(loader, new Class<?>[] { anno.value() }, handler);
	}

}
