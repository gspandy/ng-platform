/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   CountDataAction.java
 * CreateTime: 2015-12-24 19:14:59
 */
package cc.waa.ng.data.dao.buildin.action;

import static cc.waa.ng.util.jpa.JpaUtils.getCurEntityManager;
import static org.slf4j.LoggerFactory.getLogger;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;

import cc.waa.ng.data.entity.LogicalDeleteSupport;

/**
 * 获取对象总数
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class CountDataAction
	extends AbstractDataAction
	implements DataAction
{

	private static Logger logger = getLogger(CountDataAction.class);



	/**
	 * 按返回需要转换结果的类型
	 * 
	 * @param number
	 * @return
	 */
	private Object convertNumber(Long number)
	{
		if (this.returnType == int.class)
			return number.intValue();
		else if (this.returnType == Integer.class)
			return new Integer(number.intValue());
		else if (this.returnType == long.class)
			return number.longValue();
		else
			return number;
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.data.action.DataAction#execute()
	 */
	@Override
	public Object execute()
	{
		try
		{
			EntityManager entityManager = getCurEntityManager();
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Long> cq = cb.createQuery(Long.class);
			Root<?> root = cq.from(this.entityType);
			cq.select(cb.count(root));

			// 支持逻辑删除的实体类，要加入deleted的条件
			if (LogicalDeleteSupport.class.isAssignableFrom(this.entityType))
				cq.where(cb.equal(root.get("deleted"), false));

			return convertNumber(entityManager.createQuery(cq).getSingleResult());
		}
		catch (Exception e)
		{
			logger.error("获取对象总数失败，{}", this.domainType.getName());

			return convertNumber(Long.valueOf(0L));
		}
	}

}
