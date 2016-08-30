/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   AccessTokenStore.java
 * CreateTime: 2016-07-01 18:33:23
 */
package cc.waa.ng.weixin;

import java.io.IOException;

/**
 * 对AccessToken存取的封装
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public interface AccessTokenStore
{

	/**
	 * 标记是否已经存在AccessToken
	 * 
	 * @return
	 */
	boolean isExists();

	/**
	 * 读取AccessToken
	 * 
	 * @return
	 * @throws IOException
	 */
	String readAccessToken()
		throws IOException;

	/**
	 * 保存AccessToken
	 * 
	 * @param token
	 * @throws IOException
	 */
	void saveAccessToken(String token)
		throws IOException;

}
