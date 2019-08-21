<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title> 党支部后台</title>
    <link rel="stylesheet" href="../../js/layui/css/layui.css">
    <script src="../../js/layui/layui.js"></script>
    <script src="../../js/jquery/jquery-3.3.1.min.js"></script>
    <!--<link rel="stylesheet" type="text/css" href="/WImageUpload/webuploader.css">-->
    <!--<script type="text/javascript" src="/WImageUpload/webuploader.js"></script>-->
</head>
<body>

<form class="layui-form" action="">

<br>
    <!--<input type="hidden" id="shareCount" value="<#if share_count??>${share_count}<#else></#if>">-->
    <div class="layui-input-inline" style="display: none">
        <label class="layui-form-label" style="margin-left: 85px">Id</label>
        <input id="id" name="id" type="hidden"  maxlength="20" value="<#if id??>${id}<#else></#if>"/>
    </div>
    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label ">昵称</label>
        <div class="layui-input-inline">
            <input id="username" name="username" lay-verify="required" placeholder="请输入昵称" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if username??>${username}<#else></#if>">
        </div>
        <label class="layui-form-label" style="margin-left: 85px">身份证</label>
        <div class="layui-input-inline">
            <input id="ID_cord" name="ID_cord" lay-verify="required" placeholder="请输入身份证" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if ID_cord??>${ID_cord}<#else></#if>">
        </div>

    </div>

    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label" style="margin-left: 85px">性别</label>
        <div class="layui-input-inline">
            <input type="radio" name="sex" value="1" title="男" <#if sex??&&sex==1>checked</#if>>
            <input type="radio" name="sex" value="0" title="女" <#if sex??&&sex==0>checked</#if>>
        </div>
        <div class="layui-form-mid layui-word-aux"></div>
    </div>

    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label ">年龄</label>
        <!--<label class="layui-form-label " style="margin-left: 85px">党支部编号</label>-->
        <div class="layui-input-inline">
            <input id="age" name="age" lay-verify="number" placeholder="请输入年龄" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if age??>${age}<#else></#if>">
        </div>
    </div>

    <div class="layui-form-item input_row_margin_top">
    <label class="layui-form-label ">学历</label>
    <!--<label class="layui-form-label " style="margin-left: 85px">党小组编号</label>-->
    <div class="layui-input-inline">
        <input id="education" name="education" lay-verify="number" placeholder="请输入编号" maxlength="20"
               autocomplete="off" class="layui-input" value="<#if education??>${education}<#else></#if>">
    </div>
    </div>

    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label ">职务</label>
        <div class="layui-input-inline">
            <input id="dutyid" name="dutyid" lay-verify="number" placeholder="请输入职务" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if dutyid??>${dutyid}<#else></#if>">
        </div>
        <label class="layui-form-label " style="margin-left: 84px">入党时间</label>
        <div class="layui-input-inline">
            <input id="joinTime" name="joinTime" lay-verify="required" placeholder="请输入入党时间" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if joinTime??>${joinTime}<#else></#if>">
        </div>
        <div class="layui-form-mid layui-word-aux"></div>
    </div>


    <div class="layui-form-item">
        <div class="layui-form-item input_row_margin_top">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </div>

</form>

<script>

    $(function() {

        layui.use('form', function () {
            var form = layui.form;

            form.on('submit(formDemo)', function (data) {

                // if (state === 'uploading') {
                //     uploader.stop();
                // } else {
                //     uploader.upload();
                // }

                $.ajax({
                    url: "/user/addUser",
                    data: {
                        id: $("#id").val(),
                        username: $("#username").val(),
                        sex: $("#sex").val(),
                        education: $("#education").val(),
                        age: $("#age").val(),
                        dutyId: $("#dutyId").val(),
                        joinTime: $("#joinTime").val(),
                        // address: $("#address").val(),
                        ID_cord: $("#ID_cord").val()
                        // company: $("#company").val()
                    },
                    success: function () {
                        layer.msg('保存成功', {icon: 1});
                        setTimeout(function () {
                            window.location.href = '/frame/userList.ftl'
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