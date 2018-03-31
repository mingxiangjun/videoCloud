package com.team.videocloud.wangyi.upload.demo;

import com.team.videocloud.wangyi.auth.BasicCredentials;
import com.team.videocloud.wangyi.auth.Credentials;
import com.team.videocloud.wangyi.client.VcloudClient;
import com.team.videocloud.wangyi.upload.param.QueryVideoIDorWatermarkIDParam;
import com.team.videocloud.wangyi.upload.recorder.Recorder;
import com.team.videocloud.wangyi.upload.recorder.UploadRecorder;
import com.team.videocloud.wangyi.util.FileUtil;
import lombok.extern.apachecommons.CommonsLog;

import java.util.HashMap;
import java.util.Map;


/**
 * <p>Title: Upload_RecoderDemo</p>
 * <p>Description: 支持断点续传的视频上传Demo</p>
 * <p>Company: com.netease.vcloud</p>
 *
 * @date 2016-6-22
 */
@CommonsLog
public class Upload_RecoderDemo {

    public static void main(String[] args) {
         /* 输入个人信息 */
		 /* 开发者平台分配的appkey 和 appSecret */
        String appKey = "";
        String appSecret = "";


        Credentials credentials;
        credentials = new BasicCredentials(appKey, appSecret);
        VcloudClient vclient = new VcloudClient(credentials);

        try {
			/*请输入上传文件路径*/
            String filePath = "e:\\3.mp4";

            Map<String, Object> initParamMap = new HashMap<String, Object>();
			/*输入上传文件的相关信息 */
			/* 上传文件的原始名称（包含后缀名） 此参数必填*/
            initParamMap.put("originFileName", FileUtil.getFileName(filePath));


		     /* 用户命名的上传文件名称  此参数非必填*/
            //initParamMap.put("userFileName", "for_love.mp4");

		     /* 视频所属的类别ID（不填写为默认分类）此参数非必填*/
            // initParamMap.put("typeId", new Long(1056));

		     /* 频所需转码模板ID（不填写为默认模板） 此参数非必填*/
            //initParamMap.put("presetId", new Long(2007));

		     /* 转码成功后回调客户端的URL地址（需标准http格式）  此参数非必填*/
            //initParamMap.put("callbackUrl", "");

		     /* 上传视频的描述信息  此参数非必填*/
            //initParamMap.put("description", "love.mp4");
		     
		     /* 上传视频的视频水印Id 此参数非必填*/
            //initParamMap.put("watermarkId", new Long(1));

            /** 上传成功后回调客户端的URL地址（需标准http格式）此参数非必填 */

            // initParamMap.put("uploadCallbackUrl", "");

            /** 用户自定义信息，会在上传成功或转码成功后通过回调返回给用户    此参数非必填 */
            //initParamMap.put("userDefInfo", null);
			

			/* 本地用于存放上传进度相关信息的文件 */
            String recorderFilePath = "e:\\1\\2.txt";

            Recorder recorder = new UploadRecorder(recorderFilePath);


            QueryVideoIDorWatermarkIDParam queryVideoIDParam = null;


            queryVideoIDParam = vclient.uploadVideoWithRecorder(filePath, initParamMap, recorder);

            if (null != queryVideoIDParam) {
                log.info("[UploadDemo] upload video successfully and the vid is " + queryVideoIDParam.getRet().getList().get(0).getVid());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
