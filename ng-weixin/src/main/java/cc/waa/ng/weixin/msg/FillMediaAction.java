/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   FillMediaAction.java
 * CreateTime: 2016-07-05 17:14:44
 */
package cc.waa.ng.weixin.msg;

import java.io.ByteArrayOutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import cc.waa.ng.weixin.WeixinClient;
import cc.waa.ng.weixin.service.WeixinMessageService;

/**
 * 封装填充微信消息的media相关属性的操作
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class FillMediaAction
	implements Runnable
{

	private String messageId;

	private String mediaId;

	private String mediaName;

	private WeixinMessageService service;

	public void setService(WeixinMessageService service)
	{
		this.service = service;
	}

	private WeixinClient client;

	public void setClient(WeixinClient client)
	{
		this.client = client;
	}

	/**
	 * @param msgId
	 * @param id
	 * @param name
	 */
	public FillMediaAction(String msgId, String id, String name)
	{
		super();

		this.messageId	= msgId;
		this.mediaId	= id;
		this.mediaName	= name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		String type;
		ByteArrayOutputStream os = null;

		try
		{
			type = this.client.pullMedia(this.mediaId, os = new ByteArrayOutputStream());

			if (!StringUtils.isEmpty(type))
			{
				byte[] content = os.toByteArray();

				this.service.updateMedia(this.messageId, this.mediaName, type, content);
			}
		}
		finally
		{
			IOUtils.closeQuietly(os);
		}
	}

}
