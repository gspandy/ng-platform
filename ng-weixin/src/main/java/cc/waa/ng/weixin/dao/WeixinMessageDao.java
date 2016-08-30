/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   WeixinMessageDao.java
 * CreateTime: 2016-06-17 11:59:45
 */
package cc.waa.ng.weixin.dao;

import cc.waa.ng.data.dao.RepositoryDefinition;
import cc.waa.ng.weixin.obj.WeixinMessage;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@RepositoryDefinition(domainClass = WeixinMessage.class, idClass = String.class)
public interface WeixinMessageDao
{

	<T extends WeixinMessage> T blank(Class<T> type);

	<T extends WeixinMessage> T save(T message);

	WeixinMessage findOne(String id);

	<T extends WeixinMessage> T update(T message);

	int count();

}
