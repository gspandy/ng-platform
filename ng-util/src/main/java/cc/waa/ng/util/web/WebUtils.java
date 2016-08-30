/*
 * Project:    waa新一代框架的基础模块中的公共工具库
 * 
 * FileName:   WebUtils.java
 * CreateTime: 2016-01-27 17:49:03
 */
package cc.waa.ng.util.web;

import static java.lang.Integer.MAX_VALUE;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import cc.waa.ng.util.lang.GUID;

/**
 * web相关操作的工具类
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class WebUtils
{

	public static final String SESSION_KEY = "cc.waa.ng.scope.session";

	/**
	 * 获取Cookie的值
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getCookie(HttpServletRequest request, String name)
	{
		Cookie[] cookies = request.getCookies();

		if (cookies != null)
			for (Cookie cookie : cookies)
				if (StringUtils.equals(cookie.getName(), name))
					return cookie.getValue();

		return null;
	}

	/**
	 * 获取Cookie的值，如果存在则顺便更新Cookie的maxAge
	 * 
	 * @param request
	 * @param response
	 * @param name
	 * @param maxAge
	 * @return
	 */
	public static String getCookie(HttpServletRequest request,
								   HttpServletResponse response,
								   String name, int maxAge)
	{
		Cookie[] cookies = request.getCookies();

		if (cookies == null)
			return null;

		for (Cookie cookie : cookies)
		{
			if (!StringUtils.equals(cookie.getName(), name))
				continue;

			cookie.setMaxAge(maxAge);

			response.addCookie(cookie);

			return cookie.getValue();
		}

		return null;
	}

	/**
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 */
	public static void setCookie(HttpServletRequest request,
								 HttpServletResponse response,
								 String name, String value)
	{
		setCookie(request, response, name, value, -1);
	}

	/**
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 */
	public static void setCookie(HttpServletRequest request,
								 HttpServletResponse response,
								 String name, String value, int maxAge)
	{
		boolean found = false;
		Cookie[] cs;

		if ((cs = request.getCookies()) != null) for (Cookie cookie : cs)
		{
			if (StringUtils.equals(cookie.getName(), name))
			{
				cookie.setValue(value);
				cookie.setMaxAge(maxAge);

				found = true;

				break;
			}
		}

		if (!found)
		{
			Cookie cookie = new Cookie(name, value);
			cookie.setPath("/");
			cookie.setMaxAge(maxAge);

			response.addCookie(cookie);
		}
	}

	private static String getSessionIdFromCookie(HttpServletRequest request,
												 HttpServletResponse response)
	{
		String sessionId = getCookie(request, response, SESSION_KEY, MAX_VALUE);

		if (isEmpty(sessionId))
			setCookie(request, response, SESSION_KEY,
					  sessionId = new GUID().toUnsign(), MAX_VALUE);

		return sessionId;
	}

	private static String getSessionIdFromSession(HttpServletRequest request,
												  HttpServletResponse response)
	{
		HttpSession session = request.getSession();
		String sessionId = (String) session.getAttribute(SESSION_KEY);

		if (isEmpty(sessionId))
		{
			sessionId = getSessionIdFromCookie(request, response);
			session.setAttribute(SESSION_KEY, sessionId);
		}

		return sessionId;
	}

	private static String getSessionIdFromRequest(HttpServletRequest request,
												  HttpServletResponse response)
	{
		String sessionId = (String) request.getAttribute(SESSION_KEY);

		if (isEmpty(sessionId))
		{
			sessionId = getSessionIdFromSession(request, response);
			request.setAttribute(SESSION_KEY, sessionId);
		}

		return sessionId;
	}

	/**
	 * 取得当前会话id
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public static String getSessionId(HttpServletRequest request,
									  HttpServletResponse response)
	{
		return getSessionIdFromRequest(request, response);
	}

}
