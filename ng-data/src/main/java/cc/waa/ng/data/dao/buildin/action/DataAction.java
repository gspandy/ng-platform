/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   DataAction.java
 * CreateTime: 2015-11-27 16:58:59
 */
package cc.waa.ng.data.dao.buildin.action;

/**
 * 执行dao中，各个方法的接口
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public interface DataAction
{

	/**
	 * 操作完后的返回类型
	 * 
	 * @param returnType
	 */
	void setReturnType(Class<?> returnType);

	/**
	 * 操作针对的业务类型
	 * 
	 * @param domainType
	 */
	void setDomainType(Class<?> domainType);

	/**
	 * 主键的类型
	 * 
	 * @param keyType
	 */
	void setKeyType(Class<?> keyType);

	/**
	 * 可随意插入指定下标的参数值
	 * 
	 * @param index
	 * @param value
	 */
	void setParameter(int index, Object value);

	Object execute();

}
