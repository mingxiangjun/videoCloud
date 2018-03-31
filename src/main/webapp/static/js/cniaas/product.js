/**
 * Copyright: cniaas
 * Author：dingRan
 * Date: 2014/9/15.
 * Description:产品类别
 */
var productTable;

function loadproductTable(){

   // loadURL("../../static/cniaas/product.html",$("#content_test"));
    $("#product_list_table").empty().append("<table id='product-table' class='table table-responsive table-striped table-bordered table-hover table-text-center'><thead id='product-thead' class=''></thead><tbody id='product-tbody' class='table-tbody'></tbody></table>");

    $("#product-thead").empty().append(
            "<tr><th></th>"+
            "<th class='table-thead'>"+rpL("name")+"</th>"+
            "<th class='table-thead'>"+rpL("bill_type")+"</th>"+
            "<th class='table-thead'>"+rpL("billUnitCount")+"</th>"+
     /*       "<th class='table-thead'>"+rpL("bill_cycle")+"</th>"+*/
            "<th class='table-thead'>"+rpL("price")+"</th>"+
            "<th class='table-thead'>"+rpL("status")+"</th>"+
         /*   "<th class='table-thead'>"+rpL("unit_count_min")+"</th>"+
            "<th class='table-thead'>"+rpL("unit_count_max")+"</th>"+*/
            "<th class='table-thead'>"+rpL("description")+"</th>"+
            "<th class='table-thead'>"+rpL("createTime")+"</th>"+
        /*    "<th class='table-thead'>"+rpL("updateTime")+"</th>"+*/
            "</tr>");

    var  loadStr="<tr class='odd'><td valign='top' colspan='6' style='border-width:0;'><span><h3><i class='fa fa-cog fa-spin'></i>正在努力加载...</h3></span></td></tr>"
    $("#product-tbody").empty().append(loadStr);
    runproductDataTables(function(){
        productTable=$("#product-table").dataTable({
            "bFilter":true,
            "bDestroy":true,
            "bRetrieve":true,
            "sPaginationType" : "bootstrap_full",
            "aaSorting": [[ 2, "desc" ]],
            "sRowSelect": "single",
            "oLanguage": {
                "sZeroRecords": "抱歉， 没有找到",
                "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
                "sInfoEmpty": "没有数据",
                "sInfoFiltered": "(从 _MAX_ 条数据中检索)",
                "oPaginate": {"sFirst": "首页",
                    "sPrevious": "前一页",
                    "sNext": "后一页",
                    "sLast": "尾页"
                },
                "sZeroRecords": "没有检索到数据",
                "sProcessing": "<img src=’./loading.gif’ />"
            }
        });
    });
}

function runproductDataTables(callback){
    $("#btn_product_edit").addClass("disabled");
    var data={};
    doPost("/action/product/list",data,function(objs){
        if(objs.httpCode=="200"){
            var data=objs.datas;
            var str = '';
            for( var i=0;i<data.length;i++){

                    str += "<tr id='tr_" + data[i].id + "' ondblclick=\"showproductDetail('"+data[i].id+"','"+data[i].productCat.id+"')\" title='双击可查看详情'>"+
                        "<td><label class='checkbox'>"+
                        "<input id='product_" + data[i].id + "' type='checkbox' name='cbx_product_list' onclick=\"set_product_Sel(this,'"+data[i].id+"','"+data[i].productCat.id+"')\">"+
                        "<i></i></label></td>"+
                        "<td>"+data[i].productCat.name+"</td>"+
                        "<td>"+rpL(data[i].productCat.billType) +"</td>"+
                         "<td>"+data[i].billUnitCount +data[i].productCat.billUnitName+"</td>"+
                        /*  "<td>"+data[i].billCycle +"</td>"+*/
                        "<td>"+data[i].price +"</td>"+
                        "<td>"+rpL(data[i].status) +"</td>"+
                        /* "<td>"+data[i].unitCountMin +"</td>"+
                         "<td>"+data[i].unitCountMax +"</td>"+*/
                        "<td>"+ data[i].description +"</td>"+
                        "<td>"+ new Date(data[i].createTime).Format("yyyy-MM-dd hh:mm:ss")  +"</td>"+
                        /* "<td>"+ convertStr(data[i].updateTime) +"</td>"+*/
                        "</tr>";
                }
            str += "<tr id='tr_" + objs.data.id + "' ondblclick=\"showproductDetail('"+objs.data.id+"','"+objs.data.productCat.id+"')\" title='双击可查看详情'>"+
                "<td><label class='checkbox'>"+
                "<input id='product_" + objs.data.id + "' type='checkbox' name='cbx_product_list' onclick=\"set_product_Sel(this,'"+objs.data.id+"','"+objs.data.productCat.id+"')\">"+
                "<i></i></label></td>"+
                "<td>"+objs.data.productCat.name+"</td>"+
                "<td>"+rpL(objs.data.productCat.billType)+"</td>"+
                "<td>1" + objs.data.productCat.billUnitName+"</td>"+
                "<td>-</td>"+
                "<td>"+rpL(objs.data.status) +"</td>"+
                "<td>"+ objs.data.description +"</td>"+
           /*     "<td>"+ dateFormat("yyyy/MM/dd hh:mm:ss",new Date(objs.data.createTime)) +"</td>"+*/
                "<td>"+ new Date(objs.data.createTime).Format("yyyy-MM-dd hh:mm:ss") +"</td>"+
                "</tr>";
            $("#product-tbody").empty().append(str);

        }else{
            var  loadStr="<tr class='odd'><td valign='top' colspan='6' style='border-width:0;'><span><h3><i class='fa fa-cog fa-spin'></i>正在努力加载...</h3></span></td></tr>"
            $("#product-tbody").empty().append(loadStr);
            console.log("code :" + objs.code + "  msg:"+objs.message);
        }
        callback();
    });

}

var sel_product_id="";
var all_product_data="";
function set_product_Sel(obj,id){
    var obj_checked = obj.checked;
    $("#role_table input[name='cbx_role_list']:checkbox").attr("checked", false);
    obj_checked?obj.checked=true:obj.checked=false;

    if(obj.checked){
        all_product_data = null;
       // all_product_data = productTable.fnGetData(obj.parent);
        if(null != id){
            console.log("id="+id);
            sel_product_id=id;
        }
        $("#btn_product_edit").removeClass("disabled")

    }else{
        $("#btn_product_edit").addClass("disabled");
        all_product_data = null;
    }
    console.log("id="+id);
}



//初始化 产品类型
function initProductCat(callback){
    var productCat = document.getElementById("productCat_type").options;
    addOption(productCat,"---正在加载中---","");
    doPost("/action/productCat/list",{},function(objs){
        if(objs.httpCode=="200"){
            clearOptions(productCat);
                if (objs.datas.length > 0) {
                    for (var i = 0; i < objs.datas.length; i++) {
                        addOption(productCat, objs.datas[i].name, objs.datas[i].id);
                    }
                    callback();
                }
        }else{
            console.log("code :" + objs.code + "  msg:" + objs.message);
            showErrorMsg("","code :" + objs.code + "  msg:" + objs.message);
        }
    });

}



//新增验证
function validateproduct() {
    $("#product-form").validate({
        onfocusout: function (element) {
            $(element).valid();
        },
        rules: {
            email: {
                required: true,
                email: true
            },
            password: {
                required: true,
                //pwdStrengthValidator: true,
                rangelength: [8, 20]
            },
            repeatPassword: {
                required: true,
                equalTo: "#password"
            },
            sex:{
                required:true
            },
            telNumber: {
                required: true,
                phoneNumValidator: true
            },
            SMSCode:{
                required:true,
                minlength:6,
                maxlength:6
            },
            verifyCode:{
                required: true
            }
        },
        messages: {
            email: {
                required: "请输入邮箱",
                email: "请按邮箱格式输入"
            },
            password: {
                required: "请输入密码",
                //pwdStrengthValidator: "密码应该只包含………………",
                rangelength: "密码长度应该在x位到y位之间"
            },
            repeatPassword: {
                required: "请确认密码",
                equalTo: "两次输入的密码不一致"
            },
            sex:{
                required:"请选择性别"
            },
            telNumber: {
                required: "请输入手机号码",
                phoneNumValidator: "请按手机号码格式输入"
            },
            SMSCode:{
                required:"请输入短信验证码",
                minlength:"短信验证码为六位",
                maxlength:"短信验证码为六位"
            },
            verifyCode:{
                required:"请输入验证码"
            }
        },

        highlight: function(element){
            $(element).closest('.input-group-product').removeClass('has-success').addClass('has-error');
            btnConfirmState();
        },

        unhighlight: function(element){
            $(element).closest('.input-group-product').removeClass('has-error').addClass('has-success');
            btnConfirmState();
        },

        errorPlacement: function (error, element) {
            error.addClass("col-lg-3").css('text-align','left').css('color','#FC4343').css('line-height','34px');
            error.appendTo(element.parent());
        }

    });
}
function btnConfirmState(){
    var validateSize = 6;
    var size=0;
    $('#product-form .input-group-product').each(function () {
        if($(this).hasClass("has-success")){
            size+=1;
        }
    });
    if(validateSize==size){
        $("#productBtn").addClass('btn-primary').removeClass("disabled").removeClass('txt-color-darken');
    }else{
        $("#productBtn").removeClass('btn-primary').addClass("disabled").addClass('txt-color-darken');
    }
}

function addProduct(){
    var productCat_type = $.trim($("#productCat_type").val());
    var status = $.trim($("#product_status").val());
    var billUnitCount = $.trim($("#product_bill_unit_count").val());
    var unitCountMin = $.trim($("#product_unit_count_min").val());
    var unitCountMax = $.trim($("#product_unit_count_max").val());
    var price = $.trim($("#product_price").val());
    var description = $.trim($("#product_description").val());

    var units="";
    if(typeof($("#productCat_type option:selected").text())==typeof("bandwidth")){
        $('#band_test div[name^="bandwidth-val"]').each(function (){
            var min=$(this).find('input[name^="band_min"]').val();
            var max=$(this).find('input[name^="band_max"]').val();
            var band_price=$(this).find('input[name^="band_price"]').val();
            units+=min+","+max+","+band_price+";";
        });
    }

    var data = {productCatId: productCat_type,status:status ,regionId: "5eeb1925-e5de-4ea6-9323-30b622eaf351" ,billUnitCount: billUnitCount,price: price ,unitCountMin: unitCountMin ,unitCountMax: unitCountMax,description:description,units:units};
 //   $("#productBtn").removeClass('btn-primary').addClass("disabled").addClass('txt-color-darken');
    doPost("/action/product/add",data,function(objs){
        if(objs.httpCode=="200"){
            showMsg("温馨提示","添加成功。");
            loadproductTable();
            $("#product_widget-grid").show().siblings().hide();
            $("#product_btns").show();
        }else{
            console.log("code :" + objs.code + "  msg:" + objs.message);
            showErrorMsg("","code :" + objs.code + "  msg:" + objs.message);
        }
    });


}

function showproductDetail(id,productCatId){
    console.log(id);
    console.log(productCatId);
    doPost("/action/product/detail",{productCatId:productCatId,productId:id},function(objs){

    });

}
