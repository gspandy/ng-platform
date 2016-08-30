/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   WeixinLocationEvent.java
 * CreateTime: 2016-06-22 23:43:37
 */
package cc.waa.ng.weixin.obj;

import cc.waa.ng.data.obj.TargetEntity;
import cc.waa.ng.weixin.entity.WeixinLocationEventEntity;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetEntity(WeixinLocationEventEntity.class)
public interface WeixinLocationEvent
	extends WeixinEvent
{

	double getLatitude();
	void setLatitude(double latitude);

	double getLongitude();
	void setLongitude(double longitude);

	double getPrecision();
	void setPrecision(double precision);

}
