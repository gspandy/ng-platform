/*
 * Project:    waa新一代框架的基础模块中的公共工具库
 * 
 * FileName:   SimpleFieldTranslater.java
 * CreateTime: 2016-01-14 11:15:22
 */
package cc.waa.ng.data.entity.snapshot.field;

import static org.apache.commons.beanutils.PropertyUtils.getProperty;
import static org.apache.commons.beanutils.PropertyUtils.setProperty;
import static org.slf4j.LoggerFactory.getLogger;

import java.lang.reflect.Field;

import org.slf4j.Logger;

/**
 * 处理简单的属性（同名属性）翻译（转换）
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class SimpleFieldTranslater
	implements FieldTranslater
{

	private static final Logger logger = getLogger(SimpleFieldTranslater.class);



	/**
	 * 查找目标对象中相应的属性
	 * 
	 * @param tar
	 * @param fieldName
	 * @return
	 */
	private Field getSourceField(Object tar, String fieldName)
	{
		try
		{
			return tar.getClass().getDeclaredField(fieldName);
		}
		catch (NoSuchFieldException e)
		{
			logger.debug("{}中没有找到指定的属性{}", tar, fieldName);

			return null;
		}
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.util.entity.snapshot.field.FieldTranslater#translate(java.lang.Object, java.lang.Object, java.lang.reflect.Field)
	 */
	@Override
	public TranslateResult translate(Object src, Object tar, Field field)
	{
		try
		{
			String fieldName = field.getName();
			Field srcField = getSourceField(src, fieldName);

			if (srcField == null || field.getType() != srcField.getType())
				return TranslateResult.SKIPED;

			setProperty(tar, fieldName, getProperty(src, fieldName));

			return TranslateResult.SUCCESS;
		}
		catch (Exception e)
		{
			return TranslateResult.FAILURE;
		}
	}

}
