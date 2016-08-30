/*
 * Project:    waa新一代框架的基础模块中的数据访问部分
 * 
 * FileName:   LoginRecordDao.java
 * CreateTime: 2016-01-14 18:38:31
 */
package cc.waa.ng.base.data.dao.auth;

import java.util.List;

import cc.waa.ng.base.data.LoginStatus;
import cc.waa.ng.base.data.obj.User;
import cc.waa.ng.base.data.obj.auth.LoginRecord;
import cc.waa.ng.data.dao.RepositoryDefinition;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@RepositoryDefinition(domainClass = LoginRecord.class, idClass = String.class)
public interface LoginRecordDao
{

	<T extends LoginRecord> T blank(Class<T> type);

	<T extends LoginRecord> T save(T loginRecord);

	<T extends LoginRecord> T update(T loginRecord);

	/**
	 * 根据平台的sessionId（并非容器的sessionId）获取相应的LoginRecord
	 * 
	 * @param sessionId
	 * @param status
	 * @return
	 */
	LoginRecord findBySessionIdStatus(String sessionId, LoginStatus status);

	List<LoginRecord> findByLoginStatus(LoginStatus... status);

	/**
	 * 获取指定用户的登录记录
	 * 
	 * @param user		用户对象
	 * @param status	限定登录状态，为null或者数组没有对象，视为获取全部
	 * @return
	 */
	List<LoginRecord> findByUserLoginStatusIn(User user, LoginStatus... status);

}
