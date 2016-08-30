/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   WeixinEventEntity.java
 * CreateTime: 2016-06-21 17:32:03
 */
package cc.waa.ng.weixin.entity;

/**
 * 微信事件基类
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public abstract class WeixinEventEntity
	extends WeixinBaseEntity
{

	/** serialVersionUID */
	private static final long serialVersionUID = 775346443901018133L;



	/** 事件类型 */
	private String event;

	/** 事件KEY值 */
	private String eventKey;

	public String getEvent()
	{
		return this.event;
	}

	public void setEvent(String event)
	{
		this.event = event;
	}

	public String getEventKey()
	{
		return this.eventKey;
	}

	public void setEventKey(String eventKey)
	{
		this.eventKey = eventKey;
	}

}
