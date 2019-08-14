<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>网站后台内容管理系统登录登陆界面模板 - cssmoban</title>
    <meta name="keywords" content="后台登陆页面模板,后台登录界面html,后台登录模板,后台登录页面html,后台管理系统后台登录模板">
    <meta name="description" content="cssmoban提供后台管理系统登录界面html模板学习和下载">
    <meta name="viewport" content="width=device-width">
    <link href="/login/public/css/base.css" rel="stylesheet" type="text/css">
    <link href="/login/public/css/login.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/js/jquery/form/Validform_v5.3.2_min.js"></script>

</head>

<body>

<div class="login">
    <div>
        <div class="logo"></div>
        <div class="login_form">
            <div class="user">
                <input class="text_value"  name="phone" type="text" id="phone" placeholder="请输入手机号">
                <input class="text_value"  name="password" type="password" id="password">
            </div>
            <button class="button" id="submit" type="submit" onclick="login()">登录</button>

        </div>
        <div id="tip"></div>
        <div class="foot">
            Copyright © 2011-2015  All Rights Reserved. More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> - Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a>
        </div>
    </div>
</div>

<script>
    // $(function(){
    // });
    function login(){
        $.ajax({
            url:"/login/admin_login",
            data:{
                phone:$("#phone").val(),
                passWord:$('#password').val()
            },
            success:function (data) {
                if(data.result=="fail"){
                    alert(data.result)
                } else if(data.result == "success"){
                    window.location.href='/mainMenu/goto1';
                }
            }
        })
    }

</script>
</body>
</html>
