<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>思齐党建后台-修改密码</title>
    <link rel="stylesheet" href="/js/layui/css/layui.css">
    <link rel="stylesheet" href="/css/public.css">

    <script src="/js/layui/layui.js"></script>
    <script src="/js/jquery/jquery-3.3.1.min.js"></script>

</head>
<body>
<div style="width: 90%">
    <blockquote class="layui-elem-quote layui-quote-nm" id="footer"
                style="margin-top: 50px;margin-left: 5%;padding-left: 45px;border-color: #009688;color: #009688;font-weight: bold">
        修改密码
    </blockquote>
</div>
<div style="width:90%">
    <div class="container_div">
        <form class="layui-form" style="margin-top: 10px">
            <div class="layui-form-item input_row_margin_top" style="margin-top: 30px">
                <label class="layui-form-label label_width_150 text_left" style="font-size: 14px">输入旧密码</label>
                <div class="layui-input-inline">
                    <div id="second_category_div">
                        <input id="old_password" name="vender" placeholder="请输入旧密码" maxlength="20" lay-verify="required"
                               autocomplete="off" class="layui-input text_column" value="" type="password">
                    </div>

                </div>
            </div>

            <div class="layui-form-item input_row_margin_top" style="margin-top: 30px">
                <label class="layui-form-label label_width_150 text_left" style="font-size: 14px">输入新密码</label>
                <div class="layui-input-inline">
                    <div id="second_category_div">
                        <input id="new_password" name="vender" placeholder="输入新密码" maxlength="20" type="password"
                               lay-verify="required"
                               autocomplete="off" class="layui-input text_column" value="">
                    </div>
                </div>
            </div>

            <div class="layui-form-item input_row_margin_top" style="margin-top: 30px">
                <label class="layui-form-label label_width_150 text_left" style="font-size: 14px">确认密码</label>
                <div class="layui-input-inline">
                    <div id="second_category_div">
                        <input id="new_password_second" name="vender" placeholder="请输入确认密码" maxlength="20"
                               type="password"
                               lay-verify="required"
                               autocomplete="off" class="layui-input text_column" value="">
                    </div>
                </div>
            </div>
            <div class="layui-form-item input_row_margin_top" style="margin-left: 180px">
                <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </form>
    </div>
</div>

<script>
    layui.config({
        version: '1551352891272' //为了更新 js 缓存，可忽略
    });

    layui.use("form", function () {
        var form = layui.form;
        form.on('submit(formDemo)', function () {
            var old_password = $("#old_password").val();
            var new_password = $("#new_password").val();
            var seconde_password = $("#new_password_second").val();
            if (new_password != seconde_password) {
                layer.msg("2次密码不一致", {icon: 0});
                return false;
            }
            if (old_password == new_password) {
                layer.msg("新密码不能和旧密码一样", {icon: 0});
                return false;
            }
            $.ajax({
                url: "../admin/reset_password",
                method: "POST",
                data: {
                    new_password: new_password,
                    old_password: old_password
                },
                success: function (data) {
                    if (data.result == 'success') {
                        layer.msg('保存成功', {icon: 1});
                        setTimeout(function () {
                            // window.location.reload();
                            // window.location.href='http://localhost:8080/login/logout';
                            top.location.href='http://localhost:8080/login/logout';
                        }, 1500);
                    } else {
                        layer.msg(data.msg, {icon: 2});
                    }
                }
            });
            return false;
        })

    });


</script>

</body>
</html>