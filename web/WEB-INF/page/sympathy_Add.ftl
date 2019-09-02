<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title> 慰问表后台</title>
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
        <label class="layui-form-label" style="margin-left: 85px">慰问ID</label>
        <input id="sympathyId" name="sympathyId" type="hidden"  maxlength="20" value="<#if sympathyId??>${sympathyId}<#else></#if>"/>
    </div>

    <div class="layui-form-item input_row_margin_top" style="display:none ">
        <label class="layui-form-label">用户ID</label>
        <div class="layui-input-inline">
            <input id="userId" name="userId" type=""  maxlength="20" value="<#if userId??>${userId}<#else></#if>"/>
        </div>
    </div>

    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label ">姓名</label>
        <div class="layui-input-inline">
            <input id="username" name="username" lay-verify="required" placeholder="请输入姓名" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if username??>${username}<#else></#if>">
        </div>
        <label class="layui-form-label" style="margin-left: 85px">慰问品及信息</label>
        <div class="layui-input-inline">
            <input id="sympathy_product" name="sympathy_product" lay-verify="required" placeholder="请输入慰问品" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if sympathy_product??>${sympathy_product}<#else></#if>">
        </div>

    </div>

    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label" style="margin-left: -7px">性别</label>
        <div class="layui-input-inline">
            <input type="radio" name="sex" value="1" title="男" <#if sex??&&sex==1>checked</#if>>
            <input type="radio" name="sex" value="0" title="女" <#if sex??&&sex==0>checked</#if>>
        </div>
        <div class="layui-form-mid layui-word-aux"></div>
        <label class="layui-form-label" style="margin-left: 85px">慰问人单位及职务</label>
        <!--<label class="layui-form-label " style="margin-left: 85px">党小组编号</label>-->
        <div class="layui-input-inline">
            <input id="unit_and_position" name="unit_and_position" lay-verify="required" placeholder="请输入单位及职务" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if unit_and_position??>${unit_and_position}<#else></#if>">
        </div>
    </div>



    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label ">年龄</label>
        <!--<label class="layui-form-label " style="margin-left: 85px">党支部编号</label>-->
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

    <!--<div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label" style="margin-left: 84px">慰问人单位及职务</label>
        <!--<label class="layui-form-label " style="margin-left: 85px">党小组编号</label>&ndash;&gt;
        <div class="layui-input-inline">
            <input id="unit_and_position" name="unit_and_position" lay-verify="required" placeholder="请输入单位及职务" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if unit_and_position??>${unit_and_position}<#else></#if>">
        </div>
    </div>-->

    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label">困难情况</label>
        <div class="layui-input-inline">
            <select name="difficult" id="difficult">
                <option value="">全部</option>
                <option value="0" <#if difficult?? && difficult==0>selected</#if>>非困难</option>
                <option value="1" <#if difficult?? && difficult==1>selected</#if>>困难</option>
                <option value="2" <#if difficult?? && difficult==2>selected</#if>>非常困难</option>
            </select>
        </div>

        <label class="layui-form-label " style="margin-left: 84px">慰问时间</label>
        <div class="layui-input-inline">
            <input id="sympathy_time" name="sympathy_time" lay-verify="required" placeholder="请输入入党时间" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if sympathy_time??>${sympathy_time}<#else></#if>">
        </div>
        <div class="layui-form-mid layui-word-aux"></div>
    </div>

    <div class="layui-form-item input_row_margin_top">
    <label class="layui-form-label" style="margin-left: -2px">备注</label>
    <div class="layui-input-inline">
        <input id="note" name="note" lay-verify="required" placeholder="请输入备注" maxlength="20"
               autocomplete="off" class="layui-input" value="<#if note??>${note}<#else></#if>">
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

        layui.use(['laydate','form'], function () {
            var form = layui.form;

            laydate.render({
                elem: '#sympathy_time' //指定元素
            });
            laydate.render({
                elem: '#end_time' //指定元素
            });

            form.on('submit(formDemo)', function (data) {

                // if (state === 'uploading') {
                //     uploader.stop();
                // } else {
                //     uploader.upload();
                // }

                $.ajax({
                    url: "/sympathy/addSympathy",
                    data: {
                        sympathyId: $("#sympathyId").val(),
                        userId: $("#userId").val(),
                        username: $("#username").val(),
                        sympathy_product: $("#sympathy_product").val(),
                        age: $("#age").val(),
                        unit_and_position: $("#unit_and_position").val(),
                        sympathy_time: $("#sympathy_time").val(),
                        sex: $('input[name="sex"]:checked').val(),
                        note: $("#note").val(),
                        difficult: $("#difficult").val(),
                        phone: $("#phone").val()
                    },
                    success: function () {
                        layer.msg('保存成功', {icon: 1});
                        setTimeout(function () {
                            window.location.href = '/frame/sympathyList.ftl'
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