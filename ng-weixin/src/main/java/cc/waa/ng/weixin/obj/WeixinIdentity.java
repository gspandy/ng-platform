/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   WeixinIdentity.java
 * CreateTime: 2016-07-05 17:05:51
 */
package cc.waa.ng.weixin.obj;

import cc.waa.ng.data.obj.TargetEntity;
import cc.waa.ng.weixin.entity.WeixinIdentityEntity;
import cc.waa.ng.weixin.entity.WeixinMessageEntity;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetEntity(WeixinIdentityEntity.class)
public interface WeixinIdentity
{

	String getOpenId();
	void setOpenId(String openId);

	WeixinMessageEntity getLastMessage();
	void setLastMessage(WeixinMessageEntity lastMessage);

}
