/*
 * Project:    waa新一代框架的基础模块中的数据访问部分
 * 
 * FileName:   LoginStatus.java
 * CreateTime: 2016-02-03 13:55:21
 */
package cc.waa.ng.base.data;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public enum LoginStatus
{

	/**
	 * 已登录
	 */
	LOGINED(SessionStatus.LOGINED),

	/**
	 * 过时
	 */
	EXPIRED(SessionStatus.EXPIRED),

	/**
	 * 退出（主动）
	 */
	AWAY(SessionStatus.AWAY),

	/**
	 * 被逼退出
	 */
	FORCEOUT(SessionStatus.FORCEOUT);

	private SessionStatus sessionStatus;

	/**
	 * @param sessionStatus
	 */
	private LoginStatus(SessionStatus sessionStatus)
	{
		this.sessionStatus = sessionStatus;
	}

	/**
	 * 获取对应的SessionStatus
	 * 
	 * @return
	 */
	public SessionStatus toSessionStatus()
	{
		return this.sessionStatus;
	}

}
