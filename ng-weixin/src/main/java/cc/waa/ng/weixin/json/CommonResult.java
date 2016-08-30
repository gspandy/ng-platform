/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   CommonResult.java
 * CreateTime: 2016-06-30 10:15:29
 */
package cc.waa.ng.weixin.json;

import com.google.gson.annotations.SerializedName;

/**
 * 调用微信接口的返回结果（json字符串，公共基础部分的属性）
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class CommonResult
{

	@SerializedName("errcode")
	private long code;

	@SerializedName("errmsg")
	private String message;

	public long getCode()
	{
		return this.code;
	}

	public String getMessage()
	{
		return this.message;
	}

	/**
	 * 封装操作是否成功的判断
	 * 
	 * @return
	 */
	public boolean isSuccess()
	{
		return this.code == 0;
	}

}
