/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   EventHandler.java
 * CreateTime: 2016-06-29 10:02:00
 */
package cc.waa.ng.weixin.evt;

import org.dom4j.Document;

/**
 * 事件处理接口
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public interface EventHandler
{

	/**
	 * 处理时间的xml数据
	 * 
	 * @param document
	 * @return
	 */
	String handleEventXml(Document document);

}
