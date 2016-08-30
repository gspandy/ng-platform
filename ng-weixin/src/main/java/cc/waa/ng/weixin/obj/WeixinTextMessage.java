/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   WeixinTextMessage.java
 * CreateTime: 2016-06-17 11:50:29
 */
package cc.waa.ng.weixin.obj;

import cc.waa.ng.data.obj.TargetEntity;
import cc.waa.ng.weixin.entity.WeixinTextMessageEntity;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetEntity(WeixinTextMessageEntity.class)
public interface WeixinTextMessage
	extends WeixinMessage
{

	String getContent();
	void setContent(String content);

}
