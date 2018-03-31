/**
 * Copyright: cniaas
 * Author：dingRan
 * Date: 2014/9/15.
 * Description:产品类别
 */
var productPackageTable;


//loadproductPackageTable();
function loadproductPackageTable(){
  //  loadURL("../../static/cniaas/productPackage.html",$("#content_test"));

    $("#productPackage_list_table").empty().append("<table id='productPackage-table' class='table table-responsive table-striped table-bordered table-hover table-text-center'><thead id='productPackage-thead' class=''></thead><tbody id='productPackage-tbody' class='table-tbody'></tbody></table>");

    $("#productPackage-thead").empty().append(
            "<tr><th></th>"+
            "<th class='table-thead'>"+rpL("name")+"</th>"+
            "<th class='table-thead'>"+rpL("bill_cycle")+"</th>"+
            "<th class='table-thead'>"+rpL("totalPrice")+"</th>"+
            "<th class='table-thead'>"+rpL("status")+"</th>"+
            "<th class='table-thead'>"+rpL("description")+"</th>"+
            "<th class='table-thead'>"+rpL("createTime")+"</th>"+
          /*  "<th class='table-thead'>"+rpL("updateTime")+"</th>"+*/
            "</tr>");

    var  loadStr="<tr class='odd'><td valign='top' colspan='10' style='border-width:0;'><span><h3><i class='fa fa-cog fa-spin'></i>正在努力加载...</h3></span></td></tr>"
    $("#productPackage-tbody").empty().append(loadStr);
    runproductPackageDataTables(function(){
        productPackageTable=$("#productPackage-table").dataTable({
            "bDestroy":true,
            "bRetrieve":true,
            "bFilter": true,
            "sPaginationType" : "full_numbers",
            "aaSorting": [[ 6, "desc" ]],
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

function runproductPackageDataTables(callback){
    $("#btn_productPackage_edit,#btn_productPackage_delete").addClass("disabled");
    var data={};
    doPost("/action/productPackage/list",data,function(objs){
        if(objs.httpCode=="200"){
            var data=objs.datas;
            var str = '';
            for( var i=0;i<data.length;i++){
                var description="";
                if(convertStr(data[i].cpu)!=""){
                    description+="CPU: "+data[i].cpu+" GB</br>";
                }
                if(convertStr(data[i].mem)!=""){
                    description+="内存: "+data[i].mem+" GB</br>";
                }
                if(convertStr(data[i].disk)!=""){
                    description+="磁盘: "+data[i].disk+" GB</br>";
                }
                if(convertStr(data[i].bandwidth)!=""){
                    description+="带宽: "+data[i].bandwidth+" Mbps</br>";
                }
                if(convertStr(data[i].snapshot)!=""){
                    description+="快照: "+data[i].snapshot+" 个";
                }
                var billCycle;
                if(Number(data[i].billCycle)%12==0){
                     billCycle= Math.floor(data[i].billCycle/12)+"年";
                }else{
                     billCycle= data[i].billCycle>12?Math.floor(data[i].billCycle/12)+"年"+data[i].billCycle%12+"月":data[i].billCycle+"月";
                }
                str += "<tr id='tr_" + data[i].id + "' ondblclick=\"showproductPackageDetail('"+data[i].id+"')\" title='双击可查看详情'>"+
                    "<td><label class='checkbox'>"+
                    "<input id='productPackage_" + data[i].id + "' type='checkbox' name='cbx_productPackage_list' onclick=\"set_productPackage_Sel(this,'"+data[i].id+"')\">"+
                    "<i></i></label></td>"+
                    "<td>"+data[i].name+"</td>"+
                    "<td>"+billCycle +"</td>"+
                    "<td>"+data[i].totalPrice +"</td>"+
                    "<td>"+rpL(data[i].status)+"</td>"+
                    "<td style='text-align: left;'>"+ description+"</td>"+
                    "<td>"+new Date(data[i].createTime).Format("yyyy-MM-dd hh:mm:ss")  +"</td>"+
        /*            "<td>"+ convertStr(data[i].updateTime) +"</td>"+*/
                    "</tr>";
            }
            $("#productPackage-tbody").empty().append(str);

        }else{
            var  loadStr="<tr class='odd'><td valign='top' colspan='6' style='border-width:0;'><span><h3><i class='fa fa-cog fa-spin'></i>正在努力加载...</h3></span></td></tr>"
            $("#productPackage-tbody").empty().append(loadStr);
            console.log("code :" + objs.code + "  msg:"+objs.message);
        }
        callback();
    });

}

var sel_productPackage_id="";
var all_productPackage_data="";
function set_productPackage_Sel(obj,id){
    var obj_checked = obj.checked;
    $("#role_table input[name='cbx_role_list']:checkbox").attr("checked", false);
    obj_checked?obj.checked=true:obj.checked=false;

    if(obj.checked){
        all_productPackage_data = null;
      //  all_productPackage_data = productPackageTable.fnGetData(obj.parent);
        if(null != id)
            sel_productPackage_id=id;
        $("#btn_productPackage_edit,#btn_productPackage_delete").removeClass("disabled");

    }else{
        all_productPackage_data = null;
        $("#btn_productPackage_edit,#btn_productPackage_delete").addClass("disabled");
    }
   // console.log("id="+id);
}


function showproductPackageDetail(id){
    doPost("/action/productPackage/detail",{productPackageId:id},function(objs){

    });
}
