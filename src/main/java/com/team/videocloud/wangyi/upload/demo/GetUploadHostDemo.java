package com.team.videocloud.wangyi.upload.demo;



import com.team.videocloud.wangyi.auth.BasicCredentials;
import com.team.videocloud.wangyi.auth.Credentials;
import com.team.videocloud.wangyi.client.VcloudClient;
import com.team.videocloud.wangyi.upload.param.GetUploadHostParam;
import lombok.extern.apachecommons.CommonsLog;



/**
 * <p>Title: GetUploadHostDemo</p>
 * <p>Description: 获取上传加速节点地址的Demo</p>
 * <p>Company: com.netease.vcloud</p>
 *
 * @date 2016-6-21
 */
@CommonsLog
public class GetUploadHostDemo {

    public static void main(String[] args) {


		 /* 输入个人信息 */
         /* 开发者平台分配的appkey 和 appSecret */
        String appKey = "";
        String appSecret = "";


        Credentials credentials;
        credentials = new BasicCredentials(appKey, appSecret);
        VcloudClient vclient = new VcloudClient(credentials);

	     /* 存储上传文件的桶名  参数必填*/
        String bucket = "";

        try {
			/*获取上传加速节点地址返回结果的封装类*/
            GetUploadHostParam getUploadHostParam = vclient.getUploadHost(bucket);

            if (null != getUploadHostParam) {
                log.info("[GetUploadHostDemo] get uploadHost successfully. " + getUploadHostParam.toString());
            } else {
                log.info("[GetUploadHostDemo] fail to get uploadHost. ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
