/*
 * Project:    waa新一代框架的基础模块中的公共工具库
 * 
 * FileName:   MethodUtils.java
 * CreateTime: 2015-11-18 14:16:50
 */
package cc.waa.ng.util.lang;

import static org.apache.commons.lang3.StringUtils.startsWith;

import java.lang.reflect.Method;

/**
 * 对函数操作的工具库
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class MethodUtils
{

	/**
	 * 获取方法的识别标志
	 * 
	 * @param method
	 * @return
	 */
	public static String getMethodSignature(Method method)
	{
		StringBuilder signature = new StringBuilder();

		Class<?> rtType = method.getReturnType();
		if (rtType.isArray())
			signature.append(rtType.getComponentType().getName()).append("[]");
		else
			signature.append(rtType.getName());

		signature.append(" ").append(method.getName()).append("(");

		boolean firstPa = true;
		for (Class<?> paType : method.getParameterTypes())
		{
			if (firstPa)
				firstPa = false;
			else
				signature.append(", ");

			if (paType.isArray())
				signature.append(paType.getComponentType().getName()).append("[]");
			else
				signature.append(paType.getName());

			firstPa = false;
		}

		signature.append(")");

		return signature.toString();
	}

	/**
	 * 检查方法对象是否setter
	 * 
	 * @param method
	 * @return
	 */
	public static boolean isSetter(Method method)
	{
		String methodName = method.getName();

		return startsWith(methodName, "set") && method.getParameterCount() == 1;
	}

}
