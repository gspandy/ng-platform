/*
 * Project:    waa新一代框架的微信公众号接入模块
 * 
 * FileName:   WeixinClient.java
 * CreateTime: 2016-07-01 10:35:20
 */
package cc.waa.ng.weixin;

import java.io.OutputStream;
import java.util.List;

/**
 * 负责调用微信公众号接口
 * 
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public interface WeixinClient
{

	List<String> getCallbackIp();

	/**
	 * 获取临时素材内容
	 * 
	 * @param mediaId	微信平台的素材id
	 * @param os		接收内容的OutputStream 
	 * @return			成功获取内容时，返回相应的contentType；否则null伺候
	 */
	String pullMedia(String mediaId, OutputStream os);

}
