package com.team.videocloud.channel.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.team.videocloud.channel.po.Channel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 频道管理服务
 *
 * @author MingXiangjun
 * @create 2018-03-31 17:35
 **/
public interface ChannelService {
    /**
     * 创建频道
     * @param name 频道名称
     * @param type 频道类型
     * @return
     */
    public Channel createChannel(String name, int type);

    /**
     * 修改频道信息
     * @param name 频道名称
     * @param cid 频道id
     * @param type 频道类型
     * @return
     */
    public boolean updateChannel(String name,String cid,int type);

    /**
     * 删除频道
     * @param cid 频道id
     * @return
     */
    public JSONObject deleteChannel(String cid);

    /**
     * 根据频道id获取频道信息
     * @param cid
     * @return
     */
    public Map<String,Object> getChannelById(String cid);

    /**
     * 获取频道列表
     * @param pageable 分页器
     * @param status 频道状态：0：空闲,1：直播，2：禁用，3：录制中
     * @return
     */
    public Page getChannelList(Pageable pageable, int status);

    /**
     * 获取频道推流地址
     * @param cid
     * @return
     */
    public String getChannelPushAddress(String cid);

    /**
     * 设置频道录制<br/>
     *  用户推流时，即可录制为视频文件。如无需改变频道录制状态，仅修改频道录制的格式、文件名、切片时长等信息，请调用2.20
     * @param cid
     * @return
     */
    public boolean setChannelRecord(String cid);

    /**
     * 停用频道
     * @param cid
     * @return
     */
    public boolean pauseChannel(String cid);

    /**
     * 批量禁用频道
     * @param ids
     * @return
     */
    public boolean pauseChannel(String... ids);

    /**
     * 恢复频道使用
     * @param id
     * @return
     */
    public boolean resumeChannel(String id);

    /**
     * 批量恢复使用频道
     * @param ids
     * @return
     */
    public boolean resumeChannels(String... ids);

    /**
     * 获取频道录制视频列表（分页返回）
     * @param pageable
     * @param cid
     * @return
     */
    public Page getChannelRecordFile(Pageable pageable,String cid);

    /**
     * 获取频道录制视频列表（按时间范围返回）
     * @param queryMap
     * @return
     */
    public List getChannelRecordFileByTime(Map<String ,String > queryMap);

    /**
     * 设置视频录制回调地址
     * @param reordClk 录制文件生成回调地址
     * @return
     */
    public boolean setRecordFileCallback(String reordClk);

    /**
     * 设置回调的加签秘钥<br/>
     * 用该秘钥对回调内容生成MD5签名，用于用户接口的校验。可以不设置，默认为“vcloud”。该秘钥对用户所有设置的回调地址生效
     * @param signKey
     * @return
     */
    public boolean setRecordFileClkSignKey(String signKey);

    /**
     * 录制文件合并:<br/>
     * 对于同一次录制产生的切片文件，合并成一个文件，通过查询录制文件列表接口可获取。
     * 目前支持同MP4格式间或同flv格式间的文件合并，待合并文件的分辨率、音视频轨道数编码格式要求一致，
     * 且同时在合并的任务数不能超过3个, 待合并视频总时长不得超过8小时，1分钟接口调用不能超过10次。
     * 如果用户设置了回调地址，也会将合并好的视频回调给用户（回调内容不包含beginTime，endTime），参看接口2.14 （设置视频录制回调地址）
     * @param outPutName 合并文件的名称(不能含有空格),无需包含文件后缀
     * @param recordFileIds 待合并的视频文件的ID列表(文件ID类型为long),视频文件数量限制为2-20个
     * @return
     */
    public boolean mergeRecordFile(String outPutName,String... recordFileIds);

    /**
     * 重置录制操作
     * @return
     */
    public boolean resetRecord(String cid);

    /**
     * 获取直播实时转码相关地址
     * @param cid
     * @return
     */
    public String getTranscodeAddress(String cid);

    /**
     * 获取视频录制回调函数地址
     * @return
     */
    public String getRecordFileClkAddress();

    /**
     * 设置录制信息：<br/>
     * 设置直播录制信息。可以设定直播录制的格式、切片时长、文件名等信息。如需设定频道是否进行录制，请调用2.7 设置频道为录制状态接口
     * @param recordInfoMap
     * @return
     */
    public boolean setRecordInfo(Map<String ,Object> recordInfoMap);
}
