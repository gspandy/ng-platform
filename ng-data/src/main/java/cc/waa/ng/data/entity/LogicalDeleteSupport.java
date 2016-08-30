/*
 * Project:    waa新一代框架的基础模块中的实体部分
 * 
 * FileName:   LogicalDeleteSupport.java
 * CreateTime: 2015-09-25 08:56:42
 */
package cc.waa.ng.data.entity;

import java.util.Date;

/**
 * 支持逻辑删除的接口
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public interface LogicalDeleteSupport
{

	/**
	 * 是否已经删除
	 * 
	 * @return
	 */
	boolean isDeleted();

	void setDeleted(boolean deleted);

	/**
	 * 删除时间
	 * 
	 * @return
	 */
	Date getDeleteTime();

	void setDeleteTime(Date deleteTime);

}
