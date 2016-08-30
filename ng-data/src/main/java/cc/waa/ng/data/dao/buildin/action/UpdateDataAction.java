/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   UpdateDataAction.java
 * CreateTime: 2016-01-08 18:12:54
 */
package cc.waa.ng.data.dao.buildin.action;

import static cc.waa.ng.util.jpa.JpaUtils.getCurEntityManager;
import static cc.waa.ng.util.spring.ApplicationUtils.publishEvent;
import static org.slf4j.LoggerFactory.getLogger;

import java.lang.reflect.Proxy;

import javax.persistence.EntityManager;

import org.slf4j.Logger;

import cc.waa.ng.data.entity.event.EntityUpdatedEvent;
import cc.waa.ng.data.obj.ObjectInvocationHandler;

/**
 * 更新业务对象
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class UpdateDataAction
	extends AbstractDataAction
	implements DataAction
{

	private static Logger logger = getLogger(UpdateDataAction.class);



	/* (non-Javadoc)
	 * @see cc.waa.ng.data.action.DataAction#execute()
	 */
	@Override
	public Object execute()
	{
		Object object = getParameter(0);

		logger.trace("保存（更新）对象{}", object);

		ObjectInvocationHandler h = (ObjectInvocationHandler) Proxy.getInvocationHandler(object);

		if (!h.isDirty())
		{
			logger.debug("对象没有被修改，忽略更新操作");

			return object;
		}

		Object entity = h.getObject();

		try
		{
			EntityManager entityManager = getCurEntityManager();

			if (!entityManager.contains(entity))
				entityManager.merge(entity);

			publishEvent(new EntityUpdatedEvent(entity));
		}
		catch (Exception e)
		{
			logger.error("保存（更新）对象失败，{}", object);
		}

		return object;
	}

}
