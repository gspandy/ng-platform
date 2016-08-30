/*
 * Project:    waa新一代框架的基础模块中的公共工具库
 * 
 * FileName:   ArrayUtils.java
 * CreateTime: 2016-01-25 18:16:29
 */
package cc.waa.ng.util.lang;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class ArrayUtils
{

	@SuppressWarnings("unchecked")
	public static <T> T[] cast(Object[] arr)
	{
		return (T[]) arr;
	}

}
