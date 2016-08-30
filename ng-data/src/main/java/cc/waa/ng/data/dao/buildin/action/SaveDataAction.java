/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   SaveDataAction.java
 * CreateTime: 2015-11-28 11:11:02
 */
package cc.waa.ng.data.dao.buildin.action;

import static cc.waa.ng.util.jpa.JpaUtils.getCurEntityManager;
import static cc.waa.ng.util.spring.ApplicationUtils.publishEvent;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.slf4j.LoggerFactory.getLogger;

import java.lang.reflect.Proxy;
import java.util.Date;

import org.slf4j.Logger;

import cc.waa.ng.data.entity.BaseEntity;
import cc.waa.ng.data.entity.event.EntitySavedEvent;
import cc.waa.ng.data.obj.ObjectInvocationHandler;
import cc.waa.ng.util.lang.GUID;

/**
 * 保存（新建）业务对象
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class SaveDataAction
	extends AbstractDataAction
	implements DataAction
{

	private static Logger logger = getLogger(SaveDataAction.class);



	/* (non-Javadoc)
	 * @see cc.waa.ng.data.action.DataAction#execute()
	 */
	@Override
	public Object execute()
	{
		Object object = getParameter(0);

		logger.trace("保存对象{}", object);

		ObjectInvocationHandler h = (ObjectInvocationHandler) Proxy.getInvocationHandler(object);
		Object entity = h.getObject();

		if (entity instanceof BaseEntity)
		{
			BaseEntity base = (BaseEntity) entity;

			if (isEmpty(base.getId()))
				base.setId(new GUID().toUnsign());

			base.setCreateTime(new Date());		// TODO 考虑是否用统一时间
		}

		try
		{
			getCurEntityManager().persist(entity);	// 保存

			publishEvent(new EntitySavedEvent(entity));
		}
		catch (Exception e)
		{
			logger.error("保存对象失败，{}", object);
		}

		return object;
	}

}
