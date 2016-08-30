/*
 * Project:    waa新一代框架的基础模块中的公共工具库
 * 
 * FileName:   StringUtils.java
 * CreateTime: 2016-07-27 10:50:22
 */
package cc.waa.ng.util.lang;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class StringUtils
{

	public static boolean equalsAny(final CharSequence cs1, final CharSequence... cs2)
	{
		if (cs2 != null)
			for (CharSequence cs : cs2)
				if (org.apache.commons.lang3.StringUtils.equals(cs1, cs))
					return true;

		return false;
	}

}
