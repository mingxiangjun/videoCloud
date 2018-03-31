/**
 * Copyright: cniaas
 * Author：dingRan
 * Date: 2014/9/15.
 * Description:产品类别
 */
var productCatTable;


function loadproductCatTable(){
  //  loadURL("../../static/cniaas/productCat.html",$("#content_test"));

    $("#productCat_list_table").empty().append("<table id='product_cat-table' class='table table-responsive table-striped table-bordered table-hover table-text-center'><thead id='product_cat-thead' class=''></thead><tbody id='product_cat-tbody' class='table-tbody'></tbody></table>");
    $("#product_cat-thead").empty().append(
            "<tr><th></th>"+
            "<th class='table-thead'>"+rpL("name")+"</th>"+
            "<th class='table-thead'>"+rpL("bill_type")+"</th>"+
            "<th class='table-thead'>"+rpL("billUnitName")+"</th>"+
            "<th class='table-thead'>"+rpL("description")+"</th>"+
            "</tr>");

    var  loadStr="<tr class='odd'><td valign='top' colspan='6' style='border-width:0;'><span><h3><i class='fa fa-cog fa-spin'></i>正在努力加载...</h3></span></td></tr>"
    $("#product_cat-tbody").empty().append(loadStr);
    runproduct_catDataTables(function(){
        productCatTable=$("#product_cat-table").dataTable({
            "bDestroy":true,
            "bRetrieve":true,
            "sPaginationType" : "bootstrap_full",
            "aaSorting": [[ 2, "desc" ]],
            "sRowSelect": "single",
            "oLanguage": {
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

function runproduct_catDataTables(callback){
    $("#btn_productCat_edit").addClass("disabled");

    doPost("/action/productCat/list",{},function(objs){
        if(objs.httpCode=="200"){
            var data=objs.datas;
            var str = '';
            for( var i=0;i<data.length;i++){
                str += "<tr id='tr_" + data[i].id + "' ondblclick=\"showProductCatDetail('"+data[i].id+"')\" title='双击可查看详情'>"+
                    "<td><label class='checkbox'>"+
                    "<input id='productCat_" + data[i].id + "' type='checkbox' name='cbx_productCat_list' onclick=\"set_productCat_Sel(this,'"+data[i].id+"')\">"+
                    "<i></i></label></td>"+
                    "<td>"+data[i].name+"</td>"+
                    "<td>"+rpL(data[i].billType) +"</td>"+
                    "<td>"+data[i].billUnitName +"</td>"+
                    "<td>"+ data[i].description +"</td>"+
                    "</tr>";
            }
            $("#product_cat-tbody").empty().append(str);

        }else{
            var  loadStr="<tr class='odd'><td valign='top' colspan='10' style='border-width:0;'><span><h3><i class='fa fa-cog fa-spin'></i>正在努力加载...</h3></span></td></tr>"
            $("#product_cat-tbody").empty().append(loadStr);
            console.log("code :" + objs.code + "  msg:"+objs.message);
        }
        callback();
    });

}

var sel_productCat_id="";
var all_productCat_data="";
function set_productCat_Sel(obj,id){
    var obj_checked = obj.checked;
    $("#role_table input[name='cbx_role_list']:checkbox").attr("checked", false);
    obj_checked?obj.checked=true:obj.checked=false;

    if(obj.checked){
        all_productCat_data = null;
       // all_productCat_data = productCatTable.fnGetData(obj.parent);
        if(null != id)
            sel_productCat_id=id;

        $("#btn_productCat_edit").removeClass("disabled");

    }else{
        all_productCat_data = null;
        $("#btn_productCat_edit").addClass("disabled");
    }
    console.log("id="+id);
}

function showProductCatDetail(id){
    doPost("/action/productCat/detail",{productCatId:id},function(objs){

    });
}

