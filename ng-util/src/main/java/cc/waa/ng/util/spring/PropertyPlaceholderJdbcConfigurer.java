/*
 * Project:    waa新一代框架的基础模块中的公共工具库
 * 
 * FileName:   PropertyPlaceholderJdbcConfigurer.java
 * CreateTime: 2015-10-08 11:15:17
 */
package cc.waa.ng.util.spring;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

/**
 * 从数据库中读取properties配置，然后让spring加载到系统中
 * 相当于把数据库的表视为一个.properties文件
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class PropertyPlaceholderJdbcConfigurer
	extends PropertyPlaceholderConfigurer
{

	private static final Logger logger = getLogger(PropertyPlaceholderJdbcConfigurer.class);

	private static final String SQL = "SELECT PK_KEY, C_VALUE FROM NG_SYS_PROPERTY";



	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	/* (non-Javadoc)
	 * @see org.springframework.core.io.support.PropertiesLoaderSupport#loadProperties(java.util.Properties)
	 */
	@Override
	protected void loadProperties(final Properties props)
		throws IOException
	{
		super.loadProperties(props);	// 先继承原来的操作

		logger.debug("调试props当前的值{}", props);

		this.jdbcTemplate.query(SQL, new RowCallbackHandler()
		{

			@Override
			public void processRow(ResultSet rs)
				throws SQLException
			{
				String key   = rs.getString("PK_KEY"),
					   value = rs.getString("C_VALUE");

				props.setProperty(key, value);
			}

		});
	}

}
