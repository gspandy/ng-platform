/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   RelationOperator.java
 * CreateTime: 2016-01-24 16:33:52
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
public interface RelationOperator<PT, AT>
	extends Operator
{

	Predicate operate(CriteriaBuilder cb, Path<PT> path, List<AT> args);

}
