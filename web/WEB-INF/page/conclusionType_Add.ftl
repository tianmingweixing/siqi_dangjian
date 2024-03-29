<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title> 添加总结（计划）类型表</title>
    <link rel="stylesheet" href="../../js/layui/css/layui.css">
    <script src="../../js/layui/layui.js"></script>
    <script src="../../js/jquery/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="/css/public.css">
</head>
<body>
    <div style="width: 90%">
        <blockquote class="layui-elem-quote layui-quote-nm" id="footer"
                    style="margin-top: 50px;margin-left: 5%;padding-left: 45px;border-color: #009688;color: #009688;font-weight: bold">
            添加分类
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

                    <label class="layui-form-label " style="margin-left: 1px">类型名称</label>
                    <div class="layui-input-inline">
                        <input id="type_name" name="type_name" lay-verify="required" placeholder="请输入类型名称"
                               maxlength="20"
                               autocomplete="off" class="layui-input"
                               value="<#if type_name??>${type_name}<#else></#if>">
                    </div>

                </div>
                <div class="layui-form-item input_row_margin_top">

                    <label class="layui-form-label " style="margin-left: 1px">文档类型</label>
                    <div class="layui-input-inline">
                        <select name="cType" id="cType">
                            <option value="1" <#if type??&&type==1>selected</#if>>总结</option>
                            <option value="2" <#if type??&&type==2>selected</#if>>计划</option>
                        </select>
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

    $(function() {

        layui.use(['laydate','form'], function () {
            var form = layui.form;
            var laydate = layui.laydate //日期
                    ,layer = layui.layer; //弹层

            laydate.render({
                elem: '#year_limit' //指定元素
            });


            form.on('submit(formDemo)', function (data) {

                $.ajax({
                    url: "/conclusionType/addConclusionType",
                    data: {
                        id: $("#id").val(),
                        type: $("#cType").val(),
                        type_name: $("#type_name").val()
                    },
                    success: function () {
                        layer.msg('保存成功', {icon: 1});
                        setTimeout(function () {
                            window.location.href = '/frame/conclusionTypeList.ftl'
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