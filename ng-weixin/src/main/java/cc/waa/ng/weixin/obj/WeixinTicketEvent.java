/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   WeixinTicketEvent.java
 * CreateTime: 2016-06-22 23:42:39
 */
package cc.waa.ng.weixin.obj;

import cc.waa.ng.data.obj.TargetEntity;
import cc.waa.ng.weixin.entity.WeixinTicketEventEntity;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetEntity(WeixinTicketEventEntity.class)
public interface WeixinTicketEvent
	extends WeixinSubscribeEvent
{

	String getTicket();
	void setTicket(String ticket);

}
