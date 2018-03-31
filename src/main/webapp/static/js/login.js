/**
 * Copyright: cniaas
 * Author：Zhangly
 * Date: 2014/9/4.
 * Description:
 */


function validateLogin() {
    $("#login-box").validate({
        onfocusout: function (element) {
            $(element).valid();
        },
        rules: {
            user: {
                required: true
            },
            password: {
                required: true
            }
        },
        messages: {
            email: {
                required: "请输入账户名"
            },
            password: {
                required: "请输入密码"
            }
        },

        highlight: function(element){
            $(element).closest('.input-group').removeClass('has-success').addClass('has-error');
        },

        unhighlight: function(element){
            $(element).closest('.input-group').removeClass('has-error').addClass('has-success');
        },

        errorPlacement: function (error, element) {
            //error.appendTo(element.parent());
        }

    });
}


window.sessionStorage.setItem('limitTime',0);
function login(){
    var user = $.trim($("input[name='user']").val());
    var password = hex_md5($.trim($("input[name='password']").val()));

    doPostLogin('/action/userAccount/login',{user:user,password:password},function(objs){
        if(objs.httpCode == "200"){
            $(".note-error").html("").hide();
            /*doGet('/action/userAccount/index',{},function(objs){

            });*/
            window.location.replace("/action/userAccount/index");
           // window.open("/action/userAccount/index");
         //   window.location.href("/action/userAccount/index");
        }else{
            $(".note-error").html(objs.message).show();
        }
    });
}