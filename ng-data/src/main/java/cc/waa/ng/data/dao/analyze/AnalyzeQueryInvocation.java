/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   AnalyzeQueryInvocation.java
 * CreateTime: 2016-01-20 20:42:47
 */
package cc.waa.ng.data.dao.analyze;

import static cc.waa.ng.data.dao.InvokeResult.success;
import static cc.waa.ng.data.dao.analyze.Keyword.By;
import static cc.waa.ng.data.dao.analyze.Keyword.Equals;
import static cc.waa.ng.data.util.EntityUtils.entityWrapper;
import static cc.waa.ng.util.jpa.JpaUtils.getCurEntityManager;
import static cc.waa.ng.util.lang.CollectionUtils.cast;
import static cc.waa.ng.util.lang.CollectionUtils.newInstance;
import static cc.waa.ng.util.lang.CollectionUtils.toList;
import static java.lang.Integer.parseInt;
import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.indexOf;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNumeric;
import static org.apache.commons.lang3.StringUtils.length;
import static org.apache.commons.lang3.StringUtils.startsWith;
import static org.slf4j.LoggerFactory.getLogger;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.ManagedType;

import org.slf4j.Logger;

import cc.waa.ng.data.dao.AbstractQueryInvocation;
import cc.waa.ng.data.dao.DaoAssistant;
import cc.waa.ng.data.dao.InvokeResult;
import cc.waa.ng.data.dao.QueryInvocation;
import cc.waa.ng.data.entity.LogicalDeleteSupport;
import cc.waa.ng.data.obj.ObjectInvocationHandler;
import cc.waa.ng.util.jpa.JpaUtils;

/**
 * 通过解析方法名创建查询，并执行
 * 
 * <p>方法名支持find和count开头，而且必须紧接着By、OrderBy、StartAt、Top中的任意一个。排序上也有规定，必须Top在最后（如果有），依次往前的是StartAt、OrderBy、By
 * <p>By是定义查询的条件
 * <blockquote>By的定义是最复杂的。</blockquote>
 * <p>OrderBy是定义对查询结果的排序
 * <blockquote>OrderBy必须紧接着属性名（首字母大写那种），属性名后可以紧接ASC（默认）、DESC，缺省时ASC。可以同时按多个字段进行排序，例如：OrderByAmountCreateTimeDESC</blockquote>
 * <p>StartAt是设定从第几条记录开始返回结果
 * <blockquote>StartAt可以直接带上数字（例如：StartAt0、StartAt12等）。如果没有带上数字，程序会在相应的参数位取值</blockquote>
 * <p>Top是设定返回前多少条记录。
 * <blockquote>Top可以直接带上数字（例如：Top5、Top10等）。如果没有带上数字，程序会在相应的参数位取值</blockquote>
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class AnalyzeQueryInvocation
	extends AbstractQueryInvocation
	implements QueryInvocation
{

	private static final Logger logger = getLogger(AnalyzeQueryInvocation.class);



	private DaoAssistant assistant;

	/* (non-Javadoc)
	 * @see cc.waa.ng.data.dao.QueryInvocation#setAssistant(cc.waa.ng.data.dao.DaoAssistant)
	 */
	@Override
	public void setAssistant(DaoAssistant assistant)
	{
		this.assistant = assistant;
	}

	private int endIndex(StringBuilder analyze, String... tar)
	{
		int index = -1;

		if (tar != null) for (int i = 0; index == -1 && i < tar.length; i++)
			index = indexOf(analyze, tar[i]);

		return index == -1 ? analyze.length() : index;
	}

	private Attribute<?, ?> existsField(Class<?> type, StringBuilder analyze)
	{
		String fieldName;

		for (Attribute<?, ?> field : getSortedFields(type))
		{
			if (startsWith(analyze, fieldName = capitalize(field.getName())))
			{
				analyze.delete(0, fieldName.length());

				return field;
			}
		}

		return null;
	}

	/**
	 * @param analyze
	 * @param attr		判断是否需要join的字段对象（如果符合条件，则用from来join attr）
	 * @param from		是上一级的from，到这里来才决定要不要join
	 * @param ctx
	 * @return
	 */
	private Path<Object> matchJoinField(StringBuilder analyze, Attribute<?, ?> attr, From<?, ?> from, AnalyzeContext ctx)
	{
		Class<?> attrType = attr.getJavaType();
		ManagedType<?> managedType = JpaUtils.getManagedType(attrType);
		Attribute<?, ?> curAttr;

		if (managedType == null)	//  当前属性类型不是实体类
			return from.get(attr.getName());

		if ((curAttr = existsField(managedType.getJavaType(), analyze)) == null)
			return from.get(attr.getName());

		From<?, ?> joined = ctx.join(from, attr);

		return matchJoinField(analyze, curAttr, joined, ctx);
	}

	private Path<Object> matchField(StringBuilder analyze, From<?, ?> key, AnalyzeContext ctx)
	{
		logger.trace("当前的关联实体类是：{}", key.getJavaType());

		Attribute<?, ?> curAttr;

		if ((curAttr = existsField(key.getJavaType(), analyze)) == null)
			return null;
		else
			return matchJoinField(analyze, curAttr, key, ctx);
	}

	private Keyword matchKeyword(StringBuilder analyze, Keyword... keywords)
	{
		for (Keyword keyword : keywords)
			if (keyword.match(analyze))
				return keyword;

		return null;
	}

	private Predicate analyzingNested(StringBuilder analyze, CriteriaBuilder cb, From<?, ?> from, List<Object> args, AnalyzeContext ctx)
	{
		Path<Object> path;
		Keyword oper, logical;

		if (isEmpty(analyze))
			return null;

		else if ((path = matchField(analyze, from, ctx)) != null)
		{
			if ((oper = matchKeyword(analyze, Keyword.relations())) == null)
				oper = Equals;

			if ((logical = matchKeyword(analyze, Keyword.logicals())) == null)
			{
				if (analyze.length() <= 0)
					return oper.toRelation().operate(cb, path, args);

				logical = Keyword.And;
			}

			Predicate left  = oper.toRelation().operate(cb, path, args);
			Predicate right = analyzingNested(analyze, cb, from, args, ctx);

			if (right == null)
				return left;
			else
				return logical.toLogical().createPredicate(cb, left, right);
		}
		else
			return analyzingNested(analyze.deleteCharAt(0), cb, from, args, ctx);
	}

	private Predicate analyzingCondition(StringBuilder analyze, CriteriaBuilder cb, From<?, ?> from, List<Object> args, AnalyzeContext ctx)
	{
		if (matchKeyword(analyze, By) == null)
			return null;

		int index = endIndex(analyze, "OrderBy", "StartAt", "Top");
		StringBuilder val = new StringBuilder(analyze.substring(0, index));
		analyze.delete(0, index);

		return analyzingNested(val, cb, from, args, ctx);
	}

	private void analyzingOrder(StringBuilder analyze, CriteriaBuilder cb, From<?, ?> from, CriteriaQuery<?> cq, AnalyzeContext ctx)
	{
		if (!startsWith(analyze, "OrderBy"))
			return;

		analyze.delete(0, 7);

		List<Order> orders = new ArrayList<Order>();
		int index = endIndex(analyze, "StartAt", "Top");
		boolean desc;
		StringBuilder val = new StringBuilder(analyze.substring(0, index));
		Path<Object> path;
		analyze.delete(0, index);

		while (val.length() > 0)
		{
			if ((path = matchField(val, from, ctx)) != null)
			{
				desc = false;

				if (startsWith(val, "DESC"))
					desc = (val.delete(0, 4)) != null;
				else if (startsWith(val, "ASC"))
					val.delete(0, 3);

				if (desc)
					orders.add(cb.desc(path));
				else
					orders.add(cb.asc(path));
			}

			else
				val.deleteCharAt(0);
		}

		if (!orders.isEmpty())
			cq.orderBy(orders);
	}

	private int processNumberArg(StringBuilder analyze, int start, int end, List<Object> args)
	{
		String val = analyze.substring(start, end);
		analyze.delete(start, end);

		Object arg;

		if (isEmpty(val))
		{
			if (args == null || args.size() <= 0 ||
				!((arg = args.remove(0)) instanceof Number))
				throw new RuntimeException("传入的参数不正确");

			return ((Number) arg).intValue();
		}
		else if (isNumeric(val))
			return parseInt(val);

		throw new RuntimeException("查询方法名定义错误");
	}

	private void analyzingStart(StringBuilder analyze, TypedQuery<?> tq, List<Object> args)
	{
		if (!startsWith(analyze, "StartAt"))
			return;

		analyze.delete(0, 7);

		tq.setFirstResult(processNumberArg(analyze, 0, endIndex(analyze, "Top"), args));
	}

	private void analyzingMax(StringBuilder analyze, TypedQuery<?> tq, List<Object> args)
	{
		if (!startsWith(analyze, "Top"))
			return;

		analyze.delete(0, 3);

		tq.setMaxResults(processNumberArg(analyze, 0, analyze.length(), args));
	}

	/**
	 * 获取实体类的所有字段，并且重新排序（先按字段名长度的倒序，然后再字段名排顺序）
	 * 
	 * @param entityType
	 * @return
	 */
	private List<Attribute<?, ?>> getSortedFields(Class<?> entityType)
	{
		List<Attribute<?, ?>> fields = new ArrayList<Attribute<?, ?>>();

		ManagedType<?> type = JpaUtils.getManagedType(entityType);

		if (type != null) for (Attribute<?, ?> attr : type.getAttributes())
			fields.add(attr);

		Collections.sort(fields, new Comparator<Attribute<?, ?>>()
		{

			@Override
			public int compare(Attribute<?, ?> o1, Attribute<?, ?> o2)
			{
				String name1 = o1.getName(), name2 = o2.getName();
				int len1, len2;

				if ((len1 = length(name1)) == (len2 = length(name2)))
					return name2.compareTo(name2);

				return len2 - len1;
			}

		});

		return fields;
	}

	private void cleanProxyWrapper(List<Object> argList)
	{
		if (argList != null) for (int i = 0; i < argList.size(); i++)
		{
			Object obj = argList.get(i);

			if (!Proxy.isProxyClass(obj.getClass()))
				continue;

			obj = ((ObjectInvocationHandler) Proxy.getInvocationHandler(obj)).getObject();

			argList.set(i, Object.class.cast(obj));
		}
	}

	private Object queryByFind(Method method, Object[] args)
	{
		Class<?> entityType = this.assistant.getEntityType(method);
		final StringBuilder analyze = new StringBuilder(method.getName());
		final List<Object> argList = toList(cast(List.class, Object.class), Object.class, args);
		final Class<?> returnType = method.getReturnType();

		cleanProxyWrapper(argList);
		analyze.delete(0, 4);	// 去掉find

		EntityManager entityManager = getCurEntityManager();
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<?> cq = cb.createQuery(entityType);
		Root<?> root = cq.from(entityType);

		AnalyzeContext ctx = new AnalyzeContext();

		Predicate condition = analyzingCondition(analyze, cb, root, argList, ctx);

		if (LogicalDeleteSupport.class.isAssignableFrom(entityType))
		{
			if (condition == null)
				cq.where(cb.equal(root.get("deleted"), false));
			else
				cq.where(cb.and(cb.equal(root.get("deleted"), false), condition));
		}
		else if (condition != null)
			cq.where(condition);

		analyzingOrder(analyze, cb, root, cq, ctx);

		TypedQuery<?> tq = entityManager.createQuery(cq);

		analyzingStart(analyze, tq, argList);
		analyzingMax(analyze, tq, argList);

		List<?> list = tq.getResultList();

		logger.trace("查询结果的数量：{}", list == null ? "null" : String.valueOf(list.size()));

		if (Collection.class.isAssignableFrom(returnType))
		{
			Collection<Object> result = newInstance(cast(returnType, Object.class));

			if (list != null) for (Object obj : list)
				result.add(entityWrapper(obj));

			return result;
		}

		else
			return list == null || list.isEmpty() ? null : entityWrapper(list.get(0));
	}

	private Object queryByCount(Method method, Object[] args)
	{
		Class<?> entityType = this.assistant.getEntityType(method);
		final StringBuilder analyze = new StringBuilder(method.getName());
		final List<Object> argList = toList(cast(List.class, Object.class), Object.class, args);
		final Class<?> returnType = method.getReturnType();

		cleanProxyWrapper(argList);
		analyze.delete(0, 5);

		EntityManager entityManager = getCurEntityManager();
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<?> root = cq.from(entityType);

		AnalyzeContext ctx = new AnalyzeContext();

		Predicate condition = analyzingCondition(analyze, cb, root, argList, ctx);

		if (LogicalDeleteSupport.class.isAssignableFrom(entityType))
		{
			if (condition == null)
				cq.where(cb.equal(root.get("deleted"), false));
			else
				cq.where(cb.and(cb.equal(root.get("deleted"), false), condition));
		}
		else if (condition != null)
			cq.where(condition);

		cq.select(cb.count(root));

		Object result = entityManager.createQuery(cq).getSingleResult();

		if (int.class == returnType)
			return ((Number) result).intValue();
		else if (Integer.class.isAssignableFrom(returnType))
			return Integer.valueOf(((Number) result).intValue());
		else if (long.class == returnType)
			return ((Number) result).longValue();
		else
			return (Long) result;
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.data.dao.QueryInvocation#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	@Override
	public InvokeResult invoke(Object proxy, Method method, Object[] args)
	{
		String methodName = method.getName();

		try
		{
			if (startsWith(methodName, "find") && !startsWith(methodName, "findOne"))
				return success(queryByFind(method, args));
			else if (startsWith(methodName, "count"))
				return success(queryByCount(method, args));
			else
				return InvokeResult.skip();
		}
		catch (Exception e)
		{
			return InvokeResult.failure("分析&查询失败", e);
		}
	}

}
