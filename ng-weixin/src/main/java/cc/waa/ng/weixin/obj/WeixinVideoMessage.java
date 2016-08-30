/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   WeixinVideoMessage.java
 * CreateTime: 2016-06-22 23:55:02
 */
package cc.waa.ng.weixin.obj;

import cc.waa.ng.data.obj.TargetEntity;
import cc.waa.ng.weixin.entity.WeixinVideoMessageEntity;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetEntity(WeixinVideoMessageEntity.class)
public interface WeixinVideoMessage
	extends WeixinMessage
{

	String getMediaId();
	void setMediaId(String mediaId);

	String getMediaType();
	void setMediaType(String mediaType);

	byte[] getMediaContent();
	void setMediaContent(byte[] mediaContent);

	String getThumbMediaId();
	void setThumbMediaId(String thumbMediaId);

	String getThumbMediaType();
	void setThumbMediaType(String thumbMediaType);

	byte[] getThumbMediaContent();
	void setThumbMediaContent(byte[] thumbMediaContent);

}
