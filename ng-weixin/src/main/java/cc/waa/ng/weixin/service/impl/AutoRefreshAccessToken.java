/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   AutoRefreshAccessToken.java
 * CreateTime: 2016-07-01 21:23:22
 */
package cc.waa.ng.weixin.service.impl;

import static org.apache.commons.io.IOUtils.closeQuietly;
import static org.apache.commons.lang3.StringUtils.join;
import static org.slf4j.LoggerFactory.getLogger;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;

import com.google.gson.Gson;

import cc.waa.ng.weixin.AccessTokenStore;
import cc.waa.ng.weixin.WeixinClientException;
import cc.waa.ng.weixin.json.AccessTokenResult;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class AutoRefreshAccessToken
	implements MethodInterceptor
{

	private static final Logger logger = getLogger(AutoRefreshAccessToken.class);



	private String appApiPrefix;	// https://api.weixin.qq.com/cgi-bin

	public void setAppApiPrefix(String appApiPrefix)
	{
		this.appApiPrefix = appApiPrefix;
	}

	private String appId;		// wxbb09cf275682772f

	public void setAppId(String appId)
	{
		this.appId = appId;
	}

	private String appSecret;	// 790425249ade247f987cbbc218f72439

	public void setAppSecret(String appSecret)
	{
		this.appSecret = appSecret;
	}

	private AccessTokenStore tokenStore;

	public void setTokenStore(AccessTokenStore tokenStore)
	{
		this.tokenStore = tokenStore;
	}

	/**
	 * 刷新access_token
	 * 
	 * @throws IOException
	 */
	private void refreshAccessToken()
		throws IOException
	{
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		Reader in = null;

		try
		{
			HttpGet get = new HttpGet(join(this.appApiPrefix, "/token?grant_type=client_credential&appid=", this.appId, "&secret=", this.appSecret));

			httpClient = HttpClients.createDefault();
			response = httpClient.execute(get);

			in = new InputStreamReader(response.getEntity().getContent(), "UTF-8");

			AccessTokenResult tr = new Gson().fromJson(in, AccessTokenResult.class);

			this.tokenStore.saveAccessToken(tr.getAccessToken());

			logger.info("成功刷新access_token");
		}
		finally
		{
			closeQuietly(in, response, httpClient);
		}
	}

	/* (non-Javadoc)
	 * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
	 */
	@Override
	public Object invoke(MethodInvocation invocation)
		throws Throwable
	{
		logger.trace("当前正准备进入方法{}", invocation.getMethod().getName());

		if (!this.tokenStore.isExists())	// 如果一开始不存在access_token的话，先初始化
		{
			refreshAccessToken();

			logger.info("access_token初始化成功");
		}

		try
		{
			return invocation.proceed();
		}
		catch (WeixinClientException e)
		{
			if (e.getCode() == 42001l)
			{
				logger.trace("access_token已过期，需要重新请求");

				refreshAccessToken();

				return invocation.proceed();
			}

			else
				throw e;
		}
	}

}
