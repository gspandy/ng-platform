/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   WeixinShortVideoMessage.java
 * CreateTime: 2016-06-17 11:57:48
 */
package cc.waa.ng.weixin.obj;

import cc.waa.ng.data.obj.TargetEntity;
import cc.waa.ng.weixin.entity.WeixinShortVideoMessageEntity;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetEntity(WeixinShortVideoMessageEntity.class)
public interface WeixinShortVideoMessage
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
