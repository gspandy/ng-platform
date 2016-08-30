/*
 * Project:    waa新一代框架的基础模块中的公共工具库
 * 
 * FileName:   ClassUtils.java
 * CreateTime: 2015-11-20 10:13:18
 */
package cc.waa.ng.util.lang;

import static cc.waa.ng.util.lang.CollectionUtils.newInstance;
import static org.apache.commons.lang3.ArrayUtils.contains;
import static org.apache.commons.lang3.StringUtils.endsWith;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.join;
import static org.apache.commons.lang3.StringUtils.lastIndexOf;
import static org.apache.commons.lang3.StringUtils.removeEnd;
import static org.apache.commons.lang3.StringUtils.replace;
import static org.apache.commons.lang3.StringUtils.startsWith;
import static org.apache.commons.lang3.StringUtils.substring;
import static org.slf4j.LoggerFactory.getLogger;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Field;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

/**
 * 对类操作的工具库
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class ClassUtils
{

	private static final Logger logger = getLogger(ClassUtils.class);

	private static final FileFilter fileFilter = new FileFilter()
	{

		@Override
		public boolean accept(File file)
		{
			return file.isDirectory() || endsWith(file.getName(), ".class");
		}

	};

	/**
	 * 过滤是否需要该类
	 * 
	 * @param pack		类所在的包名
	 * @param name		类名（不是全名）
	 * @param filter	自定义的过滤器
	 * @return			异常或者没通过过滤器，返回null
	 */
	private static Class<?> filterClass(String pack, String name,
										ClassFilter filter)
	{
		try
		{
			Class<?> clazz = Class.forName(join(pack, ".", name));

			if (filter == null || filter.accept(pack, clazz))
				return clazz;
		}
		catch (Throwable t)
		{
			logger.error("没有找到指定的类{}，跳过", join(pack, ".", name), t);
		}

		return null;
	}

	private static String groupPack(String pack, String name)
	{
		if (isEmpty(pack))
			return name;
		else
			return join(pack, ".", name);
	}

	private static void seekClassAsFile(URL url, String pack, ClassFilter filter, Set<Class<?>> buf)
	{
		try
		{
			File path = new File(url.toURI());
			String name;
			Class<?> clazz;

			for (File f : path.listFiles(fileFilter))
			{
				name = f.getName();

				if (f.isDirectory())
					scanPackage(groupPack(pack, name), filter, buf);

				else
				{
					name = removeEnd(name, ".class");

					if ((clazz = filterClass(pack, name, filter)) != null)
						buf.add(clazz);
				}
			}
		}
		catch (Exception e)
		{
			logger.error("以file协议查找类出错", e);
		}
	}

	private static void seekClassAsJar(URL url, String pack, ClassFilter filter, Set<Class<?>> buf)
	{
		try
		{
			JarURLConnection conn = (JarURLConnection) url.openConnection();
			JarFile jarFile = conn.getJarFile();
			Enumeration<JarEntry> entries = jarFile.entries();
			String packDir = replace(pack, ".", "/");

			while (entries.hasMoreElements())
			{
				JarEntry entry = entries.nextElement();
				String name = removeEnd(entry.getName(), "/");

				if (entry.isDirectory() || !startsWith(name, packDir) || !endsWith(name, ".class"))
					continue;

				name = removeEnd(name, ".class");
				int idx = lastIndexOf(name, "/");

				String newPack = replace(substring(name, 0, idx), "/", "."),
					   newName = substring(name, idx + 1);

				Class<?> clazz = filterClass(newPack, newName, filter);

				if (clazz != null)
					buf.add(clazz);
			}
		}
		catch (Exception e)
		{
			logger.error("以jar协议查找类出错", e);
		}
	}

	private static void scanPackage(String pack, ClassFilter filter, Set<Class<?>> buf)
	{
		ClassLoader loader = Thread.currentThread().getContextClassLoader();

		String resourceName = replace(pack, ".", "/"), protocol;

		try
		{
			Enumeration<URL> urls = loader.getResources(resourceName);

			while (urls.hasMoreElements())
			{
				logger.trace("检查循环次数");

				URL url = urls.nextElement();

				if (StringUtils.equals(protocol = url.getProtocol(), "file"))
					seekClassAsFile(url, pack, filter, buf);

				else if (StringUtils.equals(protocol, "jar"))
					seekClassAsJar(url, pack, filter, buf);
			}
		}
		catch (Exception e)
		{
			logger.error("搜索类时出现问题", e);
		}
	}

	/**
	 * 从指定package中查找类
	 * 
	 * @param pack
	 * @param filter
	 * @return
	 */
	public static Set<Class<?>> scanPackage(String pack, ClassFilter filter)
	{
		Set<Class<?>> buf = new HashSet<Class<?>>();

		scanPackage(pack, filter, buf);

		return buf;
	}

	/**
	 * 获取类型在运行时能用到的Field（包括父类的Field），同名Field以子类为准
	 * 
	 * @param type
	 * @param colType
	 * @param skips
	 * @return
	 */
	public static <T extends Collection<Field>> T getRuntimeField(Class<?> type, Class<T> colType, String... skips)
	{
		try
		{
			T fields = newInstance(colType);
			Set<String> names = new HashSet<String>();
			Class<?> op = type;

			while (op != Object.class)
			{
				for (Field field : op.getDeclaredFields())
				{
					String name = field.getName();

					if (names.contains(name) || contains(skips, name) || startsWith(name, "_persistence_"))
						continue;

					fields.add(field);
					names.add(name);
				}

				op = op.getSuperclass();
			}

			return fields;
		}
		catch (Exception e)
		{
			logger.error("获取Field出错", e);

			return null;
		}
	}

	/**
	 * Comparable的类型转换，解决含泛型的类型问题
	 * 
	 * @param type
	 * @param itemType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <E, T extends Comparable<E>> Class<T> castComparable(Class<?> type, Class<E> itemType)
	{
		return (Class<T>) type;
	}

}
