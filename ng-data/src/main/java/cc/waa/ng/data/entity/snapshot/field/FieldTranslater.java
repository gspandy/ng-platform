/*
 * Project:    waa新一代框架的基础模块中的公共工具库
 * 
 * FileName:   FieldTranslater.java
 * CreateTime: 2016-01-14 10:53:53
 */
package cc.waa.ng.data.entity.snapshot.field;

import java.lang.reflect.Field;

/**
 * 快照属性翻译（转换）的接口
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public interface FieldTranslater
{

	/**
	 * @param src
	 * @param tar
	 * @param field		在Snapshot类中的Field对象
	 * @return
	 */
	TranslateResult translate(Object src, Object tar, Field field);

}
