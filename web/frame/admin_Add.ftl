<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>思齐党建后台-添加管理员</title>
    <link rel="stylesheet" href="/js/layui/css/layui.css">
    <link rel="stylesheet" href="/css/public.css">

    <script src="/js/layui/layui.js"></script>
    <script src="/js/jquery/jquery-3.3.1.min.js"></script>

</head>
<body>

<form class="layui-form" action="">

    <div style="width: 90%">
        <blockquote class="layui-elem-quote layui-quote-nm" id="footer"
                    style="margin-top: 50px;margin-left: 5%;padding-left: 45px;border-color: #009688;color: #009688;font-weight: bold">
            添加管理员
        </blockquote>
    </div>

    <div style="width: 90%">
        <div class="container_div">
            <input type="hidden" id="headImg" name="headImg" value="<#if admin_head??>${admin_head}<#else></#if>" style="width: 800px;"/>

            <!--<div class="layui-form-item input_row_margin_top">
                <label class="layui-form-label">所属支部</label>
                <div class="layui-input-inline">
                    <input id="party_branch_id" name="party_branch_id" lay-verify="required" placeholder="请输入支部ID" maxlength="20"
                           autocomplete="off" class="layui-input" value="<#if party_branch_id??>${party_branch_id}<#else></#if>">
                </div>
            </div>-->

            <div class="layui-form-item input_row_margin_top">
                <label class="layui-form-label ">昵称</label>
                <div class="layui-input-inline">
                    <input id="username" name="username" lay-verify="required" placeholder="请输入管理员昵称" maxlength="40"
                           autocomplete="off" class="layui-input" value="<#if username??>${username}<#else></#if>">
                </div>
                <label class="layui-form-label" style="margin-left: 85px">类型</label>
                <div class="layui-input-inline">
                    <select id="admin_type" name="admin_type">
                        <#if admin_type??>
                            <#if admin_type == 1><option value='1'>超级管理员</option><#else><option value='2'>普通管理员</option></#if>
                        <#else><option value='2'>普通管理员</option>
                        </#if>
                        <!--<option value='1'>超级管理员</option>
                        <option value='2'>普通管理员</option>-->
                    </select>
                </div>
            </div>

            <div class="layui-form-item input_row_margin_top">
                <label class="layui-form-label ">账号</label>
                <div class="layui-input-inline">
                    <input id="account" name="account" lay-verify="required" placeholder="请输入管理员账号" maxlength="20"
                           autocomplete="off" class="layui-input" value="<#if admin_account??>${admin_account}<#else></#if>">
                </div>
                <label class="layui-form-label" style="margin-left: 85px">密码</label>
                <div class="layui-input-inline">
                    <input id="password" name="password" lay-verify="required" placeholder="请输入管理员密码" maxlength="20"
                           autocomplete="off" class="layui-input" value="">
                </div>
            </div>

            <div class="layui-form-item input_row_margin_top">
                <div class="layui-upload">
                    <button type="button" class="layui-btn" id="test1">上传图片</button>
                    <div class="layui-upload-list">
                        <img class="layui-upload-img" id="demo1" src="<#if admin_head??>${admin_head}<#else>/images/defaultImg.jpg</#if>" style="width: 90px; height: 90px; border: 1px solid #CCCCCC;">
                        <p id="demoText"></p>
                    </div>
                </div>
            </div>

            <div class="layui-form-item input_row_margin_top">
                <div>
                    <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>

        </div>
    </div>

</form>

<script>
    var id = <#if id??>"${id}"<#else>""</#if>;

    layui.use(['form','upload'], function () {
        var $ = jQuery;
        var form = layui.form;
        var upload = layui.upload;

        //上传头像
        upload.render({
            elem: '#test1'
            ,accept: 'file'
            ,url: '/upload/uploadImage'
            ,auto: true
            ,choose: function(obj){
                //将每次选择的文件追加到文件队列
                // var files = obj.pushFile();

                //预读本地文件，如果是多文件，则会遍历。(不支持ie8/9)
                obj.preview(function(index, file, result){
                    $('#demo1').attr('src', result); //图片链接（base64）
                });
            }
            ,done: function(res){
                //如果上传
                if(res.code == 0){
                    $("#headImg").val(res.src);
                    layer.msg('上传成功');
                }
            }
            ,error: function(){
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function(){
                    uploadInst.upload();
                });
            }
        });


        form.on('submit(formDemo)', function (data) {

            $.ajax({
                url: "/admin/saveAdmin",
                type: "POST",
                data: {
                    // party_branch_id: $("#party_branch_id").val(),
                    party_branch_id:1,
                    username: $("#username").val(),
                    admin_type: $("#admin_type").val(),
                    admin_account: $("#account").val(),
                    password: $("#password").val(),
                    headImg: $("#headImg").val(),
                    id: id
                },
                success: function (data) {
                    // console.log(data)

                    if (data.code == 0){
                        layer.msg(data.msg, {icon: 1});
                        setTimeout(function () {
                            window.location.href = '/frame/adminList.ftl';
                        }, 1500);
                    }else {
                        layer.msg(data.msg, {icon: 2});
                    }
                }

            });

            return false;
        });


    });

</script>

</body>
</html>