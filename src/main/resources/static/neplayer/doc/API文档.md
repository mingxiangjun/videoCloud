# API文档

> 如果你是第一次使用neplayer，请先阅读 [开发指南](./开发指南)

## Constructor
```
neplayer( idString, [options], [ready]);
```
#### Parameters
name | type | required | description
:------|:------|:------|:------
idString | String | yes | video标签的id属性值
options | Object | no | 初始化参数对象，详情见下
ready | Function | no | 播放器初始化完成时的回调函数
```
@params options
{
    "controls": true， //是否显示控制条
    "autoplay": true， //是否自动播放(ios不支持自动播放)
    
    /*预加载选项*/
    "preload": "auto",
    /*
    'auto'预加载视频（需要浏览器允许）;
    'metadata'仅预加载视频meta信息;
    'none'不预加载;
    */
    
    "poster": "myPoster.jpg", //视频播放前显示的图片
    "loop": true, //是否循环播放
    "width": 640, //设置播放器宽度
    "height": 480, //设置播放器高度
    
    "techOrder": ["flash", "html5"], //优先使用的播放模式
    "streamTimeoutTime": 30 * 1000, //拉流超时时间，默认30s
    /*设置播放器控件*/
    controlBar: { 
        playToggle: false
    }
    /*
    //设置不显示大播放按钮
    bigPlayButton:false,
     */
    /*默认播放器控件列表
    posterImage
    textTrackDisplay
    loadingSpinner
    bigPlayButton
    controlBar
        playToggle
        volumeMenuButton
        currentTimeDisplay
        timeDivider
        durationDisplay
        progressControl
            seekBar
                loadProgressBar
                mouseTimeDisplay
                playProgressBar
        liveDisplay
        remainingTimeDisplay
        customControlSpacer
        playbackRateMenuButton
        chaptersButton
        descriptionsButton
        subtitlesButton
        captionsButton
        audioTrackButton
        fullscreenToggle
    */
}
```
关于播放器控件的介绍以及如何自定义播放器样式和控件的说明，请参见 <a href="http://vcloud.163.com/vcloud-sdk-manual/WebDemos/LivePlayer_Web/introToComponent.html" target="_blank">网易云视频web播放器样式和组件自定义</a>

##  Methods

> 请在播放器初始化完成后再调用api接口，推荐使用播放器的初始化完成回调函数。

### getCurrentPosition()
获取当前播放位置（单位毫秒）
```
var currentPos = myPlayer.getCurrentPosition();
```
###  getDuration()
获取视频总时长（单位毫秒）
```
var duration = myPlayer.getDuration();
```
###  getPlayableDuration()
获取当前已缓存位置的时间点（单位毫秒）
```
var buffered = myPlayer.getPlayableDuration();
```
###  getVersion()
获取sdk版本号
```
var version = myPlayer.getVersion();
```
### getVideoHeight()
获取视频高度
```
var height = myPlayer.getVideoHeight();
```
###  getVideoWidth()
获取视频宽度
```
var width = myPlayer.getVideoWidth();
```
###  play()
开始播放
```
myPlayer.play();
```
### pause()
暂停播放
```
myPlayer.pause();
```
###  reset()
重置播放器
```
myPlayer.reset();
```
###  release()
释放播放器所有资源
```
myPlayer.release();
```
###  seekTo(msec)
设置到指定时间点播放
#### Parameters
 name | type | required | description
:------|:------|:------|:------
 msec | Number | yes | 要跳转到的播放时间（单位毫秒）
```
myPlayer.seekTo(12345);
```
###  setDataSource(source)
设置数据源
#### Parameters
 name | type | required | description
:------|:------|:------|:------
 source | String &brvbar; Object &brvbar; Array | yes | 要设置的数据源
数据源支持三种格式：

* URL字符串(不推荐)
注意：请确保当前播放器模式(HTML5/Flash)支持这个url提供的视频，否则请使用数据源对象格式
```
myPlayer.setDataSource("http://www.example.com/path/to/video.mp4");
```
* 数据源对象
播放器会根据提供的视频格式自动切换播放模式(HTML5/Flash)
```
myPlayer.setDataSource({
    type: "video/mp4",
    src: "http://www.example.com/path/to/video.mp4"
});
```
* 包含多个不同视频格式数据源对象的数组
播放器会依次尝试，最终找到支持的格式进行播放

```
myPlayer.setDataSource([
    {type: "video/mp4",src: "http://www.example.com/path/to/video.mp4"},
    {type: "video/x-flv",src: "http://www.example.com/path/to/video.flv"},
    {type: "application/x-mpegURL",src: "http://www.example.com/path/to/video.hls"}
]);
```
*注意：如果要在播放器初始化完成后立即调用，必须将setDataSource写在播放器初始化完成时的回调函数中*

**如果要播放点播flv加密视频，需要提供额外的解密相关信息**

```
    // 播放点播flv加密视频，需要增加字段decryptInfo
    // decryptInfo字段值是一个对象，包含了transferToken, accid, token, appKey等信息
    // 这些信息需要用户通过自己的应用服务器获取
    var option = { 
        "type": "video/x-flv", 
        "src": "http://domain.vod.xxx/path/to/some.flv",
        "decryptInfo" : {
            "transferToken":"tag=xxx&time=xxx&expire=xxx&nonce=xxx&algo=1&checksum=xxx",
            "accid": "xxx",
            "token": "xxx",
            "appKey": "xxx"
        }
    }
    myPlayer.setDataSource(option);

```

### setMute(mute)
设置是否静音，如果没有参数则返回当前静音状态(true/false)

 name | type | required | description
:------|:------|:------|:------
 mute | Boolean | no | 是否静音
```
myPlayer.setMute(true)
```
### 3.14 setVolume(volume)
设置音量
#### Parameters
 name | type | required | description
:------|:------|:------|:------
 volume | Number | yes | 要设置的音量（0 ~ 1）
```
myPlayer.setVolume(0.5);
```
### getVolume()
获取当前音量（0 ~ 1）
```
var volume = myPlayer.getVolume();
```
### resize( width, height)
设置播放器宽高
#### Parameters
 name | type | required | description
:------|:------|:------|:------
 width | Number | yes | 要设置的宽度
 height | Number | yes | 要设置的高度
```
myPlayer.resize( 640, 480);
```
### enterFullscreen()
全屏播放
```
myPlayer.enterFullscreen();
```
### exitFullscreen()
退出全屏
```
myPlayer.exitFullscreen();
```
### getPlayState()
获取播放状态（1：播放中；2：暂停中；3：播放结束）
```
var playState = myPlayer.getPlayState();
```
### onPlayState( stateCode, [func])
注册播放状态回调函数
#### Parameters
 name | type | required | description
:------|:------|:------|:------
 stateCode | Number | yes | 要注册的播放状态 （1：播放中；2：暂停中；3：播放结束）
 func | function | yes | 要注册的回调函数
```
myPlayer.onPlayState(1,function(){
    console.log('play');
});
myPlayer.onPlayState(2,function(){
    console.log('pause');
});
myPlayer.onPlayState(3,function(){
    console.log('ended');
});
```
### refresh()
重新拉流并播放
```
myPlayer.refresh();
```
### on()
监听播放相关的事件。 有哪些事件可以监听？ 参见<a href="http://vcloud.163.com/vcloud-sdk-manual/WebDemos/LivePlayer_Web/introToComponent.html" target="_blank">网易云视频web播放器样式和组件自定义</a>
```
myPlayer.on("playing",function(){
    console.log("playing");
})
```
### off()
解除事件监听
### onError([func])
注册错误回调函数
#### Parameters
 name | type | required | description
:------|:------|:------|:------
 func | function | yes | 要注册的回调函数
```
/*
播放器在调用错误回调函数时，会向回调函数传递一个错误对象参数
{
    errCode: //错误代码
    errMsg: //错误信息
}
*/
myPlayer.onError(function(err){
    console.log(err.errCode);
    console.log(err.errMsg);
});
```
可能的错误代码：

 errCode | errMsg
:------|:------
 1 | You aborted the media playback
 2 | A network error caused the media download to fail part-way.
 3 | The media playback was aborted due to a corruption…media used features your browser did not support.
 4 | The media could not be loaded, either because the server or network failed or because the format is not supported.
 5 | The media is encrypted and we do not have the keys to decrypt it.
 6 | 请勿使用推流地址拉流
 7 | 拉流超时
