<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>思齐党建后台-添加活动品牌</title>
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
            添加品牌
        </blockquote>
    </div>

    <div style="width: 90%">
        <div class="container_div">

            <!--<div class="layui-form-item input_row_margin_top">
                <label class="layui-form-label">所属支部</label>
                <div class="layui-input-inline">
                    <input id="party_branch_id" name="party_branch_id" lay-verify="required" placeholder="请输入支部ID" maxlength="20"
                           autocomplete="off" class="layui-input" value="<#if party_branch_id??>${party_branch_id}<#else></#if>">
                </div>
            </div>-->

            <div class="layui-form-item input_row_margin_top">
                <label class="layui-form-label ">品牌名称</label>
                <div class="layui-input-inline">
                    <input id="brand_name" name="brand_name" lay-verify="required" placeholder="请输入品牌名称" maxlength="20"
                           autocomplete="off" class="layui-input" value="<#if brand_name??>${brand_name}<#else></#if>">
                </div>
                <#if id??>
                    <label class="layui-form-label" style="margin-left: 85px">创建时间</label>
                    <div class="layui-input-inline">
                        <input id="create_time" name="create_time" placeholder="创建时间" maxlength="20" disabled
                               autocomplete="off" class="layui-input" value="<#if create_time??>${create_time}<#else></#if>">
                    </div>
                </#if>

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
    var id = <#if id??>"${id}"<#else>""</#if>;

    layui.use('form', function () {
            var form = layui.form;

            form.on('submit(formDemo)', function (data) {
                var formData = new FormData();

                $.ajax({
                    url: "/activtiesBrand/add",
                    type: "POST",
                    data: {
                        // party_branch_id: $("#party_branch_id").val(),
                        party_branch_id: 1,
                        brand_name: $("#brand_name").val(),
                        id: id
                    },
                    success: function (data) {
                        if (data.code == 0){
                            layer.msg(data.msg, {icon: 1});
                            setTimeout(function () {
                                window.location.href = '/frame/activityBrandList.ftl';
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