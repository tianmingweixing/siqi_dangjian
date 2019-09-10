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
        <input id="id" name="id"   maxlength="20" value="<#if id??>${id}<#else></#if>"/>
    </div>
    <div class="layui-input-inline" style="display:none ">
        <label class="layui-form-label"  style="margin-left: 85px">职务id</label>
        <input id="dutyid" name="dutyid" maxlength="20" value="<#if dutyid??>${dutyid}<#else></#if>"/>
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
                <input type="radio" name="sex" value="2" title="女" <#if sex??&&sex==2>checked</#if>>
            </div>

        <label class="layui-form-label" style="margin-left: 85px">政治面貌</label>
        <div class="layui-input-inline">
            <select name="type_name" id="type_name">
                <option value="">全部</option>
            </select>
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
            <input id="education" name="education" lay-verify="required" placeholder="请输入学历" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if education??>${education}<#else></#if>">
        </div>

        <label class="layui-form-label" style="margin-left: 84px">单位</label>
        <div class="layui-input-inline">
            <input id="company" name="company" lay-verify="required" placeholder="请输入单位" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if company??>${company}<#else></#if>">
        </div>
    </div>

    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label ">入党时间</label>
        <div class="layui-input-inline">
            <input id="join_time" name="join_time" lay-verify="" placeholder="请输入入党时间" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if join_time??>${join_time}<#else></#if>">
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

    var dutyid = <#if dutyid??>"${dutyid}"<#else>""</#if>;

    $(function() {

        layui.use(['laydate', 'layer', 'table', 'carousel', 'element', 'form'], function () {
            var form = layui.form;

            var laydate = layui.laydate //日期
                    ,layer = layui.layer //弹层
                    ,element = layui.element;//元素操作

            laydate.render({
                elem: '#join_time' //指定元素
            });

            //查询计划种类
            $.ajax({
                url: "/duty/allCategory",
                async: false,
                success: function (data) {
                    $.each(data.list, function (i, item) {
                        if(item.id == dutyid) {
                            $("#type_name").append("<option selected='selected'  value='" + item.id + "'>" + item.type_name + "</option>");
                        }else{
                            $("#type_name").append("<option  value='" + item.id + "'>" + item.type_name + "</option>");

                        }
                    });

                    form.render('select');
                }
            });


            form.on('submit(formDemo)', function () {

                        $.ajax({
                            url: "/user/addUser",
                            data: {
                                id: $("#id").val(),
                                username: $("#username").val(),
                                age: $("#age").val(),
                                education: $("#education").val(),
                                age: $("#age").val(),
                                dutyid : $("#type_name").val(),
                                join_time: $("#join_time").val(),
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