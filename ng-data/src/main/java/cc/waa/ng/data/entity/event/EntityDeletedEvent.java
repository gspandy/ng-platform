/*
 * Project:    waa新一代框架的基础模块中的公共工具库
 * 
 * FileName:   EntityDeletedEvent.java
 * CreateTime: 2016-01-12 16:46:24
 */
package cc.waa.ng.data.entity.event;

/**
 * 实体类对象删除事件
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class EntityDeletedEvent
	extends EntityEvent
{

	/** serialVersionUID */
	private static final long serialVersionUID = 7252007008674053696L;



	/**
	 * @param source
	 */
	public EntityDeletedEvent(Object source)
	{
		super(source);
	}

}
