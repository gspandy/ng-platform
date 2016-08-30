/*
 * Project:    waa新一代框架的基础模块中的实体部分
 * 
 * FileName:   EntitySavedEvent.java
 * CreateTime: 2016-01-06 20:28:12
 */
package cc.waa.ng.data.entity.event;

/**
 * 实体类对象保存（新增）事件
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class EntitySavedEvent
	extends EntityEvent
{

	/** serialVersionUID */
	private static final long serialVersionUID = -6766446779845882801L;



	/**
	 * @param source
	 */
	public EntitySavedEvent(Object source)
	{
		super(source);
	}

}
