/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   WeixinCustomMenuEvent.java
 * CreateTime: 2016-06-22 23:45:20
 */
package cc.waa.ng.weixin.obj;

import cc.waa.ng.data.obj.TargetEntity;
import cc.waa.ng.weixin.entity.WeixinCustomMenuEventEntity;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetEntity(WeixinCustomMenuEventEntity.class)
public interface WeixinCustomMenuEvent
	extends WeixinEvent
{

}
