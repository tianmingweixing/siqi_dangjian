<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title> 违纪表</title>
    <link rel="stylesheet" href="../../js/layui/css/layui.css">
    <script src="../../js/layui/layui.js"></script>
    <script src="../../js/jquery/jquery-3.3.1.min.js"></script>
</head>
<body>
<div class="layui-progress">
    <div class="layui-progress-bar " lay-percent="100%"></div>
</div>
<form class="layui-form" action="">
    <br>
    <div class="layui-form-item input_row_margin_top" style="display:none ">
        <label class="layui-form-label" style="margin-left: 85px">违纪ID</label>
        <input id="id" name="id" type="hidden"  maxlength="20" value="<#if id??>${id}<#else></#if>"/>
        <label class="layui-form-label" style="margin-left: 85px">违纪凭证</label>
        <div class="layui-input-inline"><input type="" id="imgPath" name="imgPath" value="<#if certificate??>${certificate}<#else></#if>" style="width: 400px;"/></div>
    </div>

    <div class="layui-form-item input_row_margin_top" >
        <label class="layui-form-label">用户ID</label>
        <div class="layui-input-inline">
            <input id="userId" name="userId" class="layui-input" placeholder="请输入用户ID" onchange="function()"  value="<#if userId??>${userId}<#else></#if>"/>
        </div>
    </div>

    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label ">违纪人姓名</label>
        <div class="layui-input-inline">
            <input id="unit" name="unit" lay-verify="required" placeholder="请输入姓名" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if unit??>${unit}<#else></#if>">
        </div>

        <label class="layui-form-label ">年龄</label>
        <div class="layui-input-inline">
            <input id="age" name="age" lay-verify="number" placeholder="请输入年龄" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if age??>${age}<#else></#if>">
        </div>

    </div>

    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label" style="margin-left: -7px">性别</label>
        <div class="layui-input-inline">
            <input type="radio" name="sex" value="1" title="男" <#if sex??&& sex==1>checked</#if>>
            <input type="radio" name="sex" value="2" title="女" <#if sex??&& sex==2>checked</#if>>
        </div>

        <label class="layui-form-label" >手机号码</label>
        <div class="layui-input-inline">
            <input id="phone" name="phone" lay-verify="number" placeholder="请输入手机号码" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if phone??>${phone}<#else></#if>">
        </div>

    </div>

    <div class="layui-form-item input_row_margin_top" style="display: none">
        <label class="layui-form-label">类型</label>
        <div class="layui-input-inline" >
            <select name="type" id="type">
                <option value="1" <#if type?? && type==1>selected</#if>>违纪</option>
                <option value="0" <#if type?? && type==0>selected</#if>>荣誉</option>
            </select>
        </div>

    </div>

    <div class="layui-form-item input_row_margin_top">

        <label class="layui-form-label " style="margin-left: 1px">违纪时间</label>
        <div class="layui-input-inline">
            <input id="time" name="time" lay-verify="required" placeholder="请输入时间" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if time??>${time}<#else></#if>">
        </div>

        <label class="layui-form-label " style="margin-left: 1px">违纪名称</label>
        <div class="layui-input-inline">
            <input id="name" name="name" lay-verify="required" placeholder="请输入名称" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if name??>${name}<#else></#if>">
        </div>

    </div>

    <div class="layui-form-item input_row_margin_top">

        <label class="layui-form-label " style="margin-left: 1px">金额</label>
        <div class="layui-input-inline">
            <input id="amount" name="amount" lay-verify="number" placeholder="请输入金额" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if amount??>${amount}<#else></#if>">
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">违纪详情</label>
        <div class="layui-input-inline">
            <textarea name="content" id="content" placeholder="请输入详情"
                      style="width: 500px; border:1px solid #e6e6e6; font-size: 10px; line-height: 23px;color: #56aa17;
                              max-width: 1500px; height: 100px; max-height: 1000px; outline: 0;"><#if content??>${content}<#else></#if></textarea>
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-inline">
            <textarea name="note" id="note" placeholder="请输入备注"
                      style="width: 500px; border:1px solid #e6e6e6; font-size: 10px; line-height: 23px;color: #56aa17;
                              max-width: 1500px; height: 100px; max-height: 1000px; outline: 0;"><#if note??>${note}<#else></#if></textarea>
        </div>
    </div>

    <div class="layui-form-item input_row_margin_top">
        <div class="layui-upload" style="margin-left: 60px;">
            <button type="button" class="layui-btn" id="uploadImg">上传违纪凭证</button>
            <div class="layui-upload-list">
                <img class="layui-upload-img" id="certificate" src="<#if certificate??>${certificate}<#else>/home/up_load/image/2019921/3cfbd168-3d97-465e-bb26-67b4c9c6f0f8indexImg.jpg</#if>" style="width: 300px; height: 200px; border: 1px solid #CCCCCC;">
                <p id="demoText"></p>
            </div>
        </div>
    </div>



    <div class="layui-form-item">
        <div class="layui-form-item input_row_margin_top">
            <div class="layui-input-block">
                <button  class="layui-btn layui-btn-normal"  lay-submit lay-filter="formDemo">立即提交</button>
                <#if id??><#else><button type="reset" class="layui-btn layui-btn-primary">重置</button></#if>

            </div>
        </div>
    </div>

</form>

<script>

    $(function() {

        layui.use(['laydate','form','upload'], function () {
            var form = layui.form;
            var laydate = layui.laydate //日期
                    ,layer = layui.layer; //弹层
            var upload = layui.upload;


            laydate.render({
                elem: '#time' //指定元素
            });

            $("#userId").change(function(){
                $.ajax({
                    url: "/user/getUserById",
                    type: 'post',
                    data: {
                        userId : $("#userId").val()
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
                ,accept: 'file'
                ,url: '/upload/uploadImage'
                ,auto: true
                ,choose: function(obj){
                    //将每次选择的文件追加到文件队列
                    // var files = obj.pushFile();

                    //预读本地文件，如果是多文件，则会遍历。(不支持ie8/9)
                    obj.preview(function(index, file, result){
                        //???????
                        $('#certificate').attr('src', result); //图片链接（base64）
                    });
                }
                ,done: function(res){
                    //如果上传
                    if(res.code == 0){

                        //???? 图片路径数组
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
                    url: "/disciplineOfHonor/addDisciplineOfHonor",
                    type: 'post',
                    data: {
                        id: $("#id").val(),
                        userId: $("#userId").val(),
                        unit: $("#unit").val(),
                        content: $("#content").val(),
                        type: $("#type").val(),
                        passive_unit: $("#passive_unit").val(),
                        certificate: $("#imgPath").val(),
                        amount: $("#amount").val(),
                        note: $("#note").val(),
                        name: $("#name").val(),
                        time: $("#time").val()
                    },
                    success: function () {
                        layer.msg('保存成功', {icon: 1});
                        setTimeout(function () {
                            window.location.href = '/frame/disciplineList.ftl'
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