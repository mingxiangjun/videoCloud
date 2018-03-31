package com.team.videocloud.wangyi.upload.service.impl;

import com.team.videocloud.wangyi.VcloudException;
import com.team.videocloud.wangyi.upload.module.QueryVideoIDorWatermarkIDModule;
import com.team.videocloud.wangyi.upload.param.QueryVideoIDorWatermarkIDParam;
import com.team.videocloud.wangyi.upload.service.QueryVideoIDorWatermarkIDService;
import com.team.videocloud.wangyi.upload.util.UploadUtil;

import java.io.IOException;
import java.util.List;


/**
* <p>Title: QueryVideoIDorWatermarkIDServiceImpl</p>
* <p>Description:  查询视频主ID或水印图片主ID的实现类</p>
* <p>Company: com.netease.vcloud</p>
* @date       2016-6-21
*/
public class QueryVideoIDorWatermarkIDServiceImpl implements QueryVideoIDorWatermarkIDService {

	/* 
	* <p>Title: queryVideoID</p>
	* <p>Description: </p>
	* @param objectNamesList
	* @return
	* @throws IOException
	* @throws VcloudException
	* @see com.video.portal.wangyi.upload.util.service.impl.QueryVideoIDService#queryVideoID(java.util.List)
	*/
	public QueryVideoIDorWatermarkIDParam queryVideoID(List<String> objectNamesList) throws IOException, VcloudException{
		
		if(null == objectNamesList || objectNamesList.size() < 1)
			throw new VcloudException("objectNamesList is null or invalid");
		QueryVideoIDorWatermarkIDModule queryVideoIDorWatermarkIDModule = new QueryVideoIDorWatermarkIDModule(objectNamesList);
		UploadUtil util = new UploadUtil(queryVideoIDorWatermarkIDModule);
		QueryVideoIDorWatermarkIDParam queryVideoIDParam = util.queryVideoID();
		return queryVideoIDParam;

	}
}
