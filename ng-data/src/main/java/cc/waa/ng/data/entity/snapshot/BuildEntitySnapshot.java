/*
 * Project:    waa新一代框架的基础模块中的实体部分
 * 
 * FileName:   BuildEntitySnapshot.java
 * CreateTime: 2015-09-24 16:26:54
 */
package cc.waa.ng.data.entity.snapshot;

import static cc.waa.ng.data.entity.snapshot.field.TranslateResult.SKIPED;
import static cc.waa.ng.util.jpa.JpaUtils.getCurEntityManager;
import static org.slf4j.LoggerFactory.getLogger;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.springframework.context.ApplicationListener;

import cc.waa.ng.data.entity.BaseEntity;
import cc.waa.ng.data.entity.event.EntityDeletedEvent;
import cc.waa.ng.data.entity.event.EntityEvent;
import cc.waa.ng.data.entity.event.EntitySavedEvent;
import cc.waa.ng.data.entity.event.EntityUpdatedEvent;
import cc.waa.ng.data.entity.snapshot.field.FieldTranslater;
import cc.waa.ng.util.lang.GUID;

/**
 * 监听实体类事件，处理对象快照记录
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class BuildEntitySnapshot
	implements ApplicationListener<EntityEvent>
{

	private static final Logger logger = getLogger(BuildEntitySnapshot.class);



	private List<FieldTranslater> translaters;

	public void setTranslaters(List<FieldTranslater> translaters)
	{
		this.translaters = translaters;
	}

	/**
	 * 在指定对象的所有快照后面添加一条新快照记录
	 * 
	 * @param owner
	 * @param snapshotable
	 * @param timestamp
	 * @throws Exception
	 */
	private void appendSnapshot(BaseEntity owner, Snapshotable snapshotable, Date timestamp)
		throws Exception
	{
		Class<? extends Snapshot<?>> snapshotType = snapshotable.type();

		@SuppressWarnings("unchecked")
		Snapshot<BaseEntity> sst = (Snapshot<BaseEntity>) snapshotType.newInstance();

		// 设置通用属性
		sst.setId(new GUID().toUnsign());
		sst.setOwner(owner);
		sst.setStartTime(timestamp);
		sst.setTail(true);

		// 设置实体类各自的属性
		for (Field snapshotField : snapshotType.getDeclaredFields())
			for (FieldTranslater translater : this.translaters)
				if (translater.translate(owner, sst, snapshotField) != SKIPED)
					break;

		getCurEntityManager().persist(sst);
	}

	/**
	 * 查询指定对象所有快照中的最后一条
	 * 
	 * @param owner
	 * @param snapshotType
	 * @return
	 */
	private Snapshot<?> seekTailSnapshot(BaseEntity owner, Class<? extends Snapshot<?>> snapshotType)
	{
		EntityManager entityManager = getCurEntityManager();
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<? extends Snapshot<?>> cq = cb.createQuery(snapshotType);
		Root<?> root = cq.from(snapshotType);

		cq.where(cb.equal(root.get("tail"), true), cb.equal(root.get("owner"), owner));

		return entityManager.createQuery(cq).getSingleResult();
	}

	/**
	 * 有新对象保存，如果是支持快照的就初始化快照记录
	 * 
	 * @param object
	 */
	private void onEntityPersist(Object object)
	{
		Class<?> type;
		Snapshotable snapshotable;
	
		if (object == null || !(object instanceof BaseEntity) ||
			(type = object.getClass()) == null ||
			(snapshotable = type.getAnnotation(Snapshotable.class)) == null)
			return;
	
		try
		{
			BaseEntity entity = (BaseEntity) object;
	
			appendSnapshot(entity, snapshotable, entity.getCreateTime());
		}
		catch (Exception e)
		{
			logger.error("为对象[" + object + "]创建快照时出错", e);
		}
	}

	/**
	 * 有新对象更新
	 * 
	 * @param object
	 */
	private void onEntityUpdate(Object object)
	{
		Class<?> type;
		Snapshotable snapshotable;

		if (object == null || !(object instanceof BaseEntity) ||
			(type = object.getClass()) == null ||
			(snapshotable = type.getAnnotation(Snapshotable.class)) == null)
			return;

		try
		{
			Date now = new Date();	// TODO 考虑是否用统一时间
			BaseEntity entity = (BaseEntity) object;

			Snapshot<?> oldSnapshot = seekTailSnapshot(entity, snapshotable.type());

			// 结束上一条
			oldSnapshot.setEndTime(now);
			oldSnapshot.setTail(false);

			appendSnapshot(entity, snapshotable, now);
		}
		catch (Exception e)
		{
			logger.error("为对象" + object + "]创建快照时出错", e);
		}
	}

	/**
	 * 有对象被删除
	 * 
	 * @param object
	 */
	private void onEntityDelete(Object object)
	{
		Class<?> type;
		Snapshotable snapshotable;

		if (object == null || !(object instanceof BaseEntity) ||
			(type = object.getClass()) == null ||
			(snapshotable = type.getAnnotation(Snapshotable.class)) == null)
			return;

		try
		{
			Date now = new Date();	// TODO 考虑是否用统一时间
			BaseEntity entity = (BaseEntity) object;

			Snapshot<?> oldSnapshot = seekTailSnapshot(entity, snapshotable.type());

			// 结束上一条
			oldSnapshot.setEndTime(now);
		}
		catch (Exception e)
		{
			logger.error("为对象" + object + "]创建快照时出错", e);
		}
	}

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
	 */
	@Override
	public void onApplicationEvent(EntityEvent event)
	{
		if (event instanceof EntitySavedEvent)
			onEntityPersist(event.getSource());
		else if (event instanceof EntityUpdatedEvent)
			onEntityUpdate(event.getSource());
		else if (event instanceof EntityDeletedEvent)
			onEntityDelete(event.getSource());
	}

}
