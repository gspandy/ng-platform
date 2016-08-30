/*
 * Project:    waa新一代框架的基础模块中的公共工具库
 * 
 * FileName:   Snapshot.java
 * CreateTime: 2015-09-23 23:14:23
 */
package cc.waa.ng.data.entity.snapshot;

import java.io.Serializable;
import java.util.Date;

import cc.waa.ng.data.entity.BaseEntity;

/**
 * 实体类的快照实体
 * 封装所有快照都用到的基本属性
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public abstract class Snapshot<T extends BaseEntity>
	implements Serializable
{

	/** serialVersionUID */
	private static final long serialVersionUID = -3049914344984439617L;



	private String id;

	/** 快照开始时间 */
	private Date startTime;

	/** 快照结束时间，为空null表示当前快照状态未结束 */
	private Date endTime;	// 不包含

	/** 标识是否众多快照记录的尾(最后一个)，方便程序插入快照记录 */
	private boolean tail;

	public String getId()
	{
		return this.id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public Date getStartTime()
	{
		return this.startTime;
	}

	public void setStartTime(Date startTime)
	{
		this.startTime = startTime;
	}

	public Date getEndTime()
	{
		return this.endTime;
	}

	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}

	public boolean isTail()
	{
		return this.tail;
	}

	public void setTail(boolean tail)
	{
		this.tail = tail;
	}

	public abstract T getOwner();

	public abstract void setOwner(T owner);

}
