package com.team.videocloud.wangyi.util;

import com.team.videocloud.wangyi.VcloudException;
import com.team.videocloud.wangyi.config.Config;
import org.apache.http.client.methods.HttpGet;
import java.util.Date;

/**
* <p>Title: HttpGetBuilder</p>
* <p>Description: 构造HttpGet的工具类</p>
* <p>Company: com.netease.vcloud</p>
* @date       2016-6-21
*/
public class HttpGetBuilder {
	/**
	 * 
	 * <p>Title: getHttpGet</p>
	 * <p>Description: 根据接口创建相应的HttpGet</p>
	 * @param url      接口地址
	 * @return 创建的 HttpGet
	 * @throws VcloudException
	 */
	public static HttpGet getHttpGet(String url) throws VcloudException {
		
		HttpGet httpGet = new HttpGet(url);		
		String appKey = null;
		String appSecret = null;
//		appKey = VcloudPropertiesUtil.readValue("appKey");
//		appSecret = VcloudPropertiesUtil.readValue("appSecret");
		
		appKey = Config.getAppKey();
		appSecret = Config.getAppSecret();

		if (null == appKey || null == appSecret) {			
			throw new VcloudException("[HttpGetBuilder] fail to read appKey or appSecret");		
		}
        String nonce =  "1";
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce ,curTime);//参考 计算CheckSum的java代码

        // 设置请求的header
        httpGet.addHeader("AppKey", appKey);
        httpGet.addHeader("Nonce", nonce);
        httpGet.addHeader("CurTime", curTime);
        httpGet.addHeader("CheckSum", checkSum);
        httpGet.addHeader("Content-Type", "application/json;charset=utf-8");      
            
        return httpGet;			
	}
}
