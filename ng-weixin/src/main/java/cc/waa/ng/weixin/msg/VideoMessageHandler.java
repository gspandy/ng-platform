/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   VideoMessageHandler.java
 * CreateTime: 2016-06-21 14:59:16
 */
package cc.waa.ng.weixin.msg;

import static java.lang.Long.parseLong;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import cc.waa.ng.weixin.obj.WeixinMessage;

/**
 * 视频/小视频信息处理器
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@Component
public class VideoMessageHandler
	extends AbstractMessageHandler
	implements MessageHandler
{

	private static final Logger logger = getLogger(VideoMessageHandler.class);



	@Resource(name = "weixinMessageHandlerMap")
	private Map<String, MessageHandler> handlers;

	@PostConstruct
	public void init()
	{
		this.handlers.put("video", this);
		this.handlers.put("shortvideo", this);

		logger.trace("cc.waa.ng.weixin.msg.VideoMessageHandler已成功注入到weixinMessageHandlerMap");
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.weixin.msg.MessageHandler#handleMessageXml(org.dom4j.Document)
	 */
	@Override
	public String handleMessageXml(Document document)
	{
		WeixinMessage msg = null;

		Element root = document.getRootElement();

		Date   originTime = new Date(parseLong(root.elementText("CreateTime")) * 1000);
		String toUserName = root.elementText("ToUserName"),
			 fromUserName = root.elementText("FromUserName"),
					msgId = root.elementText("MsgId"),
				  mediaId = root.elementText("MediaId"),
			 thumbMediaId = root.elementText("ThumbMediaId"),
			 	  msgType = root.elementText("MsgType");

		if (StringUtils.equals(msgType, "video"))
			msg = this.service.storeVideoMessage(msgId, fromUserName, toUserName,
												 originTime, mediaId, thumbMediaId);

		else if (StringUtils.equals(msgType, "shortvideo"))
			msg = this.service.storeShortVideoMessage(msgId, fromUserName, toUserName,
													  originTime, mediaId, thumbMediaId);

		if (msg != null)
		{
			fillMediaContent(msg, mediaId, "media");
			fillMediaContent(msg, thumbMediaId, "thumbMedia");
		}

		return null;
	}

}
