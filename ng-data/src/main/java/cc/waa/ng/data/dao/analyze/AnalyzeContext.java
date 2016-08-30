/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   AnalyzeContext.java
 * CreateTime: 2016-02-02 11:50:16
 */
package cc.waa.ng.data.dao.analyze;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.metamodel.Attribute;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class AnalyzeContext
	implements Serializable
{

	/** serialVersionUID */
	private static final long serialVersionUID = 565482270849089348L;



	private final Map<Attribute<?, ?>, Join<?, ?>> joindMap;

	public AnalyzeContext()
	{
		super();

		this.joindMap = new HashMap<Attribute<?, ?>, Join<?, ?>>();
	}

	public Join<?, ?> join(From<?, ?> from, Attribute<?, ?> attr)
	{
		Join<?, ?> joined = this.joindMap.get(attr);

		if (joined == null)
			this.joindMap.put(attr, joined = from.join(attr.getName()));

		return joined;
	}

}
