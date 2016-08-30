/*
 * Project:    waa新一代框架的演示模块
 * 
 * FileName:   TestDao.java
 * CreateTime: 2015-12-28 08:50:20
 */
package cc.waa.ng.demo.dao;

import org.springframework.transaction.annotation.Transactional;

import cc.waa.ng.data.dao.RepositoryDefinition;
import cc.waa.ng.demo.obj.Test;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@RepositoryDefinition(domainClass = Test.class, idClass = String.class)
public interface TestDao
{

	public Test blank();

	@Transactional
	public Test save(Test test);

	@Transactional(readOnly = true)
	public int count();

	@Transactional(readOnly = true)
	public Test findOne(String id);

	@Transactional
	public void delete(String id);

}
