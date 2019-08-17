<head>
    <meta charset="utf-8">
</head>
<div class="layui-header">
    <div class="layui-logo">思齐党建后台管理系统</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-left">
        <li class="layui-nav-item"><a onclick="goto(1)">控制台</a></li>
        <#--<li class="layui-nav-item"><a onclick="goto(2)">商品管理</a></li>-->
        <#--<li class="layui-nav-item"><a onclick="goto(3)">用户</a></li>-->
        <#--<li class="layui-nav-item">-->
            <#--<a href="javascript:;">其它系统</a>-->
            <#--<dl class="layui-nav-child">-->
                <#--<dd><a href="">邮件管理</a></dd>-->
                <#--<dd><a href="">消息管理</a></dd>-->
                <#--<dd><a href="">授权管理</a></dd>-->
            <#--</dl>-->
        <#--</li>-->
    </ul>
    <ul class="layui-nav layui-layout-right">
        <li class="layui-nav-item">
            <a href="javascript:;">
                <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                贤心
            </a>
            <dl class="layui-nav-child">
                <dd><a onclick="logout()">退出登陆</a></dd>
            </dl>
        </li>
    </ul>
</div>
<script>
    function logout() {
        window.location.href='../login/logout';
    }
    function goto(type){
        switch (type) {
            case 1:
                window.location.href='goto1';
                break;
            case 2:
                window.location.href='goto2';
                break;
            case 3:
                window.location.href='goto1';
                break;
        }
    }

</script>