package com.team.videocloud.channel.po;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 直播频道信息
 *
 * @author MingXiangjun
 * @create 2018-04-01 10:28
 */
@Entity
@Table(name = "channel")
@Accessors
@Getter
@Setter
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    /**
     * 频道远程id
     */
    @Column(name = "channel_id",unique = true, nullable = false)
    private String cid;
    /**
     * 频道名称
     */
    private String name;
    /**
     * 频道类型 ( 0 : rtmp, 1 : hls, 2 : http)
     */
    private int type;
    /**
     * 频道状态（0：空闲； 1：直播； 2：禁用； 3：直播录制）
     */
    private int status;
    /**
     * 1-开启录制； 0-关闭录制
     */
    private int needRecord;
    /**
     * 1-flv； 0-mp4
     */
    private int format;
    /**
     * 录制切片时长(分钟)，默认120分钟
     */
    private int duration;
    /**
     * 录制后文件名
     */
    private String filename;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private long ctime;
    /**
     * 推流地址
     */
    @Column(name = "push_url")
    private String pushUrl;
    /**
     * http拉流地址
     */
    @Column(name = "http_pull_url")
    private String httpPullUrl;
    /**
     * hls拉流地址
     */
    @Column(name = "hls_pull_url")
    private String hlsPullUrl;
    /**
     * rtmp协议拉流地址
     */
    @Column(name="rtmpP_pull_url")
    private String rtmpPullUrl;
}
