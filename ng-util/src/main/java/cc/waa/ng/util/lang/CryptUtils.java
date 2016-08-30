/*
 * Project:    waa新一代框架的基础模块中的公共工具库
 * 
 * FileName:   CryptUtils.java
 * CreateTime: 2016-06-14 19:05:42
 */
package cc.waa.ng.util.lang;

import static java.security.KeyPairGenerator.getInstance;
import static javax.crypto.Cipher.DECRYPT_MODE;
import static org.apache.commons.io.IOUtils.closeQuietly;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;

import javax.crypto.Cipher;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * 加密/解密工具类
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class CryptUtils
{

	private static final String	KEY_ALGORITHM	= "RSA";

	private static final int	KEY_SIZE		= 1024;

	private static final String	KEY_PATH		= "/rsa_key";

	private static final Provider PROVIDER = new BouncyCastleProvider();

	/**
	 * 获取系统内部（classpath中的ras_key）的密钥对
	 * 
	 * @return
	 * @throws Exception 
	 */
	private static KeyPair getInternalRsaKeyPair()
		throws Exception
	{
		KeyPair keyPair = null;
		ObjectInputStream ois = null;

		try
		{
			InputStream is = CryptUtils.class.getResourceAsStream(KEY_PATH);

			if (is == null)
				throw new RuntimeException("classpath中没有找到key文件{" + KEY_PATH + "}");

			ois = new ObjectInputStream(is);

			keyPair = (KeyPair) ois.readObject();
		}
		finally
		{
			closeQuietly(ois);
		}

		return keyPair;
	}

	public static PublicKey getInternalRsaPublicKey()
		throws Exception
	{
		return getInternalRsaKeyPair().getPublic();
	}

	/**
	 * 生成新的密钥对
	 * 
	 * @return
	 * @throws Exception
	 */
	public static KeyPair rsaKeyPair()
		throws Exception
	{
		KeyPairGenerator keyPairGen = getInstance(KEY_ALGORITHM, PROVIDER);

		keyPairGen.initialize(KEY_SIZE, new SecureRandom());

		return keyPairGen.generateKeyPair();
	}

	/**
	 * 使用系统内部密钥对的私钥解密内容
	 * 
	 * @param raw
	 * @return
	 * @throws Exception
	 */
	public static byte[] rsaDecryptByInternalPrivateKey(byte[] raw)
		throws Exception
	{
		Cipher cipher = Cipher.getInstance(KEY_ALGORITHM, PROVIDER);

		cipher.init(DECRYPT_MODE, getInternalRsaKeyPair().getPrivate());

		int blocksize = cipher.getBlockSize(), i = 0;
		ByteArrayOutputStream out = null;

		try
		{
			out = new ByteArrayOutputStream(64);

			while (raw.length - i * blocksize > 0)
			{
				out.write(cipher.doFinal(raw, i * blocksize, blocksize));

				i++;
			}

			return out.toByteArray();
		}
		finally
		{
			closeQuietly(out);
		}
	}

}
