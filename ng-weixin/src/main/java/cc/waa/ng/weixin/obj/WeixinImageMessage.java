/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   WeixinImageMessage.java
 * CreateTime: 2016-06-17 11:52:46
 */
package cc.waa.ng.weixin.obj;

import cc.waa.ng.data.obj.TargetEntity;
import cc.waa.ng.weixin.entity.WeixinImageMessageEntity;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetEntity(WeixinImageMessageEntity.class)
public interface WeixinImageMessage
	extends WeixinMessage
{

	String getPicUrl();
	void setPicUrl(String picUrl);

	String getMediaId();
	void setMediaId(String mediaId);

	String getMediaType();
	void setMediaType(String mediaType);

	byte[] getMediaContent();
	void setMediaContent(byte[] mediaContent);

}
