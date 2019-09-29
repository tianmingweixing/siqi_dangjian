<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title> 会议类型</title>
    <link rel="stylesheet" href="../../js/layui/css/layui.css">
    <link rel="stylesheet" href="/css/public.css">
    <script src="../../js/layui/layui.js"></script>
    <script src="../../js/jquery/jquery-3.3.1.min.js"></script>
</head>
<body>

<form class="layui-form" action="">

    <div style="width: 90%">
        <blockquote class="layui-elem-quote layui-quote-nm" id="footer"
                    style="margin-top: 50px;margin-left: 5%;padding-left: 45px;border-color: #009688;color: #009688;font-weight: bold">
            修改会议类型
        </blockquote>
    </div>

    <div style="width:90%">
        <div class="container_div">
    <div class="layui-form-item input_row_margin_top" style="display:none ">
        <label class="layui-form-label" style="margin-left: 85px">会议ID</label>
        <input id="id" name="id" type="hidden"  maxlength="20" value="<#if id??>${id}<#else></#if>"/>
    </div>


    <div class="layui-form-item input_row_margin_top">

        <label class="layui-form-label " style="margin-left: 1px">类型名称</label>
        <div class="layui-input-inline">
            <input id="type_name" name="type_name" lay-verify="required" placeholder="请输入类型名称" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if type_name??>${type_name}<#else></#if>">
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
        </div>
    </div>
</form>

<script>

    $(function() {

        layui.use(['laydate','form'], function () {
            var form = layui.form;
            var laydate = layui.laydate //日期
                    ,layer = layui.layer; //弹层

            form.on('submit(formDemo)', function () {

                $.ajax({
                    url: "/meetingType/addMeetingType",
                    data: {
                        id: $("#id").val(),
                        type_name: $("#type_name").val()
                    },
                    success: function () {
                        layer.msg('保存成功', {icon: 1});
                        setTimeout(function () {
                            window.location.href = '/frame/meetingTypeList.ftl'
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