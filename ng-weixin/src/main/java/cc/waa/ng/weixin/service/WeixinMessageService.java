/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   WeixinMessageService.java
 * CreateTime: 2016-06-17 16:02:35
 */
package cc.waa.ng.weixin.service;

import java.util.Date;

import cc.waa.ng.weixin.obj.WeixinImageMessage;
import cc.waa.ng.weixin.obj.WeixinLinkMessage;
import cc.waa.ng.weixin.obj.WeixinLocationMessage;
import cc.waa.ng.weixin.obj.WeixinShortVideoMessage;
import cc.waa.ng.weixin.obj.WeixinTextMessage;
import cc.waa.ng.weixin.obj.WeixinVideoMessage;
import cc.waa.ng.weixin.obj.WeixinVoiceMessage;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public interface WeixinMessageService
{

	/**
	 * 创建微信的文字信息
	 * 
	 * @param msgId
	 * @param fromUserName
	 * @param toUserName
	 * @param originTime
	 * @param content
	 * @return
	 */
	WeixinTextMessage storeTextMessage(String msgId, String fromUserName,
									   String toUserName, Date originTime,
									   String content);

	/**
	 * 创建微信的图片信息
	 * 
	 * @param msgId
	 * @param fromUserName
	 * @param toUserName
	 * @param originTime
	 * @param picUrl
	 * @param mediaId
	 * @return
	 */
	WeixinImageMessage storeImageMessage(String msgId, String fromUserName,
										 String toUserName, Date originTime,
										 String picUrl, String mediaId);

	/**
	 * 创建微信的语音消息
	 * 
	 * @param msgId
	 * @param fromUserName
	 * @param toUserName
	 * @param originTime
	 * @param mediaId
	 * @param format
	 * @param recognition
	 * @return
	 */
	WeixinVoiceMessage storeVoiceMessage(String msgId, String fromUserName,
										 String toUserName, Date originTime,
										 String mediaId, String format,
										 String recognition);

	/**
	 * 创建微信的视频信息
	 * 
	 * @param msgId
	 * @param fromUserName
	 * @param toUserName
	 * @param originTime
	 * @param mediaId
	 * @param thumbMediaId
	 * @return
	 */
	WeixinVideoMessage storeVideoMessage(String msgId, String fromUserName,
										 String toUserName, Date originTime,
										 String mediaId, String thumbMediaId);

	/**
	 * 创建微信的小视频信息
	 * 
	 * @param msgId
	 * @param fromUserName
	 * @param toUserName
	 * @param originTime
	 * @param mediaId
	 * @param thumbMediaId
	 * @return
	 */
	WeixinShortVideoMessage storeShortVideoMessage(String msgId, String fromUserName,
												   String toUserName, Date originTime,
												   String mediaId, String thumbMediaId);

	/**
	 * 创建微信的位置信息
	 * 
	 * @param msgId
	 * @param fromUserName
	 * @param toUserName
	 * @param originTime
	 * @param locationX
	 * @param locationY
	 * @param scale
	 * @param label
	 * @return
	 */
	WeixinLocationMessage storeLocationMessage(String msgId, String fromUserName,
											   String toUserName, Date originTime,
											   double locationX, double locationY,
											   int scale, String label);

	/**
	 * 创建微信的链接信息
	 * 
	 * @param msgId
	 * @param fromUserName
	 * @param toUserName
	 * @param originTime
	 * @param title
	 * @param description
	 * @param url
	 * @return
	 */
	WeixinLinkMessage storeLinkMessage(String msgId, String fromUserName,
									   String toUserName, Date originTime,
									   String title, String description,
									   String url);

	/**
	 * 更新微信消息对象中的media数据
	 * 
	 * @param msgId			微信消息对象id
	 * @param name			media的名称（以为一个微信消息对象中可能存在多种media，加以区分）
	 * @param type			media的ContentType，例如：image/jpeg
	 * @param content		media的二进制内容
	 */
	void updateMedia(String msgId, String name, String type, byte[] content);

}
