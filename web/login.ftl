<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>思齐党建后台</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <!-- CSS -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="../login/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../login/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="../login/css/form-elements.css">
    <link rel="stylesheet" href="../login/css/style.css">


    <script type="text/javascript" src="../js/jquery/jquery-3.3.1.min.js"></script>
   <script type="text/javascript" src="../js/jquery/form/Validform_v5.3.2_min.js"></script>

</head>


<!-- Top content -->
<body>

<div class="top-content">

    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-sm-offset-2 text">
                    <h1><strong>思齐党建后台登陆</strong></h1>
                    <div class="description">
                        <p>
                            Do <a href="#"><strong>"思齐"</strong></a> own party building platform, party building for the motherland to add luster.
                        </p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3 form-box">
                    <div class="form-top">
                        <div class="form-top-left">
                            <h3>Login</h3>
                            <p>请输入您的账号和密码:</p>
                        </div>
                        <div class="form-top-right">
                            <i class="fa fa-key"></i>
                        </div>
                    </div>
                    <div class="form-bottom">
                        <div class="form-group">
                            <label class="sr-only" for="form-username">用户名</label>
                            <input type="text" name="form-username" placeholder="用户名..." class="form-username form-control" id="form-username">
                        </div>
                        <div class="form-group">

                            <label class="sr-only" for="form-password">密码</label>
                            <input type="password" name="form-password" placeholder="密码..." class="form-password form-control" id="form-password">
                        </div>
                        <button class="btn" id="submit" onclick="login()">登录</button>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3 social-login">
                    <h4>思齐-云亭 : 携手共赢</h4>
                   <!-- <div class="social-login-buttons">
                        <a class="btn btn-link-1 btn-link-1-facebook" href="#">
                            <i class="fa fa-facebook"></i> Facebook
                        </a>
                        <a class="btn btn-link-1 btn-link-1-twitter" href="#">
                            <i class="fa fa-twitter"></i> Twitter
                        </a>
                        <a class="btn btn-link-1 btn-link-1-google-plus" href="#">
                            <i class="fa fa-google-plus"></i> Google Plus
                        </a>
                    </div>-->
                </div>
            </div>
        </div>
    </div>

</div>



<!-- Javascript -->
<script src="../login/bootstrap/js/bootstrap.min.js"></script>
<script src="../login/js/jquery.backstretch.min.js"></script>
<script src="../login/js/scripts.js"></script>

<!--[if lt IE 10]>
<script src="../login/js/placeholder.js"></script>
<![endif]-->

<script>
    function login(){
        $.ajax({
            url:"/login/admin_login",
            data:{
                account:$("#form-username").val(),
                passWord:$('#form-password').val()
            },
            success:function (data) {
                // console.log(data)
                if(data.result=="fail"){
                    alert(data.msg);
                } else if(data.result == "success"){
                    window.location.href='/mainMenu/goto1';
                }
            }
        })
    }

</script>

</body>


<!--<body>








</body>-->
</html>
