/*
 * Project:    waa新一代框架的基础模块中的数据访问部分
 * 
 * FileName:   EmptyProfile.java
 * CreateTime: 2016-01-20 11:34:02
 */
package cc.waa.ng.base.data.obj;

import cc.waa.ng.base.data.entity.EmptyProfileEntity;
import cc.waa.ng.data.obj.TargetEntity;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetEntity(EmptyProfileEntity.class)
public interface EmptyProfile
	extends Profile
{

}
