/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   DaoAssistant.java
 * CreateTime: 2016-01-26 11:31:01
 */
package cc.waa.ng.data.dao;

import java.lang.reflect.Method;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public interface DaoAssistant
{

	Class<?> getEntityType(Method method);

}
