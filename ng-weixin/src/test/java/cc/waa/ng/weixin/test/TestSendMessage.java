/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   TestSendMessage.java
 * CreateTime: 2016-06-27 17:08:12
 */
package cc.waa.ng.weixin.test;

import static org.apache.commons.io.IOUtils.closeQuietly;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.join;
import static org.slf4j.LoggerFactory.getLogger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;

import com.google.gson.Gson;

import cc.waa.ng.weixin.json.AccessTokenResult;
import cc.waa.ng.weixin.json.CallbackIpResult;
import cc.waa.ng.weixin.json.CommonResult;
import cc.waa.ng.weixin.json.MediaUploadResult;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class TestSendMessage
{

	private static final Logger logger = getLogger(TestSendMessage.class);

	private static boolean checkAccessTokenValid(String accessToken)
		throws IOException
	{
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		Reader in = null;

		HttpGet get = new HttpGet(join("https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=", accessToken));

		try
		{
			if (isEmpty(accessToken))
				return false;

			httpClient = HttpClients.createDefault();
			response = httpClient.execute(get);

			in = new InputStreamReader(response.getEntity().getContent(), "UTF-8");

			CallbackIpResult cir = new Gson().fromJson(in, CallbackIpResult.class);

			if (!cir.isSuccess())
				System.out.println(cir.getMessage());

			return cir.isSuccess();
		}
		finally
		{
			closeQuietly(in, response, httpClient);
		}
	}

	private static String refreshAccessToken(File targetFile)
		throws IOException
	{
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		Reader in = null;
		Writer out = null;

		HttpGet get = new HttpGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxbb09cf275682772f&secret=790425249ade247f987cbbc218f72439");
		String token;

		try
		{
			httpClient = HttpClients.createDefault();
			response = httpClient.execute(get);

			in = new InputStreamReader(response.getEntity().getContent(), "UTF-8");

			AccessTokenResult tr = new Gson().fromJson(in, AccessTokenResult.class);

			IOUtils.write(token = tr.getAccessToken(), out = new FileWriter(targetFile));

			logger.info("成功刷新access_token");

			return token;
		}
		finally
		{
			closeQuietly(out, in, response, httpClient);
		}
	}

	protected static String getAccessToken()
		throws IOException
	{
		String token;
		File tokenFile = new File("access_token");

		if (tokenFile.exists())
		{
			token = IOUtils.toString(new FileInputStream(tokenFile), "UTF-8");

			if (!checkAccessTokenValid(token))
			{
				logger.warn("access_token已失效");

				token = refreshAccessToken(tokenFile);
			}

			return token;
		}

		return refreshAccessToken(tokenFile);
	}

	protected static void sendReply(String toUser, String reply)
		throws IOException
	{
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		Reader in = null;

		HttpPost post = new HttpPost(join("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=", getAccessToken()));
		Gson gson = new Gson();

		try
		{
			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("touser", toUser);
			msg.put("msgtype", "text");
			msg.put("text", Collections.singletonMap("content", reply));

			post.setEntity(new StringEntity(gson.toJson(msg), "UTF-8"));

			httpClient = HttpClients.createDefault();
			response = httpClient.execute(post);

			in = new InputStreamReader(response.getEntity().getContent(), "UTF-8");

			System.out.println(gson.fromJson(in, CommonResult.class).isSuccess());
		}
		finally
		{
			closeQuietly(in, response, httpClient);
		}
	}

	protected static void sendImage(String toUser, String imageFile)
		throws IOException
	{
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		Reader in = null;

		HttpPost post = new HttpPost(join("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=", getAccessToken()));

		try
		{
			String mediaId = upload("image", imageFile);

			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("touser", toUser);
			msg.put("msgtype", "image");
			msg.put("image", Collections.singletonMap("media_id", mediaId));

			post.setEntity(new StringEntity(new Gson().toJson(msg), "UTF-8"));

			httpClient = HttpClients.createDefault();
			response = httpClient.execute(post);

			System.out.println(IOUtils.toString(response.getEntity().getContent(), "UTF-8"));
		}
		finally
		{
			closeQuietly(in, response, httpClient);
		}
	}

	protected static String upload(String type, String file)
		throws IOException
	{
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		Reader in = null;

		HttpPost post = new HttpPost(join("https://api.weixin.qq.com/cgi-bin/media/upload?access_token=", getAccessToken(), "&type=", type));

		try
		{
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.addBinaryBody("media", new File(file));

			post.setEntity(builder.build());

			httpClient = HttpClients.createDefault();
			response = httpClient.execute(post);

			in = new InputStreamReader(response.getEntity().getContent(), "UTF-8");

			MediaUploadResult ur = new Gson().fromJson(in, MediaUploadResult.class);

			return ur.getMediaId();
		}
		finally
		{
			closeQuietly(in, response, httpClient);
		}
	}

	protected static void user(String openId)
		throws IOException
	{
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		Reader in = null;

		HttpGet get = new HttpGet(join("https://api.weixin.qq.com/cgi-bin/user/info?access_token=", getAccessToken(), "&openid=", openId));

		try
		{
			httpClient = HttpClients.createDefault();
			response = httpClient.execute(get);

			in = new InputStreamReader(response.getEntity().getContent(), "UTF-8");

			System.out.println(IOUtils.toString(in));
		}
		finally
		{
			closeQuietly(in, response, httpClient);
		}
	}

	protected static void users()
		throws IOException
	{
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		Reader in = null;

		HttpGet get = new HttpGet(join("https://api.weixin.qq.com/cgi-bin/user/get?access_token=", getAccessToken()));

		try
		{
			httpClient = HttpClients.createDefault();
			response = httpClient.execute(get);

			in = new InputStreamReader(response.getEntity().getContent(), "UTF-8");

			System.out.println(IOUtils.toString(in));
		}
		finally
		{
			closeQuietly(in, response, httpClient);
		}
	}

	/**
	 * @param mediaId
	 * @param os
	 * @return
	 * @throws IOException
	 */
	public static String writeMediaContent(String mediaId, OutputStream os)
		throws IOException
	{
		logger.trace("try to load content of media...");

		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;

		HttpGet get = new HttpGet(join("https://api.weixin.qq.com/cgi-bin/media/get?access_token=", getAccessToken(), "&media_id=", mediaId));

		try
		{
			httpClient = HttpClients.createDefault();
			response = httpClient.execute(get);
			StatusLine sl = response.getStatusLine();

			if (sl.getStatusCode() == 200)
			{
				IOUtils.copy(response.getEntity().getContent(), os);

				return response.getFirstHeader("Content-Type").getValue();
			}
			else
			{
				System.out.println(sl.getStatusCode() + ", " + sl.getReasonPhrase());

				return null;
			}
		}
		finally
		{
			closeQuietly(response, httpClient);
		}
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args)
		throws IOException
	{
//		user("opRuwwoVXhQKZ0FMDMdhCMZUS1kM");
//		sendReply("opRuwwoVXhQKZ0FMDMdhCMZUS1kM", "哈哈");
//		sendImage("opRuwwoVXhQKZ0FMDMdhCMZUS1kM", "image.png");
	}

}
