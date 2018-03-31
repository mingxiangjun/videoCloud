package com.team.videocloud.wangyi.util;

import com.alibaba.fastjson.JSON;
import com.team.videocloud.wangyi.BaseModule;
import com.team.videocloud.wangyi.VcloudException;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


/**
* <p>Title: JsonParamObjectUtil</p>
* <p>Description: 执行HttpPost请求，返回输出参数封装类的工具类</p>
* <p>Company: com.netease.vcloud</p>
* @date       2016-7-14
*/
@CommonsLog
public class JsonParamObjectUtil {
	/**
	 * <p>Title: jsonParamObjectBuilder</p>
	 * <p>Description: 执行HttpPost请求 得到 json数据对应的参数封装类工具</p>
	 * @param url            操作URL
	 * @param module         输入参数的封装类
	 * @param clazz          对应待返回的参数封装类的class
	 * @return object        对应待返回的参数封装类
	 * @throws IOException
	 * @throws VcloudException 
	 */
	public <T> T jsonParamObjectBuilder(String url, BaseModule module, Class <T> clazz) throws IOException, VcloudException {

		HttpClient httpClient = HttpClientBuilder.getHttpClient();
		HttpPost httpPost = HttpPostBuilder.getHttpPost(url);	
		
		StringEntity params = new StringEntity(JSON.toJSONString(module),"UTF-8");	
		// 设置请求的参数		
		httpPost.setEntity(params);		
		// 执行请求
		HttpResponse response = httpClient.execute(httpPost);
		String stringBody = EntityUtils.toString(response.getEntity(), "utf-8");		
		//logger.info("[JsonParamObjectUtil] : json stringBody" + stringBody);

		// 得到Json数据
		 T object = null;
		try {
			 object =  JSON.parseObject(stringBody, clazz);			
		} catch (Exception e) {
			log.error("[JsonParamObjectUtil] json parse exception : "	+ e + ",msg : " + e.getMessage());
			throw new VcloudException("[JsonParamObjectUtil] json parse exception", e);			
		}
		return object;	
	}
}
