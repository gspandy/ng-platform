/*
 * Project:    waa新一代框架的基础模块中的实体部分
 * 
 * FileName:   BaseEntity.java
 * CreateTime: 2015-09-24 15:32:22
 */
package cc.waa.ng.data.entity;

import java.util.Date;

/**
 * 统一封装框架内部实体类的最基本属性（可针对其进行单独管理的）
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public interface BaseEntity
	extends Comparable<BaseEntity>
{

	String getId();

	void setId(String id);

	Date getCreateTime();

	void setCreateTime(Date createTime);

	default int compareTo(BaseEntity other)
	{
		if (this.getId() == null)
			return -1;	// 没设的id的，无法判断大小，只能返回固定值，表示不相等

		return this.getId().compareTo(other.getId());
	}

}
