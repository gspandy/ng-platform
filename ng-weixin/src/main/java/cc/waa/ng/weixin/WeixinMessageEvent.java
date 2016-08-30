/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   WeixinMessageEvent.java
 * CreateTime: 2016-06-30 10:03:59
 */
package cc.waa.ng.weixin;

import cc.waa.ng.util.spring.FrameworkEvent;
import cc.waa.ng.weixin.obj.WeixinMessage;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public abstract class WeixinMessageEvent
	extends FrameworkEvent
{

	/** serialVersionUID */
	private static final long serialVersionUID = 7442486333758596792L;



	/**
	 * @param source
	 */
	public WeixinMessageEvent(WeixinMessage source)
	{
		super(source);
	}

}
