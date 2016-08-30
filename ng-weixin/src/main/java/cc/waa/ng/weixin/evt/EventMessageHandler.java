/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   EventMessageHandler.java
 * CreateTime: 2016-06-28 14:42:35
 */
package cc.waa.ng.weixin.evt;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.dom4j.Document;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import cc.waa.ng.weixin.msg.MessageHandler;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@Component
public class EventMessageHandler
	implements MessageHandler
{

	private static final Logger logger = getLogger(EventMessageHandler.class);



	@Resource(name = "weixinMessageHandlerMap")
	private Map<String, MessageHandler> messageHandlers;

	@Resource(name = "weixinEventHandlerMap")
	private Map<String, EventHandler> eventHandlers;

	@PostConstruct
	public void init()
	{
		this.messageHandlers.put("event", this);

		logger.trace("cc.waa.ng.weixin.evn.EventMessageHandler已成功注入到weixinMessageHandlerMap");
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.weixin.msg.MessageHandler#handleMessageXml(org.dom4j.Document)
	 */
	@Override
	public String handleMessageXml(Document document)
	{
		logger.trace("有事件到……");

		EventHandler handler;

		String event = document.getRootElement().elementText("Event");

		if (this.eventHandlers.containsKey(event))
		{
			if ((handler = this.eventHandlers.get(event)) != null)
				return handler.handleEventXml(document);
			else
				throw new RuntimeException("异常：handler为null");
		}

		else
			logger.warn("没有对该事件（{}）作出响应的代码，{}", event, document.asXML());

		return null;
	}

}
