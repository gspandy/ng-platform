/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   DefaultWeixinClient.java
 * CreateTime: 2016-07-01 12:04:37
 */
package cc.waa.ng.weixin.service.impl;

import static org.apache.commons.io.IOUtils.closeQuietly;
import static org.apache.commons.lang3.StringUtils.join;
import static org.slf4j.LoggerFactory.getLogger;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;

import com.google.gson.Gson;

import cc.waa.ng.weixin.AccessTokenStore;
import cc.waa.ng.weixin.WeixinClient;
import cc.waa.ng.weixin.WeixinClientException;
import cc.waa.ng.weixin.json.CallbackIpResult;
import cc.waa.ng.weixin.json.CommonResult;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class DefaultWeixinClient
	implements WeixinClient
{

	private static final Logger logger = getLogger(DefaultWeixinClient.class);



	private String appApiPrefix;

	public void setAppApiPrefix(String appApiPrefix)
	{
		this.appApiPrefix = appApiPrefix;
	}

	private AccessTokenStore tokenStore;

	public void setTokenStore(AccessTokenStore tokenStore)
	{
		this.tokenStore = tokenStore;
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.weixin.WeixinClient#getCallbackIp()
	 */
	@Override
	public List<String> getCallbackIp()
	{
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		Reader in = null;

		try
		{
			HttpGet get = new HttpGet(join(this.appApiPrefix, "/getcallbackip?access_token=", this.tokenStore.readAccessToken()));

			httpClient = HttpClients.createDefault();
			response = httpClient.execute(get);

			in = new InputStreamReader(response.getEntity().getContent(), "UTF-8");

			CallbackIpResult cir = new Gson().fromJson(in, CallbackIpResult.class);

			if (!cir.isSuccess())
				System.out.println(cir.getMessage());

			else
				throw new WeixinClientException(cir.getCode(), cir.getMessage());

			return cir.getIpList();
		}
		catch (IOException e)
		{
			logger.error("接口调用失败", e);

			return Collections.emptyList();
		}
		finally
		{
			closeQuietly(in, response, httpClient);
		}
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.weixin.WeixinClient#pullMedia(java.lang.String, java.io.OutputStream)
	 */
	@Override
	public String pullMedia(String mediaId, OutputStream os)
	{
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		Reader in = null;

		try
		{
			HttpGet get = new HttpGet(join(this.appApiPrefix, "/media/get?access_token=", this.tokenStore.readAccessToken(), "&media_id=", mediaId));

			httpClient = HttpClients.createDefault();
			response = httpClient.execute(get);

			String contentType = response.getFirstHeader("Content-Type").getValue();

			if (StringUtils.equals(contentType, "text/plain"))
			{
				in = new InputStreamReader(response.getEntity().getContent(), "UTF-8");

				CommonResult cr = new Gson().fromJson(in, CommonResult.class);

				throw new WeixinClientException(cr.getCode(), cr.getMessage());
			}

			else
			{
				IOUtils.copy(response.getEntity().getContent(), os);

				return contentType;
			}
		}
		catch (IOException e)
		{
			logger.error("接口调用失败", e);

			return null;
		}
		finally
		{
			closeQuietly(in, response, httpClient);
		}
	}

}
