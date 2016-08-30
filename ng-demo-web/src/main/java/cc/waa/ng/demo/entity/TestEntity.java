/*
 * Project:    waa新一代框架的演示模块
 * 
 * FileName:   TestEntity.java
 * CreateTime: 2015-12-25 17:51:42
 */
package cc.waa.ng.demo.entity;

import java.io.Serializable;
import java.util.Date;

import cc.waa.ng.data.entity.BaseEntity;
import cc.waa.ng.data.entity.TargetObject;
import cc.waa.ng.demo.obj.Test;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetObject(Test.class)
public class TestEntity
	implements BaseEntity, Serializable
{

	/** serialVersionUID */
	private static final long serialVersionUID = -7565414679691031623L;



	private String id;

	private String message;

	private Date createTime;

	/* (non-Javadoc)
	 * @see cc.waa.ng.util.entity.BaseEntity#getId()
	 */
	@Override
	public String getId()
	{
		return this.id;
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.util.entity.BaseEntity#setId(java.lang.String)
	 */
	@Override
	public void setId(String id)
	{
		this.id = id;
	}

	public String getMessage()
	{
		return this.message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.util.entity.BaseEntity#getCreateTime()
	 */
	@Override
	public Date getCreateTime()
	{
		return this.createTime;
	}

	/* (non-Javadoc)
	 * @see cc.waa.ng.util.entity.BaseEntity#setCreateTime(java.util.Date)
	 */
	@Override
	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

}
