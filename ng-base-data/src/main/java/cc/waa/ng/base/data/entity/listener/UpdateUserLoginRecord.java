/*
 * Project:    waa新一代框架的基础模块中的实体部分
 * 
 * FileName:   UpdateUserLoginRecord.java
 * CreateTime: 2016-01-14 21:57:52
 */
package cc.waa.ng.base.data.entity.listener;

import static cc.waa.ng.util.spring.ApplicationUtils.publishEvent;
import static org.slf4j.LoggerFactory.getLogger;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.springframework.context.ApplicationListener;

import cc.waa.ng.base.data.LoginStatus;
import cc.waa.ng.base.data.entity.UserEntity;
import cc.waa.ng.base.data.entity.auth.LoginRecordEntity;
import cc.waa.ng.base.data.entity.auth.SessionEntity;
import cc.waa.ng.data.entity.event.EntityEvent;
import cc.waa.ng.data.entity.event.EntitySavedEvent;
import cc.waa.ng.data.entity.event.EntityUpdatedEvent;
import cc.waa.ng.util.jpa.JpaUtils;

/**
 * 处理LoginRecordEntity更新（包括新增）时，UserEntity和SessionEntity相应的修改
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class UpdateUserLoginRecord
	implements ApplicationListener<EntityEvent>
{

	private static final Logger logger = getLogger(UpdateUserLoginRecord.class);



	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
	 */
	@Override
	public void onApplicationEvent(EntityEvent event)
	{
		Object source;

		if (event == null || (source = event.getSource()) == null || !(source instanceof LoginRecordEntity))
			return;

		if (!(event instanceof EntitySavedEvent) &&		// 只处理新增或者更新操作
			!(event instanceof EntityUpdatedEvent))
			return;

		logger.trace("触发了{}的{}、{}事件", source, EntitySavedEvent.class.getName(), EntityUpdatedEvent.class.getName());

		LoginRecordEntity rec = (LoginRecordEntity) source;
		LoginStatus status = rec.getLoginStatus();
		UserEntity user = null;
		SessionEntity session = rec.getSession();

		session.setStatus(status.toSessionStatus());

		if (status == LoginStatus.LOGINED)	// 状态为登录时，要处理用户的属性
		{
			user = rec.getUser();

			if (user.getFirstLogin() == null)
				user.setFirstLogin(rec);
			user.setLastLogin(rec);
			user.setLoginCount(user.getLoginCount() + 1);

			session.setLoginRecord(rec);
			session.setLoginTimes(session.getLoginTimes() + 1);
		}

		else	// 其它情况就应该退出登录的了
			session.setLoginRecord(null);

		EntityManager em = JpaUtils.getCurEntityManager();

		if (user != null)
		{
			if (!em.contains(user))
				em.merge(user);

			publishEvent(new EntityUpdatedEvent(user));
		}

		if (!em.contains(session))
			em.merge(session);

		publishEvent(new EntityUpdatedEvent(session));
	}

}
