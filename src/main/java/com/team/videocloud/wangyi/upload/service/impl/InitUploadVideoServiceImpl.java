package com.team.videocloud.wangyi.upload.service.impl;

import com.team.videocloud.wangyi.VcloudException;
import com.team.videocloud.wangyi.upload.module.InitUploadVideoModule;
import com.team.videocloud.wangyi.upload.param.InitUploadVideoParam;
import com.team.videocloud.wangyi.upload.service.InitUploadVideoService;
import com.team.videocloud.wangyi.upload.util.UploadUtil;

import java.io.IOException;
import java.util.Map;

/**
* <p>Title: InitUploadVideoServiceImpl</p>
* <p>Description: 视频上传初始化的实现类</p>
* <p>Company: com.netease.vcloud</p>
* @date       2016-6-21
*/
public class InitUploadVideoServiceImpl implements InitUploadVideoService {

	/* 
	* <p>Title: initUploadVideo</p>
	* <p>Description: </p>
	* @param initParamMap
	* @return
	* @throws IOException
	* @throws VcloudException
	* @see com.video.portal.wangyi.upload.util.service.impl.InitUploadVideoService#initUploadVideo(java.util.Map)
	*/
	public InitUploadVideoParam initUploadVideo(Map<String, Object> initParamMap) throws IOException, VcloudException{
		if(!checkInitParamMap(initParamMap))
			throw new VcloudException("originFileName is null or invalid");
		
		InitUploadVideoModule initUploadVideoModule = new InitUploadVideoModule(initParamMap);
		UploadUtil util = new UploadUtil(initUploadVideoModule);
		InitUploadVideoParam initUploadVideoParam = util.initUploadVideo();
		return initUploadVideoParam;

	}
	
	/**
	 * 
	* <p>Title: checkInitParamMap</p>
	* <p>Description: 判断视频初始化参数集合中必填参数是否缺失</p>
	* @param initParamMap  视频初始化参数集合
	* @return 如果必填参数缺失，则返回false, 否则返回true
	 */
	private boolean checkInitParamMap(Map<String, Object> initParamMap){		
		String originFileName = (String) initParamMap.get("originFileName");
		if(null == originFileName || "".equals(originFileName.trim()))
			return false;
		return true;		
	}

}
