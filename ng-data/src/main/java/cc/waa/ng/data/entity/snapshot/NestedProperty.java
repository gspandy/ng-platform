/*
 * Project:    waa新一代框架的基础模块中的公共工具库
 * 
 * FileName:   NestedProperty.java
 * CreateTime: 2016-01-14 10:27:31
 */
package cc.waa.ng.data.entity.snapshot;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 指定要绑定的嵌套的属性
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface NestedProperty
{

	/**
	 * 支持嵌套了的属性表达式
	 * 
	 * @return
	 */
	String value();

}
