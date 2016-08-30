/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   WeixinLinkMessageEntity.java
 * CreateTime: 2016-06-22 21:55:12
 */
package cc.waa.ng.weixin.entity;

import cc.waa.ng.data.entity.TargetObject;
import cc.waa.ng.weixin.obj.WeixinLinkMessage;

/**
 * 微信链接消息
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetObject(WeixinLinkMessage.class)
public class WeixinLinkMessageEntity
	extends WeixinMessageEntity
{

	/** serialVersionUID */
	private static final long serialVersionUID = -1112351577293307537L;



	private String title;

	private String description;

	private String url;

	public String getTitle()
	{
		return this.title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return this.description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getUrl()
	{
		return this.url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

}
