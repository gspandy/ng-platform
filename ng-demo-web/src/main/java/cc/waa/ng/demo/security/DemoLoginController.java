/*
 * Project:    waa新一代框架的演示模块
 * 
 * FileName:   DemoLoginController.java
 * CreateTime: 2016-03-24 10:13:16
 */
package cc.waa.ng.demo.security;

import static org.apache.commons.codec.binary.Hex.encodeHex;
import static org.apache.commons.lang3.StringUtils.join;
import static org.slf4j.LoggerFactory.getLogger;

import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.openid.OpenIDAuthenticationFilter;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import cc.waa.ng.util.lang.CryptUtils;

/**
 * 登录界面
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class DemoLoginController
	implements Controller, ApplicationContextAware
{

	private static final Logger logger = getLogger(DemoLoginController.class);



	private boolean formLoginEnable, formLoginRememberMeEnable;
	private String usernameParameter, passwordParameter, formLoginRememberMeParameter;

	private boolean openIdEnable, openIdRememberMeEnable;
	private String openIdUsernameParameter, openIdRememberMeParameter;

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
	{
		try
		{
			UsernamePasswordAuthenticationFilter upaf = applicationContext.getBean(UsernamePasswordAuthenticationFilter.class);

			if (upaf != null)
			{
				this.formLoginEnable = true;
				this.usernameParameter = upaf.getUsernameParameter();
				this.passwordParameter = upaf.getPasswordParameter();

				RememberMeServices rmService = upaf.getRememberMeServices();

				if (rmService instanceof AbstractRememberMeServices)
				{
					this.formLoginRememberMeEnable = true;
					this.formLoginRememberMeParameter = ((AbstractRememberMeServices) rmService).getParameter();
				}
			}
		}
		catch (NoSuchBeanDefinitionException e)
		{
			logger.warn("系统已设置为不支持用户名和密码登录");
		}

		try
		{
			OpenIDAuthenticationFilter oidaf = applicationContext.getBean(OpenIDAuthenticationFilter.class);

			if (oidaf != null)
			{
				this.openIdEnable = true;
				this.openIdUsernameParameter = "openid_identifier";		// ??

				RememberMeServices rmService = oidaf.getRememberMeServices();

				if (rmService instanceof AbstractRememberMeServices)
				{
					this.openIdRememberMeEnable = true;
					this.openIdRememberMeParameter = ((AbstractRememberMeServices) rmService).getParameter();
				}
			}
		}
		catch (NoSuchBeanDefinitionException e)
		{
			logger.info("系统已设置为不支持用openId登录");
		}
	}

	private Map<String, ?> buildModel(HttpServletRequest request)
		throws Exception
	{
		Map<String, Object> model = new HashMap<String, Object>();

		model.put("formLoginEnable", this.formLoginEnable);
		model.put("formLoginRememberMeEnable", this.formLoginRememberMeEnable);
		model.put("openIdEnable", this.openIdEnable);
		model.put("openIdRememberMeEnable", this.openIdRememberMeEnable);
		model.put("loginUrl", join(request.getContextPath(), "/login"));

		if (this.formLoginEnable)
		{
			model.put("usernameParameter", this.usernameParameter);
			model.put("passwordParameter", this.passwordParameter);
		}

		if (this.formLoginRememberMeEnable)
			model.put("formLoginRememberMeParameter", this.formLoginRememberMeParameter);

		if (this.openIdEnable)
			model.put("openIdUsernameParameter", this.openIdUsernameParameter);

		if (this.openIdRememberMeEnable)
			model.put("openIdRememberMeParameter", this.openIdRememberMeParameter);

		CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

		if (token == null)
			model.put("csrfEnable", false);

		else
		{
			model.put("csrfEnable", true);
			model.put("csrfParameter", token.getParameterName());
			model.put("csrfToken", token.getToken());
		}

		PublicKey pubKey = CryptUtils.getInternalRsaPublicKey();

		if (pubKey instanceof RSAPublicKey)
		{
			RSAPublicKey rsaPubKey = (RSAPublicKey) pubKey;

			byte[] modulus  = rsaPubKey.getModulus().toByteArray();
			byte[] exponent = rsaPubKey.getPublicExponent().toByteArray();

			model.put("rsaModulus", new String(encodeHex(modulus)));
			model.put("rsaExponent", new String(encodeHex(exponent)));
		}

		return model;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.Controller#handleRequest(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		return new ModelAndView("login", buildModel(request));
	}

}
