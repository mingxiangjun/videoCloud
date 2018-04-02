package com.team.videocloud.channel.dao;

import com.team.videocloud.channel.po.Channel;
import com.team.videocloud.common.util.UIDGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ChannelDaoTest {
    @Autowired
    private ChannelDao channelDao;
    @Test
    @Transactional
    public void deleteByChannelId() throws Exception {
        channelDao.deleteByChannelId("6b2387eb-5f3b-4e7e-ad39-a7c385aba388");
    }

    @Test
    public void saveChannel(){
        Channel channel = new Channel();
        channel.setCid(UIDGenerator.getUUID());
        channel.setHlsPullUrl(UIDGenerator.getUUID());
        channelDao.save(channel);
    }
}