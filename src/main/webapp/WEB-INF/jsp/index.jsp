<%--
  Created by IntelliJ IDEA.
  User: dingRan
  Date: 2014/9/15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>管理中心</title>
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="HandheldFriendly" content="True">
    <meta name="MobileOptimized" content="320">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <link rel="stylesheet" media="screen" href="../../static/css/bootstrap.min.css">
    <link rel="stylesheet" media="screen" href="../../static/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" media="screen" href="../../static/css/smartadmin-production_unminified.css">
    <link rel="stylesheet" type="text/css" media="screen" href="../../static/css/smartadmin-skins.css">
    <link rel="stylesheet" type="text/css" media="screen" href="../../static/css/demo.css">
    <link rel="stylesheet" href="../../static/css/google-fonts.css">
<%--
    <link rel="stylesheet" media="screen" href="../../static/js/libs/DataTables-1.10.2/CSS/jquery.dataTables.css">
--%>
    <link rel="stylesheet" media="screen" href="../../static/css/transaction.css">
    <link rel="stylesheet" media="screen" href="../../static/css/userCenter.css">
    <link rel="stylesheet" media="screen" href="../../static/css/custom.css">
    <link rel="stylesheet" media="screen" href="../../static/css/jquery-ui.css">
    <link rel="stylesheet" media="screen" href="../../static/css/products.css">
</head>
<body>

<nav id="ezcloud-userCenter-header" class="navbar userCenter-header" role="navigation" style="background-color: #095D89;">
    <div class="container-fluid container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#menu-items">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <!--<div id="brand-logo" class="logo"></div>-->
        </div>

        <div class="collapse navbar-collapse" id="menu-items">
            <ul class="nav navbar-nav" style="height: 65px;" >
                <li  style="float: left;margin-top: -15px;" class="navbar-left"><div id="logo" class="logo "></div></li>
                <li  style="margin-top: 5px;" class="  user-info pull-right"><a onclick="logout()" href="#">退出</a></li>
                <li  style="margin-top: 5px;" class="  user-info pull-right"><a href="#">帮助中心</a></li>
                <li  style="margin-top: 5px;" class="  user-info pull-right"><a href="#">用户中心</a></li>
                <li  style="margin-top: 5px;" class="  user-info pull-right"><a href="#">控制台</a></li>
                <li  style="margin-top: 5px;" class="  user-info pull-right"><a href="#">服务与支持</a></li>
                <li  style="margin-top: 5px;" class="  user-info pull-right"><a  href="../../products.html">产品</a></li>
                <li  style="margin-top: 5px;" class="  user-info pull-right"><a href="#">首页</a></li>

            </ul>

        </div>
    </div>
</nav>

<div id="content" class="container">
    <div class="row">
        <div class="col-lg-3 icon-append">管理中心</div>
        <div class="col-lg-9 userCenter text-right" style="float:right;list-style:none;">

            ${accountEmail},你好！
        </div>
    </div>
</div>
<hr style="background-color:#B7B7B7; height:1px;margin-top: 6px;">

<div class="container tab-content">

    <div class="tab-pane active" id="account-manager">

        <div class="row">
            <ul class="nav nav-tabs nav-vertical  col-lg-2" style="font-size:1.2em;font-family: '黑体';" role="left-tablist">
                <li class="active"><a href="#" onclick="loadURL('../../action/cniaas/productCat.html',$('#content_test'))" role="tab" data-toggle="tab">产品类别</a></li>
                <li><a href="#" onclick="loadURL('../../action/cniaas/product.html',$('#content_test'))" role="tab" data-toggle="tab">产品管理</a></li>
                <li><a href="#" onclick="loadURL('../../action/cniaas/productPackage.html',$('#content_test'))" role="tab" data-toggle="tab">套餐管理</a></li>
            </ul>
            <div id="content_test" class="col-lg-10" style="display: block;">

            </div>
        </div>
    </div>

</div>

<hr style="background-color:#B7B7B7; height:1px;margin-top: 50px;">
<div id="footer">
    Copyright @ 2014 CNIaas.com
</div>
<script src="../../static/js/libs/jquery.min.js"></script>
<script> if (!window.jQuery) { document.write('<script src="../../static/js/libs/jquery-2.0.2.min.js"><\/script>');} </script>
<script src="../../static/js/libs/jquery-ui.min.js"></script>
<script> if (!window.jQuery.ui) { document.write('<script src="../../static/js/libs/jquery-ui-1.10.3.min.js"><\/script>');} </script>
<script src="../../static/js/libs/bootstrap.js"></script>
<script src="../../static/js/notification/SmartNotification.js"></script>
<script src="../../static/js/smartwidgets/jarvis.widget.js"></script>
<script src="../../static/js/plugin/easy-pie-chart/jquery.easy-pie-chart.min.js"></script>
<script src="../../static/js/plugin/sparkline/jquery.sparkline.min.js"></script>
<script src="../../static/js/jquery-validate/jquery.validate.min.js"></script>
<script src="../../static/js/jquery-validate/jquery.validate.additional-methods.js"></script>
<script src="../../static/js/plugin/masked-input/jquery.maskedinput.min.js"></script>
<script src="../../static/js/plugin/select2/select2-customized.js"></script>
<script src="../../static/js/plugin/bootstrap-slider/bootstrap-slider.min.js"></script>
<script src="../../static/js/plugin/msie-fix/jquery.mb.browser.min.js"></script>
<script src="../../static/js/demo.js"></script>
<script src="../../static/js/chart.js"></script>
<script src="../../static/js/app_sa.js"></script>
<%--<script src="../../static/js/libs/DataTables-1.10.2/js/jquery.dataTables.js"></script>--%>
<script src="../../static/js/libs/ajax.js"></script>
<script src="../../static/js/libs/i18n/dynamicLocale.js"></script>
<script src="../../static/js/libs/i18n/i18n_locale.js"></script>
<script src="../../static/js/libs/md5.js"></script>
<script src="../../static/js/libs/tools.js"></script>
<script src="../../static/js/userCenter/userCenter.js"></script>
<script type="text/javascript">
    loadURL('../../action/cniaas/productCat.html',$('#content_test'));
</script>

</body>


</html>
