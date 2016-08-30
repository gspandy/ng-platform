/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   Assistant.java
 * CreateTime: 2016-01-26 13:41:29
 */
package cc.waa.ng.data.dao;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public enum Assistant
{

	DOMAIN(DomainAssistant.class),

	ENTITY(EntityAssistant.class);

	private Class<? extends DaoAssistant> assistantType;

	/**
	 * @param assistantType
	 */
	private Assistant(Class<? extends DaoAssistant> assistantType)
	{
		this.assistantType = assistantType;
	}

	public DaoAssistant newInstance()
	{
		try
		{
			return this.assistantType.newInstance();
		}
		catch (ReflectiveOperationException e)
		{
			return null;
		}
	}

}
