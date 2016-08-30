/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   WeixinVoiceMessage.java
 * CreateTime: 2016-06-22 23:55:48
 */
package cc.waa.ng.weixin.obj;

import cc.waa.ng.data.obj.TargetEntity;
import cc.waa.ng.weixin.entity.WeixinVoiceMessageEntity;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetEntity(WeixinVoiceMessageEntity.class)
public interface WeixinVoiceMessage
	extends WeixinMessage
{

	String getMediaId();
	void setMediaId(String mediaId);

	String getMediaType();
	void setMediaType(String mediaType);

	byte[] getMediaContent();
	void setMediaContent(byte[] mediaContent);

	String getFormat();
	void setFormat(String format);

	String getRecognition();
	void setRecognition(String recognition);

}
