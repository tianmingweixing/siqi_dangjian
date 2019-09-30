<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>layout 后台大布局 - 注册</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css">
    <link rel="stylesheet" href="../css/public.css">
    <link rel="stylesheet" type="text/css" href="/WImageUpload/webuploader.css">
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jquery/jquery-3.3.1.min.js"></script>
    <!--引入JS-->
    <script type="text/javascript" src="/WImageUpload/webuploader.js"></script>

</head>
<body>
<br>
<form class="layui-form" action="">
    <div class="layui-form-item">
        <label class="layui-form-label">账户</label>
        <div class="layui-input-block">
            <input type="text" id="account" name="title" required  lay-verify="required" placeholder="请输入账户名" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码框</label>
        <div class="layui-input-inline">
            <input type="password" id="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script>
    //Demo
    layui.use('form', function(){
        var form = layui.form;

        //监听提交
        form.on('submit(formDemo)', function(data){
            // layer.msg(JSON.stringify(data.field));
            $.ajax({
                url:"/login/admin_register",
                data:{
                    account:$("#account").val(),
                    passWord:$('#password').val()
                },
                success:function (data) {
                    if(data.msg == "该账户已被使用"){
                        alert(data.msg)
                    }
                    if(data.msg=="注册失败"){
                        alert(data.msg)
                    } else if(data.msg=="注册成功"){
                        alert(data.msg)
                        window.location.href='../login/js/login.ftl';
                    }
                }
            })
            return false;
        });
    });
</script>




</body>
</html>