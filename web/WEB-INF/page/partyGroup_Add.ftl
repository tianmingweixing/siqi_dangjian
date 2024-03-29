<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title> 支部班子添加页面</title>
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
            修改支部班子信息
        </blockquote>
    </div>

    <div style="width:90%">
        <div class="container_div">
            <div class="layui-input-inline" style="display: none">
                <label class="layui-form-label" style="margin-left: 85px">党支部Id</label>
                <input id="id" name="id" type="hidden" maxlength="20" value="<#if id??>${id}<#else></#if>"/>
            </div>
            <div class="layui-form-item input_row_margin_top">
                <label class="layui-form-label ">班子名称</label>
                <div class="layui-input-inline">
                    <input id="name" name="name" lay-verify="required" placeholder="支部班子名称" maxlength="20"
                           autocomplete="off" class="layui-input" value="<#if name??>${name}<#else></#if>">
                </div>
            </div>
            <br>

            <div class="layui-form-item input_row_margin_top">
                <label class="layui-form-label ">成立时间</label>
                <div class="layui-input-inline">
                    <input id="foundingTime" name="foundingTime" placeholder="请输入成立时间" maxlength="20"
                           autocomplete="off" class="layui-input"
                           value="<#if foundingTime??>${foundingTime}<#else></#if>">
                </div>

                <label class="layui-form-label ">换届时间</label>
                <div class="layui-input-inline">
                    <input id="changeTime" name="changeTime" lay-verify="required" placeholder="请输入换届时间" maxlength="20"
                           autocomplete="off" class="layui-input" value="<#if changeTime??>${changeTime}<#else></#if>">
                </div>
            </div>
            <br>

            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">班子职责</label>
                <div class="layui-input-block">
            <textarea name="duty" id="duty" placeholder="请输入班子职责"
                      style="width: 500px; border:1px solid #e6e6e6; font-size: 10px; line-height: 23px;color: #0c060f;
                              max-width: 1500px; height: 100px; max-height: 1000px; outline: 0;"><#if duty??>${duty}<#else></#if></textarea>
                </div>
            </div>
            <br>

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

    $(function () {
        layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'element', 'form'], function () {
            var form = layui.form;

            var laydate = layui.laydate //日期
                    , layer = layui.layer; //弹层

            laydate.render({
                elem: '#foundingTime'
            });

            laydate.render({
                elem: '#changeTime'
            });

            form.on('submit(formDemo)', function () {
                $.ajax({
                    url: "/partyGroup/addPartyGroup",
                    data: {
                        id: $("#id").val(),
                        name: $("#name").val(),
                        duty: $("#duty").val(),
                        foundingTime: $("#foundingTime").val(),
                        changeTime: $("#changeTime").val()
                    },
                    success: function () {
                        layer.msg('保存成功', {icon: 1});
                        setTimeout(function () {
                            window.location.href = '/frame/partyGroupList.ftl'
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