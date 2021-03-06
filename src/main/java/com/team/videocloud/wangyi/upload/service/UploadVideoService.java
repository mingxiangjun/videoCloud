package com.team.videocloud.wangyi.upload.service;

import com.team.videocloud.wangyi.VcloudException;
import com.team.videocloud.wangyi.upload.param.QueryVideoIDorWatermarkIDParam;

import java.io.IOException;
import java.util.Map;
/**
* <p>Title: UploadVideoService</p>
* <p>Description: 简单的视频上传接口</p>
* <p>Company: com.netease.vcloud</p>
* @date       2016-6-30
*/
public interface UploadVideoService {

	/**
	 * 
	 * <p>Title: uploadVideo</p>
	 * <p>Description: 简单的视频上传</p>
	 * @param filePath       上传文件路径
	 * @param initParamMap   初始化参数集合
	 * @return 视频上传成功，则返回根据对象名查询视频ID输出参数的封装类，否则返回null
	 * @throws VcloudException
	 * @throws IOException
	 */
	public abstract QueryVideoIDorWatermarkIDParam uploadVideo(String filePath,
															   Map<String, Object> initParamMap) throws VcloudException,
			IOException;

}