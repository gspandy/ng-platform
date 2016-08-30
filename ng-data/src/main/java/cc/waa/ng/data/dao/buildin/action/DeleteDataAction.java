/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   DeleteDataAction.java
 * CreateTime: 2015-12-25 11:20:01
 */
package cc.waa.ng.data.dao.buildin.action;

import static cc.waa.ng.util.jpa.JpaUtils.getCurEntityManager;
import static cc.waa.ng.util.spring.ApplicationUtils.publishEvent;
import static org.slf4j.LoggerFactory.getLogger;

import java.lang.reflect.Proxy;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;

import cc.waa.ng.data.entity.LogicalDeleteSupport;
import cc.waa.ng.data.entity.event.EntityDeletedEvent;
import cc.waa.ng.data.obj.ObjectInvocationHandler;

/**
 * 删除业务对象
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class DeleteDataAction
	extends AbstractDataAction
	implements DataAction
{

	private static Logger logger = getLogger(SaveDataAction.class);



	private void removeByKey(Object key)
	{
		removeEntity(getCurEntityManager().find(this.entityType, key));
	}

	private void removeEntity(Object entity)
	{
		EntityManager entityManager = getCurEntityManager();

		if (entity instanceof LogicalDeleteSupport)
		{
			LogicalDeleteSupport object = (LogicalDeleteSupport) entity;

			if (!object.isDeleted())
			{
				object.setDeleted(true);
				object.setDeleteTime(new Date());	// TODO 考虑是否用统一时间

				if (!entityManager.contains(entity))
					entityManager.merge(entity);

				// 起码执行过删除操作才触发事件
				publishEvent(new EntityDeletedEvent(entity));
			}
		}

		else if (entity != null)
		{
			entityManager.remove(entity);

			// 起码执行过删除操作才触发事件
			publishEvent(new EntityDeletedEvent(entity));
		}
	}

	private void removeOne(Object object)
	{
		if (this.keyType.isInstance(object))
			removeByKey(object);

		else
		{
			ObjectInvocationHandler h = (ObjectInvocationHandler) Proxy.getInvocationHandler(object);

			removeEntity(h.getObject());
		}
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.data.action.DataAction#execute()
	 */
	@Override
	public Object execute()
	{
		Object object = getParameter(0);

		try
		{
			if (object instanceof List)
				for (Object obj : (List<?>) object)
					removeOne(obj);

			else if (object instanceof Object[])
				for (Object obj : (Object[]) object)
					removeOne(obj);

			else
				removeOne(object);
		}
		catch (Exception e)
		{
			logger.error("删除对象失败，{}", this.domainType.getName());
		}

		return null;	// FIXME 还需要判断返回的类型，有可能出现boolean或者int等等
	}

}
