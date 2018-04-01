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
    @Column(name = "channel_id",unique = true, nullable = false)
    private String cid;
    private String name;
    private int type;
    @Column(name = "create_time")
    private long ctime;
    @Column(name = "push_url")
    private String pushUrl;
    @Column(name = "http_pull_url")
    private String httpPullUrl;
    @Column(name = "hls_pull_url")
    private String hlsPullUrl;
    @Column(name="rtmpP_pull_url")
    private String rtmpPullUrl;
}
