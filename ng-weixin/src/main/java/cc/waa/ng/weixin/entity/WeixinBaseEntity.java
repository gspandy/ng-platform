/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   WeixinBaseEntity.java
 * CreateTime: 2016-06-21 17:33:27
 */
package cc.waa.ng.weixin.entity;

import java.io.Serializable;
import java.util.Date;

import cc.waa.ng.data.entity.BaseEntity;

/**
 * 微信事件和微信消息的共同抽象类（不排除还有其它类型，并且不会映射到数据库）
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public abstract class WeixinBaseEntity
	implements BaseEntity, Serializable
{

	/** serialVersionUID */
	private static final long serialVersionUID = -5541204746670516817L;



	/** 系统分配的guid */
	private String id;

	/** 发送方帐号（一个OpenID） */
	private String fromUserName;

	/** 开发者微信号 */
	private String toUserName;

	/**
	 * 消息创建时间 （整型）
	 * （由于框架统一使用createTime来保存记录创建时间，所以这里要改名）
	 */
	private Date originTime;

	/** 消息类型 */
	private String msgType;

	/** 创建时间 */
	private Date createTime;

	/* (non-Javadoc)
	 * @see cc.waa.ng.data.entity.BaseEntity#getId()
	 */
	@Override
	public String getId()
	{
		return this.id;
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.data.entity.BaseEntity#setId(java.lang.String)
	 */
	@Override
	public void setId(String id)
	{
		this.id = id;
	}

	public String getFromUserName()
	{
		return this.fromUserName;
	}

	public void setFromUserName(String fromUserName)
	{
		this.fromUserName = fromUserName;
	}

	public String getToUserName()
	{
		return this.toUserName;
	}

	public void setToUserName(String toUserName)
	{
		this.toUserName = toUserName;
	}

	public Date getOriginTime()
	{
		return this.originTime;
	}

	public void setOriginTime(Date originTime)
	{
		this.originTime = originTime;
	}

	public String getMsgType()
	{
		return this.msgType;
	}

	public void setMsgType(String msgType)
	{
		this.msgType = msgType;
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.data.entity.BaseEntity#getCreateTime()
	 */
	@Override
	public Date getCreateTime()
	{
		return this.createTime;
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.data.entity.BaseEntity#setCreateTime(java.util.Date)
	 */
	@Override
	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

}
