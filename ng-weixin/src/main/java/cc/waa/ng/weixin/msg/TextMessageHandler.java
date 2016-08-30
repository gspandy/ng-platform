/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   TextMessageHandler.java
 * CreateTime: 2016-06-20 15:45:37
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cc.waa.ng.weixin.service.WeixinMessageService;

/**
 * 文字信息处理器
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@Component
public class TextMessageHandler
	implements MessageHandler
{

	private static final Logger logger = getLogger(TextMessageHandler.class);



	@Autowired
	private WeixinMessageService service;

	@Resource(name = "weixinMessageHandlerMap")
	private Map<String, MessageHandler> handlers;

	@PostConstruct
	public void init()
	{
		this.handlers.put("text", this);

		logger.trace("cc.waa.ng.weixin.msg.TextMessageHandler已成功注入到weixinMessageHandlerMap");
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.weixin.msg.MessageHandler#handleMessageXml(org.dom4j.Document)
	 */
	@Override
	public String handleMessageXml(Document document)
	{
		Element root = document.getRootElement();

		Date originTime		= new Date(parseLong(root.elementText("CreateTime")) * 1000);
		String toUserName	= root.elementText("ToUserName"),
			   fromUserName	= root.elementText("FromUserName"),
			   msgId		= root.elementText("MsgId"),
			   content		= root.elementText("Content");

		this.service.storeTextMessage(msgId, fromUserName, toUserName,
									   originTime, content);

		logger.trace("成功保存一条文字消息");

		return null;
	}

}
