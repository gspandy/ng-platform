/*
 * Project:    waa新一代框架的基础模块中的公共工具库
 * 
 * FileName:   DateUtils.java
 * CreateTime: 2016-03-31 18:41:21
 */
package cc.waa.ng.util.lang;

import java.util.Date;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class DateUtils
{

	private static final ThreadLocal<Date> dateHolderThread = new ThreadLocal<Date>();

	/**
	 * 获取当前线程的时间（并非线程开始执行的时间，只是控制同一线程获得的时间一致）
	 * 
	 * @return
	 */
	public static Date getThreadDate()
	{
		Date date;

		if ((date = dateHolderThread.get()) == null)
			dateHolderThread.set(date = new Date());

		return date;
	}

	public static Date getTransactionDate()
	{
		return getThreadDate();
	}

}
