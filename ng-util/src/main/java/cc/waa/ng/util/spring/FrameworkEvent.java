/*
 * Project:    waa新一代框架的基础模块中的公共工具库
 * 
 * FileName:   FrameworkEvent.java
 * CreateTime: 2016-01-06 20:29:43
 */
package cc.waa.ng.util.spring;

import org.springframework.context.ApplicationEvent;

/**
 * 封装ng框架的通用的事件类
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public abstract class FrameworkEvent
	extends ApplicationEvent
{

	/** serialVersionUID */
	private static final long serialVersionUID = 311366333839680739L;



	/**
	 * @param source
	 */
	public FrameworkEvent(Object source)
	{
		super(source);
	}

}
