package com.team.videocloud.channel.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.team.videocloud.channel.dao.ChannelDao;
import com.team.videocloud.channel.po.Channel;
import com.team.videocloud.channel.service.ChannelService;
import com.team.videocloud.common.CommonConstants;
import com.team.videocloud.common.bean.ReturnSet;
import com.team.videocloud.common.config.NetEaseVidoeConfig;
import com.team.videocloud.wangyi.util.CheckSumBuilder;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 网易云直播服务实现类
 *
 * @author MingXiangjun
 * @create 2018-03-31 18:13
 */
@Service(value = "neteaseVideoChannelService")
@CommonsLog
public class NeteaseVideoChannelServiceImpl implements ChannelService{
    @Autowired
    private NetEaseVidoeConfig netEaseVidoeConfig;
    @Autowired
    private ChannelDao channelDao;
    /**
     * 创建频道
     *
     * @param name 频道名称
     * @param type 频道类型
     * @return
     */
    @Override
    public Channel createChannel(String name, int type) {
        JSONObject params = new JSONObject();
        params.put("name",name);
        params.put("type",type);
        String remoteUrl = CommonConstants.THIRD_VIDEO_DOMAIN + CommonConstants.CREATE_CHANNEL;
        JSONObject result = getRemoteProcessResult(remoteUrl,params);
        if (CommonConstants.Status.SUCCESS_STATUS.getCode() != result.getInteger("code")){
            log.error("调用远程接口出错："+remoteUrl+"\t"+result.getString("msg"));
            return null;
        }else{
            JSONObject channelJson = (JSONObject)result.get("ret");
            Channel channel = getChannelFromJson(channelJson);
            if (channel != null) {
                channelDao.save(channel);
            }
            return channel;
        }
    }

    /**
     * 通过Json对象生成Channel对象
     * @param channelJson
     * @return
     */
    private Channel getChannelFromJson(JSONObject channelJson) {
        Channel channel = new Channel();
        channel.setCid(channelJson.getString("cid"));
        channel.setName(channelJson.getString("name"));
        channel.setPushUrl(channelJson.getString("pushUrl"));
        channel.setHttpPullUrl(channelJson.getString("httpPullUrl"));
        channel.setHlsPullUrl(channelJson.getString("hlsPullUrl"));
        channel.setRtmpPullUrl(channelJson.getString("rtmpPullUrl"));
        channel.setCtime(channelJson.getLong("ctime"));
        return channel;
    }

    /**
     * 修改频道信息
     *
     * @param name 频道名称
     * @param cid  频道id
     * @param type 频道类型
     * @return 返回数据结果集
     */
    @Override
    @Transactional
    public ReturnSet updateChannel(String name, String cid, int type) {
        JSONObject params = new JSONObject();
        params.put("name",name);
        params.put("cid",cid);
        params.put("type",type);
        JSONObject result = getRemoteProcessResult(CommonConstants.THIRD_VIDEO_DOMAIN+CommonConstants.UPDATE_CHANNEL,params);
        int code = (Integer)result.get("code");
        if (code == CommonConstants.Status.SUCCESS_STATUS.getCode()) {
            return ReturnSet.success();
        }else{
            return ReturnSet.fail(result.get("msg").toString()).code(code);
        }
    }

    /**
     * 删除频道
     *
     * @param cid 频道id
     * @return 删除结果，如果报错，则包含错误信息
     */
    @Override
    @Transactional(readOnly = false)
    public ReturnSet deleteChannel(String cid) {
        JSONObject params = new JSONObject();
        params.put("cid",cid);
        JSONObject result = getRemoteProcessResult(CommonConstants.THIRD_VIDEO_DOMAIN+CommonConstants.DELETE_CHANNEL,params);
        int code = (int) result.get("code");
        if (CommonConstants.Status.SUCCESS_STATUS.getCode() == (Integer) result.get("code")){
            channelDao.deleteByChannelId(cid);
            log.info("频道删除成功，频道id："+cid);
            return ReturnSet.success();
        }else{
            log.error("删除频道["+cid+"]失败:"+code+"\t"+result.get("msg"));
            return ReturnSet.fail(result.get("msg").toString()).code(code);
        }
    }

    /**
     * 根据频道id获取频道信息
     *
     * @param cid 频道ID
     * @return 执行结果，获取到返回Channel对象，获取不到返回null
     */
    @Override
    public Channel getChannelById(String cid) {
        JSONObject params = new JSONObject();
        params.put("cid",cid);
        String remoteUrl = CommonConstants.THIRD_VIDEO_DOMAIN + CommonConstants.GET_CHANNEL;
        JSONObject result = getRemoteProcessResult(remoteUrl,params);
        if (CommonConstants.Status.SUCCESS_STATUS.getCode() != result.getInteger("code")){
            log.error("调用远程接口出错："+remoteUrl+"\t"+result.getString("msg"));
            return null;
        }else{
            JSONObject channelJson = (JSONObject)result.get("ret");
            Channel channel = getChannelFromJson(channelJson);
            return channel;
        }
    }

    /**
     * 获取频道列表
     *
     * @param pageable 分页器(页码起始值为1)
     * @param status   频道状态：0：空闲,1：直播，2：禁用，3：录制中
     * @return Page分页频道列表
     */
    @Override
    public Page getChannelList(Pageable pageable, int status) {
        JSONObject params = new JSONObject();
        // 单页记录数，默认值为10
        params.put("records",pageable.getPageSize());
        // 要取第几页，默认值为1
        params.put("pnum",pageable.getPageNumber());
        // 排序的域，支持的排序域为：ctime（默认）
        params.put("ofield",pageable.getSort().toString());
        Sort sort = pageable.getSort();
        for (Sort.Order order: sort){
            Sort.Direction direction = order.getDirection();
            if (direction.isAscending()){
                // 升序还是降序，1升序，0降序，默认为desc
                params.put("sort", 1);
            }
        }
        String remoteUrl = CommonConstants.THIRD_VIDEO_DOMAIN + CommonConstants.GET_CHANNEL_LIST;
        JSONObject result = getRemoteProcessResult(remoteUrl,params);
        if (CommonConstants.Status.SUCCESS_STATUS.getCode() != result.getInteger("code")){
            log.error("调用远程接口出错："+remoteUrl+"\t"+result.getString("msg"));
            return null;
        }else{
            JSONObject channelJson = (JSONObject)result.get("ret");
            List<Channel> resultList = getChannelListFromJson(channelJson);
            Page page = new PageImpl(resultList,pageable,resultList.size());
            return page;
        }
    }

    private List<Channel> getChannelListFromJson(JSONObject channelJsons) {
        List<Channel> resultList = new ArrayList<Channel>();
        JSONArray resultJsonArr = channelJsons.getJSONArray("list");
        for (int i=0 ;i<resultJsonArr.size();i++){
            JSONObject channelJson = (JSONObject) resultJsonArr.get(i);
            Channel channel = getChannelFromJson(channelJson);
            resultList.add(channel);
        }
        return resultList;
    }

    /**
     * 获取频道推流地址
     *
     * @param cid
     * @return
     */
    @Override
    public String getChannelPushAddress(String cid) {
        return null;
    }

    /**
     * 设置频道录制<br/>
     * 用户推流时，即可录制为视频文件。如无需改变频道录制状态，仅修改频道录制的格式、文件名、切片时长等信息，请调用2.20
     *
     * @param cid
     * @return
     */
    @Override
    public ReturnSet setChannelRecord(String cid) {
        return null;
    }

    /**
     * 停用频道
     *
     * @param cid
     * @return
     */
    @Override
    public ReturnSet pauseChannel(String cid) {
        return null;
    }

    /**
     * 批量禁用频道
     *
     * @param ids
     * @return
     */
    @Override
    public ReturnSet pauseChannel(String... ids) {
        return null;
    }

    /**
     * 恢复频道使用
     *
     * @param id
     * @return
     */
    @Override
    public ReturnSet resumeChannel(String id) {
        return null;
    }

    /**
     * 批量恢复使用频道
     *
     * @param ids
     * @return
     */
    @Override
    public ReturnSet resumeChannels(String... ids) {
        return null;
    }

    /**
     * 获取频道录制视频列表（分页返回）
     *
     * @param pageable
     * @param cid
     * @return
     */
    @Override
    public Page getChannelRecordFile(Pageable pageable, String cid) {
        return null;
    }

    /**
     * 获取频道录制视频列表（按时间范围返回）
     *
     * @param queryMap
     * @return
     */
    @Override
    public List getChannelRecordFileByTime(Map<String, String> queryMap) {
        return null;
    }

    /**
     * 设置视频录制回调地址
     *
     * @param reordClk 录制文件生成回调地址
     * @return
     */
    @Override
    public boolean setRecordFileCallback(String reordClk) {
        return false;
    }

    /**
     * 设置回调的加签秘钥<br/>
     * 用该秘钥对回调内容生成MD5签名，用于用户接口的校验。可以不设置，默认为“vcloud”。该秘钥对用户所有设置的回调地址生效
     *
     * @param signKey
     * @return
     */
    @Override
    public boolean setRecordFileClkSignKey(String signKey) {
        return false;
    }

    /**
     * 录制文件合并:<br/>
     * 对于同一次录制产生的切片文件，合并成一个文件，通过查询录制文件列表接口可获取。
     * 目前支持同MP4格式间或同flv格式间的文件合并，待合并文件的分辨率、音视频轨道数编码格式要求一致，
     * 且同时在合并的任务数不能超过3个, 待合并视频总时长不得超过8小时，1分钟接口调用不能超过10次。
     * 如果用户设置了回调地址，也会将合并好的视频回调给用户（回调内容不包含beginTime，endTime），参看接口2.14 （设置视频录制回调地址）
     *
     * @param outPutName    合并文件的名称(不能含有空格),无需包含文件后缀
     * @param recordFileIds 待合并的视频文件的ID列表(文件ID类型为long),视频文件数量限制为2-20个
     * @return
     */
    @Override
    public boolean mergeRecordFile(String outPutName, String... recordFileIds) {
        return false;
    }

    /**
     * 重置录制操作
     *
     * @param cid
     * @return
     */
    @Override
    public boolean resetRecord(String cid) {
        return false;
    }

    /**
     * 获取直播实时转码相关地址
     *
     * @param cid
     * @return
     */
    @Override
    public String getTranscodeAddress(String cid) {
        return null;
    }

    /**
     * 获取视频录制回调函数地址
     *
     * @return
     */
    @Override
    public String getRecordFileClkAddress() {
        return null;
    }

    /**
     * 设置录制信息：<br/>
     * 设置直播录制信息。可以设定直播录制的格式、切片时长、文件名等信息。如需设定频道是否进行录制，请调用2.7 设置频道为录制状态接口
     *
     * @param recordInfoMap
     * @return
     */
    @Override
    public boolean setRecordInfo(Map<String, Object> recordInfoMap) {
        return false;
    }

    public NeteaseVideoChannelServiceImpl() {
        super();
    }

    /**
     * 获取网易云直播公共参数
     * @return HttpPost对象
     */
    private HttpPost getHttpPost(String url){
        HttpPost post = new HttpPost(url);
        post.addHeader("AppKey",netEaseVidoeConfig.getAppKey());
        String nonce = netEaseVidoeConfig.getNonce();
        post.addHeader("Nonce", nonce);
        String curTime = netEaseVidoeConfig.getCurTime();
        String appSecret = netEaseVidoeConfig.getAppSecret();
        String checkSum = CheckSumBuilder.getCheckSum(appSecret,nonce,curTime);
        post.addHeader("CurTime", curTime);
        post.addHeader("CheckSum",checkSum);
        post.addHeader("Content-Type", "application/json;charset=utf-8");
        return post;
    }

    /**
     * 调用远程接口，并返回结果
     * @param url 远程接口调用地址
     * @param params 远程接口参数
     * @return 执行结果
     */
    private JSONObject getRemoteProcessResult(String url,JSONObject params){
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = getHttpPost(url);

        // 设置请求的参数
        httpPost.setEntity(new StringEntity(params.toString(), Consts.UTF_8));
        JSONObject object = null;
        // 执行请求
        try {
            HttpResponse response = httpClient.execute(httpPost);
            String stringBody = EntityUtils.toString(response.getEntity(), "utf-8");
            object =  JSONObject.parseObject(stringBody);
        } catch (Exception e) {
            log.error("调用远程接口失败，接口地址： "	+ url + ",参数 : " + params.toString());
            e.printStackTrace();
        }
        return object;
    }
}
