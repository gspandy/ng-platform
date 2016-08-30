/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   WeixinTextMessageEntity.java
 * CreateTime: 2016-06-16 20:08:06
 */
package cc.waa.ng.weixin.entity;

import cc.waa.ng.data.entity.TargetObject;
import cc.waa.ng.weixin.obj.WeixinTextMessage;

/**
 * 微信文本消息
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetObject(WeixinTextMessage.class)
public class WeixinTextMessageEntity
	extends WeixinMessageEntity
{

	/** serialVersionUID */
	private static final long serialVersionUID = -2283527217948490281L;



	/** 文本消息内容 */
	private String content;

	public String getContent()
	{
		return this.content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

}
