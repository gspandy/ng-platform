/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   AccessTokenResult.java
 * CreateTime: 2016-06-30 10:21:02
 */
package cc.waa.ng.weixin.json;

import com.google.gson.annotations.SerializedName;

/**
 * 接口（获取access token）的返回结果
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class AccessTokenResult
	extends CommonResult
{

	@SerializedName("access_token")
	private String accessToken;

	@SerializedName("expires_in")
	private long expiresIn;

	public String getAccessToken()
	{
		return this.accessToken;
	}

	public long getExpiresIn()
	{
		return this.expiresIn;
	}

}
