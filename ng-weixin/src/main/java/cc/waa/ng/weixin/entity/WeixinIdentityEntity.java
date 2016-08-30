/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   WeixinIdentityEntity.java
 * CreateTime: 2016-06-17 19:08:11
 */
package cc.waa.ng.weixin.entity;

import cc.waa.ng.base.data.entity.IdentityEntity;
import cc.waa.ng.data.entity.TargetObject;
import cc.waa.ng.weixin.obj.WeixinIdentity;

/**
 * 绑定微信公众号的鉴权标识
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetObject(WeixinIdentity.class)
public class WeixinIdentityEntity
	extends IdentityEntity
{

	/** serialVersionUID */
	private static final long serialVersionUID = -7405750792268581156L;



	private String openId;

	private WeixinMessageEntity lastMessage;

	public String getOpenId()
	{
		return this.openId;
	}

	public void setOpenId(String openId)
	{
		this.openId = openId;
	}

	public WeixinMessageEntity getLastMessage()
	{
		return this.lastMessage;
	}

	public void setLastMessage(WeixinMessageEntity lastMessage)
	{
		this.lastMessage = lastMessage;
	}

}
