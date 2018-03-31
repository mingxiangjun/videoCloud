package com.video.portal.wangyi.upload.service.impl;

import com.video.portal.wangyi.VcloudException;
import com.video.portal.wangyi.upload.module.SetCallbackModule;
import com.video.portal.wangyi.upload.param.SetCallbackParam;
import com.video.portal.wangyi.upload.service.SetCallbackService;
import com.video.portal.wangyi.upload.util.UploadUtil;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * 
* <p>Title: SetCallbackServiceImpl</p>
* <p>Description:  设置上传回调地址的实现类</p>
* <p>Company: com.netease.vcloud</p>
* @date       2016-8-28
 */
public class SetCallbackServiceImpl implements SetCallbackService {

	/** 日志实例*/
	public static final Logger logger = Logger.getLogger(SetCallbackServiceImpl.class);
	
	/* 
	* <p>Title: setCallback</p>
	* <p>Description: </p>
	* @param callbackUrl
	* @return
	* @throws IOException
	* @throws VcloudException
	* @see com.video.portal.wangyi.upload.service.impl.SetCallbackService#setCallback(java.lang.String)
	*/
	public SetCallbackParam setCallback(String callbackUrl) throws IOException, VcloudException{
		SetCallbackModule setCallbackModule = new SetCallbackModule(callbackUrl);
		UploadUtil util = new UploadUtil(setCallbackModule);
		SetCallbackParam setCallbackParam = util.setCallback();
		return setCallbackParam;
	}
}
