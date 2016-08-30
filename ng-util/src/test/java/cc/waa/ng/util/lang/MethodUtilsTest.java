/*
 * Project:    waa新一代框架的基础模块中的公共工具库
 * 
 * FileName:   MethodUtilsTest.java
 * CreateTime: 2015-11-18 14:34:23
 */
package cc.waa.ng.util.lang;

import static cc.waa.ng.util.lang.MethodUtils.getMethodSignature;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class MethodUtilsTest
{

	private Method method1, method2, method3;

	private final String
		expected1 = "java.lang.reflect.Type[] getGenericInterfaces()",
		expected2 = "java.lang.String getName()",
		expected3 = "int binarySearch(java.lang.Object[], java.lang.Object)";

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp()
		throws Exception
	{
		method1 = Class.class.getMethod("getGenericInterfaces");
		method2 = Class.class.getMethod("getName");
		method3 = Arrays.class.getMethod("binarySearch", Object[].class, Object.class);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown()
		throws Exception
	{
		method1 = null;
	}

	/**
	 * Test method for {@link cc.waa.ng.util.lang.MethodUtils#getMethodSignature(java.lang.reflect.Method)}.
	 */
	@Test
	public void testGetMethodSignature1()
	{
		assertEquals(this.expected1, getMethodSignature(this.method1));
	}

	/**
	 * Test method for {@link cc.waa.ng.util.lang.MethodUtils#getMethodSignature(java.lang.reflect.Method)}.
	 */
	@Test
	public void testGetMethodSignature2()
	{
		assertEquals(this.expected2, getMethodSignature(this.method2));
	}

	/**
	 * Test method for {@link cc.waa.ng.util.lang.MethodUtils#getMethodSignature(java.lang.reflect.Method)}.
	 */
	@Test
	public void testGetMethodSignature3()
	{
		assertEquals(this.expected3, getMethodSignature(this.method3));
	}

}
