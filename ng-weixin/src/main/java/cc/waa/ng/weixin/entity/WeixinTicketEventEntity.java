/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   WeixinTicketEventEntity.java
 * CreateTime: 2016-06-21 18:16:49
 */
package cc.waa.ng.weixin.entity;

import cc.waa.ng.data.entity.TargetObject;
import cc.waa.ng.weixin.obj.WeixinTicketEvent;

/**
 * 扫描带参数二维码事件
 * 
 * 事件KEY值(eventKey)说明：
 * event = subscribe	qrscene_为前缀，后面为二维码的参数值
 * event = SCAN			是一个32位无符号整数，即创建二维码时的二维码scene_id
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetObject(WeixinTicketEvent.class)
public class WeixinTicketEventEntity
	extends WeixinSubscribeEventEntity
{

	/** serialVersionUID */
	private static final long serialVersionUID = 8097921485616266712L;



	/** 二维码的ticket，可用来换取二维码图片 */
	private String ticket;

	public String getTicket()
	{
		return this.ticket;
	}

	public void setTicket(String ticket)
	{
		this.ticket = ticket;
	}

}
