<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title> 会议表后台</title>
    <link rel="stylesheet" href="../../js/layui/css/layui.css">
    <script src="../../js/layui/layui.js"></script>
    <script src="../../js/jquery/jquery-3.3.1.min.js"></script>
</head>
<body>

<form class="layui-form" action="">

<br>
    <div class="layui-form-item input_row_margin_top" style="display:none">
        <label class="layui-form-label" style="margin-left: 85px">会议ID
            <div class="layui-input-inline"><input id="id" name="id" type=""  value="<#if id??>${id}<#else></#if>"/></div>

        <label class="layui-form-label" style="margin-left: 85px">会议图片</label>
            <div class="layui-input-inline"><input type="" id="imgPath" name="imgPath" value="<#if images_a??>${images_a}<#else></#if>" style="width: 400px;"/></div>
    </div>

   <!-- <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label">用户ID</label>
        <div class="layui-input-inline">
            <input id="userId" name="userId" type=""  maxlength="20" value="<#if userId??>${userId}<#else></#if>"/>
        </div>
    </div>-->


    <div class="layui-form-item input_row_margin_top">

        <label class="layui-form-label ">会议名称</label>
        <div class="layui-input-inline">
            <input id="name" name="name" lay-verify="required" placeholder="请输入名称" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if name??>${name}<#else></#if>">
        </div>

        <label class="layui-form-label">会议类型</label>
        <div class="layui-input-inline">
            <select name="meeting_type" id="meeting_type">
                <option value="">全部</option>
                <option value="1" <#if meeting_type?? && meeting_type==1>selected</#if>>支委会</option>
                <option value="2" <#if meeting_type?? && meeting_type==2>selected</#if>>党员大会</option>
                <option value="3" <#if meeting_type?? && meeting_type==3>selected</#if>>廉政</option>
            </select>
        </div>

    </div>

    <div class="layui-form-item input_row_margin_top">

        <label class="layui-form-label " style="margin-left: 1px">开始时间</label>
        <div class="layui-input-inline">
            <input id="start_time" name="start_time" lay-verify="required" placeholder="请输入开始时间" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if start_time??>${start_time}<#else></#if>">
        </div>
        <div class="layui-form-mid layui-word-aux"></div>
        <label class="layui-form-label " style="margin-left: -10px">结束时间</label>
        <div class="layui-input-inline">
            <input id="end_time" name="end_time" lay-verify="required" placeholder="请输入结束时间" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if end_time??>${end_time}<#else></#if>">
        </div>

    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">会议内容</label>
        <div class="layui-input-block">
            <textarea name="content" id="content" placeholder="请输入会议内容"
                      class="layui-textarea"><#if content??>${content}<#else></#if></textarea>
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">会议指导</label>
        <div class="layui-input-block">
                <textarea name="guide" id="guide" placeholder="请输入会议指导"
                          class="layui-textarea"><#if guide??>${guide}<#else></#if></textarea>
        </div>
    </div>

    <div class="layui-form-item input_row_margin_top">
        <div class="layui-upload" style="margin-left: 40px;">
            <button type="button" class="layui-btn" id="uploadImg">上传图片</button>
            <div class="layui-upload-list">
                <img class="layui-upload-img" id="images_a" src="<#if images_a??>${images_a}<#else>/home/up_load/image/201992/31976cf7-a50c-4b44-8d9e-27545253fded添加.png</#if>" style="width: 90px; height: 90px; border: 1px solid #CCCCCC;">
                <p id="demoText"></p>
            </div>
        </div>
    </div>


    <div class="layui-form-item">
        <div class="layui-form-item input_row_margin_top">
            <div class="layui-input-block">
                <button  class="layui-btn layui-btn-normal"  lay-submit lay-filter="formDemo">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </div>

</form>

<script>

    $(function() {

        layui.use(['laydate','form','upload'], function () {
            var form = layui.form;
            // var $ = layui.jQuery;

            var laydate = layui.laydate //日期
                    ,layer = layui.layer; //弹层
            var upload = layui.upload;


            laydate.render({
                elem: '#start_time' //指定元素
            });
            laydate.render({
                elem: '#end_time' //指定元素
            });

            //上传头像
            upload.render({
                elem: '#uploadImg'
                ,accept: 'file'
                ,url: '/upload/uploadImage'
                ,auto: true
                ,choose: function(obj){
                    //将每次选择的文件追加到文件队列
                    // var files = obj.pushFile();

                    //预读本地文件，如果是多文件，则会遍历。(不支持ie8/9)
                    obj.preview(function(index, file, result){
                        $('#images_a').attr('src', result); //图片链接（base64）
                    });
                }
                ,done: function(res){
                    //如果上传
                    if(res.code == 0){
                        $("#imgPath").val(res.src);
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
                    url: "/meeting/addMeeting",
                    type:'post',
                    data: {
                        id: $("#id").val(),
                        name: $("#name").val(),
                        meeting_type: $("#meeting_type").val(),
                        content: $("#content").val(),
                        guide: $("#guide").val(),
                        imgPath: $("#imgPath").val(),
                        start_time: $("#start_time").val(),
                        end_time: $("#end_time").val()
                    },
                    success: function () {
                        layer.msg('保存成功', {icon: 1});
                        setTimeout(function () {
                            window.location.href = '/frame/meetingList.ftl'
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