/*
 * Project:    waa新一代框架的基础模块中的公共工具库
 * 
 * FileName:   CollectionUtils.java
 * CreateTime: 2016-01-24 11:53:07
 */
package cc.waa.ng.util.lang;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class CollectionUtils
{

	@SuppressWarnings("rawtypes")
	private static final Map<Class<? extends Collection>, Class<? extends Collection>> defaultTypes = new HashMap<Class<? extends Collection>, Class<? extends Collection>>();

	static
	{
		defaultTypes.put(List.class, ArrayList.class);
		defaultTypes.put(Set.class, HashSet.class);
	}

	/**
	 * 自动创建Collection的实例
	 * 
	 * @param colType		Collection的子接口，包括List、Set……
	 * @param itemType		Collection存放的对象类型
	 * @return
	 */
	public static <E, T extends Collection<E>> T newInstance(Class<T> colType)
	{
		try
		{
			return colType.cast(defaultTypes.get(colType).newInstance());
		}
		catch (Exception e)
		{
			throw new RuntimeException("自动创建实例失败", e);
		}
	}

	/**
	 * 转换为存放指定类型的Collection类
	 * 
	 * @param colType
	 * @param itemType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <E, T extends Collection<E>> Class<T> cast(Class<?> colType, Class<E> itemType)
	{
		return (Class<T>) colType;
	}

	/**
	 * 自动将数组转换为Collection
	 * 
	 * @param colType
	 * @param items
	 * @return
	 */
	public static <E, T extends Collection<E>> T toList(Class<T> colType, Class<E> itemType, Object... items)
	{
		T result = newInstance(colType);

		if (items != null) for (Object obj : items)
			result.add(itemType.cast(obj));

		return result;
	}

}
