/*
 * Project:    waa新一代框架的基础模块中的公共工具库
 * 
 * FileName:   NestedFieldTranslater.java
 * CreateTime: 2016-01-14 14:13:34
 */
package cc.waa.ng.data.entity.snapshot.field;

import static org.apache.commons.beanutils.PropertyUtils.getNestedProperty;
import static org.apache.commons.beanutils.PropertyUtils.setProperty;

import java.lang.reflect.Field;

import cc.waa.ng.data.entity.snapshot.NestedProperty;

/**
 * 处理已配置多层嵌套属性的翻译（转换）
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class NestedFieldTranslater
	implements FieldTranslater
{

	/* (non-Javadoc)
	 * @see cc.waa.ng.util.entity.snapshot.field.FieldTranslater#translate(java.lang.Object, java.lang.Object, java.lang.reflect.Field)
	 */
	@Override
	public TranslateResult translate(Object src, Object tar, Field field)
	{
		NestedProperty anno = field.getAnnotation(NestedProperty.class);

		if (anno == null)
			return TranslateResult.SKIPED;

		try
		{
			setProperty(tar, field.getName(), getNestedProperty(src, anno.value()));

			return TranslateResult.SUCCESS;
		}
		catch (Exception e)
		{
			return TranslateResult.FAILURE;
		}
	}

}
