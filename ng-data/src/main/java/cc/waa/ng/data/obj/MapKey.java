/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   MapKey.java
 * CreateTime: 2016-01-20 09:59:33
 */
package cc.waa.ng.data.obj;

import static java.lang.annotation.ElementType.METHOD;
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
@Target(METHOD)
public @interface MapKey
{

	String value();

}
