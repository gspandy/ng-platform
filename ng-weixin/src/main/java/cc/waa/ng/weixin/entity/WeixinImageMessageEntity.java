/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   WeixinImageMessageEntity.java
 * CreateTime: 2016-06-16 22:51:12
 */
package cc.waa.ng.weixin.entity;

import cc.waa.ng.data.entity.TargetObject;
import cc.waa.ng.weixin.obj.WeixinImageMessage;

/**
 * 微信图片消息
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetObject(WeixinImageMessage.class)
public class WeixinImageMessageEntity
	extends WeixinMessageEntity
{

	/** serialVersionUID */
	private static final long serialVersionUID = 3561413443558127789L;



	private String picUrl;

	private String mediaId;

	private String mediaType;

	private byte[] mediaContent;

	public String getPicUrl()
	{
		return this.picUrl;
	}

	public void setPicUrl(String picUrl)
	{
		this.picUrl = picUrl;
	}

	public String getMediaId()
	{
		return this.mediaId;
	}

	public void setMediaId(String mediaId)
	{
		this.mediaId = mediaId;
	}

	public String getMediaType()
	{
		return this.mediaType;
	}

	public void setMediaType(String mediaType)
	{
		this.mediaType = mediaType;
	}

	public byte[] getMediaContent()
	{
		return this.mediaContent;
	}

	public void setMediaContent(byte[] mediaContent)
	{
		this.mediaContent = mediaContent;
	}

}
