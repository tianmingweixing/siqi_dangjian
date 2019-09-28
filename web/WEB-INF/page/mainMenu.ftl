<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>思齐党建 - Layui</title>
    <link rel="stylesheet" href="/js/layui/css/layui.css">
    <link rel="shortcut icon" href="https://www.baidu.com/favicon.ico" type="image/x-icon">
    <script src="../../js/layui/layui.js"></script>
    <script src="../../js/jquery/jquery-3.3.1.min.js"></script>
</head>

<style type="text/css">
    .hide {display: none;}
</style>

<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <#include '../../frame/head.ftl'/>
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item">
                    <a href="javascript:;" onclick="toIndex()">首页</a>
                    <!--<dl class="layui-nav-child">
                    </dl>-->
                </li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;">支部管理</a>
                    <dl class="layui-nav-child">
                        <dd id="play" class="layui-this layui-hide" ><a onclick="branch_1()">支部信息</a></dd>
                        <dd id="group" class="layui-hide"><a onclick="branch_2()">班子管理</a></dd>
                        <dd id="teams" class="layui-hide"><a onclick="branch_3()">党组管理</a></dd>
                        <!--<dd><a onclick="branch_4()">图片上传</a></dd>-->
                    </dl>
                </li>

                <li class="layui-nav-item">
                    <a href="javascript:;">用户管理</a>
                    <dl class="layui-nav-child">
                        <dd id="user" class="layui-hide"><a onclick="user_1()">用户列表</a></dd>
                        <dd id="usertype" class="layui-hide"><a onclick="user_2()">人员分类</a></dd>
                    </dl>
                </li>

                <li class="layui-nav-item">
                    <a href="javascript:;">活动管理</a>
                    <dl class="layui-nav-child">
                        <dd id="activity" class="layui-hide"><a onclick="activity_1()">活动列表</a></dd>
                        <dd id="acttype" class="layui-hide"><a onclick="activity_2()">活动分类</a></dd>
                        <dd id="brand" class="layui-hide"><a onclick="activity_3()">活动品牌</a></dd>
                        <dd id="tips" class="layui-hide"><a onclick="activity_4()">活动心得</a></dd>
                    </dl>
                </li>

                <li class="layui-nav-item">
                    <a href="javascript:;">会议管理</a>
                    <dl class="layui-nav-child">
                        <dd id="meeting" class="layui-hide"><a onclick="meeting_1()">会议列表</a></dd>
                        <dd id="meettype" class="layui-hide"><a onclick="meeting_2()">会议分类</a></dd>
                    </dl>
                </li>

                <li class="layui-nav-item">
                    <a href="javascript:;">文档管理</a>
                    <dl class="layui-nav-child">
                        <dd id="work" class="layui-hide"><a onclick="conclusion_1()">工作计划</a></dd>
                        <dd id="summary" class="layui-hide"><a onclick="conclusion_2()">工作总结</a></dd>
                        <dd id="wordtype" class="layui-hide"><a onclick="conclusion_3()">文档分类</a></dd>
                    </dl>
                </li>

                <li class="layui-nav-item">
                    <a href="javascript:;">党务管理</a>
                    <dl class="layui-nav-child">
                        <dd id="honor" class="layui-hide"><a onclick="partywork_1()">表彰奖励列表</a></dd>
                        <dd id="discipline" class="layui-hide"><a onclick="partywork_2()">违纪违法列表</a></dd>
                        <dd id="condole" class="layui-hide"><a onclick="partywork_3()">慰问情况列表</a></dd>
                        <dd id="notice" class="layui-hide"><a onclick="partywork_4()">党内公示公告</a></dd>
                    </dl>
                </li>

                <li class="layui-nav-item">
                    <a href="javascript:;">系统设置</a>
                    <dl class="layui-nav-child">
                        <dd id="admin" class="layui-hide"><a onclick="set_1()">管理员列表</a></dd>
                        <dd id="pwd" class="layui-hide"><a onclick="set_2()">修改密码</a></dd>
                        <dd id="com" class="layui-hide"><a onclick="set_3()">公司信息</a></dd>
                        <dd id="lunbo" class="layui-hide"><a onclick="set_4()">轮播设置</a></dd>
                    </dl>
                </li>

            </ul>
        </div>
    </div>

    <div class="layui-body" style="overflow-y: hidden;">
        <!-- 内容主体区域 -->
        <iframe style="width:100%;height:100%" id="mainIframe" name="mainIframe" src="/frame/index.ftl" frameborder="0" scrolling="auto" ></iframe>
    </div>


</div>

<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;
    });

    $().ready(function () {
        //初始化信息
        //initAdmin();
    });

    $.ajax({
        url: "/admin/getAdmin",
        type: "POST",
        data: {
            // party_branch_id: $("#party_branch_id").val(),
        },
        success: function (data) {
            if (data.code == 0){
                var authority = data.admin.authority;
                var obj2 = jQuery.parseJSON(authority);
                //console.log(obj2);
                //var obj = eval('('+authority+')');
                var arr = Object.keys(obj2);
                console.log(arr);
                //$("#authority[group]").removeClass("layui-hide");

                for(var key in obj2){
                    if (key == "authority[play]"){
                        $("dd#play").removeClass("layui-hide");
                    }
                    if (key == "authority[group]"){
                        $("dd#group").removeClass("layui-hide");
                    }
                    if (key == "authority[teams]"){
                        $("dd#teams").removeClass("layui-hide");
                    }

                    if (key == "authority[user]"){
                        $("dd#user").removeClass("layui-hide");
                    }
                    if (key == "authority[usertype]"){
                        $("dd#usertype").removeClass("layui-hide");
                    }

                    if (key == "authority[activity]"){
                        $("dd#activity").removeClass("layui-hide");
                    }
                    if (key == "authority[acttype]"){
                        $("dd#acttype").removeClass("layui-hide");
                    }
                    if (key == "authority[brand]"){
                        $("dd#brand").removeClass("layui-hide");
                    }
                    if (key == "authority[tips]"){
                        $("dd#tips").removeClass("layui-hide");
                    }

                    if (key == "authority[meeting]"){
                        $("dd#meeting").removeClass("layui-hide");
                    }
                    if (key == "authority[meettype]"){
                        $("dd#meettype").removeClass("layui-hide");
                    }

                    if (key == "authority[work]"){
                        $("dd#work").removeClass("layui-hide");
                    }
                    if (key == "authority[summary]"){
                        $("dd#summary").removeClass("layui-hide");
                    }
                    if (key == "authority[wordtype]"){
                        $("dd#wordtype").removeClass("layui-hide");
                    }

                    if (key == "authority[honor]"){
                        $("dd#honor").removeClass("layui-hide");
                    }
                    if (key == "authority[discipline]"){
                        $("dd#discipline").removeClass("layui-hide");
                    }
                    if (key == "authority[condole]"){
                        $("dd#condole").removeClass("layui-hide");
                    }
                    if (key == "authority[notice]"){
                        $("dd#notice").removeClass("layui-hide");
                    }

                    if (key == "authority[admin]"){
                        $("dd#admin").removeClass("layui-hide");
                    }
                    if (key == "authority[com]"){
                        $("dd#com").removeClass("layui-hide");
                    }
                    if (key == "authority[pwd]"){
                        $("dd#pwd").removeClass("layui-hide");
                    }
                    if (key == "authority[lunbo]"){
                        $("dd#lunbo").removeClass("layui-hide");
                    }

                }

            }
        }
    });

    //支部班子
    function toIndex(){
        $("#mainIframe").attr("src", "/frame/index.ftl");
    }
    function branch_1(){
        $("#mainIframe").attr("src", "/frame/partyBranchList.ftl");
    }
    function branch_2(){
        $("#mainIframe").attr("src", "/frame/partyGroupList.ftl");
    }
    function branch_3(){
        $("#mainIframe").attr("src", "/frame/partyTeamList.ftl");
    }
    function branch_4(){
        $("#mainIframe").attr("src", "/frame/imgTest.ftl");
    }

    function user_1(){
        $("#mainIframe").attr("src", "/frame/userList.ftl");
    }
    function user_2() {
        $("#mainIframe").attr("src", "/frame/dutyList.ftl");
    }

    function activity_1() {
        $("#mainIframe").attr("src", "/frame/activityList.ftl");
    }
    function activity_2() {
        $("#mainIframe").attr("src", "/frame/activityTypeList.ftl");
    }
    function activity_3() {
        $("#mainIframe").attr("src", "/frame/activityBrandList.ftl");
    }
    function activity_4() {
        $("#mainIframe").attr("src", "/frame/activityTipsList.ftl");
    }

    function meeting_1() {
        $("#mainIframe").attr("src", "/frame/meetingList.ftl");
    }
    function meeting_2() {
        $("#mainIframe").attr("src", "/frame/meetingTypeList.ftl");
    }

    function conclusion_1() {
        $("#mainIframe").attr("src", "/frame/conclusionList_jh.ftl");
    }
    function conclusion_2() {
        $("#mainIframe").attr("src", "/frame/conclusionList_zj.ftl");
    }
    function conclusion_3() {
        $("#mainIframe").attr("src", "/frame/conclusionTypeList.ftl");
    }


    function partywork_1() {
        $("#mainIframe").attr("src", "/frame/honorList.ftl");
    }
    function partywork_2() {
        $("#mainIframe").attr("src", "/frame/disciplineList.ftl");
    }

    function partywork_3() {
        $("#mainIframe").attr("src", "/frame/sympathyList.ftl");
    }

    function partywork_4() {
        $("#mainIframe").attr("src", "/frame/noticeList.ftl");
    }

    function set_1() {
        $("#mainIframe").attr("src", "/frame/adminList.ftl");
    }
    function set_2() {
        $("#mainIframe").attr("src", "/frame/resetPassword.ftl");
    }
    function set_3() {
        $("#mainIframe").attr("src", "/frame/comInfo.ftl");
    }
    function set_4() {
        $("#mainIframe").attr("src", "/frame/lunbo.ftl");
    }

</script>
</body>
</html>