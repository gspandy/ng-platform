/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   WeixinMessageEntity.java
 * CreateTime: 2016-06-16 20:11:25
 */
package cc.waa.ng.weixin.entity;

/**
 * 微信消息的基类
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public abstract class WeixinMessageEntity
	extends WeixinBaseEntity
{

	/** serialVersionUID */
	private static final long serialVersionUID = -3024995873904489832L;



	private String msgId;

	public String getMsgId()
	{
		return this.msgId;
	}

	public void setMsgId(String msgId)
	{
		this.msgId = msgId;
	}

}
