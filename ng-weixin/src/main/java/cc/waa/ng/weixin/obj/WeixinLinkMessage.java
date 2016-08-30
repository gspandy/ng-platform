/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   WeixinLinkMessage.java
 * CreateTime: 2016-06-22 23:51:14
 */
package cc.waa.ng.weixin.obj;

import cc.waa.ng.data.obj.TargetEntity;
import cc.waa.ng.weixin.entity.WeixinLinkMessageEntity;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetEntity(WeixinLinkMessageEntity.class)
public interface WeixinLinkMessage
	extends WeixinMessage
{

	String getTitle();
	void setTitle(String title);

	String getDescription();
	void setDescription(String description);

	String getUrl();
	void setUrl(String url);

}
