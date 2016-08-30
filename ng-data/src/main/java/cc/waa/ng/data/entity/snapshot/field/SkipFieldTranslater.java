/*
 * Project:    waa新一代框架的基础模块中的公共工具库
 * 
 * FileName:   SkipFieldTranslater.java
 * CreateTime: 2016-01-14 11:05:38
 */
package cc.waa.ng.data.entity.snapshot.field;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 忽略指定的属性翻译（转换）
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class SkipFieldTranslater
	implements FieldTranslater
{

	private List<String> skipFields;

	public void setSkipFields(List<String> skipFields)
	{
		this.skipFields = skipFields;
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.util.entity.snapshot.field.FieldTranslater#translate(java.lang.Object, java.lang.Object, java.lang.reflect.Field)
	 */
	@Override
	public TranslateResult translate(Object src, Object tar, Field field)
	{
		if (this.skipFields != null && this.skipFields.contains(field.getName()))
//		if ("serialVersionUID".equals(field.getName()))
			return TranslateResult.SUCCESS;

		return TranslateResult.SKIPED;
	}

}
