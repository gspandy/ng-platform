/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   WeixinSubscribeEvent.java
 * CreateTime: 2016-06-22 23:40:51
 */
package cc.waa.ng.weixin.obj;

import cc.waa.ng.data.obj.TargetEntity;
import cc.waa.ng.weixin.entity.WeixinSubscribeEventEntity;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetEntity(WeixinSubscribeEventEntity.class)
public interface WeixinSubscribeEvent
	extends WeixinEvent
{

}
