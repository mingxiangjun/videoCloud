package com.team.videocloud.common;

/**
 * Copyright：DR Technology (Beijing) CO.,LTD
 * Author: DingRan
 * Date: 2018/3/29
 * Description:
 */
public final class CommonConstants {
    private CommonConstants() {
    }

    public final static String SESSION_ID = "d5uf9pl0c6";
    public static final String RANDOM_CODE = "RANDOMVALIDATECODEKEY";

    public enum Status{
        SUCCESS_STATUS(200,"操作成功"),
        FAIL_IDENTIFYFAIL(409,"用户登录认证失败"),
        FIAL_DELETE ( 603,"频道删除失败"),
        FIAL_CREATE ( 604,"频道添加失败"),
        FAIL_USER_NOT_EXIST(607,"用户信息不存在"),
        FAIL_CHANNEL_EMPTY_NAME(610,"频道名称为空"),
        FAIL_EXIST_CHANNEL_NAME(611,"频道名称已经存在"),
        FAIL_INVALID_CHANNEL_TYPE(612,"频道类型错误"),
        FAIL_INVALID_CHECKSUM(613,"CheckSum为空"),
        FAIL_INVALID_APPKEY(614,"AppKey为空"),
        FAIL_INVALID_CURTIME(615,"CurTime为空"),
        FAIL_INVALID_ACCOUNT_STATUS(635,"服务未开通，请联系商务申请开通！");
        private int code;
        private String msg;
        Status(int code,String msg){
            this.code=code;
            this.msg=msg;
        }
        public int getCode(){
            return code;
        }
        public String getMsg(){
            return msg;
        }
    }

    /**
     * 第三方云直播平台域名
     */
    public static final String THIRD_VIDEO_DOMAIN = "https://vcloud.163.com";
    /**
     * 直播频道创建API
     */
    public static final String CREATE_CHANNEL = "/app/channel/create";
    /**
     * 修改直播频道信息
     */
    public static final String UPDATE_CHANNEL="/app/channel/update";
    /**
     * 删除直播频道API
     */
    public static final String DELETE_CHANNEL="/app/channel/delete";
    /**
     * 获取直播频道信息
     */
    public static final String GET_CHANNEL="/app/channel/channelstats";
    /**
     * 获取直播频道列表
     */
    public static final String GET_CHANNEL_LIST="/app/channel/channellist";
    /**
     * 用户创建频道时获取的推流地址失效时，重新获取推流地址
     */
    public static final String GET_PUSH_ADDRESS="/app/address";
    /**
     * 设置频道为录制状态
     */
    public static final String SET_CHANNEL_ALWAYSRECORD="/app/channel/setAlwaysRecord";
    /**
     * 禁用(暂停)用户正在直播的频道
     */
    public static final String PAUSE_CHANNEL="/app/channel/pause";
    /**
     * 批量禁用用户正在直播的频道
     */
    public static final String PAUSE_CHANNEL_LIST="/app/channellist/pause";
    /**
     * 恢复被禁用的频道
     */
    public static final String RESUME_CHANNEL="/app/channel/resume";
    /**
     * 批量恢复频道
     */
    public static final String RESUME_CHANNEL_LIST="/app/channellist/resume";
    /**
     * 获取某频道录制视频文件列表，按生成时间由近至远提供分页
     */
    public static final String GET_CHANNEL_VIDEO_LIST="/app/videolist";
    /**
     * 通过开始和结束的时间点，获取某频道录制视频文件列表。(时间跨度不能超过1周)
     */
    public static final String GET_CHANNEL_VIDEO_LIST_BY_TIME="/app/vodvideolist";
    /**
     * 设置视频录制回调地址:
     *  用户录制文件生成后，会将生成文件信息推送到该地址, 目前支持HTTP POST,HTTPS POST方式
     */
    public static final String SET_VIDEO_CALLBACK="/app/record/setcallback";
    /**
     * 设置回调的加签秘钥
     */
    public static final String SET_SIGN_KEY="/app/callback/setSignKey";
    /**
     * 合并录制文件
     */
    public static final String MERGE_VIDEO_FILE="/app/video/merge";
    /**
     * 录制重置：在直播录制过程中，结束正在进行的录制，开启一个新的录制任务。可用来对录制进行主动分片
     */
    public static final String RESET_RECORD="/app/channel/resetRecord";
    /**
     * 获取直播实时转码相关地址
     */
    public static final String GET_TRANSCODE_ADDRESS="/app/transcodeAddress";
    /**
     * 视频录制回调地址查询
     */
    public static final String GET_RECORD_CALLBACK="/app/record/callbackQuery";
    /**
     * 设置录制信息
     */
    public static final String SET_RECORD_INFO="/app/channel/setupRecordInfo";
}
