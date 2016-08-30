/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   WeixinMessageReceiveEvent.java
 * CreateTime: 2016-06-30 11:13:00
 */
package cc.waa.ng.weixin;

import cc.waa.ng.weixin.obj.WeixinMessage;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class WeixinMessageReceiveEvent
	extends WeixinMessageEvent
{

	/** serialVersionUID */
	private static final long serialVersionUID = 2095139210398258745L;



	/**
	 * @param source
	 */
	public WeixinMessageReceiveEvent(WeixinMessage source)
	{
		super(source);
	}

}
