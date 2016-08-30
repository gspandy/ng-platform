/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   Map2SetMethodMapper.java
 * CreateTime: 2016-01-19 19:10:40
 */
package cc.waa.ng.data.obj.mapper;

import static cc.waa.ng.data.util.EntityUtils.entityWrapper;
import static org.slf4j.LoggerFactory.getLogger;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;

import cc.waa.ng.data.entity.LogicalDeleteSupport;
import cc.waa.ng.data.obj.MapKey;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class Map2SetMethodMapper
	extends AbstractMethodMapper
	implements MethodMapper
{

	private static final Logger logger = getLogger(Map2SetMethodMapper.class);



	/* (non-Javadoc)
	 * @see cc.waa.ng.data.obj.mapper.MethodMapper#apply(java.lang.Object, java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	@Override
	public MapResult apply(Object src, Object tar, Method method, Object[] args)
	{
		// 只针对getter？
		Method tarMethod = null;
		MapKey anno = null;

		if (method.getReturnType() != Map.class ||
			(tarMethod = getSameMethodInTarget(method, tar.getClass())) == null ||
			tarMethod.getReturnType() != Set.class)
			return MapResult.skip();

		if ((anno = method.getAnnotation(MapKey.class)) == null)
			throw new RuntimeException("必须在方法上设置@MapKey");

		logger.trace("进入Map2Set的处理");

		Map<Object, Object> result = new HashMap<Object, Object>();

		try
		{
			Set<?> set = (Set<?>) tarMethod.invoke(tar, args);

			if (set != null) for (Object obj : set)
			{
				if (obj instanceof LogicalDeleteSupport &&
					((LogicalDeleteSupport) obj).isDeleted())
					continue;

				Object key = PropertyUtils.getNestedProperty(obj, anno.value());

				obj = entityWrapper(obj);
				key = entityWrapper(key);

				result.put(key, obj);
			}

			return MapResult.success(result);
		}
		catch (Exception e)
		{
			return MapResult.failure();
		}
	}

}
