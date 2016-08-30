/*
 * Project:    waa新一代框架的基础模块中的实体部分
 * 
 * FileName:   ClassAttributeConverter.java
 * CreateTime: 2015-09-18 18:04:50
 */
package cc.waa.ng.data.entity;

import javax.persistence.AttributeConverter;

/**
 * 属性为Class类型的转换类(对String的转换)
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class ClassAttributeConverter
	implements AttributeConverter<Class<?>, String>
{

	/* (non-Javadoc)
	 * @see javax.persistence.AttributeConverter#convertToDatabaseColumn(java.lang.Object)
	 */
	@Override
	public String convertToDatabaseColumn(Class<?> attribute)
	{
		return attribute == null ? null : attribute.getName();
	}

	/* (non-Javadoc)
	 * @see javax.persistence.AttributeConverter#convertToEntityAttribute(java.lang.Object)
	 */
	@Override
	public Class<?> convertToEntityAttribute(String dbData)
	{
		try
		{
			return (Class<?>) Class.forName(dbData);
		}
		catch (Exception e)
		{
			return null;
		}
	}

}
