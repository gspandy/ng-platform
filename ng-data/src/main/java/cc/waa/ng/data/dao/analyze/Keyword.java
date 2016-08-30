/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   Keyword.java
 * CreateTime: 2016-01-24 15:44:13
 */
package cc.waa.ng.data.dao.analyze;

import static org.apache.commons.lang3.StringUtils.startsWith;

import cc.waa.ng.data.dao.analyze.operator.AndOperator;
import cc.waa.ng.data.dao.analyze.operator.BetweenOperator;
import cc.waa.ng.data.dao.analyze.operator.EndsWithOperator;
import cc.waa.ng.data.dao.analyze.operator.EqualsOperator;
import cc.waa.ng.data.dao.analyze.operator.GreaterEqualsOperator;
import cc.waa.ng.data.dao.analyze.operator.GreaterThanOperator;
import cc.waa.ng.data.dao.analyze.operator.InOperator;
import cc.waa.ng.data.dao.analyze.operator.IsNotNullOperator;
import cc.waa.ng.data.dao.analyze.operator.IsNullOperator;
import cc.waa.ng.data.dao.analyze.operator.LessEqualsOperator;
import cc.waa.ng.data.dao.analyze.operator.LessThanOperator;
import cc.waa.ng.data.dao.analyze.operator.LikeOperator;
import cc.waa.ng.data.dao.analyze.operator.LogicalOperator;
import cc.waa.ng.data.dao.analyze.operator.Operator;
import cc.waa.ng.data.dao.analyze.operator.OrOperator;
import cc.waa.ng.data.dao.analyze.operator.RelationOperator;
import cc.waa.ng.data.dao.analyze.operator.StartsWithOperator;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public enum Keyword
{

	find(null),

	count(null),

	By(null),

	OrderBy(null),

	StartAt(null),

	Top(null),

	And(new AndOperator()),

	Or(new OrOperator()),

	Equals(new EqualsOperator()),

	LessThan(new LessThanOperator()),

	LessEquals(new LessEqualsOperator()),

	GreaterThan(new GreaterThanOperator()),

	GreaterEquals(new GreaterEqualsOperator()),

	Between(new BetweenOperator()),

	StartsWith(new StartsWithOperator()),

	EndsWith(new EndsWithOperator()),

	Like(new LikeOperator()),

	IsNull(new IsNullOperator()),

	IsNotNull(new IsNotNullOperator()),

	In(new InOperator()),

	EQ(Equals.toRelation()),

	LT(LessThan.toRelation()),

	LE(LessEquals.toRelation()),

	GT(GreaterThan.toRelation()),

	GE(GreaterEquals.toRelation()),

	BT(Between.toRelation()),

	SW(StartsWith.toRelation()),

	EW(EndsWith.toRelation()),

	LK(Like.toRelation());

	private Operator operator;

	public static Keyword[] relations()
	{
		return new Keyword[] { Equals, LessThan, LessEquals, GreaterThan, GreaterEquals, Between, StartsWith, EndsWith, Like, IsNull, IsNotNull, In, EQ, LT, LE, GT, GE, BT, SW, EW, LK };
	}

	public static Keyword[] logicals()
	{
		return new Keyword[] { And, Or };
	}

	/**
	 * @param operator
	 */
	private Keyword(Operator operator)
	{
		this.operator = operator;
	}

	public boolean match(StringBuilder analyze)
	{
		if (startsWith(analyze, name()))
			return analyze.delete(0, name().length()) != null;
		else
			return false;
	}

	@SuppressWarnings("unchecked")
	public RelationOperator<Object, Object> toRelation()
	{
		if (this.operator instanceof RelationOperator)
			return (RelationOperator<Object, Object>) this.operator;

		throw new RuntimeException("该关键字不是关系运算符");
	}

	public LogicalOperator toLogical()
	{
		if (this.operator instanceof LogicalOperator)
			return (LogicalOperator) this.operator;

		throw new RuntimeException("该关键字不是逻辑运算符");
	}

}
