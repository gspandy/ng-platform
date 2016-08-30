/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   TestGson.java
 * CreateTime: 2016-06-27 23:22:58
 */
package cc.waa.ng.weixin.test;

import com.google.gson.Gson;

import cc.waa.ng.weixin.json.CommonResult;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class TestGson
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Gson gson = new Gson();

		CommonResult er = gson.fromJson("{\"errcode\":10001,\"errmsg\":\"未知错误\"}", CommonResult.class);

		System.out.println(er.getCode());
		System.out.println(er.getMessage());
	}

}
