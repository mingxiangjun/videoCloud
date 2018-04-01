var cameraList;
var microPhoneList;
var myPublisher;

$(function () {

    var viewOptions = {
        videoWidth: 640,    // Number 可选 推流分辨率 宽度 default 640
        videoHeight: 480,   // Number 可选 推流分辨率 高度 default 480
        fps: 15,            // Number 可选 推流帧率 default 15
        bitrate: 600,       // Number 可选 推流码率 default 600
        video: true,       // Boolean 可选 是否推流视频 default true
        audio: true       // Boolean 可选 是否推流音频 default true
    };
    var flashOptions = {
        previewWindowWidth: 862,    // Number 可选 预览窗口宽度 default 862
        previewWindowHeight: 486,   // Number 可选 预览窗口高度 default 446

        /* String 可选 flash显示模式 default transparent
            'window' 窗口模式，flash影片是存在于Windows中的一个显示实例，独立于浏览器的HTML渲染层，总是会遮住位置与他重合的所有HTML层
            'opaque' 无窗口模式，flash影片是浏览器HTML渲染层中的一个元素，可以通过z-index来控制是否遮盖其他HTML元素
            'transparent' 透明模式，flash影片的背景色alpha值将为0，并且只会绘制stage上真实可见的对象，也可以通过z-index来控制是否遮盖其他HTML元素
        */
        wmode: 'transparent',

        /* String 可选 flash质量 default high
            'low'    低
            'medium' 中
            'high'   高（推荐）
            'best'   极高
         */
        quality: 'high',

        /* String 可选 flash执行脚本许可 default always
            'always' 总是允许flash执行js脚本
            'never' 总是禁止flash执行js脚本
            'samedomain' 只用当flash和js脚本来自同域时才允许flash执行js脚本
         */
        allowScriptAccess: 'always'
    }
    // initPublisher();
    // setTimeout("initPublisher()",3000);
})
function initPublisher() {
    myPublisher = new nePublisher("publisher",
        function () {
            cameraList = this.getCameraList();
            console.log("当前摄像头信息："+cameraList);
            microPhoneList = this.getMicroPhoneList();
            console.log("当前麦克风信息："+microPhoneList);
        },function (code, desc) {
            alter("加载失败，错误信息："+desc);
        });
}

function createChannel() {
    var channelName = $("#channelName").val();
    var channelType = $("#channelType").val();
    var params = {
        "name":channelName,
        "type":channelType
    };
    $.ajax({
        "url":"/channel/create",
        "data":params,
        "dataType":"json",
        "success":function(data){
            alert(data);
            var channelInfo = JSON.parse(data);
            $("#rtmpPullUrl").val(channelInfo.rtmpPullUrl);
        },
        "error":function (e) {
            alert(e);
        }
    });
}

function deleteChannel() {
    var channelId = $("#channelId").val();
    var params = {
        "channelId":channelId,
    };
    $.ajax({
        "url":"/channel/delete",
        "data":params,
        "dataType":"json",
        "success":function(){

        },
        "error":function (e) {
            alert(e);
        }
    });
}