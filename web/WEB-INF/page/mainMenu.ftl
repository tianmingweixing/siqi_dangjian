<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>思齐党建 - Layui</title>
    <link rel="stylesheet" href="/js/layui/css/layui.css">

    <script src="../../js/layui/layui.js"></script>
    <script src="../../js/jquery/jquery-3.3.1.min.js"></script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <#include '../../frame/head.ftl'/>
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item">
                    <a href="javascript:;">首页</a>
                    <!--<dl class="layui-nav-child">
                    </dl>-->
                </li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;">支部管理</a>
                    <dl class="layui-nav-child">
                        <dd class="layui-this"><a onclick="branch_1()">支部信息</a></dd>
                        <dd><a onclick="branch_2()">班子管理</a></dd>
                        <dd><a onclick="branch_3()">党组管理</a></dd>
                    </dl>
                </li>

                <li class="layui-nav-item">
                    <a href="javascript:;">用户管理</a>
                    <dl class="layui-nav-child">
                        <dd><a onclick="user_1()">用户列表</a></dd>
                        <dd><a onclick="user_2()">人员分类</a></dd>
                    </dl>
                </li>

                <li class="layui-nav-item">
                    <a href="javascript:;">活动管理</a>
                    <dl class="layui-nav-child">
                        <dd><a onclick="activity_1()">活动列表</a></dd>
                        <dd><a onclick="activity_2()">活动分类</a></dd>
                        <dd><a onclick="activity_3()">活动品牌</a></dd>
                        <dd><a onclick="activity_4()">活动心得</a></dd>
                    </dl>
                </li>

                <li class="layui-nav-item">
                    <a href="javascript:;">会议管理</a>
                    <dl class="layui-nav-child">
                        <dd><a onclick="meeting_1()">会议列表</a></dd>
                        <dd><a onclick="meeting_2()">会议分类</a></dd>
                    </dl>
                </li>

                <li class="layui-nav-item">
                    <a href="javascript:;">文档管理</a>
                    <dl class="layui-nav-child">
                        <dd><a onclick="conclusion_1()">工作计划</a></dd>
                        <dd><a onclick="conclusion_2()">工作总结</a></dd>
                        <dd><a onclick="conclusion_3()">文档分类</a></dd>
                    </dl>
                </li>

                <li class="layui-nav-item">
                    <a href="javascript:;">党务管理</a>
                    <dl class="layui-nav-child">
                        <dd><a onclick="partywork_1()">表彰奖励列表</a></dd>
                        <dd><a onclick="partywork_2()">违纪违法列表</a></dd>
                        <dd><a onclick="partywork_3()">慰问情况列表</a></dd>
                        <dd><a onclick="partywork_4()">党内公示公告</a></dd>
                    </dl>
                </li>

                <li class="layui-nav-item">
                    <a href="javascript:;">系统设置</a>
                    <dl class="layui-nav-child">
                        <dd><a onclick="set_1()">管理员列表</a></dd>
                        <dd><a onclick="set_2()">修改密码</a></dd>
                        <dd><a onclick="set_3()">公司信息</a></dd>
                        <dd><a onclick="set_4()">轮播设置</a></dd>
                    </dl>
                </li>

            </ul>
        </div>
    </div>

    <div class="layui-body" style="overflow-y: hidden">
        <!-- 内容主体区域 -->
        <iframe style="width:100%;height:100%" id="mainIframe" name="mainIframe" src="/frame/partyBranchList.ftl" frameborder="0" scrolling="auto" ></iframe>
    </div>


</div>

<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;
    });
    //支部班子
    function branch_1(){
        $("#mainIframe").attr("src", "/frame/partyBranchList.ftl");
    }
    function branch_2(){
        $("#mainIframe").attr("src", "/frame/partyGroupList.ftl");
    }
    function branch_3(){
        $("#mainIframe").attr("src", "/frame/partyTeamList.ftl");
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
        $("#mainIframe").attr("src", "/frame/commentRecord.ftl");
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
        $("#mainIframe").attr("src", "/frame/conclusionList.ftl");
    }
    function conclusion_2() {
        $("#mainIframe").attr("src", "/frame/conclusionList_b.ftl");
    }
    function conclusion_3() {
        $("#mainIframe").attr("src", "/frame/conclusionTypeList.ftl");
    }


    function partywork_1() {
        $("#mainIframe").attr("src", "/frame/rewardList.ftl");
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