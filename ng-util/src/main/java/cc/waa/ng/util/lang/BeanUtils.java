/*
 * Project:    waa新一代框架的基础模块中的公共工具库
 * 
 * FileName:   BeanUtils.java
 * CreateTime: 2016-07-01 11:49:50
 */
package cc.waa.ng.util.lang;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * 对bean操作的工具库
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class BeanUtils
{

	/**
	 * 动态对bean的属性赋值（过滤掉Exception的抛出，改用返回boolean表示是否正确赋值）
	 * 
	 * @param bean
	 * @param name
	 * @param value
	 * @return
	 */
	public static boolean setProperty(Object bean, String name, Object value)
	{
		try
		{
			PropertyUtils.setProperty(bean, name, value);

			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

}
