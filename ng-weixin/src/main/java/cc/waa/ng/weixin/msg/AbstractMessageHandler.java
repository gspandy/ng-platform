/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   AbstractMessageHandler.java
 * CreateTime: 2016-07-01 11:30:12
 */
package cc.waa.ng.weixin.msg;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cc.waa.ng.weixin.WeixinClient;
import cc.waa.ng.weixin.obj.WeixinMessage;
import cc.waa.ng.weixin.service.WeixinMessageService;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public abstract class AbstractMessageHandler
{

	@Autowired
	protected WeixinMessageService service;

	@Autowired
	@Qualifier("weixinClientProxied")
	private WeixinClient client;

	@Autowired
	@Qualifier("weixinTaskExecutor")
	private Executor executor;

	private Runnable newMediaAction(String messageId, String id, String name)
	{
		FillMediaAction action = new FillMediaAction(messageId, id, name);

		action.setClient(this.client);
		action.setService(this.service);

		return action;
	}

	/**
	 * @param msg
	 * @param id
	 * @param name
	 */
	protected void fillMediaContent(WeixinMessage msg, String id, String name)
	{
		this.executor.execute(newMediaAction(msg.getId(), id, name));
	}

}
