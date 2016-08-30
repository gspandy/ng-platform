/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   BlankDataAction.java
 * CreateTime: 2015-11-27 18:27:36
 */
package cc.waa.ng.data.dao.buildin.action;

import static cc.waa.ng.data.util.EntityUtils.entityWrapper;
import static org.slf4j.LoggerFactory.getLogger;

import java.lang.reflect.Modifier;

import org.slf4j.Logger;

import cc.waa.ng.data.obj.TargetEntity;

/**
 * 创建空白的业务对象
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class BlankDataAction
	extends AbstractDataAction
	implements DataAction
{

	private static Logger logger = getLogger(BlankDataAction.class);



	/* (non-Javadoc)
	 * @see cc.waa.ng.data.action.DataAction#execute()
	 */
	@Override
	public Object execute()
	{
		try
		{
			if (!Modifier.isAbstract(this.entityType.getModifiers()))
				return entityWrapper(this.entityType.newInstance());

			Class<?> objClass = getParameter(0, Class.class);
			TargetEntity te = objClass.getAnnotation(TargetEntity.class);

			if (te == null)
				throw new RuntimeException("指定的业务对象类没有设置TargetEntity");

			return entityWrapper(te.value().newInstance());
		}
		catch (Exception e)
		{
			logger.error("创建空白业务对象失败，{}", this.returnType.getName());

			return null;
		}
	}

}
