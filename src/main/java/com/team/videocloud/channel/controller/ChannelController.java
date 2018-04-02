package com.team.videocloud.channel.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.team.videocloud.channel.po.Channel;
import com.team.videocloud.channel.service.ChannelService;
import com.team.videocloud.common.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public String createChannel(@RequestParam(value = "name")String name,@RequestParam(value = "type") int type){
        Channel result = channelService.createChannel(name,type);
        return JSONObject.toJSON(result).toString();
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public String deleteChannel(@RequestParam(value = "channelId") String channelId){
        JSONObject result = channelService.deleteChannel(channelId);
        return result.toString();
    }
}
