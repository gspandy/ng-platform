/*
 * Project:    waa新一代框架的数据访问（存储）模块
 * 
 * FileName:   InvokeResult.java
 * CreateTime: 2016-01-20 18:53:15
 */
package cc.waa.ng.data.dao;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class InvokeResult
{

	private static final InvokeResult SKIP = new InvokeResult(InvokeStatus.SKIP);

	private static enum InvokeStatus
	{
		SKIP, FAILURE, SUCCESS
	};

	public static InvokeResult skip()
	{
		return SKIP;
	}

	public static InvokeResult failure(String message, Exception cause)
	{
		return new InvokeResult(InvokeStatus.FAILURE, message, cause);
	}

	public static InvokeResult success(Object result)
	{
		return new InvokeResult(InvokeStatus.SUCCESS, result);
	}



	private InvokeResult(InvokeStatus status)
	{
		super();

		this.status = status;
	}

	private InvokeResult(InvokeStatus status, Object result)
	{
		super();

		this.status = status;
		this.result = result;
	}

	private InvokeResult(InvokeStatus status, String message, Exception cause)
	{
		super();

		this.status = status;
		this.message = message;
		this.cause = cause;
	}

	private InvokeStatus status;

	private String message;

	public String getMessage()
	{
		return this.message;
	}

	private Exception cause;

	public Exception getCause()
	{
		return this.cause;
	}

	private Object result;

	public Object getResult()
	{
		return this.result;
	}

	public boolean isSkip()
	{
		return this.status == InvokeStatus.SKIP;
	}

	public boolean isFailure()
	{
		return this.status == InvokeStatus.FAILURE;
	}

	public boolean isSuccess()
	{
		return this.status == InvokeStatus.SUCCESS;
	}

}
