/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   DataRepositoryRegister.java
 * CreateTime: 2015-11-20 09:47:10
 */
package cc.waa.ng.data.dao;

import static cc.waa.ng.util.lang.ClassUtils.scanPackage;
import static org.apache.commons.lang3.StringUtils.uncapitalize;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

import cc.waa.ng.util.lang.ClassFilter;

/**
 * 扫描指定的package，自动注册符合条件的dao
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class DataRepositoryRegister
	implements ApplicationContextAware, ClassFilter
{

	private static final Logger logger = getLogger(DataRepositoryRegister.class);



	private Assistant assistant = Assistant.DOMAIN;

	public void setAssistant(Assistant assistant)
	{
		this.assistant = assistant;
	}

	private List<String> scanPackages;

	public void setScanPackages(List<String> scanPackages)
	{
		this.scanPackages = scanPackages;
	}

	private List<QueryInvocation> invocations;

	public void setInvocations(List<QueryInvocation> invocations)
	{
		this.invocations = invocations;
	}

	private ApplicationContext applicationContext;

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
	{
		this.applicationContext = applicationContext;
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.base.util.lang.ClassFilter#accept(java.lang.String, java.lang.Class)
	 */
	@Override
	public boolean accept(String pack, Class<?> clazz)
	{
		return clazz.getAnnotation(RepositoryDefinition.class) != null;
	}

	/**
	 * 从ApplicationContext中获取Bean的注册器
	 * 
	 * @return
	 */
	private BeanDefinitionRegistry prepareBeanDefinitionRegistry()
	{
		if (!(this.applicationContext instanceof ConfigurableApplicationContext))
			return null;

		ConfigurableApplicationContext ctx = (ConfigurableApplicationContext) this.applicationContext;
		ConfigurableListableBeanFactory beanFactory = ctx.getBeanFactory();

		if (!(beanFactory instanceof BeanDefinitionRegistry))
			return null;

		return (BeanDefinitionRegistry) beanFactory;
	}

	/**
	 * 创建指定的dao对象，并注册到spring的上下文中
	 * 
	 * @param daoType
	 */
	private void buildAndBind(Class<?> daoType)
	{
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(DataRepositoryFactoryBean.class);
		builder.addPropertyValue("daoType", daoType);
		builder.addPropertyValue("invocations", this.invocations);

		String beanId = uncapitalize(daoType.getSimpleName());

		prepareBeanDefinitionRegistry().registerBeanDefinition(beanId, builder.getRawBeanDefinition());
	}

	/**
	 * 扫描并注册dao对象
	 */
	public void init()
	{
		if (prepareBeanDefinitionRegistry() == null)
			return;

		logger.trace("检查传入需要扫描的包的数量{}", this.scanPackages.size());

		if (this.scanPackages == null)
		{
			logger.warn("没有传入任何需要扫描的包，系统不会注册任何dao对象");

			return;
		}

		if (this.invocations != null)
		{
			DaoAssistant assistant = this.assistant.newInstance();

			for (QueryInvocation qi : this.invocations)
				qi.setAssistant(assistant);
		}

		for (String pack : this.scanPackages)
			for (Class<?> clazz : scanPackage(pack, this))
				buildAndBind(clazz);
	}

}
