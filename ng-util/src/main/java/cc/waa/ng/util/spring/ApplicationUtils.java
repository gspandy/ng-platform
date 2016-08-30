/*
 * Project:    waa新一代框架的基础模块中的公共工具库
 * 
 * FileName:   ApplicationUtils.java
 * CreateTime: 2016-01-06 20:36:19
 */
package cc.waa.ng.util.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 基于Spring框架的应用工具类
 * 需要在xml文件中配置一下，以便注入当前Spring的ApplicationContext
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class ApplicationUtils
	implements ApplicationContextAware
{

	/** Spring的上下文 */
	private static ApplicationContext context;

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext context)
		throws BeansException
	{
		ApplicationUtils.context = context;
	}

	/**
	 * 触发事件
	 * 
	 * @param event
	 */
	public static void publishEvent(FrameworkEvent event)
	{
		context.publishEvent(event);
	}

}
