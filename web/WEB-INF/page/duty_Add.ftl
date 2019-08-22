<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title> 职务表后台</title>
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
        <!--<label class="layui-form-label" style="margin-left: 85px">党内职务</label>
        <div class="layui-input-inline">
            <input id="party_duty" name="party_duty" lay-verify="required" placeholder="请输入身份证" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if party_duty??>${party_duty}<#else></#if>">
        </div>-->

    </div>

    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label">政治面貌</label>
        <div class="layui-input-inline">
            <select name="name" id="name">
                <option value="">全部</option>
                <option value="1" <#if name?? && name==1>selected</#if>>积极分子</option>
                <option value="2" <#if name?? && name==2>selected</#if>>预备党员</option>
                <option value="3" <#if name?? && name==3>selected</#if>>发展对象</option>
                <option value="4" <#if name?? && name==4>selected</#if>>正式党员</option>
                <option value="5" <#if name?? && name==5>selected</#if>>党委</option>
            </select>
        </div>
        <!--
        <label class="layui-form-label " style="margin-left: 84px">入党时间</label>
        <div class="layui-input-inline">
            <input id="joinTime" name="joinTime" lay-verify="required" placeholder="请输入入党时间" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if joinTime??>${joinTime}<#else></#if>">
        </div>
        <div class="layui-form-mid layui-word-aux"></div>
        -->
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
                    url: "/duty/addDuty",
                    data: {
                        id: $("#id").val(),
                        name: $("#name").val(),
                        party_duty: $("#party_duty").val()
                        /*age: $("#age").val(),
                        dutyId: $("#dutyid").val(),
                        phone: $("#phone").val()*/
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