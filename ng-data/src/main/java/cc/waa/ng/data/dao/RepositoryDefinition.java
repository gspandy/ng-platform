/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   RepositoryDefinition.java
 * CreateTime: 2015-11-20 17:49:14
 */
package cc.waa.ng.data.dao;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.io.Serializable;
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
public @interface RepositoryDefinition
{

	public Class<?> domainClass();

	public Class<? extends Serializable> idClass();

}
