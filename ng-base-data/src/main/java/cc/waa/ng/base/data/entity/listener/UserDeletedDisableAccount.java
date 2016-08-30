/*
 * Project:    waa新一代框架的基础模块中的实体部分
 * 
 * FileName:   UserDeletedDisableAccount.java
 * CreateTime: 2016-01-12 17:56:51
 */
package cc.waa.ng.base.data.entity.listener;

import static cc.waa.ng.util.jpa.JpaUtils.getCurEntityManager;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.Set;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.springframework.context.ApplicationListener;

import cc.waa.ng.base.data.entity.IdentityEntity;
import cc.waa.ng.base.data.entity.UserEntity;
import cc.waa.ng.data.entity.event.EntityDeletedEvent;

/**
 * 成功删除用户后，回收账号
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class UserDeletedDisableAccount
	implements ApplicationListener<EntityDeletedEvent>
{

	private static final Logger logger = getLogger(UserDeletedDisableAccount.class);



	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
	 */
	@Override
	public void onApplicationEvent(EntityDeletedEvent event)
	{
		Object entity;

		if (event == null || (entity = event.getSource()) == null ||
			!(entity instanceof UserEntity))
			return;

		UserEntity user = (UserEntity) entity;
		Set<IdentityEntity> identities = user.getIdentities();

		EntityManager entityManager = getCurEntityManager();

		if (identities != null) for (IdentityEntity identity : identities)
		{
			entityManager.remove(identity);

			logger.trace("成功回收身份标识：{}", identity);
		}
	}

}
