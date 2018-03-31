package com.video.portal.wangyi.upload.demo;

import com.video.portal.wangyi.auth.BasicCredentials;
import com.video.portal.wangyi.auth.Credentials;
import com.video.portal.wangyi.client.VcloudClient;
import com.video.portal.wangyi.upload.param.SetCallbackParam;
import org.apache.log4j.Logger;

/**
 * <p>Title: SetCallbackDemo</p>
 * <p>Description: 设置上传回调地址的Demo</p>
 * <p>Company: com.netease.vcloud</p>
 *
 * @date 2016-8-28
 */
public class SetCallbackDemo {

    /**
     * 日志实例
     */
    public static final Logger logger = Logger.getLogger(SetCallbackDemo.class);

    public static void main(String[] args) {


		 /* 输入个人信息 */
         /* 开发者平台分配的appkey 和 appSecret */
        String appKey = "";
        String appSecret = "";

        Credentials credentials;
        credentials = new BasicCredentials(appKey, appSecret);
        VcloudClient vclient = new VcloudClient(credentials);

	     /* 上传成功后回调客户端的URL地址    参数必填*/
        String callbackUrl = "http://127.0.0.1/client/callback";

        try {
			/*设置上传回调地址接口输出参数的封装类*/
            SetCallbackParam setCallbackParam = vclient.setCallback(callbackUrl);

            if (setCallbackParam.getCode() == 200) {
                logger.info("[SetCallbackDemo] set callback successfully. ");
            } else {
                logger.info("[SetCallbackDemo] fail to set callback. msg:" + setCallbackParam.getMsg());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
