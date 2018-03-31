package com.video.portal.wangyi.upload.service.impl;

import com.video.portal.wangyi.VcloudException;
import com.video.portal.wangyi.upload.param.QueryOffsetParam;
import com.video.portal.wangyi.upload.service.QueryOffsetService;
import com.video.portal.wangyi.upload.util.UploadUtil;
import org.apache.log4j.Logger;

import java.io.IOException;


/**
* <p>Title: QueryOffsetServiceImpl</p>
* <p>Description: 断点续传查询断点的实现类</p>
* <p>Company: com.netease.vcloud</p>
* @date       2016-6-30
*/
public class QueryOffsetServiceImpl implements QueryOffsetService  {

	/** 日志实例*/
	public static final Logger logger = Logger.getLogger(QueryOffsetServiceImpl.class);
	
	
	/* 
	* <p>Title: getPartOffset</p>
	* <p>Description: </p>
	* @param uploadHost
	* @param bucket
	* @param object
	* @param context
	* @param xNosToken
	* @return
	* @throws IOException
	* @throws VcloudException
	* @see com.video.portal.wangyi.upload.util.service.impl.QueryOffsetService#getPartOffset(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	*/
	public QueryOffsetParam getPartOffset(String uploadHost, String bucket, String object, String context, String xNosToken) throws IOException, VcloudException{
		
		if(null == uploadHost || "".equals(uploadHost.trim()))
			throw new VcloudException("uploadHost is null or invalid");
		if(null == bucket || "".equals(bucket.trim()))
			throw new VcloudException("bucket is null or invalid");
		if(null == object || "".equals(object.trim()))
			throw new VcloudException("object is null or invalid");
		if(null == context || "".equals(context.trim()))
			throw new VcloudException("context is null or invalid");
		if(null == xNosToken || "".equals(xNosToken.trim()))
			throw new VcloudException("xNosToken is null or invalid");
		
		UploadUtil util = new UploadUtil(uploadHost, bucket, object, context, xNosToken);
		QueryOffsetParam queryOffsetParam = util.getPartOffset();
		return queryOffsetParam;
	}
}
