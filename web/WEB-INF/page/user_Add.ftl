<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title> 党支部后台</title>
    <link rel="stylesheet" href="../../js/layui/css/layui.css">
    <script src="../../js/layui/layui.js"></script>
    <script src="../../js/jquery/jquery-3.3.1.min.js"></script>
</head>
<body>

<form class="layui-form" action="">

<br>
    <div class="layui-input-inline" style="display:none ">
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
        <label class="layui-form-label" style="margin-left: 0px">性别</label>
        <div class="layui-input-inline">
            <input type="radio" name="sex" value="1" title="男" <#if sex??&&sex==1>checked</#if>>
            <input type="radio" name="sex" value="0" title="女" <#if sex??&&sex==0>checked</#if>>
        </div>
    </div>



    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label ">年龄</label>
        <div class="layui-input-inline">
            <input id="age" name="age" lay-verify="number" placeholder="请输入年龄" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if age??>${age}<#else></#if>">
        </div>

        <label class="layui-form-label" style="margin-left: 84px">手机号码</label>
        <div class="layui-input-inline">
            <input id="phone" name="phone" lay-verify="number" placeholder="请输入手机号码" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if phone??>${phone}<#else></#if>">
        </div>
    </div>

    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label ">学历</label>
        <div class="layui-input-inline">
            <input id="education" name="education" lay-verify="number" placeholder="请输入学历" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if education??>${education}<#else></#if>">
        </div>

        <label class="layui-form-label" style="margin-left: 84px">单位</label>
        <div class="layui-input-inline">
            <input id="company" name="company" lay-verify="required" placeholder="请输入单位" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if company??>${company}<#else></#if>">
        </div>
    </div>

    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label">职务</label>
        <div class="layui-input-inline">
            <select name="dutyid" id="dutyid">
                <option value="">全部</option>
                <option value="1" <#if dutyid?? && dutyid==1>selected</#if>>积极分子</option>
                <option value="2" <#if dutyid?? && dutyid==2>selected</#if>>正式党员</option>
                <option value="3" <#if dutyid?? && dutyid==3>selected</#if>>党委</option>
            </select>
        </div>

        <label class="layui-form-label " style="margin-left: 84px">入党时间</label>
        <div class="layui-input-inline">
            <input id="joinTime" name="joinTime" lay-verify="required" placeholder="请输入入党时间" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if joinTime??>${joinTime}<#else></#if>">
        </div>
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


            form.on('submit(formDemo)', function () {

                $.ajax({
                    url: "/user/addUser",
                    data: {
                        id: $("#id").val(),
                        username: $("#username").val(),
                        sex: $("#sex").val(),
                        education: $("#education").val(),
                        age: $("#age").val(),
                        dutyId: $("#dutyid").val(),
                        joinTime: $("#joinTime").val(),
                        sex: $('input[name="sex"]:checked').val(),
                        ID_cord: $("#ID_cord").val(),
                        company: $("#company").val(),
                        phone: $("#phone").val()
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