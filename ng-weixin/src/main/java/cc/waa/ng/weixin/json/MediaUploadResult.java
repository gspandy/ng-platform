/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   MediaUploadResult.java
 * CreateTime: 2016-06-30 10:43:20
 */
package cc.waa.ng.weixin.json;

import com.google.gson.annotations.SerializedName;

/**
 * 接口（新增临时素材）的返回结果
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class MediaUploadResult
	extends CommonResult
{

	@SerializedName("type")
	private String type;

	@SerializedName("media_id")
	private String mediaId;

	@SerializedName("created_at")
	private String createdAt;

	public String getType()
	{
		return this.type;
	}

	public String getMediaId()
	{
		return this.mediaId;
	}

	public String getCreatedAt()
	{
		return this.createdAt;
	}

}
