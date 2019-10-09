<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title> 入党申请页面</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css">
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jquery/jquery-3.3.1.min.js"></script>
</head>
<body>
    <div style="width: 90%">
        <blockquote class="layui-elem-quote layui-quote-nm" id="footer"
                    style="margin-top: 50px;margin-left: 5%;padding-left: 45px;border-color: #009688;color: #009688;font-weight: bold">
            添加入党申请
        </blockquote>
    </div>
    <div style="width:90%">
        <div class="container_div">
            <form class="layui-form" action="">
                <br>
                <div class="layui-form-item input_row_margin_top" style="display:none ">
                    <label class="layui-form-label" style="margin-left: 85px">入党申请ID</label>
                    <input id="id" name="id" type="hidden" maxlength="20" value=""/>
                    <label class="layui-form-label" style="margin-left: 85px">申请表图片</label>
                    <div class="layui-input-inline"><input type="" id="imgPath" name="imgPath" value=""
                                                           style="width: 400px;"/></div>
                </div>


                <div class="layui-form-item input_row_margin_top" style="display:none ">
                    <label class="layui-form-label">用户ID</label>
                    <div class="layui-input-inline">
                        <input id="userId" name="userId" class="layui-input" placeholder="请输入用户ID" value=""/>
                    </div>
                </div>


                <div class="layui-form-item input_row_margin_top">
                    <label class="layui-form-label ">姓名</label>
                    <div class="layui-input-inline">
                        <input id="userName" name="userName"  lay-verify="required" placeholder="请输入姓名"
                               maxlength="20"
                               autocomplete="off" class="layui-input" value="">
                    </div>
                    <label class="layui-form-label">手机号码</label>
                    <div class="layui-input-inline">
                        <input id="phone" name="phone" lay-verify="number" placeholder="请输入手机号码" maxlength="20"
                               autocomplete="off" class="layui-input" value="">
                    </div>

                </div>


                <div class="layui-form-item input_row_margin_top">
                    <div class="layui-upload" style="margin-left: 60px;">
                        <button type="button" class="layui-btn" id="uploadImg">上传入党申请</button>
                        <div class="layui-upload-list">
                            <img class="layui-upload-img" id="appForm" src="/images/defaultImg.jpg"
                                 style="width: 300px; height: 200px; border: 1px solid #CCCCCC;">
                            <p id="demoText"></p>
                        </div>
                    </div>
                </div>


                <div class="layui-form-item">
                    <div class="layui-form-item input_row_margin_top">
                        <div class="layui-input-block">
                            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="formDemo">立即提交</button>
                            <!--<button type="reset" class="layui-btn layui-btn-primary">重置</button>-->
                        </div>
                    </div>
                </div>

            </form>
        </div>
    </div>
<script>

    $(function () {

        if (parent.PartitionData) {
            $("#userName").val(parent.PartitionData[0].username);
            $("#userId").val(parent.PartitionData[0].id);
        }

        layui.use(['laydate', 'form', 'upload'], function () {
            var form = layui.form;
            var laydate = layui.laydate //日期
                    , layer = layui.layer; //弹层
            var upload = layui.upload;


            laydate.render({
                elem: '#time' //指定元素
            });

            $("#userId").change(function () {
                $.ajax({
                    url: "/user/getUserById",
                    type: 'post',
                    data: {
                        userId: $("#userId").val()
                    },
                    success: function (data) {
                        console.log(data.user.sex);

                        layer.msg('用户查询成功', {icon: 1});
                        $("#unit").val(data.user.userName);
                        $("#age").val(data.user.age);
                        $("#phone").val(data.user.phone);
                        $('input:radio').eq(data.user.sex).attr('checked', 'true');
                        form.render();
                    }
                });
            });

            //上传凭证
            upload.render({
                elem: '#uploadImg'
                , accept: 'file'
                , url: '/upload/uploadImage'
                , auto: true
                , choose: function (obj) {
                    //将每次选择的文件追加到文件队列
                    // var files = obj.pushFile();

                    //预读本地文件，如果是多文件，则会遍历。(不支持ie8/9)
                    obj.preview(function (index, file, result) {
                        //???????
                        $('#appForm').attr('src', result); //图片链接（base64）
                    });
                }
                , done: function (res) {
                    //如果上传
                    if (res.code == 0) {

                        //???? 图片路径数组
                        $("#imgPath").val(res.src);
                        layer.msg('上传成功');
                    }
                }
                , error: function () {
                    //演示失败状态，并实现重传
                    var demoText = $('#demoText');
                    demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                    demoText.find('.demo-reload').on('click', function () {
                        uploadInst.upload();
                    });
                }
            });


            form.on('submit(formDemo)', function (data) {
                var defalutImg = "/images/defaultImg.jpg";

                if ($("#imgPath").val() == "") {
                    $('#imgPath').val(defalutImg);
                }
                $.ajax({
                    url: "/applicationForm/addApplicationForm",
                    type: 'post',
                    data: {
                        id: $("#id").val(),
                        userId: $("#userId").val(),
                        appForm: $("#imgPath").val(),
                        userName: $("#userName").val(),
                        phone: $("#phone").val()
                    },
                    success: function () {
                        layer.msg('保存成功', {icon: 1});
                        setTimeout(function () {
                            window.location.href = '/applicationFormList.ftl'
                        }, 1500);
                    }
                });
                return false;
            });
        });
    });


</script>

</body>
</html>