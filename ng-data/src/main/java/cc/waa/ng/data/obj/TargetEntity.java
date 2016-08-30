/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   TargetEntity.java
 * CreateTime: 2015-11-27 18:55:02
 */
package cc.waa.ng.data.obj;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 指定业务对象对应的实体类
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface TargetEntity
{

	Class<?> value();

}
