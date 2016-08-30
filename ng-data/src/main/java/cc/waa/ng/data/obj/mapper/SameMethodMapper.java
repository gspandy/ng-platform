/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   SameMethodMapper.java
 * CreateTime: 2016-01-19 16:44:13
 */
package cc.waa.ng.data.obj.mapper;

import java.lang.reflect.Method;

/**
 * 相同方法的操作映射
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class SameMethodMapper
	extends AbstractMethodMapper
	implements MethodMapper
{

	/* (non-Javadoc)
	 * @see cc.waa.ng.data.obj.mapper.MethodMapper#apply(java.lang.Object, java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	@Override
	public MapResult apply(Object src, Object tar, Method method, Object[] args)
	{
		Method tarMethod = null;

		if ((tarMethod = getSameMethodInTarget(method, tar.getClass())) == null)
			return MapResult.skip();

		try
		{
			return MapResult.success(tarMethod.invoke(tar, args));
		}
		catch (Exception e)
		{
			return MapResult.failure();
		}
	}

}
