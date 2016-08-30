/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   WeixinSubscribeEventEntity.java
 * CreateTime: 2016-06-22 10:02:29
 */
package cc.waa.ng.weixin.entity;

import cc.waa.ng.data.entity.TargetObject;
import cc.waa.ng.weixin.obj.WeixinSubscribeEvent;

/**
 * 关注/取消关注事件
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetObject(WeixinSubscribeEvent.class)
public class WeixinSubscribeEventEntity
	extends WeixinEventEntity
{

	/** serialVersionUID */
	private static final long serialVersionUID = -2199398401176009923L;

}
