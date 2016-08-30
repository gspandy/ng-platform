/*
 * Project:    waa新一代框架的基础模块中的数据访问部分
 * 
 * FileName:   Identity.java
 * CreateTime: 2016-05-06 11:32:08
 */
package cc.waa.ng.base.data.obj;

import java.util.Date;

import cc.waa.ng.base.data.entity.IdentityEntity;
import cc.waa.ng.data.obj.TargetEntity;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetEntity(IdentityEntity.class)
public interface Identity
{

	String getId();

	User getUser();

	void setUser(User user);

	boolean isEnabled();

	void setEnabled(boolean enabled);

	Date getCreateTime();

}
