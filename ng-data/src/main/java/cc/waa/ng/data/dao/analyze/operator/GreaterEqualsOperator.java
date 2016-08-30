/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   GreaterEqualsOperator.java
 * CreateTime: 2016-01-25 18:20:38
 */
package cc.waa.ng.data.dao.analyze.operator;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class GreaterEqualsOperator
	implements RelationOperator<Comparable<Object>, Comparable<Object>>
{

	/* (non-Javadoc)
	 * @see cc.waa.ng.data.dao.analyze.operator.RelationOperator#operate(javax.persistence.criteria.CriteriaBuilder, javax.persistence.criteria.Path, java.util.List)
	 */
	@Override
	public Predicate operate(CriteriaBuilder cb, Path<Comparable<Object>> path, List<Comparable<Object>> args)
	{
		return cb.greaterThanOrEqualTo(path, args.remove(0));
	}

}
