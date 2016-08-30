/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   MapResult.java
 * CreateTime: 2016-01-19 16:48:31
 */
package cc.waa.ng.data.obj.mapper;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class MapResult
{

	public static MapResult skip()
	{
		return new MapResult(MapStatus.SKIPED);
	}

	public static MapResult failure()
	{
		return new MapResult(MapStatus.FAILURE);
	}

	public static MapResult success(Object result)
	{
		return new MapResult(MapStatus.SUCCESS, result);
	}

	private MapStatus status;

	private Object result;

	public MapStatus getStatus()
	{
		return this.status;
	}

	public Object getResult()
	{
		return this.result;
	}

	/**
	 * @param status
	 */
	public MapResult(MapStatus status)
	{
		super();

		this.status = status;
	}

	/**
	 * @param status
	 * @param result
	 */
	public MapResult(MapStatus status, Object result)
	{
		super();

		this.status = status;
		this.result = result;
	}

	public boolean isSkiped()
	{
		return this.status == MapStatus.SKIPED;
	}

	public boolean isSuccess()
	{
		return this.status == MapStatus.SUCCESS;
	}

}
