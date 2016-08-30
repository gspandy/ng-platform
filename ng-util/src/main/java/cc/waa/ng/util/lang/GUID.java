/*
 * Project:    waa新一代框架的基础模块中的公共工具库
 * 
 * FileName:   GUID.java
 * CreateTime: 2015-09-24 18:02:12
 */
package cc.waa.ng.util.lang;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.DataOutput;
import java.math.BigInteger;
import java.rmi.server.UID;
import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public final class GUID
{

	private static final Logger logger = getLogger(GUID.class);

	/*
	 * 以36进制输出时的固定长度
	 */
	public static final int FIX_LEN_36 = 25;

	/**
	 * 从guid中提取相应的年份月份
	 * 
	 * @param guid
	 * @return
	 */
	public static int pickYearMonth(String guid)
	{
		BigInteger i = new BigInteger(guid, 36);
		i = i.divide(new BigInteger("10000", 16));
		i = i.mod(new BigInteger("8000000000000000", 16));

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(i.longValue());

		return cal.get(Calendar.YEAR) * 100 + cal.get(Calendar.MONTH) + 1;
	}



	private final UID m_uid;

	private String tmp_string;
	private String tmp_unsign;

	private final DataOutput guidOutput = new DataOutput()
	{

		/*
		 * 一个存放32位十六进制的字符串
		 */
		private StringBuffer m_buf;

		private void writeBuffer(String s)
		{
			if (this.m_buf == null)
			{
				this.m_buf = new StringBuffer(32);

				/*
				 * 预留十六进制的头四位作扩展
				 * 
				 * 　　当为0x0000时，为普通GUID模式，一个32位十六进制的无符号
				 * 数字的字符串形式
				 * 　　当为0x10E5时，为首位非0模式
				 * 　　当为0xA8EA时，为首位非数字模式
				 */
				this.m_buf.append("10E5");
			}

			this.m_buf.append(s);
		}

		/* (non-Javadoc)
		 * @see java.io.DataOutput#writeShort(int)
		 */
		@Override
		public void writeShort(int v)
		{
			v = ((v >= 0) ? v | Short.MIN_VALUE : v & Short.MAX_VALUE);
			v &= 0x0000FFFF;

			String s = Integer.toHexString(v);
			s = StringUtils.leftPad(s, 4, '0');

			writeBuffer(s);
		}

		/* (non-Javadoc)
		 * @see java.io.DataOutput#writeInt(int)
		 */
		@Override
		public void writeInt(int v)
		{
			v = (v >= 0) ? v | Integer.MIN_VALUE : v & Integer.MAX_VALUE;

			String s = Integer.toHexString(v);
			s = StringUtils.leftPad(s, 8, '0');

			writeBuffer(s);
		}

		/* (non-Javadoc)
		 * @see java.io.DataOutput#writeLong(long)
		 */
		@Override
		public void writeLong(long v)
		{
			v = (v >= 0) ? v | Long.MIN_VALUE : v & Long.MAX_VALUE;

			String s = Long.toHexString(v);
			s = StringUtils.leftPad(s, 16, '0');

			writeBuffer(s);
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
		{
			BigInteger i = new BigInteger(this.m_buf.toString(), 16);

			String s = i.toString(36);
			s = StringUtils.leftPad(s, FIX_LEN_36, '0');

			return s;
		}


		// ---------------------------------------------------------------------
		// ---------------------------------------------------- 不需实现的方法 -
		// ---------------------------------------------------------------------

		@Override public void write(int b) {}
		@Override public void write(byte[] b) {}
		@Override public void write(byte[] b, int off, int len) {}
		@Override public void writeBoolean(boolean v) {}
		@Override public void writeByte(int v) {}
		@Override public void writeChar(int v) {}
		@Override public void writeFloat(float v) {}
		@Override public void writeDouble(double v) {}
		@Override public void writeBytes(String s) {}
		@Override public void writeChars(String s) {}
		@Override public void writeUTF(String s) {}

	};


	public GUID()
	{
		super();

		this.m_uid = new UID();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		if (this.tmp_string == null)
			this.tmp_string = this.m_uid.toString();

		return this.tmp_string;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null || !(obj instanceof GUID))
			return false;

		return this.m_uid.equals(((GUID) obj).m_uid);
	}

	/**
	 * 将GUID输出为无符号的25位36进制的字符串
	 * 
	 * @return
	 */
	public String toUnsign()
	{
		if (this.tmp_unsign == null)
		{
			try
			{
				this.m_uid.write(this.guidOutput);
			}
			catch (Exception e)
			{
				logger.error("fail to generate the GUID string.", e);
			}

			this.tmp_unsign = this.guidOutput.toString();
		}

		return this.tmp_unsign;
	}

}
