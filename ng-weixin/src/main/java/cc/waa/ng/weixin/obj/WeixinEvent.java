/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   WeixinEvent.java
 * CreateTime: 2016-06-22 23:20:32
 */
package cc.waa.ng.weixin.obj;

import java.util.Date;

import cc.waa.ng.data.obj.TargetEntity;
import cc.waa.ng.weixin.entity.WeixinEventEntity;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetEntity(WeixinEventEntity.class)
public interface WeixinEvent
{

	String getId();

	String getFromUserName();
	void setFromUserName(String fromUserName);

	String getToUserName();
	void setToUserName(String toUserName);

	Date getOriginTime();
	void setOriginTime(Date originTime);

	String getMsgType();
	void setMsgType(String msgType);

	Date getCreateTime();

	String getEvent();
	void setEvent(String event);

	String getEventKey();
	void setEventKey(String eventKey);

}
