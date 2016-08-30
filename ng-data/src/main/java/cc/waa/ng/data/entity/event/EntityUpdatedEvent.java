/*
 * Project:    waa新一代框架的基础模块中的公共工具库
 * 
 * FileName:   EntityUpdatedEvent.java
 * CreateTime: 2016-01-08 17:39:16
 */
package cc.waa.ng.data.entity.event;

/**
 * 实体类对象更新事件
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class EntityUpdatedEvent
	extends EntityEvent
{

	/** serialVersionUID */
	private static final long serialVersionUID = 1299740556736258686L;



	/**
	 * @param source
	 */
	public EntityUpdatedEvent(Object source)
	{
		super(source);
	}

}
