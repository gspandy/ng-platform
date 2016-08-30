/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   WeixinVoiceMessageEntity.java
 * CreateTime: 2016-06-22 21:40:08
 */
package cc.waa.ng.weixin.entity;

import cc.waa.ng.data.entity.TargetObject;
import cc.waa.ng.weixin.obj.WeixinVoiceMessage;

/**
 * 微信语音消息
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetObject(WeixinVoiceMessage.class)
public class WeixinVoiceMessageEntity
	extends WeixinMessageEntity
{

	/** serialVersionUID */
	private static final long serialVersionUID = 6307498848355687138L;



	/** 语音消息媒体id，可以调用多媒体文件下载接口拉取数据 */
	private String mediaId;

	private String mediaType;

	private byte[] mediaContent;

	/** 语音格式，如amr，speex等 */
	private String format;

	/** 语音识别结果（使用UTF8编码） */
	private String recognition;

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

	public String getFormat()
	{
		return this.format;
	}

	public void setFormat(String format)
	{
		this.format = format;
	}

	public String getRecognition()
	{
		return this.recognition;
	}

	public void setRecognition(String recognition)
	{
		this.recognition = recognition;
	}

}
