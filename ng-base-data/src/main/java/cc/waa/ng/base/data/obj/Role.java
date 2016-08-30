/*
 * Project:    waa新一代框架的基础模块中的数据访问部分
 * 
 * FileName:   Role.java
 * CreateTime: 2015-09-18 19:07:45
 */
package cc.waa.ng.base.data.obj;

import java.util.Date;

import cc.waa.ng.base.data.entity.RoleEntity;
import cc.waa.ng.data.obj.TargetEntity;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetEntity(RoleEntity.class)
public interface Role
{

	String getId();

	String getName();

	void setName(String name);

	String getCode();

	void setCode(String code);

	Class<? extends Profile> getProfileType();

	void setProfileType(Class<? extends Profile> profileType);

	String getDescription();

	void setDescription(String description);

	boolean isDeleted();

	Date getCreateTime();

	Date getDeleteTime();

}
