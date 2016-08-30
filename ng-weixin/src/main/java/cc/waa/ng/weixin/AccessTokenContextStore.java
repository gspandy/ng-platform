/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   AccessTokenContextStore.java
 * CreateTime: 2016-07-02 00:53:32
 */
package cc.waa.ng.weixin;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.WebApplicationContext;

/**
 * 用户ServletContext的attribute来存取AccessToken
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class AccessTokenContextStore
	implements AccessTokenStore, ApplicationContextAware
{

	private String storeKey;

	public void setStoreKey(String storeKey)
	{
		this.storeKey = storeKey;
	}

	private ServletContext context;

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
	{
		if (!(applicationContext instanceof WebApplicationContext))
			return;

		this.context = ((WebApplicationContext) applicationContext).getServletContext();
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.weixin.AccessTokenStore#isExists()
	 */
	@Override
	public boolean isExists()
	{
		return this.context.getAttribute(this.storeKey) != null;
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.weixin.AccessTokenStore#readAccessToken()
	 */
	@Override
	public String readAccessToken()
	{
		return (String) this.context.getAttribute(this.storeKey);
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.weixin.AccessTokenStore#saveAccessToken(java.lang.String)
	 */
	@Override
	public void saveAccessToken(String token)
	{
		this.context.setAttribute(this.storeKey, token);
	}

}
