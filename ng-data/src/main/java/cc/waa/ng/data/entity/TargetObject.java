/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   TargetObject.java
 * CreateTime: 2016-01-20 10:43:55
 */
package cc.waa.ng.data.entity;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface TargetObject
{

	Class<?> value();

}
