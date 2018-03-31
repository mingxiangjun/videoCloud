package com.team.videocloud.channel.controller;

import com.team.videocloud.channel.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 频道控制器
 *
 * @author MingXiangjun
 * @create 2018-03-31 18:14
 */
@Controller
@RequestMapping(value = "/channel")
public class ChannelController {
    @Autowired
    private ChannelService channelService;

    @RequestMapping(value = "/create")
    public void createChannel(@RequestParam(value = "name")String name,@RequestParam(value = "type") int type){
        channelService.createChannel(name,type);
    }
}
