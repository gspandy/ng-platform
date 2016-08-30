/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   AccessTokenFileStore.java
 * CreateTime: 2016-07-01 18:38:17
 */
package cc.waa.ng.weixin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.apache.commons.io.IOUtils;

/**
 * 用文件存取AccessToken
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class AccessTokenFileStore
	implements AccessTokenStore
{

	private File tokenFile;

	public void setTokenFile(File tokenFile)
	{
		this.tokenFile = tokenFile;
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.weixin.AccessTokenStore#isExists()
	 */
	@Override
	public boolean isExists()
	{
		return this.tokenFile.exists();
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.weixin.AccessTokenStore#readAccessToken()
	 */
	@Override
	public String readAccessToken()
		throws IOException
	{
		return IOUtils.toString(new FileInputStream(this.tokenFile), "UTF-8");
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.weixin.AccessTokenStore#saveAccessToken(java.lang.String)
	 */
	@Override
	public void saveAccessToken(String token)
		throws IOException
	{
		Writer out = null;

		try
		{
			IOUtils.write(token, out = new FileWriter(this.tokenFile));
		}
		finally
		{
			IOUtils.closeQuietly(out);
		}
	}

}
