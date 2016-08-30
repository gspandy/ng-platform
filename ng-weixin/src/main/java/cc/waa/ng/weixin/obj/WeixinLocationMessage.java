/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   WeixinLocationMessage.java
 * CreateTime: 2016-06-17 11:54:58
 */
package cc.waa.ng.weixin.obj;

import cc.waa.ng.data.obj.TargetEntity;
import cc.waa.ng.weixin.entity.WeixinLocationMessageEntity;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetEntity(WeixinLocationMessageEntity.class)
public interface WeixinLocationMessage
	extends WeixinMessage
{

	double getLocationX();
	void setLocationX(double locationX);

	double getLocationY();
	void setLocationY(double locationY);

	int getScale();
	void setScale(int scale);

	String getLabel();
	void setLabel(String label);

}
