/*
 * Project:    waa新一代框架的基础模块中的公共工具库
 * 
 * FileName:   Snapshotable.java
 * CreateTime: 2015-09-25 09:07:38
 */
package cc.waa.ng.data.entity.snapshot;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 支持快照的实体类
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface Snapshotable
{

	/**
	 * 实体类对应快照的类型
	 * 
	 * @return
	 */
	Class<? extends Snapshot<?>> type();

}
