/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   WeixinClientException.java
 * CreateTime: 2016-07-01 19:17:35
 */
package cc.waa.ng.weixin;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class WeixinClientException
	extends RuntimeException
{

	/** serialVersionUID */
	private static final long serialVersionUID = -8908749086207211973L;



	private long code;

	public long getCode()
	{
		return this.code;
	}

	public WeixinClientException(long code, String message)
	{
		super(message);

		this.code = code;
	}

}
