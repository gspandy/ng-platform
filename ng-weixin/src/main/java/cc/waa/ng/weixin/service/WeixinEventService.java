/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   WeixinEventService.java
 * CreateTime: 2016-06-23 17:44:27
 */
package cc.waa.ng.weixin.service;

import cc.waa.ng.weixin.obj.WeixinCustomMenuEvent;
import cc.waa.ng.weixin.obj.WeixinLocationEvent;
import cc.waa.ng.weixin.obj.WeixinSubscribeEvent;
import cc.waa.ng.weixin.obj.WeixinTicketEvent;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public interface WeixinEventService
{

	WeixinSubscribeEvent createSubscribeEvent();

	WeixinTicketEvent createTicketEvent();

	WeixinLocationEvent createLocationEvent();

	WeixinCustomMenuEvent createCustomMenuEvent();

}
