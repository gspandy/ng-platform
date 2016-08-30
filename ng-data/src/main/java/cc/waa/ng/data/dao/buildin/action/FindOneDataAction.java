/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   FindOneDataAction.java
 * CreateTime: 2015-12-23 11:07:37
 */
package cc.waa.ng.data.dao.buildin.action;

import static cc.waa.ng.data.util.EntityUtils.entityWrapper;
import static cc.waa.ng.util.jpa.JpaUtils.getCurEntityManager;
import static org.slf4j.LoggerFactory.getLogger;

import javax.persistence.EntityManager;

import org.slf4j.Logger;

/**
 * 根据id获取业务对象
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class FindOneDataAction
	extends AbstractDataAction
	implements DataAction
{

	private static Logger logger = getLogger(FindOneDataAction.class);



	/* (non-Javadoc)
	 * @see cc.waa.ng.data.action.DataAction#execute()
	 */
	@Override
	public Object execute()
	{
		String id = getParameter(0, String.class);

		try
		{
			EntityManager entityManager = getCurEntityManager();
			Object entity = entityManager.find(this.entityType, id);

			return entityWrapper(entity);
		}
		catch (Exception e)
		{
			logger.error("获取对象失败，{}", id);

			return null;
		}
	}

}
