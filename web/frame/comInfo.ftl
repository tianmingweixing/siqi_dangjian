<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>思齐党建后台-公司信息设置</title>
    <link rel="stylesheet" href="/js/layui/css/layui.css">
    <link rel="stylesheet" href="/css/public.css">

    <script src="/js/layui/layui.js"></script>
    <script src="/js/jquery/jquery-3.3.1.min.js"></script>

</head>
<body>

<form class="layui-form" action="">

    <!--<div class="layui-form-item input_row_margin_top" style="margin-top: 30px">
        <label class="layui-form-label">商品类别</label>
        <div class="layui-input-inline">
            <select id="category_type" name="category_type" lay-filter="select_first_category">
                <option value=''>请选择</option>
            </select>
        </div>
        <div class="layui-input-inline" style="margin-left: 30px">
            <select id="category_second_type" name="category_second_type">
                <option value=''>请选择</option>
            </select>
        </div>
    </div>-->
    <div style="width: 90%">
        <blockquote class="layui-elem-quote layui-quote-nm" id="footer"
                    style="margin-top: 50px;margin-left: 5%;padding-left: 45px;border-color: #009688;color: #009688;font-weight: bold">
            公司信息设置
        </blockquote>
    </div>

    <div style="width: 90%">
        <div class="container_div">
            <input type="hidden" id="com_img" name="com_img" value="${com_img!}" style="width: 800px;"/>

            <div class="layui-form-item input_row_margin_top">
                <label class="layui-form-label ">公司名称</label>
                <div class="layui-input-inline">
                    <input id="com_name" name="com_name" lay-verify="required" placeholder="请输入公司名称" maxlength="40"
                           autocomplete="off" class="layui-input" value="">
                </div>
                <label class="layui-form-label" style="margin-left: 85px">公司电话</label>
                <div class="layui-input-inline">
                    <input id="com_phone" name="com_phone" lay-verify="required" placeholder="请输入公司电话" maxlength="20"
                           autocomplete="off" class="layui-input" value="">
                </div>
            </div>
            <div class="layui-form-item input_row_margin_top">
                <label class="layui-form-label">公司图片</label>
                <div class="layui-form-item input_row_margin_top" style="padding-left: 112px;">
                    <div class="layui-upload">
                        <button type="button" class="layui-btn" id="test1">上传图片</button>
                        <div class="layui-upload-list">
                            <img class="layui-upload-img" id="demo1" src="/images/defaultImg.jpg" alt="公司图片" style="width: 200px; height: auto; min-height: 60px; border: 1px solid #CCCCCC;">
                            <p id="demoText"></p>
                        </div>
                    </div>
                </div>
            </div>


            <div class="layui-form-item layui-form-text input_row_margin_top" style="padding-right: 900px">
                <label class="layui-form-label">公司简介</label>
                <div class="layui-input-block">
            <textarea name="com_info" id="com_info" placeholder="请输入内容" style="width: 585px; height: 200px;" maxlength="1000"
                      class="layui-textarea"></textarea>
                </div>
            </div>

            <div class="layui-form-item input_row_margin_top">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>

        </div>
    </div>

</form>
<script type="text/javascript" src="/js/util/compressImg.js"></script>
<script>
    <!--var count =<#if imageList??>${imageList?size}<#else >0</#if>;-->

    layui.use(['form','upload'], function () {
        var $ = jQuery;
        var form = layui.form;
        var upload = layui.upload;

        //上传头像
        upload.render({
            elem: '#test1'
            ,accept: 'file'
            ,type: "POST"
            ,url: '/upload/uploadImage'
            ,auto: false
            ,choose: function(obj){
                //将每次选择的文件追加到文件队列
                var files = obj.pushFile();

                var imgFiles = files;
                var filesArry = [];
                for (var key in imgFiles) { //将上传的文件转为数组形式
                    filesArry.push(imgFiles[key])
                }

                for (let i = filesArry.length-1; i >= 0; i--) {
                    var file = filesArry[i]; //获取最后选择的图片,即处理多选情况

                    if (navigator.appName == "Microsoft Internet Explorer" && parseInt(navigator.appVersion.split(";")[1]
                            .replace(/[ ]/g, "").replace("MSIE", "")) < 9) {
                        return obj.upload(index, file)
                    }
                    canvasDataURL(file, function (blob) {
                        var aafile = new File([blob], file.name, {
                            type: file.type
                        })
                        var isLt1M;
                        if (file.size < aafile.size) {
                            isLt1M = file.size
                        } else {
                            isLt1M = aafile.size
                        }

                        if (isLt1M / 1024 / 1024 > 2) {
                            return layer.alert('上传图片过大！')
                        } else {
                            if (file.size < aafile.size) {
                                return obj.upload(i, file)
                            }
                            obj.upload(i, aafile)
                        }
                    })
                }


                //预读本地文件，如果是多文件，则会遍历。(不支持ie8/9)
                obj.preview(function(index, file, result){
                    $('#demo1').attr('src', result); //图片链接（base64）
                });
            }
            ,done: function(res){
                //如果上传
                if(res.code == 0){
                    $("#com_img").val(res.src);
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
            /*var formData = new FormData();
            formData.append("vedio", document.getElementById("chooseVedio").files[0]);
            formData.append("uuid", uuid);*/

            $.ajax({
                url: "../config/setConfig",
                type: "POST",
                data: {
                    com_name: $("#com_name").val(),
                    com_phone: $("#com_phone").val(),
                    com_info: $("#com_info").val(),
                    com_img: $("#com_img").val(),
                },
                success: function (data) {

                    if (data.code == 0){
                        layer.msg(data.msg, {icon: 1});
                        setTimeout(function () {
                            location.reload();
                        }, 1500);
                    } else {
                        layer.msg(data.msg, {icon: 2});
                    }
                }
            });
            return false;
        });

       /* $().ready(function () {
            //初始化信息//
            initComInfo();
        })*/

        $(function () {
            innitComInfo();
        })

        //初始化公司信息
        function innitComInfo() {
            $.ajax({
                url: "/config/getConfig",//请求地址
                type: "GET",//请求方式
                dataType: "JSON",//返回数据类型
                data: {},//发送的参数
                success: function (data) {
                    console.log(data);
                    if (data.code == 0) {
                        $("#com_name").val(data.comInfo.comName);
                        $("#com_phone").val(data.comInfo.comPhone);
                        $("#com_info").val(data.comInfo.comInfo);
                        $("#com_img").val(data.comInfo.comImg);
                        $('#demo1').attr('src', data.comInfo.comImg);

                        layer.msg(data.msg, {icon: 1});
                    }else {
                        layer.msg(data.msg, {icon: 2});
                    }
                },
                error: function () {
                    //失败执行的方法
                    layer.msg("查询出错", {icon: 2});
                }
            });
        }


    });

</script>

</body>
</html>