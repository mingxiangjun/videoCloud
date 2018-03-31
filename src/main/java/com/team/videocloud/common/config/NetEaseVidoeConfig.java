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
    @Value(value = "${netease.public.appKey}")
    private String appKey;
    @Value(value = "${netease.public.appSecret}")
    private String appSecret;
    // 随机数（随机数，最大长度128个字符
    private String nonce;
    // 当前UTC时间戳，从1970年1月1日0点0分0秒开始到现在的秒数
    private String curTime;
    // 服务器认证需要，SHA1(AppSecret+Nonce+CurTime)，16进制字符小写
    private String checkSum;

    public String getAppKey() {
        return appKey;
    }

    public String getNonce() {
        return UIDGenerator.getUUID();
    }

    public String getCurTime() {
        return String.valueOf((new Date()).getTime() / 1000L);
    }

    public String getCheckSum() {
        return CheckSumBuilder.getCheckSum(getAppSecret(),getNonce(),getCurTime());
    }

    public String getAppSecret() {
        return appSecret;
    }
}
