/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   IsNotNullOperator.java
 * CreateTime: 2016-01-26 16:13:39
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
public class IsNotNullOperator
	implements RelationOperator<Object, Object>
{

	/* (non-Javadoc)
	 * @see cc.waa.ng.data.dao.analyze.operator.RelationOperator#operate(javax.persistence.criteria.CriteriaBuilder, javax.persistence.criteria.Path, java.util.List)
	 */
	@Override
	public Predicate operate(CriteriaBuilder cb, Path<Object> path, List<Object> args)
	{
		return cb.isNotNull(path);
	}

}
