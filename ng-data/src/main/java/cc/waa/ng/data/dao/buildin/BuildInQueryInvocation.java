/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   BuildInQueryInvocation.java
 * CreateTime: 2016-01-20 18:51:25
 */
package cc.waa.ng.data.dao.buildin;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import cc.waa.ng.data.dao.DaoAssistant;
import cc.waa.ng.data.dao.InvokeResult;
import cc.waa.ng.data.dao.QueryInvocation;
import cc.waa.ng.data.dao.RepositoryDefinition;
import cc.waa.ng.data.dao.buildin.action.BlankDataAction;
import cc.waa.ng.data.dao.buildin.action.CountDataAction;
import cc.waa.ng.data.dao.buildin.action.DataAction;
import cc.waa.ng.data.dao.buildin.action.DeleteDataAction;
import cc.waa.ng.data.dao.buildin.action.FindOneDataAction;
import cc.waa.ng.data.dao.buildin.action.SaveDataAction;
import cc.waa.ng.data.dao.buildin.action.UpdateDataAction;

/**
 * 执行内置的查询操作
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class BuildInQueryInvocation
	implements QueryInvocation
{

	private static final Map<String, Class<? extends DataAction>> dataActionMap = new HashMap<String, Class<? extends DataAction>>();

	static
	{
		dataActionMap.put("blank", BlankDataAction.class);
		dataActionMap.put("save", SaveDataAction.class);
		dataActionMap.put("findOne", FindOneDataAction.class);
		dataActionMap.put("count", CountDataAction.class);
		dataActionMap.put("delete", DeleteDataAction.class);
		dataActionMap.put("update", UpdateDataAction.class);
	}



	/* (non-Javadoc)
	 * @see cc.waa.ng.data.dao.QueryInvocation#setAssistant(cc.waa.ng.data.dao.DaoAssistant)
	 */
	@Override
	public void setAssistant(DaoAssistant assistant)
	{
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.data.dao.QueryInvocation#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	@Override
	public InvokeResult invoke(Object proxy, Method method, Object[] args)
	{
		String methodName = method.getName();

		if (!dataActionMap.containsKey(methodName))
			return InvokeResult.skip();

		Class<?> type = method.getDeclaringClass();
		RepositoryDefinition anno = type.getAnnotation(RepositoryDefinition.class);

		try
		{
			DataAction action = dataActionMap.get(methodName).newInstance();

			action.setReturnType(method.getReturnType());
			action.setDomainType(anno.domainClass());
			action.setKeyType(anno.idClass());

			// method的参数，设置action相应的属性
			if (args != null) for (int i = 0; i < args.length; i++)
				action.setParameter(i, args[i]);

			return InvokeResult.success(action.execute());
		}
		catch (Exception e)
		{
			return InvokeResult.failure("执行dao的方法时发生错误", e);
		}
	}

}
