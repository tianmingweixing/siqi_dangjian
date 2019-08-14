<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>layout 后台大布局 - Layui</title>
    <link rel="stylesheet" href="/js/layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <#include '../../frame/head.ftl'/>
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">数据统计</a>
                    <dl class="layui-nav-child">
                        <dd class="layui-this"><a onclick="business_1()">商品统计</a></dd>
                        <dd><a onclick="business_2()">用户统计</a></dd>
                        <dd><a onclick="business_3()">订单统计</a></dd>
                        <dd><a onclick="business_4()">账目统计</a></dd>
                        <dd><a onclick="business_5()">退货统计</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">系统设置</a>
                    <dl class="layui-nav-child">
                        <dd><a onclick="set_1()">添加商品分类</a></dd>
                        <dd><a onclick="set_3()">添加管理员</a></dd>
                        <dd><a onclick="set_2()">修改密码</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">商城社区</a>
                    <dl class="layui-nav-child">
                        <dd><a onclick="go_2()">用户评论</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body" style="overflow-y: hidden">
        <!-- 内容主体区域 -->
        <iframe  style="width:100%;height:100%" id="mainIframe" name="mainIframe" src="../frame/goodList.ftl" frameborder="0" scrolling="auto" ></iframe>
    </div>


</div>
<script src="../../js/layui/layui.js"></script>
<script src="../../js/jquery/jquery-3.3.1.min.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;
    });
    function business_1(){
        $("#mainIframe").attr("src", "/frame/goodList.ftl");
    }
    function business_2(){
        $("#mainIframe").attr("src", "/frame/userList.ftl");
    }
    function business_3(){
        $("#mainIframe").attr("src", "/frame/orderList.ftl");
    }
    function business_4() {
        $("#mainIframe").attr("src", "/frame/billList.ftl");
    }
    function business_5() {
        $("#mainIframe").attr("src", "/frame/backGoodList.ftl");
    }
    function set_1() {
        $("#mainIframe").attr("src", "/frame/addCategory.ftl");
    }
    function set_2() {
        $("#mainIframe").attr("src", "/frame/resetPassword.ftl");
    }
    function set_3() {
        $("#mainIframe").attr("src", "/frame/addAdmin.ftl");
    }
    function go_2() {
        $("#mainIframe").attr("src", "/frame/commentRecord.ftl");
    }

</script>
</body>
</html>