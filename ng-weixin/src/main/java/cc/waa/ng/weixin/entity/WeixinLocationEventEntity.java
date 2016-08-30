/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   WeixinLocationEventEntity.java
 * CreateTime: 2016-06-21 18:21:14
 */
package cc.waa.ng.weixin.entity;

import cc.waa.ng.data.entity.TargetObject;
import cc.waa.ng.weixin.obj.WeixinLocationEvent;

/**
 * 上报地理位置事件
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetObject(WeixinLocationEvent.class)
public class WeixinLocationEventEntity
	extends WeixinEventEntity
{

	/** serialVersionUID */
	private static final long serialVersionUID = -1350470527623529080L;



	/** 地理位置纬度 */
	private double latitude;

	/** 地理位置经度 */
	private double longitude;

	/** 地理位置精度 */
	private double precision;

	public double getLatitude()
	{
		return this.latitude;
	}

	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
	}

	public double getLongitude()
	{
		return this.longitude;
	}

	public void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}

	public double getPrecision()
	{
		return this.precision;
	}

	public void setPrecision(double precision)
	{
		this.precision = precision;
	}

}
