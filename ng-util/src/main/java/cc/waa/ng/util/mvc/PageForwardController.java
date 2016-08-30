/*
 * Project:    waa新一代框架的基础模块中的公共工具库
 * 
 * FileName:   PageForwardController.java
 * CreateTime: 2015-10-02 20:51:19
 */
package cc.waa.ng.util.mvc;

import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.join;
import static org.apache.commons.lang3.StringUtils.removeEndIgnoreCase;
import static org.apache.commons.lang3.StringUtils.removeStartIgnoreCase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class PageForwardController
	extends AbstractController
{

	private String suffix = ".html";

	public void setSuffix(String suffix)
	{
		this.suffix = suffix;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
												 HttpServletResponse response)
		throws Exception
	{
		String url		= request.getRequestURI(),
			   sevlet	= request.getServletContext().getContextPath();

		if (isNotEmpty(this.suffix))
			url = removeEndIgnoreCase(url, this.suffix);

		if (!equalsIgnoreCase(sevlet, "/"))
			sevlet = join(sevlet, "/");

		url = removeStartIgnoreCase(url, sevlet);

		return new ModelAndView(isEmpty(url) ? "index" : url);
	}

}
