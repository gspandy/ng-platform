/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   VoiceMessageHandler.java
 * CreateTime: 2016-06-24 10:28:41
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
 * 语音消息处理器
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@Component
public class VoiceMessageHandler
	extends AbstractMessageHandler
	implements MessageHandler
{

	private static final Logger logger = getLogger(VoiceMessageHandler.class);



	@Resource(name = "weixinMessageHandlerMap")
	private Map<String, MessageHandler> handlers;

	@PostConstruct
	public void init()
	{
		this.handlers.put("voice", this);

		logger.trace("cc.waa.ng.weixin.msg.VoiceMessageHandler已成功注入到weixinMessageHandlerMap");
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
			   mediaId		= root.elementText("MediaId"),
			   format		= root.elementText("Format"),
			   recognition	= root.elementText("Recognition");

		msg = this.service.storeVoiceMessage(msgId, fromUserName, toUserName,
											 originTime, mediaId, format,
											 recognition);

		fillMediaContent(msg, mediaId, "media");

		logger.trace("成功保存一条语音消息");

		return null;
	}

}
