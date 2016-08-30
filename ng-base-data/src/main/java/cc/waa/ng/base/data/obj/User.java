/*
 * Project:    waa新一代框架的基础模块中的数据访问部分
 *
 * FileName:   User.java
 * CreateTime: 2015-09-17 16:59:37
 */
package cc.waa.ng.base.data.obj;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import cc.waa.ng.base.data.PassHasher;
import cc.waa.ng.base.data.entity.IdentityEntity;
import cc.waa.ng.base.data.entity.UserEntity;
import cc.waa.ng.base.data.obj.auth.LoginRecord;
import cc.waa.ng.data.obj.BaseObject;
import cc.waa.ng.data.obj.MapKey;
import cc.waa.ng.data.obj.TargetEntity;

/**
 * 用户的业务对象
 *
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetEntity(UserEntity.class)
public interface User
	extends BaseObject
{

	String getId();

	Set<IdentityEntity> getIdentities();

	void setIdentities(Set<IdentityEntity> identities);

	PassHasher getPassHasher();

	void setPassHasher(PassHasher passHasher);

	String getPassHash();

	void setPassHash(String passHash);

	@MapKey("role")
	Map<Role, Profile> getProfiles();

	boolean isDeleted();

	Date getCreateTime();

	Date getDeleteTime();

	boolean isLocked();

	void setLocked(boolean locked);

	boolean isExpired();

	void setExpired(boolean expired);

	int getLoginCount();

	LoginRecord getFirstLogin();

	LoginRecord getLastLogin();

}
