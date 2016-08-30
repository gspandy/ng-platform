/*
 * Project:    waa新一代框架的基础模块中的实体部分
 * 
 * FileName:   EmptyProfileEntity.java
 * CreateTime: 2015-09-18 11:07:21
 */
package cc.waa.ng.base.data.entity;

import cc.waa.ng.base.data.obj.EmptyProfile;
import cc.waa.ng.data.entity.TargetObject;

/**
 * 空的身份对象，仅实现用户和角色的关联关系（相当于一个关联表）
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@TargetObject(EmptyProfile.class)
public class EmptyProfileEntity
	extends ProfileEntity
{

	/** serialVersionUID */
	private static final long serialVersionUID = 9137592273408399604L;

}
