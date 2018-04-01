package com.team.videocloud.channel.dao;

import com.team.videocloud.channel.po.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author MingXiangjun
 * @create 2018-04-01 10:39
 **/
public interface ChannelDao extends JpaRepository<Channel,Long>{
    @Query(value = "delete from Channel c where c.cid=:channelId")
    public void deleteByChannelId(@Param(value = "channelId") String channelId);
}
