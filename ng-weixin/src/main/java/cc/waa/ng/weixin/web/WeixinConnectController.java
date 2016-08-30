/*
 * Project:    ng-weixin
 * 
 * FileName:   WeixinConnectController.java
 * CreateTime: 2016-06-16 17:07:54
 */
package cc.waa.ng.weixin.web;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cc.waa.ng.weixin.msg.MessageHandler;

/**
 * 接收微信消息和事件的接口
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@Controller
public class WeixinConnectController
{

	private static final Logger logger = getLogger(WeixinConnectController.class);



	@Resource(name = "weixinVerifyToken")
	private String verifyToken;

	@Resource(name = "weixinMessageHandlerMap")
	private Map<String, MessageHandler> handlers;

	/**
	 * 验证服务器地址的有效性
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(path = "/weixin/connect", method = RequestMethod.GET)
	public String verify(String signature, String timestamp, String nonce,
						 String echostr)
		throws Exception
	{
		String[] arrCheck = { this.verifyToken, timestamp, nonce };

		Arrays.sort(arrCheck);	// 字典序排序

		String digest = DigestUtils.sha1Hex(StringUtils.join(arrCheck));

		if (StringUtils.equals(digest, signature))
			return echostr;

		else
		{
			logger.error("有效性验证失败，signature={}，timestamp={}，nonce={}，echostr={}", signature, timestamp, nonce, echostr);

			return null;
		}
	}

	/**
	 * 接收微信信息
	 * 
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws DocumentException 
	 */
	@ResponseBody
	@RequestMapping(path = "/weixin/connect", method = RequestMethod.POST)
	public String reviceMessage(HttpServletRequest request)
		throws IOException, DocumentException
	{
		String msgType;
		MessageHandler handler;

		SAXReader reader = new SAXReader();
		Document doc = reader.read(request.getInputStream());
		Element root = doc.getRootElement();

		logger.trace("有消息到……{}", doc.asXML());

		if (this.handlers.containsKey(msgType = root.elementText("MsgType")))
		{
			if ((handler = this.handlers.get(msgType)) != null)
				return handler.handleMessageXml(doc);
			else
				throw new RuntimeException("异常：handler为null");
		}

		else
			logger.warn("没有对该类型（{}）消息作出响应的代码，{}", msgType, doc.asXML());

		return null;
	}

}
