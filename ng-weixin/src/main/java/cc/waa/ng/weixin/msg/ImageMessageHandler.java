/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   ImageMessageHandler.java
 * CreateTime: 2016-06-21 11:57:17
 */
package cc.waa.ng.weixin.msg;

import static java.lang.Long.parseLong;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import cc.waa.ng.weixin.obj.WeixinMessage;

/**
 * 图片信息处理器
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@Component
public class ImageMessageHandler
	extends AbstractMessageHandler
	implements MessageHandler
{

	private static final Logger logger = getLogger(ImageMessageHandler.class);



	@Resource(name = "weixinMessageHandlerMap")
	private Map<String, MessageHandler> handlers;

	@PostConstruct
	public void init()
	{
		this.handlers.put("image", this);

		logger.trace("cc.waa.ng.weixin.msg.ImageMessageHandler已成功注入到weixinMessageHandlerMap");
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.weixin.msg.MessageHandler#handleMessageXml(org.dom4j.Document)
	 */
	@Override
	public String handleMessageXml(Document document)
	{
		WeixinMessage msg;

		Element root = document.getRootElement();

		Date originTime		= new Date(parseLong(root.elementText("CreateTime")) * 1000);
		String toUserName	= root.elementText("ToUserName"),
			   fromUserName	= root.elementText("FromUserName"),
			   msgId		= root.elementText("MsgId"),
			   picUrl		= root.elementText("PicUrl"),
			   mediaId		= root.elementText("MediaId");

		msg = this.service.storeImageMessage(msgId, fromUserName, toUserName,
											 originTime, picUrl, mediaId);

		fillMediaContent(msg, mediaId, "media");

		logger.trace("成功保存一条图片消息");

		return null;
	}

}
