/*
 * Project:    waa新一代框架的基础模块中的公共工具库
 * 
 * FileName:   GUIDTest.java
 * CreateTime: 2015-10-11 22:37:36
 */
package cc.waa.ng.util.lang;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class GUIDTest
{

	/**
	 * Test method for {@link cc.waa.ng.base.util.GUID#toUnsign()}.
	 */
	@Test
	public void testToUnsign1()
	{
		String result = new GUID().toUnsign();

		assertTrue(result != null && result.length() == 25);
	}

	/**
	 * Test method for {@link cc.waa.ng.base.util.GUID#toUnsign()}.
	 */
	@Test
	public void testToUnsign2()
	{
		String result = new GUID().toUnsign();

		assertTrue(result != null && result.length() == 25);
	}

}
