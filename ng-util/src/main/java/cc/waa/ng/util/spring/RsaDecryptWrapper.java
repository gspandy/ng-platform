/*
 * Project:    waa新一代框架的基础模块中的公共工具库
 * 
 * FileName:   RsaDecryptWrapper.java
 * CreateTime: 2016-06-14 20:36:39
 */
package cc.waa.ng.util.spring;

import static org.apache.commons.codec.binary.Hex.decodeHex;
import static org.slf4j.LoggerFactory.getLogger;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;

import cc.waa.ng.util.lang.CryptUtils;

/**
 * 对密码做RSA解码，完成解密后才交给实际的PasswordEncoder验证
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class RsaDecryptWrapper
	implements PasswordEncoder
{

	private static final Logger logger = getLogger(RsaDecryptWrapper.class);



	private PasswordEncoder target;

	public void setTarget(PasswordEncoder nested)
	{
		this.target = nested;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.crypto.password.PasswordEncoder#encode(java.lang.CharSequence)
	 */
	@Override
	public String encode(CharSequence rawPassword)
	{
		return this.target.encode(rawPassword);
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.crypto.password.PasswordEncoder#matches(java.lang.CharSequence, java.lang.String)
	 */
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword)
	{
		try
		{
			byte[] raw = decodeHex(String.valueOf(rawPassword).toCharArray());
			raw = CryptUtils.rsaDecryptByInternalPrivateKey(raw);

			rawPassword = StringUtils.reverse(new String(raw));
		}
		catch (Exception e)
		{
			logger.error("密码解密错误", e);
		}

		return this.target.matches(rawPassword, encodedPassword);
	}

}
