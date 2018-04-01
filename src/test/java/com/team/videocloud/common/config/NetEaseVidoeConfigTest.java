package com.team.videocloud.common.config;

import com.team.videocloud.wangyi.util.CheckSumBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class NetEaseVidoeConfigTest {
    @Autowired
    NetEaseVidoeConfig netEaseVidoeConfig;
    @Test
    public void getAppKey() throws Exception {
        String appKey = netEaseVidoeConfig.getAppKey();
        System.out.println("dangqianAppKey:"+appKey);
        String appSecret = netEaseVidoeConfig.getAppSecret();
        String nonce = netEaseVidoeConfig.getNonce();
        String curTime = netEaseVidoeConfig.getCurTime();
        System.out.println("计算出来的结果："+CheckSumBuilder.getCheckSum(appSecret,nonce,curTime));
    }

    @Test
    public void getNonce() throws Exception {
    }

    @Test
    public void getCurTime() throws Exception {
    }

    @Test
    public void getCheckSum() throws Exception {
    }

    @Test
    public void getAppSecret() throws Exception {
    }

}