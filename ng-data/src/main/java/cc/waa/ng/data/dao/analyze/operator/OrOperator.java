/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   OrOperator.java
 * CreateTime: 2016-01-25 11:48:12
 */
package cc.waa.ng.data.dao.analyze.operator;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class OrOperator
	implements LogicalOperator
{

	/* (non-Javadoc)
	 * @see cc.waa.ng.data.dao.analyze.LogicalOperator#createPredicate(javax.persistence.criteria.CriteriaBuilder, javax.persistence.criteria.Expression, javax.persistence.criteria.Expression)
	 */
	@Override
	public Predicate createPredicate(CriteriaBuilder cb, Expression<Boolean> left, Expression<Boolean> right)
	{
		return cb.or(left, right);
	}

}
