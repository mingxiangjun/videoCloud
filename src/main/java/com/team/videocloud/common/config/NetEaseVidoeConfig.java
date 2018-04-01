package com.team.videocloud.common.config;

import com.team.videocloud.common.util.UIDGenerator;
import com.team.videocloud.wangyi.util.CheckSumBuilder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 网易云直播配置文件
 *
 * @author MingXiangjun
 * @create 2018-03-31 17:42
 */
@Component
public class NetEaseVidoeConfig {
    // 开发者平台分配的AppKey
    @Value("${netease.public.appKey}")
    private String appKey;
    @Value("${netease.public.appSecret}")
    private String appSecret;
    // 随机数（随机数，最大长度128个字符
    private String nonce = null;
    // 当前UTC时间戳，从1970年1月1日0点0分0秒开始到现在的秒数
    private String curTime = null;

    public String getAppKey() {
        return appKey;
    }

    public String getNonce() {
        String uuid = UIDGenerator.getUUID();
        return uuid;
    }

    public String getCurTime() {
        this.curTime = String.valueOf((new Date()).getTime() / 1000L);
        return curTime;
    }

    public String getAppSecret() {
        return appSecret;
    }
}
