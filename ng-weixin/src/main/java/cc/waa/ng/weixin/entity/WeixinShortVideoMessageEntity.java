/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   WeixinShortVideoMessageEntity.java
 * CreateTime: 2016-06-16 22:54:22
 */
package cc.waa.ng.weixin.entity;

import cc.waa.ng.data.entity.TargetObject;
import cc.waa.ng.weixin.obj.WeixinShortVideoMessage;

/**
 * 微信小视频消息
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetObject(WeixinShortVideoMessage.class)
public class WeixinShortVideoMessageEntity
	extends WeixinMessageEntity
{

	/** serialVersionUID */
	private static final long serialVersionUID = -3367795457522952698L;



	/** 视频消息媒体id，可以调用多媒体文件下载接口拉取数据 */
	private String mediaId;

	private String mediaType;

	private byte[] mediaContent;

	/** 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据 */
	private String thumbMediaId;

	private String thumbMediaType;

	private byte[] thumbMediaContent;

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

	public String getThumbMediaId()
	{
		return this.thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId)
	{
		this.thumbMediaId = thumbMediaId;
	}

	public String getThumbMediaType()
	{
		return this.thumbMediaType;
	}

	public void setThumbMediaType(String thumbMediaType)
	{
		this.thumbMediaType = thumbMediaType;
	}

	public byte[] getThumbMediaContent()
	{
		return this.thumbMediaContent;
	}

	public void setThumbMediaContent(byte[] thumbMediaContent)
	{
		this.thumbMediaContent = thumbMediaContent;
	}

}
