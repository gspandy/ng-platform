/*
 * Project:    waa新一代框架的基础模块中的数据访问部分
 * 
 * FileName:   AdminProfile.java
 * CreateTime: 2016-01-20 11:34:54
 */
package cc.waa.ng.base.data.obj;

import cc.waa.ng.base.data.entity.AdminProfileEntity;
import cc.waa.ng.data.obj.TargetEntity;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetEntity(AdminProfileEntity.class)
public interface AdminProfile
	extends Profile
{

}
