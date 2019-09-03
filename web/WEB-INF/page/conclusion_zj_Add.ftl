<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title> 工作总结表</title>
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
        <label class="layui-form-label" style="margin-left: 85px">总结ID</label>
        <input id="id" name="id" type="hidden"  maxlength="20" value="<#if id??>${id}<#else></#if>"/>
    </div>


    <div class="layui-form-item input_row_margin_top">


        <label class="layui-form-label">季度类型</label>
        <div class="layui-input-inline">
            <select name="conclusion_type" id="conclusion_type">
                <option value="">全部</option>
            </select>
        </div>

    </div>

    <div class="layui-form-item input_row_margin_top">

        <label class="layui-form-label " style="margin-left: 1px">年限</label>
        <div class="layui-input-inline">
            <input id="year_limit" name="year_limit" lay-verify="required" placeholder="请输入年限" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if year_limit??>${year_limit}<#else></#if>">
        </div>

        <label class="layui-form-label " style="margin-left: 1px">标题</label>
        <div class="layui-input-inline">
            <input id="title" name="title" lay-verify="required" placeholder="请输入标题" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if title??>${title}<#else></#if>">
        </div>

    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">总结内容</label>
        <div class="layui-input-block">
            <textarea name="plan_content" id="plan_content" placeholder="请输入总结内容"
                      style="width: 1000px; border:1px solid #e6e6e6; font-size: 16px; line-height: 23px; font-family: 微软雅黑;
                              max-width: 1500px; height: 400px; max-height: 1000px; outline: 0;"><#if plan_content??>${plan_content}<#else></#if></textarea>
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

        layui.use(['element','laydate','form'], function () {
            var form = layui.form;
            var laydate = layui.laydate //日期
                    ,layer = layui.layer; //弹层
            var element = layui.element;


            laydate.render({
                elem: '#year_limit' //指定元素
            });

            //查询计划种类
            $.ajax({
                url: "allCategory?type_name=总结",
                async: false,
                success: function (data) {
                    $.each(data.list, function (i, item) {
                        $("#conclusion_type").append("<option  value='" + item.id + "'>" + item.type_name + "</option>");
                    });
                    form.render('select');
                }
            });

            form.on('submit(formDemo)', function (data) {

                $.ajax({
                    url: "/conclusion/addConclusion",
                    type: 'post',
                    data: {
                        id: $("#id").val(),
                        title: $("#title").val(),
                        plan_content: $("#plan_content").val(),
                        conclusion_type_id: $("#conclusion_type_id").val(),
                        type: $("#type").val(),
                        year_limit: $("#year_limit").val()
                    },
                    success: function () {
                        layer.msg('保存成功', {icon: 1});
                        setTimeout(function () {
                            window.location.href = '/frame/conclusionList_zj.ftl'
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