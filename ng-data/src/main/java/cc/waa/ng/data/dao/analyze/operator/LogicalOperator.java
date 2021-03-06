/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   LogicalOperator.java
 * CreateTime: 2016-01-25 11:47:26
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
public interface LogicalOperator
	extends Operator
{

	Predicate createPredicate(CriteriaBuilder cb, Expression<Boolean> left, Expression<Boolean> right);

}
