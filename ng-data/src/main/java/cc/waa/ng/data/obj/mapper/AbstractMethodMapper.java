/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   AbstractMethodMapper.java
 * CreateTime: 2016-01-20 09:26:22
 */
package cc.waa.ng.data.obj.mapper;

import java.lang.reflect.Method;

import cc.waa.ng.data.obj.TargetEntity;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public abstract class AbstractMethodMapper
	implements MethodMapper
{

	/**
	 * 在目标类型中获取与method相同的方法（包括名称、参数，参数必要时需要转换）
	 * 
	 * @param method
	 * @param tarType
	 * @return
	 */
	protected Method getSameMethodInTarget(Method method, Class<?> tarType)
	{
		try
		{
			String methodName = method.getName();
			Class<?>[] paramTypes = method.getParameterTypes();

			if (paramTypes != null) for (int i = 0; i < paramTypes.length; i++)
			{
				// TODO 用通用的方式实现转换
				TargetEntity te = paramTypes[i].getAnnotation(TargetEntity.class);

				if (te == null)
					continue;

				paramTypes[i] = te.value();
			}

			return tarType.getMethod(methodName, paramTypes);
		}
		catch (Exception e)
		{
			return null;
		}
	}

}
