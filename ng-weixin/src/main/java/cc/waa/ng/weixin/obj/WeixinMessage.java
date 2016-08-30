/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   WeixinMessage.java
 * CreateTime: 2016-06-17 11:46:13
 */
package cc.waa.ng.weixin.obj;

import java.util.Date;

import cc.waa.ng.data.obj.TargetEntity;
import cc.waa.ng.weixin.entity.WeixinMessageEntity;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetEntity(WeixinMessageEntity.class)
public interface WeixinMessage
{

	String getId();

	String getMsgId();
	void setMsgId(String msgId);

	String getFromUserName();
	void setFromUserName(String fromUserName);

	String getToUserName();
	void setToUserName(String toUserName);

	Date getOriginTime();
	void setOriginTime(Date originTime);

	String getMsgType();
	void setMsgType(String msgType);

	Date getCreateTime();

}
