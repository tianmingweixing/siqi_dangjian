<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>思齐党建后台</title>
    <link rel="stylesheet" href="/js/layui/css/layui.css">
    <link rel="stylesheet" href="/css/public.css">
    <script src="/js/layui/layui.js"></script>
    <script src="/js/jquery/jquery-3.3.1.min.js"></script>

</head>
<body>

<form class="layui-form" action="">

    <div style="width: 90%">
        <blockquote class="layui-elem-quote layui-quote-nm" id="footer"
                    style="margin-top: 50px;margin-left: 5%;padding-left: 45px;border-color: #009688;color: #009688;font-weight: bold">
            添加活动心得
        </blockquote>
    </div>

    <div style="width: 90%">
        <div class="container_div">

            <div class="layui-form-item input_row_margin_top">
                <label class="layui-form-label ">用户ID</label>
                <div class="layui-input-inline">
                    <input id="userId" name="userId" lay-verify="required" placeholder="请输入用户ID" maxlength="20"
                           autocomplete="off" class="layui-input" value="<#if userId??>${userId}<#else></#if>">
                </div>
                <label class="layui-form-label ">活动ID</label>
                <div class="layui-input-inline">
                    <input id="activityId" name="activityId" lay-verify="required" placeholder="请输入活动ID" maxlength="20"
                           autocomplete="off" class="layui-input" value="<#if activityId??>${activityId}<#else></#if>">
                </div>
            </div>

            <div class="layui-form-item layui-form-text input_row_margin_top">
                <label class="layui-form-label">心得内容</label>
                <div class="layui-input-block">
            <textarea name="content" id="content" placeholder="请输入心得内容" style="width: 500px; height: 200px;" maxlength="1000"
                      class="layui-textarea"><#if content??>${content}<#else></#if></textarea>
                </div>
            </div>

            <div class="layui-form-item input_row_margin_top">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>


        </div>
    </div>

</form>

<script>

    var id =<#if id??>"${id}"<#else>""</#if>;

    layui.use('form', function () {
            var form = layui.form;
            var $ = jQuery;
            //监听提交
            form.on('submit(formDemo)', function (data) {
                var formData = new FormData();
                var userId = $("#userId").val();
                var activityId = $("#activityId").val();
                var content = $("#content").val();

                $.ajax({
                    url: "/tips/add",
                    type: "POST",
                    data: {
                        // party_branch_id: $("#party_branch_id").val(),
                        party_branch_id:1,
                        userId: userId,
                        activityId: activityId,
                        content: content,
                        id:id
                    },
                    success: function (data) {
                        if (data.code == 0){
                            layer.msg('保存成功', {icon: 1});
                            setTimeout(function () {
                                window.location.href = '/frame/activityTipsList.ftl'
                            }, 1500);
                        } else {
                            layer.msg(data.msg, {icon: 2});
                        }
                    }
                });
                return false;
            });
        });

</script>

</body>
</html>