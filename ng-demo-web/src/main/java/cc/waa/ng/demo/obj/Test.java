/*
 * Project:    waa新一代框架的演示模块
 * 
 * FileName:   Test.java
 * CreateTime: 2015-12-28 08:48:57
 */
package cc.waa.ng.demo.obj;

import java.util.Date;

import cc.waa.ng.data.obj.TargetEntity;
import cc.waa.ng.demo.entity.TestEntity;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetEntity(TestEntity.class)
public interface Test
{

	public String getId();

	public String getMessage();

	public void setMessage(String message);

	public Date getCreateTime();

}
