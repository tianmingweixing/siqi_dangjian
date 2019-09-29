<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title> 工作计划</title>
    <link rel="stylesheet" href="../../js/layui/css/layui.css">
    <script src="../../js/layui/layui.js"></script>
    <script src="../../js/jquery/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="/css/public.css">
</head>
<body>
    <div style="width: 90%">
        <blockquote class="layui-elem-quote layui-quote-nm" id="footer"
                    style="margin-top: 50px;margin-left: 5%;padding-left: 45px;border-color: #009688;color: #009688;font-weight: bold">
            添加工作计划
        </blockquote>
    </div>
    <div style="width:90%">
        <div class="container_div">
            <form class="layui-form" action="">

                <br>
                <div class="layui-form-item input_row_margin_top" style="display:none ">
                    <label class="layui-form-label" style="margin-left: 85px">总结（计划）ID</label>
                    <input id="id" name="id" type="hidden" maxlength="20" value="<#if id??>${id}<#else></#if>"/>
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

                    <label class="layui-form-label " style="margin-left: 1px">计划时间</label>
                    <div class="layui-input-inline">
                        <input id="year_limit" name="year_limit" lay-verify="required" placeholder="请输入年限" maxlength="20"
                               autocomplete="off" class="layui-input" value="<#if year_limit??>${year_limit}<#else></#if>">
                    </div>

                    <label class="layui-form-label " style="margin-left: 1px">标题</label>
                    <div class="layui-input-inline">
                        <input id="title" name="title" lay-verify="required" style="width: 380px" placeholder="请输入标题" maxlength="100"
                               autocomplete="off" class="layui-input" value="<#if title??>${title}<#else></#if>">
                    </div>

                </div>

                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">计划内容</label>
                    <div class="layui-input-block">
                <textarea name="plan_content" id="plan_content" placeholder="请输入内容"
                          style="width: 700px; border:1px solid #e6e6e6; font-size: 10px; line-height: 23px;color: #56aa17;
                                  max-width: 1500px; height: 300px; max-height: 1000px; outline: 0;"><#if plan_content??>${plan_content}<#else></#if></textarea>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-form-item input_row_margin_top">
                        <div class="layui-input-block">
                            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="formDemo">立即提交</button>
                    <#if id??><#else>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button></#if>

                        </div>
                    </div>
                </div>

            </form>
        </div>
    </div>
<script>
    var conclusion_type_id = <#if conclusion_type_id??>"${conclusion_type_id}"<#else>""</#if>;

    $(function() {

        layui.use(['element','laydate','form'], function () {
            var $ = jQuery;

            var form = layui.form;
            var laydate = layui.laydate //日期
                    ,layer = layui.layer; //弹层
            var element = layui.element;

            laydate.render({
                elem: '#year_limit' //指定元素
            });

            //查询计划种类
            $.ajax({
                url: "allCategory?default_type=计划",
                async: false,
                success: function (data) {
                    $.each(data.list, function (i, item) {
                        if(item.id == conclusion_type_id){
                            $("#conclusion_type").append("<option selected='selected' value='" + item.id + "'>" + item.type_name + "</option>")
                        }else{
                            $("#conclusion_type").append("<option  value='" + item.id + "'>" + item.type_name + "</option>");
                        }
                    });
                    form.render('select');
                }
            });

            form.on('submit(formDemo)', function () {
                $.ajax({
                    url: "/conclusion/addConclusion",
                    type: 'post',
                    data: {
                        id: $("#id").val(),
                        title: $("#title").val(),
                        plan_content: $("#plan_content").val(),
                        conclusion_type_id: $("#conclusion_type").val(),
                        year_limit: $("#year_limit").val()
                    },
                    success: function () {
                        layer.msg('保存成功', {icon: 1});
                        setTimeout(function () {
                            window.location.href = '/frame/conclusionList_jh.ftl'
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