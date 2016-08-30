/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   MessageHandler.java
 * CreateTime: 2016-06-20 15:33:03
 */
package cc.waa.ng.weixin.msg;

import org.dom4j.Document;

/**
 * 消息处理接口
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public interface MessageHandler
{

	/**
	 * 处理消息的xml数据
	 * 
	 * @param document
	 * @return
	 */
	String handleMessageXml(Document document);

}
