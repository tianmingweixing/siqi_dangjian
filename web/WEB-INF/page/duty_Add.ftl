<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title> 职务表后台</title>
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
        <label class="layui-form-label ">党内职务</label>
        <div class="layui-input-inline">
            <input id="party_duty" name="party_duty" lay-verify="required" placeholder="请输入党内职务" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if party_duty??>${party_duty}<#else></#if>">
        </div>

    </div>

    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label">人员类型</label>
        <div class="layui-input-inline">
            <input id="type_name" name="type_name" lay-verify="required" placeholder="请输入类型名称" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if type_name??>${type_name}<#else></#if>">
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

            form.on('submit(formDemo)', function (data) {

                $.ajax({
                    url: "/duty/addDuty",
                    data: {
                        id: $("#id").val(),
                        type_name: $("#type_name").val(),
                        party_duty: $("#party_duty").val()
                    },
                    success: function () {
                        layer.msg('保存成功', {icon: 1});
                        setTimeout(function () {
                            window.location.href = '/frame/dutyList.ftl'
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