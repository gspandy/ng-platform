/*
 * Project:    waa新一代框架的基础模块中的公共工具库
 * 
 * FileName:   EntityEvent.java
 * CreateTime: 2016-01-06 20:55:25
 */
package cc.waa.ng.data.entity.event;

import cc.waa.ng.util.spring.FrameworkEvent;

/**
 * 有关实体类对象的事件（抽象，须继承为具体事件后才能使用）
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public abstract class EntityEvent
	extends FrameworkEvent
{

	/** serialVersionUID */
	private static final long serialVersionUID = 2633791162131279552L;



	/**
	 * @param source
	 */
	public EntityEvent(Object source)
	{
		super(source);
	}

}
