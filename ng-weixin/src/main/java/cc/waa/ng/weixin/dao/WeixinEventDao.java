/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   WeixinEventDao.java
 * CreateTime: 2016-06-22 23:59:55
 */
package cc.waa.ng.weixin.dao;

import cc.waa.ng.data.dao.RepositoryDefinition;
import cc.waa.ng.weixin.obj.WeixinEvent;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@RepositoryDefinition(domainClass = WeixinEvent.class, idClass = String.class)
public interface WeixinEventDao
{

	<T extends WeixinEvent> T blank(Class<T> type);

	<T extends WeixinEvent> T save(T event);

	int count();

}
