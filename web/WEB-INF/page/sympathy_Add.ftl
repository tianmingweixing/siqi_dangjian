<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>慰问表后台</title>
    <link rel="stylesheet" href="../../js/layui/css/layui.css">
    <script src="../../js/layui/layui.js"></script>
    <script src="../../js/jquery/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="/css/public.css">

</head>
<body>
<div style="width: 90%">
    <blockquote class="layui-elem-quote layui-quote-nm" id="footer"
                style="margin-top: 50px;margin-left: 5%;padding-left: 45px;border-color: #009688;color: #009688;font-weight: bold">
        添加慰问信息
    </blockquote>
</div>
<div style="width:90%">
    <div class="container_div">
        <form class="layui-form" action="">

            <br>
            <div class="layui-form-item input_row_margin_top" style="display:none ">
                <label class="layui-form-label" style="margin-left: 85px">慰问ID</label>
                <input id="sympathyId" name="sympathyId" type="hidden" maxlength="20"
                       value="<#if sympathyId??>${sympathyId}<#else></#if>"/>
            </div>

            <div class="layui-form-item input_row_margin_top" style="display:none ">
                <label class="layui-form-label">用户ID</label>
                <div class="layui-input-inline">
                    <input id="userId" name="userId" class="layui-input" placeholder="请输入用户ID" onchange="function()"
                           value="<#if userId??>${userId}<#else></#if>"/>
                </div>
            </div>

            <div class="layui-form-item input_row_margin_top">
                <div class="layui-inline">
                    <label class="layui-form-label ">姓名</label>
                    <div class="layui-input-inline">
                        <input id="username" name="username" lay-verify="required" placeholder="请输入姓名" maxlength="20"
                               disabled
                               autocomplete="off" class="layui-input" value="<#if username??>${username}<#else></#if>">
                    </div>
                </div>

                <div class="layui-inline">
                    <label class="layui-form-label ">年龄</label>
                    <div class="layui-input-inline">
                        <input id="age" name="age" lay-verify="number" placeholder="请输入年龄" maxlength="20"
                               autocomplete="off" class="layui-input" value="<#if age??>${age}<#else></#if>">
                    </div>
                </div>

            </div>

            <div class="layui-form-item ">
                <div class="layui-inline">
                    <label class="layui-form-label" style="margin-left: -7px">性别</label>
                    <div class="layui-input-inline">
                        <input type="radio" name="sex" value="1" title="男" <#if sex??&& sex==1>checked</#if>>
                        <input type="radio" name="sex" value="2" title="女" <#if sex??&& sex==2>checked</#if>>
                    </div>
                </div>

                <div class="layui-inline">
                    <label class="layui-form-label">手机号码</label>
                    <div class="layui-input-inline">
                        <input id="phone" name="phone" lay-verify="number" placeholder="请输入手机号码" maxlength="20"
                               autocomplete="off" class="layui-input" value="<#if phone??>${phone}<#else></#if>">
                    </div>
                </div>

            </div>

            <div class="layui-form-item ">
                <div class="layui-inline">

                    <label class="layui-form-label">单位</label>
                    <div class="layui-input-inline">
                        <input id="unit_and_position" name="unit_and_position" lay-verify="required"
                               placeholder="请输入慰问人单位"
                               maxlength="20"
                               autocomplete="off" class="layui-input"
                               value="<#if unit_and_position??>${unit_and_position}<#else></#if>">
                    </div>
                </div>
                <div class="layui-inline">

                    <label class="layui-form-label">困难情况</label>
                    <div class="layui-input-inline">
                        <select name="difficult" id="difficult">
                            <option value="">全部</option>
                            <option value="0" <#if difficult?? && difficult==0>selected</#if>>非困难</option>
                            <option value="1" <#if difficult?? && difficult==1>selected</#if>>困难</option>
                            <option value="2" <#if difficult?? && difficult==2>selected</#if>>非常困难</option>
                        </select>
                    </div>
                </div>

            </div>

            <div class="layui-form-item input_row_margin_top">

                <div class="layui-inline">
                    <label class="layui-form-label ">慰问时间</label>
                    <div class="layui-input-inline">
                        <input id="sympathy_time" name="sympathy_time" lay-verify="required" placeholder="请输入慰问时间"
                               maxlength="20"
                               autocomplete="off" class="layui-input"
                               value="<#if sympathy_time??>${sympathy_time}<#else></#if>">
                    </div>
                </div>
            </div>


            <div class="layui-form-item input_row_margin_top">

                <div class="layui-form-item layui-form-text">
                    <div class="layui-inline">
                        <label class="layui-form-label">慰问信息</label>
                        <div class="layui-input-block">
                            <textarea name="sympathy_product" id="sympathy_product" placeholder="请输入慰问信息"
                                      style="width: 500px; border:1px solid #e6e6e6; font-size: 10px; line-height: 23px;color: #0c060f;
                                  max-width: 1500px; height: 100px; max-height: 1000px; outline: 0;"><#if sympathy_product??>${sympathy_product}<#else></#if></textarea>
                        </div>
                    </div>
                </div>

                <div class="layui-form-item layui-form-text">
                    <div class="layui-inline">
                        <label class="layui-form-label">备注</label>
                        <div class="layui-input-block">
                             <textarea name="note" id="note" placeholder="请输入备注"
                                       style="width: 500px; border:1px solid #e6e6e6; font-size: 10px; line-height: 23px;color: #0c060f;
                                  max-width: 1500px; height: 100px; max-height: 1000px; outline: 0;"><#if note??>${note}<#else></#if></textarea>
                        </div>
                    </div>
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
    </div>
</div>
<script>

    $(function () {


        layui.use(['laydate', 'form'], function () {
            var form = layui.form;
            var laydate = layui.laydate //日期
                    , layer = layui.layer //弹层
                    , element = layui.element;//元素操作

            laydate.render({
                elem: '#sympathy_time' //指定元素
            });

            $("#userId").change(function () {
                $.ajax({
                    url: "/user/getUserById",
                    type: 'post',
                    data: {
                        userId: $("#userId").val()
                    },
                    success: function (data) {
                        console.log(data.user.sex);

                        layer.msg('用户查询成功', {icon: 1});
                        $("#username").val(data.user.userName);
                        $("#age").val(data.user.age);
                        $("#phone").val(data.user.phone);
                        $('input:radio').eq(data.user.sex).attr('checked', 'true');
                        form.render();
                    }
                });
            });

            form.on('submit(formDemo)', function () {

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