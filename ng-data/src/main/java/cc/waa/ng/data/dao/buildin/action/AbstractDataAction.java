/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   AbstractDataAction.java
 * CreateTime: 2015-11-27 18:39:54
 */
package cc.waa.ng.data.dao.buildin.action;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import cc.waa.ng.data.obj.TargetEntity;

/**
 * 抽象封装各个方法的接口，并且提供部分共用的方法
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public abstract class AbstractDataAction
	implements DataAction
{

	private static Logger logger = getLogger(AbstractDataAction.class);



	protected Class<?> returnType;

	/* (non-Javadoc)
	 * @see cc.waa.ng.data.action.DataAction#setReturnType(java.lang.Class)
	 */
	@Override
	public void setReturnType(Class<?> returnType)
	{
		this.returnType = returnType;
	}

	protected Class<?> domainType, entityType;

	/* (non-Javadoc)
	 * @see cc.waa.ng.data.action.DataAction#setDomainType(java.lang.Class)
	 */
	@Override
	public void setDomainType(Class<?> domainType)
	{
		TargetEntity target;

		this.domainType = domainType;

		if ((target = domainType.getAnnotation(TargetEntity.class)) == null)
			logger.warn("业务对象类型没有指定实体类型的映射");
		else
			this.entityType = target.value();
	}

	protected Class<?> keyType;

	/* (non-Javadoc)
	 * @see cc.waa.ng.data.action.DataAction#setKeyType(java.lang.Class)
	 */
	@Override
	public void setKeyType(Class<?> keyType)
	{
		this.keyType = keyType;
	}

	private final List<Object> parameters = new ArrayList<Object>();

	/* (non-Javadoc)
	 * @see cc.waa.ng.data.action.DataAction#setParameter(int, java.lang.Object)
	 */
	@Override
	public void setParameter(int index, Object value)
	{
		if (this.parameters.size() > index)
			this.parameters.set(index, value);

		else
		{
			while (this.parameters.size() < index)
				this.parameters.add(null);

			this.parameters.add(value);
		}
	}

	protected Object getParameter(int index)
	{
		return this.parameters.get(index);
	}

	protected <T> T getParameter(int index, Class<T> type)
	{
		return type.cast(getParameter(index));
	}

}
