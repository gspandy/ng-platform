/*
 * Project:    waa新一代框架的基础模块中的数据访问部分
 * 
 * FileName:   Profile.java
 * CreateTime: 2015-09-18 19:08:08
 */
package cc.waa.ng.base.data.obj;

import java.util.Date;

import cc.waa.ng.base.data.entity.ProfileEntity;
import cc.waa.ng.data.obj.TargetEntity;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetEntity(ProfileEntity.class)
public interface Profile
{

	String getId();

	User getUser();

	void setUser(User user);

	Role getRole();

	void setRole(Role role);

	boolean isDeleted();

	Date getCreateTime();

	Date getDeleteTime();

}
