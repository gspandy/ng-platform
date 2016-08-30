/*
 * Project:    waa新一代框架的基础模块中的公共工具库
 * 
 * FileName:   ClassUtilsTest.java
 * CreateTime: 2015-11-20 11:21:51
 */
package cc.waa.ng.util.lang;

import static org.apache.commons.lang3.StringUtils.endsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class ClassUtilsTest
{

	private Method methodFilterClass;	// 利用java的反射机制进行测试

	private ClassFilter filter1 = new ClassFilter()
	{

		@Override
		public boolean accept(String pack, Class<?> clazz)
		{
			return clazz.isInterface() && endsWith(clazz.getName(), "Dao");
		}

	};

	private ClassFilter filter2 = new ClassFilter()
	{

		@Override
		public boolean accept(String pack, Class<?> clazz)
		{
			return OutputStream.class.isAssignableFrom(clazz);
		}

	};

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp()
		throws Exception
	{
		this.methodFilterClass = ClassUtils.class.getDeclaredMethod("filterClass", String.class, String.class, ClassFilter.class);
		this.methodFilterClass.setAccessible(true);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown()
		throws Exception
	{
		this.methodFilterClass.setAccessible(false);
		this.methodFilterClass = null;
	}

	/**
	 * Test method for {@link cc.waa.ng.util.lang.ClassUtils#filterClass(java.lang.String, java.lang.String, cc.waa.ng.util.lang.ClassFilter)}.
	 */
	@Test
	public void testFilterClass1()
	{
		try
		{
			assertEquals(Object.class, this.methodFilterClass.invoke(null, "java.lang", "Object", null));
		}
		catch (Exception e)
		{
			fail("有异常抛出");
		}
	}

	/**
	 * Test method for {@link cc.waa.ng.util.lang.ClassUtils#filterClass(java.lang.String, java.lang.String, cc.waa.ng.util.lang.ClassFilter)}.
	 */
	@Test
	public void testFilterClass2()
	{
		try
		{
			assertNull(this.methodFilterClass.invoke(null, "java.lang", "Object", this.filter1));
		}
		catch (Exception e)
		{
			fail("有异常抛出");
		}
	}

	/**
	 * Test method for {@link cc.waa.ng.util.lang.ClassUtils#filterClass(java.lang.String, java.lang.String, cc.waa.ng.util.lang.ClassFilter)}.
	 */
	@Test
	public void testFilterClass3()
	{
		try
		{
			assertEquals(FileOutputStream.class, this.methodFilterClass.invoke(null, "java.io", "FileOutputStream", this.filter2));
		}
		catch (Exception e)
		{
			fail("有异常抛出");
		}
	}

	/**
	 * Test method for {@link cc.waa.ng.util.lang.ClassUtils#scanPackage(java.lang.String, cc.waa.ng.util.lang.ClassFilter)}.
	 */
	@Test
	public void testScanPackage1()
	{
		assertNotNull(ClassUtils.scanPackage("org.apache.commons", null));
	}

}
