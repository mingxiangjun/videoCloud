package com.team.videocloud.wangyi.upload.service.impl;

import com.team.videocloud.wangyi.VcloudException;
import com.team.videocloud.wangyi.upload.module.SetCallbackModule;
import com.team.videocloud.wangyi.upload.param.SetCallbackParam;
import com.team.videocloud.wangyi.upload.service.SetCallbackService;
import com.team.videocloud.wangyi.upload.util.UploadUtil;

import java.io.IOException;

/**
 * 
* <p>Title: SetCallbackServiceImpl</p>
* <p>Description:  设置上传回调地址的实现类</p>
* <p>Company: com.netease.vcloud</p>
* @date       2016-8-28
 */
public class SetCallbackServiceImpl implements SetCallbackService {

	/*
	* <p>Title: setCallback</p>
	* <p>Description: </p>
	* @param callbackUrl
	* @return
	* @throws IOException
	* @throws VcloudException
	* @see com.video.portal.wangyi.upload.service.impl.SetCallbackService#setCallback(java.lang.String)
	*/
	public SetCallbackParam setCallback(String callbackUrl) throws IOException, VcloudException {
		SetCallbackModule setCallbackModule = new SetCallbackModule(callbackUrl);
		UploadUtil util = new UploadUtil(setCallbackModule);
		SetCallbackParam setCallbackParam = util.setCallback();
		return setCallbackParam;
	}
}
