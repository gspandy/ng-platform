/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   LikeOperator.java
 * CreateTime: 2016-01-25 18:41:32
 */
package cc.waa.ng.data.dao.analyze.operator;

import static org.apache.commons.lang3.StringUtils.join;

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
public class LikeOperator
	implements RelationOperator<String, String>
{

	/* (non-Javadoc)
	 * @see cc.waa.ng.data.dao.analyze.operator.RelationOperator#operate(javax.persistence.criteria.CriteriaBuilder, javax.persistence.criteria.Path, java.util.List)
	 */
	@Override
	public Predicate operate(CriteriaBuilder cb, Path<String> path, List<String> args)
	{
		return cb.like(path, join("%", args.remove(0), "%"));
	}

}
