/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   MethodMapper.java
 * CreateTime: 2016-01-19 16:19:33
 */
package cc.waa.ng.data.obj.mapper;

import java.lang.reflect.Method;

/**
 * 业务对象对实体类对象的操作映射
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public interface MethodMapper
{

	MapResult apply(Object src, Object tar, Method method, Object[] args);

}
