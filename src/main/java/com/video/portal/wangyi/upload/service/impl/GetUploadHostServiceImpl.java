package com.video.portal.wangyi.upload.service.impl;

import com.video.portal.wangyi.VcloudException;
import com.video.portal.wangyi.upload.param.GetUploadHostParam;
import com.video.portal.wangyi.upload.service.GetUploadHostService;
import com.video.portal.wangyi.upload.util.UploadUtil;
import org.apache.log4j.Logger;

import java.io.IOException;


/**
* <p>Title: GetUploadHostServiceImpl</p>
* <p>Description: 获取上传加速节点地址的实现类</p>
* <p>Company: com.netease.vcloud</p>
* @date       2016-6-21
*/
public class GetUploadHostServiceImpl implements GetUploadHostService {

	/** 日志实例*/
	public static final Logger logger = Logger.getLogger(GetUploadHostServiceImpl.class);
	
		
	/* 
	* <p>Title: getUploadHost</p>
	* <p>Description: </p>
	* @param bucket
	* @return
	* @throws IOException
	* @throws VcloudException
	* @see com.video.portal.wangyi.upload.service.GetUploadHostService#getUploadHost(java.lang.String)
	*/
	public GetUploadHostParam getUploadHost(String bucket) throws IOException, VcloudException{
		if(null == bucket || "".equals(bucket.trim()))
			throw new VcloudException("bucket is null or invalid");
		UploadUtil util = new UploadUtil(bucket);
		GetUploadHostParam getUploadHostParam = util.getUploadHost();
		return getUploadHostParam;

	}
}
