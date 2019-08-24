<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title> 工作总结（计划）表</title>
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
    <div class="layui-form-item input_row_margin_top" style="display:none ">
        <label class="layui-form-label" style="margin-left: 85px">总结（计划）ID</label>
        <input id="id" name="id" type="hidden"  maxlength="20" value="<#if id??>${id}<#else></#if>"/>
    </div>


    <div class="layui-form-item input_row_margin_top">

        <label class="layui-form-label">工作会议类型</label>
        <div class="layui-input-inline">
            <select name="type" id="type">
                <option value="">全部</option>
                <option value="1" <#if type?? && type==0>selected</#if>>总结 </option>
                <option value="2" <#if type?? && type==1>selected</#if>>计划</option>
            </select>
        </div>

        <label class="layui-form-label">季度类型</label>
        <div class="layui-input-inline">
            <select name="conclusion_type_id" id="conclusion_type_id">
                <option value="">全部</option>
                <option value="1" <#if conclusion_type_id?? && conclusion_type_id==1>selected</#if>>年度总结 </option>
                <option value="2" <#if conclusion_type_id?? && conclusion_type_id==2>selected</#if>>半年度总结</option>
                <option value="3" <#if conclusion_type_id?? && conclusion_type_id==3>selected</#if>>月度总结</option>
                <option value="4" <#if conclusion_type_id?? && conclusion_type_id==4>selected</#if>>日度总结</option>
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
        <label class="layui-form-label">计划(总结)内容</label>
        <div class="layui-input-block">
            <textarea name="plan_content" id="plan_content" placeholder="请输入内容"
                      class="layui-textarea"><#if plan_content??>${plan_content}<#else></#if></textarea>
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

        layui.use(['laydate','form'], function () {
            var form = layui.form;
            var laydate = layui.laydate //日期
                    ,layer = layui.layer; //弹层

            laydate.render({
                elem: '#year_limit' //指定元素
            });


            form.on('submit(formDemo)', function (data) {

                $.ajax({
                    url: "/conclusion/addConclusion",
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
                            window.location.href = '/frame/conclusionList.ftl'
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