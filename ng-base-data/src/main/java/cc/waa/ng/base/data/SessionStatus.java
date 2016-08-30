/*
 * Project:    waa新一代框架的基础模块中的数据访问部分
 * 
 * FileName:   SessionStatus.java
 * CreateTime: 2016-01-27 18:18:44
 */
package cc.waa.ng.base.data;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public enum SessionStatus
{

	/**
	 * 普通，一搬指未登录状态
	 */
	NORMAL,

	/**
	 * 已登录
	 */
	LOGINED,

	/**
	 * 过时
	 */
	EXPIRED,

	/**
	 * 退出（主动）
	 */
	AWAY,

	/**
	 * 被逼退出
	 */
	FORCEOUT

}
