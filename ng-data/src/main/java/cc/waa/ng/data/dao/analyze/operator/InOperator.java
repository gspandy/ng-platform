/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   InOperator.java
 * CreateTime: 2016-03-02 14:05:13
 */
package cc.waa.ng.data.dao.analyze.operator;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import org.slf4j.Logger;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class InOperator
	implements RelationOperator<Object, Object>
{

	private static final Logger logger = getLogger(InOperator.class);



	/* (non-Javadoc)
	 * @see cc.waa.ng.data.dao.analyze.operator.RelationOperator#operate(javax.persistence.criteria.CriteriaBuilder, javax.persistence.criteria.Path, java.util.List)
	 */
	@Override
	public Predicate operate(CriteriaBuilder cb, Path<Object> path, List<Object> args)
	{
		Object arg = args.remove(0);
		In<Object> in = cb.in(path);

		if (arg == null)
			logger.warn("传入的参数为空");

		else if (arg.getClass().isArray())
			for (Object obj : (Object[]) arg)
				in.value(obj);

		else if (arg instanceof Collection)
			for (Object obj : (Collection<?>) arg)
				in.value(obj);

		else
			in.value(arg);

		return in;
	}

}
