/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   CallbackIpResult.java
 * CreateTime: 2016-06-30 10:24:19
 */
package cc.waa.ng.weixin.json;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * 接口（获取微信服务器IP地址）的返回结果
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class CallbackIpResult
	extends CommonResult
{

	@SerializedName("ip_list")
	private List<String> ipList;

	public List<String> getIpList()
	{
		return this.ipList;
	}

}
