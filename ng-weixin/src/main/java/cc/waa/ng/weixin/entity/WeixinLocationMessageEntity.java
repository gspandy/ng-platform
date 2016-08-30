/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   WeixinLocationMessageEntity.java
 * CreateTime: 2016-06-16 22:59:05
 */
package cc.waa.ng.weixin.entity;

import cc.waa.ng.data.entity.TargetObject;
import cc.waa.ng.weixin.obj.WeixinLocationMessage;

/**
 * 微信地理位置消息
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetObject(WeixinLocationMessage.class)
public class WeixinLocationMessageEntity
	extends WeixinMessageEntity
{

	/** serialVersionUID */
	private static final long serialVersionUID = -6930864644598305828L;



	/** 地理位置维度 */
	private double locationX;

	/** 地理位置经度 */
	private double locationY;

	/** 地图缩放大小 */
	private int scale;

	/** 地理位置信息 */
	private String label;

	public double getLocationX()
	{
		return this.locationX;
	}

	public void setLocationX(double locationX)
	{
		this.locationX = locationX;
	}

	public double getLocationY()
	{
		return this.locationY;
	}

	public void setLocationY(double locationY)
	{
		this.locationY = locationY;
	}

	public int getScale()
	{
		return this.scale;
	}

	public void setScale(int scale)
	{
		this.scale = scale;
	}

	public String getLabel()
	{
		return this.label;
	}

	public void setLabel(String label)
	{
		this.label = label;
	}

}
