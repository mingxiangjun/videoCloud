/**
 * Copyright: cniaas
 * Author：Zhangly
 * Date: 2014/9/11.
 * Description:
 */

function caculateSumCost(data,productsInfo){

    var cycle = data.time;
    var products = {};
    products["cpu"]=data.cpu;
    products["mem"]=data.memory  / 1024;
    products["bandwidth"]=data.bandwidth;
    products["disk"]=data.harddisk;

    var sum = 0;
    for(var obj in productsInfo){
        if(productsInfo[obj][0].productCat.billType == "Nonlinear") {
            for (var i = 0; i < productsInfo[obj].length; i++) {
                if (productsInfo[obj][i].billUnitCount == products[obj]) {
                    sum = sum + productsInfo[obj][i].price * cycle;
                }
            }
        }else{
            if( obj == "disk"){
                sum = sum + productsInfo[obj][0].price * cycle * products[obj] / 10;
            } else if(obj = "bandwidth"){
                var bandWidthCost = 0;
                for(var j=0;j<productsInfo[obj].length;j++){
                    if(products[obj] >= productsInfo[obj][j].unitCountMin ){
                        if (products[obj] >= productsInfo[obj][j].unitCountMax ){
                            bandWidthCost = bandWidthCost + (productsInfo[obj][j].unitCountMax - productsInfo[obj][j].unitCountMin + 1) * productsInfo[obj][j].price;
                        }else if(products[obj] < productsInfo[obj][j].unitCountMax ){
                            bandWidthCost = bandWidthCost + (products[obj] - productsInfo[obj][j].unitCountMin + 1) * productsInfo[obj][j].price;
                        }
                    }else{
                        break;
                    }
                }
                sum  = sum + bandWidthCost;
            }
        }
    }
    return sum;
}

$(function() {
    var bandwidthSlider = $( "#bandwidth-slider" ).slider({
        min:0,
        max:200,
        step:1,
        value:1,
        slide: function(event,ui){
            console.log(ui.value);
            $('input[name="band-width"]').val(ui.value);
            if(ui.value > 10){
                bandwidthSlider.slider({step:10});
            }
        }
    });

    $('input[name="band-width"]').val($("#bandwidth-slider").slider("value"));

    $('input[name="band-width"]').change(function() {
        if(this.value>=0 && this.value <= 200){
            bandwidthSlider.slider("value", this.value);
        }
    });
});

$(function() {
    var diskSlider = $( "#disksize-slider" ).slider({
        min:0,
        max:1000,
        step:10,
        value:0,
        slide: function(event,ui){
            console.log(ui.value);
            $('input[name="disk-size"]').val(ui.value);
        }
    });
    $('input[name="disk-size"]').val($("#disksize-slider").slider("value"));
    //值改变时赋值滑块
    $('input[name="disk-size"]').change(function() {
        if(this.value>=0 && this.value <= 200){
            diskSlider.slider("value", this.value);
        }
    });
});

; (function ($) {
    var _oBaseConfig = {//数据配置对象
        "cpu": {
            "cpu_1":{
                "value": 1,
                "text": "1核",
                "arrMemory": [{"value":1024,"text":"1G"},{"value":2048, "text":"2G"},{"value":4096,"text":"4G" },{"value":8192,"text":"8G" }]
            },
            "cpu_2":{
                "value": 2,
                "text": "2核",
                "arrMemory": [{"value":1024,"text":"1G"},{"value":2048,"text":"2G"},{"value":4096, "text":"4G"},{"value":8192,"text":"8G" }]
            },
            "cpu_4":{
                "value": 4,
                "text": "4核",
                "arrMemory": [{"value":4096, "text":"4G"},{"value":8192,"text":"8G" },{"value":12288, "text":"12G"},{"value":16384,"text":"16G" }]
            },
            "cpu_8":{
                "value": 8,
                "text": "8核",
                "arrMemory": [{"value":8192,"text":"8G" },{"value":12288, "text":"12G"},{"value":16384,"text":"16G" },{"value":24576, "text":"24G"},{"value":32768,"text":"32G" }]
            }
        }
    }
    /***********************CPU内存管理********************************/
    var oCpuManager={
        init:function(costFn){
            this.eCpuList=$('#uc-cpu');//cpu容器标签
            this.eMemoryList=$('#uc-ram');//内存容器标签
            this.eCpuHidden=$('#uc-cpu-hidden');//cpu表单隐藏标签
            this.eMemoryHidden=$('#uc-ram-hidden');//内存表单隐藏标签
            this.eMemoryAlert=$('#uc-ram-alert');
            this.cupTemplateFn=_.template(this._cpuTemplate);//cpu渲染模板方法
            this.memorylateFn=_.template(this._memoryTemplate);//内存渲染模板方法
            this.bAlert=false;
            this.iCurCpuValue=1;
            this.iAlert=this.iCurMemoryValue=1024;
            this.oCpuData=_oBaseConfig['cpu'];
            this.costFn =costFn;
            this._renderCpu();
        },
        _renderCpu:function(){//渲染CPU
            var sKey='',oCpu=null,arrHTML=[];

            for (sKey in this.oCpuData) {
                oCpu=this.oCpuData[sKey];
                arrHTML.push(this.cupTemplateFn(oCpu));
            }
            this.eCpuList.html(arrHTML.join(''));

            $('a[data-value="'+ this.iCurCpuValue+'"]',this.eCpuList).addClass('current');
            this._resetCpu();
            this.renderMemory();
            this.bind();
        },
        renderMemory:function(){//渲染内存
            var oCpu=this.oCpuData['cpu_'+this.iCurCpuValue],
                arrMemory=oCpu['arrMemory'],
                iLen=arrMemory.length,
                oTemp=null, i = 0,arrHTML=[];

            for (; i < iLen; i++) {
                oTemp=arrMemory[i];
                arrHTML.push(this.memorylateFn(oTemp));
            }
            this.eMemoryList.html(arrHTML.join(''));
            this._resetMemory();
        },
        _resetCpu:function(){
            $('.current',this.eCpuList).removeClass('current');
            $('a[data-value="'+ this.iCurCpuValue+'"]',this.eCpuList).addClass('current');
        },
        _resetMemory:function(){
            var eCur=$('a[data-value="'+ this.iCurMemoryValue+'"]',this.eMemoryList);
            $('.current',this.eMemoryList).removeClass('current');
            if(eCur.length>0){
                eCur.addClass('current');
            }else{
                eCur=this.eMemoryList.children().first();
                eCur.addClass('current');
                this.iCurMemoryValue=eCur.attr('data-value');
            }
            this._resetData();
        },
        _resetData:function(){
            this.eCpuHidden.val(this.iCurCpuValue);
            this.eMemoryHidden.val(this.iCurMemoryValue);
            this._alert();
        },
        _alert:function(){//内存信息提示
            if(this.iCurMemoryValue == this.iAlert && !this.bAlert){
                this.eMemoryAlert.height(0).animate({
                    height:26
                },.3E3);
                this.bAlert=true;
            }else if(this.bAlert){
                this.eMemoryAlert.animate({
                    height:0
                },.3E3);
                this.bAlert=false;
            }
        },
        bind:function(){
            var _this=this;
            this.eCpuList.click(function(event){//添加cpu容器点击事件
                var oTaget=event.target,iDataValue=$(oTaget).attr('data-value');
                if(iDataValue && _this.iCurCpuValue !==iDataValue){
                    _this.iCurCpuValue=iDataValue;
                    _this._resetCpu();
                    _this.renderMemory();
                    _this.costFn &&  _this.costFn();
                }
            });
            this.eMemoryList.click(function(event){//添加内存容器点击事件
                var oTaget=event.target,iDataValue=$(oTaget).attr('data-value');
                if(iDataValue && _this.iCurMemoryValue !==iDataValue){
                    _this.iCurMemoryValue=iDataValue;
                    _this._resetMemory();
                    _this.costFn &&  _this.costFn();
                }
            })
        },
        getData:function(){
            return {'cpu':this.iCurCpuValue,'memory':this.iCurMemoryValue}
        },
        _cpuTemplate:'<a href="javascript:;" data-value="<%=value%>" class="unit" hidefocus=""><%=text%></a>',
        _memoryTemplate:'<a href="javascript:;" data-value="<%=value%>" class="unit" hidefocus=""><%=text%></a>'
    }
    /***********************滑动条***********************************/
    function SilderBar(eC,eText,opts){
        this.eC=eC||$('');
        this.eText=eText||$('');
        this.init(opts);
    }
    SilderBar.prototype={
        constructor:SilderBar,
        init:function(opts){
            this.eRange=$('.range',this.eC);
            this.eDragBtn=$('.drag',this.eRange);
            this.eContainer=$('.container',this.eC);

            this.iMaxW=this.eC.width();
            this.iLeft=this.eC.offset()['left'];
            this.iDragW=this.eDragBtn.width()/2+2;//拖拽条框度

            this.opts=opts;
            this.arrUnitCount=this.opts.unit||1;
            this.iSelData=this.opts.iInitData||1;
            this.iUnit=this.opts.iUnit;
            this.costFn =this.opts.costFn;
            this.switchData=this.opts.switchData;
            this.iMin=this.opts.iMin||0;

            this.initRender();
            this.bind();
            this.initBarPos(this.iSelData);
        },
        initRender:function(){
            var _this=this;
            this.arrCUnit=$('.unit',this.eContainer); //选择区域块
            this.arrEUnit=$('.block',this.eRange);//内容区域块
            this.arrUnitWidth=[];

            this.arrEUnit.each(function(i,item){
                var eItem=$(item).attr('data-index',i),iW=eItem.width();
                _this.arrUnitWidth.push(iW);
                _this.arrCUnit.eq(i).css('width',iW).attr('data-index',i);
            });

            this.iMaxValue=(function(){
                var iCount=0;
                $.each(_this.arrUnitCount,function(i,item){
                    iCount+=item;
                });
                return iCount;
            })();
        },
        initBarPos:function(iSelData){
            var iSection=0, iUnit = 0,iW=0,iLen=this.arrUnitCount.length,iTemp=0,iCut=0;
            iSelData =iSelData/this.iUnit;
            if(iSelData>this.iMaxValue)iSelData=this.iMaxValue;
            for(;iSection<iLen;iSection++){
                iTemp+=this.arrUnitCount[iSection];
                if(iSelData<=iTemp)break;
                iW+=this.arrUnitWidth[iSection];
                iCut +=this.arrUnitCount[iSection];
            }
            iSection=iSection==iLen?iSection-1:iSection;
            iUnit=this.arrUnitWidth[iSection]/this.arrUnitCount[iSection];
            iW +=(iSelData-iCut)*iUnit
            iW = iW ==0?-7:iW ;
            this.sliderAnimateTo(iW-this.iDragW,iW-this.iDragW,iSection,iSelData-iCut);
        },
        bind:function(){//事件绑定
            var _this=this;
            this.eDragBtn.bind('drag',function(event){
                _this._dragFn(event);
            });
            this.eDragBtn.bind('dragend',function(event){
                _this._dragEndFn(event);
            });
            this.arrCUnit.click(function(event){
                _this.clickFn(event,$(this).attr('data-index'));
            });
            this.arrEUnit.click(function(event){
                _this.clickFn(event,$(this).attr('data-index'));
            });
            this.eText.bind('blur',function(event){
                _this._fillBackFn($(this).val());
            })
        },
        _dragFn:function(event){
            var oPos=this._getDragPos(event);
            this.eDragBtn.css('left',oPos['left']);
            this.eContainer.css('width',oPos['width']);
            this._setSelectedData(oPos['index'],oPos['count']);
        },
        _dragEndFn:function(event){
            var oPos=this._getDragPos(event);
            if(oPos['left']>0){
                this.sliderAnimateTo(oPos['iRelLeft'],oPos['iRelLeft'],oPos['index'],oPos['count']);
            }else{
                this.sliderAnimateTo(this.iMin*oPos['iUnit']-this.iDragW,this.iMin*oPos['iUnit']-this.iDragW,0,this.iMin);
            }
            this.costFn && this.costFn();
        },
        _fillBackFn:function(sValue){
            sValue =$.trim(sValue);
            if(sValue=='' ||/\D/g.test(sValue)||sValue< this.iUnit){
                sValue=0;
            }
            this.initBarPos(sValue);
        },
        clickFn:function(event,iIndex){
            var iStartRLeft=this._getStartLeft(iIndex),
                iX=event.pageX-iStartRLeft,
                iUnit = this.arrUnitWidth[iIndex]/this.arrUnitCount[iIndex],
                iCounts=0,iRLeft=0;

            iX =iX <this.iMaxW?iX:this.iMaxW,
                iCounts=Math.ceil(iX/iUnit);
            iRLeft=iCounts*iUnit+iStartRLeft-this.iLeft-this.iDragW;

            this.sliderAnimateTo(iRLeft,iRLeft,iIndex,iCounts);
            this.costFn && this.costFn();
        },
        _getStartLeft:function(iIndex){
            var iLeft=this.iLeft;
            for (var i = 0; i < iIndex; i++) {
                iLeft+=this.arrUnitWidth[i];
            }
            return iLeft;
        },
        _getDragPos:function(event){//获取拖拽位置（矫正）
            var iW=event.pageX-this.iLeft,iLeft=iW;
            iW =iW>0?iW:(iLeft=-this.iDragW,0);
            iW =iW <this.iMaxW?iW:(iLeft=this.iMaxW ,this.iMaxW),
                iToalL=0,iLen=this.arrUnitWidth.length,iCount=0,iCut=0,iUnit=0;

            for (var i = 0; i < iLen; i++) {
                iToalL +=this.arrUnitWidth[i];
                if(iW<iToalL) break;
                iCut +=this.arrUnitWidth[i];
            }
            if(i==iLen){
                i=iLen-1;
                iCount=this.arrUnitCount[i];
            }else{
                iUnit=this.arrUnitWidth[i]/this.arrUnitCount[i];
                iCount=Math.ceil((iW-iCut)/iUnit);
            }
            return {'width':iW,'left':iLeft- this.iDragW,'index':i,'count':iCount,"iUnit":iUnit,"iRelLeft":iCut+iUnit*iCount- this.iDragW};
        },
        sliderTo:function(iLeft,iW){
            this.eDragBtn.css('left',iLeft);
            this.eContainer.css('width',iW);
            this._setSelectedData(iIndex,iCounts);
        },
        sliderAnimateTo:function(iLeft,iW,iIndex,iCounts){
            this.eDragBtn.stop().animate({
                'left':iLeft
            });
            this.eContainer.stop().animate({
                'width':iW
            });
            this._setSelectedData(iIndex,iCounts);
        },
        _setSelectedData:function(iIndex,iCounts){
            for (var i = 0; i < iIndex; i++) {
                iCounts +=this.arrUnitCount[i]
            }
            this.iSelData=iCounts*this.iUnit;
            if(this.switchData){
                this.iSelData=this.switchData(this.iSelData)||this.iSelData;
            }
            this.eText.val(this.iSelData);
        },
        getData:function(){
            return this.iSelData;
        }
    }
    /***********************带宽管理********************************/
    var oBandwidthManager={
        init:function(costFn){
            var _this=this;
            this.costFn =costFn;
            this.oSilderBar=new SilderBar($('#uc-band'),$('#uc-band-value'),{
                unit:[5,45,50],
                iInitData:1,
                iUnit:1,
                costFn:function(){
                    _this.costFn &&  _this.costFn();
                }
            });
        },
        getData:function(){
            return this.oSilderBar.getData();
        }
    }
    /***********************磁盘大小管理********************************/
    var oHardDiskManager={
        init:function(costFn){
            var _this=this;
            this.costFn =costFn;
            this.oSilderBar=new SilderBar($('#hard-disk'),$('#hard-disk-value'),{
                unit:[10,15,25],
                iInitData:10,
                iUnit:10,
                costFn:function(){
                    _this.costFn &&  _this.costFn();
                }
            });
        },
        getData:function(){
            return this.oSilderBar.getData();
        }
    }
    /***********************购买时长管理********************************/
    var oTimeManager={
        init:function(costFn,time){
            var _this=this;
            this.costFn =costFn;
            var realTime = time?time:1;
            this.oSilderBar=new SilderBar($('#uc-duration'),$('#uc-duration-value'),{
                unit:[1,1,1,1,1,1,1,1,1,1,1,1,1,1],
                iInitData:realTime,
                iUnit:1,
                iMin:1,
                costFn:function(){
                    _this.costFn &&  _this.costFn();
                },
                switchData:function(iValue){
                    return _this.switchData(iValue);
                }
            });
        },
        getData:function(){
            return this.switchData(this.oSilderBar.getData());
        },
        switchData:function(iValue){
            switch (String(iValue)) {
                case '13':{//代表2年
                    return '24'
                }
                case '14':{//代表3年
                    return '36'
                }
            }

            return iValue;
        }
    }

    /***********************管理********************************/
    var oManager={
        init:function(time){
            var _this=this;
            this.bInit=true;
            this.productsInfo = {};
            this.productsId='';


            this.changeTime();

            oCpuManager.init(function(){
                _this.setCost();
            });
            oBandwidthManager.init(function(){
                _this.setCost();
            });
            oHardDiskManager.init(function(){
                _this.setCost();
            });
            oTimeManager.init(function(){
                _this.setCost();
            });
            this.bind();
        },
        bind:function(){
            var _this=this;

            $('#submit-btn').click(function(){


                    //TODO:表单提交
                    //$('#form-vm').submit();
                    var  cycle = oTimeManager.getData();
                    doPost("/action/orderInfo/buy",{productId:_this.productsId,billCycle:cycle},function(objs){

                    });

            });

            this.bInit=false;
        },
        getData:function(){
            var oCup=oCpuManager.getData();
            return {
                'cpu':oCup['cpu'],//cup大小
                'memory':oCup['memory'],//内存大小
                'bandwidth':oBandwidthManager.getData(),//带宽
                'harddisk':oHardDiskManager.getData(),//磁盘大小
                'time':oTimeManager.getData()//购买时间
            }
        },
        changeTime:function(){
            var _this=this;
            var  cycle = 1;
            doPost("/action/product/findProduct", {"billCycle":cycle}, function (objs) {
                if(objs.httpCode == "200"){
                    _this.productsInfo = objs.data;
                    _oBaseConfig['cpu'] = {};
                     for(var i = 0;i< objs.data['cpu'].length;i++){

                     var arrMemory = [];
                     for(var j=0;j<objs.data['mem'].length;j++){
                     arrMemory.push({
                     "value":objs.data['mem'][j].billUnitCount*1024,
                     "text":objs.data['mem'][j].billUnitCount+"G"
                     });
                     }
                         _oBaseConfig['cpu']['cpu_'+objs.data['cpu'][i].billUnitCount] = {
                             "value": objs.data['cpu'][i].billUnitCount,
                             "text": objs.data['cpu'][i].billUnitCount+"核",
                             "arrMemory": arrMemory
                         }
                     }
                    oCpuManager.init(function(){
                        _this.setCost();
                    });
                    _this.setCost();
                }else{

                }
            });
        },
        setCost:function(){ //计算费用
            var _this = this;
            var data={},iTotal= 0;
            if(!this.bInit){
                data=this.getData();

                var cycle = data.time;
                var products = {};
                products["cpu"]=data.cpu;
                products["mem"]=data.memory  / 1024;
                products["bandwidth"]=data.bandwidth;
                products["disk"]=$("#hard-disk-value").val();

                var sum = 0;
                _this.productsId = "";
                console.log(products);
                console.log(this.productsInfo);
                for(var obj in this.productsInfo){
                    console.log(obj);
                    if(this.productsInfo[obj][0].productCat.billType == "Nonlinear") {
                        for (var i = 0; i < this.productsInfo[obj].length; i++) {
                            if(obj == "disk"){
                                console.log("1231231232");
                                sum = sum + this.productsInfo[obj][0].price * products[obj] *cycle / 10 ;
                                if($.trim(products[obj]) !== '' && products[obj] !== undefined )
                                {_this.productsId += this.productsInfo[obj][0].id+"_"+ products[obj] +",";}
                            }else{
                            if (this.productsInfo[obj][i].billUnitCount == products[obj]) {
                                sum = sum + this.productsInfo[obj][i].price * cycle;
                                if($.trim(products[obj]) !== '' && products[obj] !== undefined )
                                {_this.productsId += this.productsInfo[obj][i].id+"_"+ products[obj] +",";}
                            }
                            }
                        }
                    }else{
                        if(obj == "bandwidth"){
                            var bandWidthCost = 0;
                            for(var j=0;j<this.productsInfo[obj].length;j++){
                                if(products[obj] >= this.productsInfo[obj][j].unitCountMin ){
                                    if (products[obj] >= this.productsInfo[obj][j].unitCountMax ){
                                        bandWidthCost = bandWidthCost + (this.productsInfo[obj][j].unitCountMax - this.productsInfo[obj][j].unitCountMin + 1) * this.productsInfo[obj][j].price;
                                    }else if(products[obj] < this.productsInfo[obj][j].unitCountMax ){
                                        bandWidthCost = bandWidthCost + (products[obj] - this.productsInfo[obj][j].unitCountMin + 1) * this.productsInfo[obj][j].price;
                                        if($.trim(products[obj]) !== '' && products[obj] !== undefined )
                                        {_this.productsId += this.productsInfo[obj][j].id+"_"+ products[obj] +",";}
                                    }
                                }else{
                                    break;
                                }
                            }

                            sum  = sum + bandWidthCost;
                        }
                    }
                }
                iTotal = sum;
                $('#cost-container').html(iTotal);
            }
        }
    }

    //初始化所有组件
    $(function(){
        oManager.init();
    });
})(jQuery);