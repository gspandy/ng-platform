/*
 * Project:    waa新一代框架的基础模块中的实体部分
 * 
 * FileName:   AdminProfileEntity.java
 * CreateTime: 2015-09-18 20:59:56
 */
package cc.waa.ng.base.data.entity;

import cc.waa.ng.base.data.obj.AdminProfile;
import cc.waa.ng.data.entity.TargetObject;

/**
 * 后台管理员身份（能进后台的用户就配该身份和相应的角色）
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetObject(AdminProfile.class)
public class AdminProfileEntity
	extends ProfileEntity
{

	/** serialVersionUID */
	private static final long serialVersionUID = 1157872527920247564L;

}
