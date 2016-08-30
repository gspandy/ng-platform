/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   WeixinMessageServiceImpl.java
 * CreateTime: 2016-06-17 16:36:04
 */
package cc.waa.ng.weixin.service.impl;

import static cc.waa.ng.util.lang.BeanUtils.setProperty;
import static org.apache.commons.lang3.StringUtils.join;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.Date;

import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import cc.waa.ng.weixin.WeixinMessageReceiveEvent;
import cc.waa.ng.weixin.dao.WeixinMessageDao;
import cc.waa.ng.weixin.msg.ImageMessageHandler;
import cc.waa.ng.weixin.obj.WeixinImageMessage;
import cc.waa.ng.weixin.obj.WeixinLinkMessage;
import cc.waa.ng.weixin.obj.WeixinLocationMessage;
import cc.waa.ng.weixin.obj.WeixinMessage;
import cc.waa.ng.weixin.obj.WeixinShortVideoMessage;
import cc.waa.ng.weixin.obj.WeixinTextMessage;
import cc.waa.ng.weixin.obj.WeixinVideoMessage;
import cc.waa.ng.weixin.obj.WeixinVoiceMessage;
import cc.waa.ng.weixin.service.WeixinMessageService;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class WeixinMessageServiceImpl
	implements WeixinMessageService, ApplicationContextAware
{

	private static final Logger logger = getLogger(ImageMessageHandler.class);



	private WeixinMessageDao weixinMessageDao;

	public void setWeixinMessageDao(WeixinMessageDao weixinMessageDao)
	{
		this.weixinMessageDao = weixinMessageDao;
	}

	private ApplicationContext applicationContext;

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
	{
		this.applicationContext = applicationContext;
	}

	private void triggerEvent(WeixinMessage message)
	{
		this.applicationContext.publishEvent(new WeixinMessageReceiveEvent(message));
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.weixin.service.WeixinMessageService#createTextMessage(java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.lang.String)
	 */
	@Override
	public WeixinTextMessage storeTextMessage(String msgId, String fromUserName, String toUserName, Date originTime,
			String content)
	{
		WeixinTextMessage msg = this.weixinMessageDao.blank(WeixinTextMessage.class);

		msg.setContent(content);
		msg.setFromUserName(fromUserName);
		msg.setMsgId(msgId);
		msg.setMsgType("text");
		msg.setOriginTime(originTime);
		msg.setToUserName(toUserName);

		triggerEvent(msg = this.weixinMessageDao.save(msg));

		return msg;
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.weixin.service.WeixinMessageService#createImageMessage(java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.lang.String, java.lang.String)
	 */
	@Override
	public WeixinImageMessage storeImageMessage(String msgId, String fromUserName, String toUserName, Date originTime,
			String picUrl, String mediaId)
	{
		WeixinImageMessage msg = this.weixinMessageDao.blank(WeixinImageMessage.class);

		msg.setFromUserName(fromUserName);
		msg.setMediaId(mediaId);
		msg.setMsgId(msgId);
		msg.setMsgType("image");
		msg.setOriginTime(originTime);
		msg.setPicUrl(picUrl);
		msg.setToUserName(toUserName);

		triggerEvent(msg = this.weixinMessageDao.save(msg));

		return msg;
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.weixin.service.WeixinMessageService#createVoiceMessage(java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public WeixinVoiceMessage storeVoiceMessage(String msgId, String fromUserName, String toUserName, Date originTime,
			String mediaId, String format, String recognition)
	{
		WeixinVoiceMessage msg = this.weixinMessageDao.blank(WeixinVoiceMessage.class);

		msg.setFormat(format);
		msg.setFromUserName(fromUserName);
		msg.setMediaId(mediaId);
		msg.setMsgId(msgId);
		msg.setMsgType("voice");
		msg.setOriginTime(originTime);
		msg.setRecognition(recognition);
		msg.setToUserName(toUserName);

		triggerEvent(msg = this.weixinMessageDao.save(msg));

		return msg;
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.weixin.service.WeixinMessageService#createVideoMessage(java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.lang.String, java.lang.String)
	 */
	@Override
	public WeixinVideoMessage storeVideoMessage(String msgId, String fromUserName, String toUserName, Date originTime,
			String mediaId, String thumbMediaId)
	{
		WeixinVideoMessage msg = this.weixinMessageDao.blank(WeixinVideoMessage.class);

		msg.setFromUserName(fromUserName);
		msg.setMediaId(mediaId);
		msg.setMsgId(msgId);
		msg.setMsgType("video");
		msg.setOriginTime(originTime);
		msg.setThumbMediaId(thumbMediaId);
		msg.setToUserName(toUserName);

		triggerEvent(msg = this.weixinMessageDao.save(msg));

		return msg;
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.weixin.service.WeixinMessageService#createShortVideoMessage(java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.lang.String, java.lang.String)
	 */
	@Override
	public WeixinShortVideoMessage storeShortVideoMessage(String msgId, String fromUserName, String toUserName,
			Date originTime, String mediaId, String thumbMediaId)
	{
		WeixinShortVideoMessage msg = this.weixinMessageDao.blank(WeixinShortVideoMessage.class);

		msg.setFromUserName(fromUserName);
		msg.setMediaId(mediaId);
		msg.setMsgId(msgId);
		msg.setMsgType("shortvideo");
		msg.setOriginTime(originTime);
		msg.setThumbMediaId(thumbMediaId);
		msg.setToUserName(toUserName);

		triggerEvent(msg = this.weixinMessageDao.save(msg));

		return msg;
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.weixin.service.WeixinMessageService#createLocationMessage(java.lang.String, java.lang.String, java.lang.String, java.util.Date, double, double, int, java.lang.String)
	 */
	@Override
	public WeixinLocationMessage storeLocationMessage(String msgId, String fromUserName, String toUserName,
			Date originTime, double locationX, double locationY, int scale, String label)
	{
		WeixinLocationMessage msg = this.weixinMessageDao.blank(WeixinLocationMessage.class);

		msg.setFromUserName(fromUserName);
		msg.setLabel(label);
		msg.setLocationX(locationX);
		msg.setLocationY(locationY);
		msg.setMsgId(msgId);
		msg.setMsgType("location");
		msg.setOriginTime(originTime);
		msg.setScale(scale);
		msg.setToUserName(toUserName);

		triggerEvent(msg = this.weixinMessageDao.save(msg));

		return msg;
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.weixin.service.WeixinMessageService#createLinkMessage(java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public WeixinLinkMessage storeLinkMessage(String msgId, String fromUserName, String toUserName, Date originTime,
			String title, String description, String url)
	{
		WeixinLinkMessage msg = this.weixinMessageDao.blank(WeixinLinkMessage.class);

		msg.setDescription(description);
		msg.setFromUserName(fromUserName);
		msg.setMsgId(msgId);
		msg.setMsgType("link");
		msg.setOriginTime(originTime);
		msg.setTitle(title);
		msg.setToUserName(toUserName);
		msg.setUrl(url);

		triggerEvent(msg = this.weixinMessageDao.save(msg));

		return msg;
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.weixin.service.WeixinMessageService#updateMedia(java.lang.String, java.lang.String, java.lang.String, byte[])
	 */
	@Override
	public void updateMedia(String msgId, String name, String type, byte[] content)
	{
		logger.trace("更新media内容……");

		WeixinMessage msg = this.weixinMessageDao.findOne(msgId);

		if (msg != null)
		{
			if (setProperty(msg, join(name, "Content"), content))
			{
				msg = this.weixinMessageDao.update(msg);

				if (setProperty(msg, join(name, "Type"), type))
					msg = this.weixinMessageDao.update(msg);

				else
					logger.error("media类型赋值失败");
			}

			else
				logger.error("media内容赋值失败");
		}

		else
			logger.warn("没有找到指定的微信消息记录");
	}

}
