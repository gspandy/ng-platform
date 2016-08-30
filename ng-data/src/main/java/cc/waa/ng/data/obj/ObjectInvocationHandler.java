/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   ObjectInvocationHandler.java
 * CreateTime: 2015-11-20 21:29:12
 */
package cc.waa.ng.data.obj;

import static cc.waa.ng.data.util.EntityUtils.entityWrapper;
import static cc.waa.ng.util.lang.MethodUtils.isSetter;
import static org.apache.commons.beanutils.PropertyUtils.getProperty;
import static org.apache.commons.lang3.StringUtils.removeStart;
import static org.apache.commons.lang3.StringUtils.uncapitalize;
import static org.slf4j.LoggerFactory.getLogger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import cc.waa.ng.data.entity.TargetObject;
import cc.waa.ng.data.obj.mapper.MapResult;
import cc.waa.ng.data.obj.mapper.MethodMapper;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class ObjectInvocationHandler
	implements InvocationHandler
{

	private static final Logger logger = getLogger(ObjectInvocationHandler.class);



	private final Object object;

	public Object getObject()
	{
		return this.object;
	}

	private boolean dirty;

	public boolean isDirty()
	{
		return this.dirty;
	}

	private List<MethodMapper> mappers;

	public boolean addMethodMapper(MethodMapper mapper)
	{
		if (this.mappers == null)
			this.mappers = new ArrayList<MethodMapper>();

		return this.mappers.add(mapper);
	}

	/**
	 * @param object
	 */
	public ObjectInvocationHandler(Object object)
	{
		super();

		this.object = object;
	}

	/**
	 * 检查新值是否被修改（dirty）
	 * 
	 * @param oldValue
	 * @param newValue
	 * @return
	 */
	private boolean checkDirty(Object oldValue, Object newValue)
	{
		if (oldValue == null && newValue == null)
			return false;
	
		if (newValue != null && Proxy.isProxyClass(newValue.getClass()))
		{
			InvocationHandler h = Proxy.getInvocationHandler(newValue);

			if (h instanceof ObjectInvocationHandler)
				newValue = ((ObjectInvocationHandler) h).getObject();
		}

		return oldValue == null || !oldValue.equals(newValue);
	}

	private boolean checkDirty(Method method, Object newValue)
	{
		// TIPS: method is setter

		try
		{
			final String methodName = method.getName();
			String propertyName = uncapitalize(removeStart(methodName, "set"));
			Object oldValue = getProperty(this.object, propertyName);

			return checkDirty(oldValue, newValue);
		}
		catch (Exception e)
		{
			throw new RuntimeException("运行失败", e);
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
		throws Throwable
	{
		boolean needCheckDirty = false, dirty = false;
		MapResult result = null;

		if (needCheckDirty = isSetter(method))
			dirty = checkDirty(method, args[0]);

		if (this.mappers != null) for (MethodMapper mapper : this.mappers)
		{
			result = mapper.apply(proxy, this.object, method, transArgs(args));

			if (!result.isSkiped())
			{
				logger.trace("当前方法（{}）已选定{}", method.getName(), mapper.getClass().getName());

				break;
			}
		}

		if (!result.isSuccess())
			throw new RuntimeException("赋值失败");

		if (needCheckDirty && dirty)
			this.dirty = dirty;

		return transResult(result.getResult());
	}

	private Object[] transArgs(Object[] args)
	{
		if (args != null) for (int i = 0; i < args.length; i++)
		{
			if (args[i] == null)
				continue;

			TargetEntity anno;

			// TODO 用通用的方式实现转换
			if (args[i] instanceof Class &&
				(anno = ((Class<?>) args[i]).getAnnotation(TargetEntity.class)) != null)
			{
				args[i] = anno.value();
			}
			else if (Proxy.isProxyClass(args[i].getClass()))
			{
				InvocationHandler h = Proxy.getInvocationHandler(args[i]);

				if (h instanceof ObjectInvocationHandler)
					args[i] = ((ObjectInvocationHandler) h).getObject();
			}
		}

		return args;
	}

	private Object transList(List<?> col)
	{
		logger.trace("结果是List对象，有{}个元素", col == null ? 0 : col.size());

		List<Object> tmp = new ArrayList<Object>();

		for (int i = 0; i < col.size(); i++)
			tmp.add(transResult(col.get(i)));

		return tmp;
	}

	private Object transResult(Object result)
	{
		TargetObject anno;

		if (result instanceof List)
			return transList((List<?>) result);

		else if (result instanceof Class && (anno = ((Class<?>) result).getAnnotation(TargetObject.class)) != null)
			return anno.value();

		else
			return entityWrapper(result);
	}

}
