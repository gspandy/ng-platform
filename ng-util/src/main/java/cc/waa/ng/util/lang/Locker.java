/*
 * Project:    waa新一代框架的基础模块中的公共工具库
 * 
 * FileName:   Locker.java
 * CreateTime: 2016-05-18 09:35:42
 */
package cc.waa.ng.util.lang;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 实现针对同一对象在多线程下的同步锁
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class Locker
{

	/** 记录对象id对应的锁 */
	private static final Map<String, AtomicInteger> locks = new HashMap<String, AtomicInteger>();

	/**
	 * 锁定对象
	 * 
	 * @param key		对象的id（主键）
	 * @return
	 */
	public static synchronized Object lock(String key)
	{
		AtomicInteger lock = locks.get(key);

		if (lock == null)
			locks.put(key, lock = new AtomicInteger(1));
		else
			lock.incrementAndGet();

		return lock;
	}

	/**
	 * 解锁对象，在操作完成后执行
	 * 
	 * 注意：解锁操作必须在锁定的范围内进行，例如：
	 * synchronized (lock("1"))
	 * {
	 * 		....
	 * 
	 * 		release("1");
	 * }
	 * 
	 * @param id		对象的id（主键）
	 */
	public static void release(String id)
	{
		AtomicInteger lock = locks.get(id);

		if (lock == null)
			return;

		if (lock.decrementAndGet() == 0)	// 引用数降至0时，从map中删除
			locks.remove(id);
	}

}
