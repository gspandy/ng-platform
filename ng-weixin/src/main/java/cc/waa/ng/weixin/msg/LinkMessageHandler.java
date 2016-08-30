/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   LinkMessageHandler.java
 * CreateTime: 2016-06-24 11:18:47
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
 * 链接消息处理器
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@Component
public class LinkMessageHandler
	implements MessageHandler
{

	private static final Logger logger = getLogger(LinkMessageHandler.class);



	@Autowired
	private WeixinMessageService service;

	@Resource(name = "weixinMessageHandlerMap")
	private Map<String, MessageHandler> handlers;

	@PostConstruct
	public void init()
	{
		this.handlers.put("link", this);

		logger.trace("cc.waa.ng.weixin.msg.LinkMessageHandler已成功注入到weixinMessageHandlerMap");
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.weixin.msg.MessageHandler#handleMessageXml(org.dom4j.Document)
	 */
	@Override
	public String handleMessageXml(Document document)
	{
		Element root = document.getRootElement();

		Date   originTime = new Date(parseLong(root.elementText("CreateTime")) * 1000);
		String toUserName = root.elementText("ToUserName"),
			 fromUserName = root.elementText("FromUserName"),
					msgId = root.elementText("MsgId"),
					title = root.elementText("Title"),
			  description = root.elementText("Description"),
					  url = root.elementText("Url");

		this.service.storeLinkMessage(msgId, fromUserName, toUserName,
									   originTime, title, description, url);

		logger.trace("成功保存一条链接消息");

		return null;
	}

}
