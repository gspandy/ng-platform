/*
 * Project:    waa新一代框架的基础模块中的公共工具库
 * 
 * FileName:   ClassFilter.java
 * CreateTime: 2015-11-20 10:26:19
 */
package cc.waa.ng.util.lang;

/**
 * 配合相关查找类的方法，过滤查找结果，留下自己真正想要的类
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public interface ClassFilter
{

	/**
	 * 检查当前类是否想要的
	 * 
	 * @param pack
	 * @param clazz
	 * @return
	 */
	boolean accept(String pack, Class<?> clazz);

}
