<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title> 荣誉表</title>
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
        <label class="layui-form-label" style="margin-left: 85px">荣誉ID</label>
        <input id="id" name="id" type="hidden"  maxlength="20" value="<#if id??>${id}<#else></#if>"/>
    </div>

    <div class="layui-form-item input_row_margin_top" >
        <label class="layui-form-label">用户ID</label>
        <div class="layui-input-inline">
            <input id="userId" name="userId" class="layui-input" placeholder="请输入用户ID" onchange="function()"  value="<#if userId??>${userId}<#else></#if>"/>
        </div>
    </div>

    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label ">被授奖个人</label>
        <div class="layui-input-inline">
            <input id="unit" name="unit" lay-verify="required" placeholder="被授奖个人" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if unit??>${unit}<#else></#if>">
        </div>

        <label class="layui-form-label ">年龄</label>
        <div class="layui-input-inline">
            <input id="age" name="age" lay-verify="number" placeholder="请输入年龄" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if age??>${age}<#else></#if>">
        </div>

    </div>

    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label" style="margin-left: -7px">性别</label>
        <div class="layui-input-inline">
            <input type="radio" name="sex" value="1" title="男" <#if sex??&& sex==1>checked</#if>>
            <input type="radio" name="sex" value="2" title="女" <#if sex??&& sex==2>checked</#if>>
        </div>

        <label class="layui-form-label" >手机号码</label>
        <div class="layui-input-inline">
            <input id="phone" name="phone" lay-verify="number" placeholder="请输入手机号码" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if phone??>${phone}<#else></#if>">
        </div>

    </div>

    <div class="layui-form-item input_row_margin_top" style="display: none">
        <label class="layui-form-label">类型</label>
        <div class="layui-input-inline">
            <select name="type" id="type">
                <option value="0" <#if type?? && type==0>selected</#if>>荣誉</option>
                <option value="1" <#if type?? && type==1>selected</#if>>违纪</option>
            </select>
        </div>

    </div>

    <div class="layui-form-item input_row_margin_top">

        <label class="layui-form-label " style="margin-left: 1px">荣誉时间</label>
        <div class="layui-input-inline">
            <input id="time" name="time" lay-verify="required" placeholder="请输入时间" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if time??>${time}<#else></#if>">
        </div>

        <label class="layui-form-label " style="margin-left: 1px">荣誉名称</label>
        <div class="layui-input-inline">
            <input id="name" name="name" lay-verify="required" placeholder="请输入名称" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if name??>${name}<#else></#if>">
        </div>

    </div>

    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label " style="margin-left: 1px">荣誉凭证</label>
        <div class="layui-input-inline">
            <input id="certificate" name="certificate" lay-verify="required" placeholder="请输入凭证" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if certificate??>${certificate}<#else></#if>">
        </div>

        <label class="layui-form-label " style="margin-left: 1px">金额</label>
        <div class="layui-input-inline">
            <input id="amount" name="amount" lay-verify="required|number" placeholder="请输入金额" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if amount??>${amount}<#else></#if>">
        </div>
    </div>

    <div class="layui-form-item">

        <label class="layui-form-label">授奖单位</label>
        <div class="layui-input-inline">
            <input class="layui-input" name="passive_unit" id="passive_unit" placeholder="请输入授奖单位" value="<#if passive_unit??>${passive_unit}<#else></#if>">
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">荣誉详情</label>
        <div class="layui-input-block">
            <textarea name="content" id="content" placeholder="请输入详情"
                      style="width: 600px; border:1px solid #e6e6e6; font-size: 16px; line-height: 23px; font-family: 微软雅黑;
                              max-width: 1500px; height: 200px; max-height: 1000px; outline: 0;"><#if content??>${content}<#else></#if></textarea>
        </div>

        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea name="note" id="note" placeholder="请输入备注"
                      style="width: 600px; border:1px solid #e6e6e6; font-size: 16px; line-height: 23px; font-family: 微软雅黑;
                              max-width: 1500px; height: 200px; max-height: 1000px; outline: 0;"><#if note??>${note}<#else></#if></textarea>
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
                elem: '#time' //指定元素
            });

            $("#userId").change(function(){
                $.ajax({
                    url: "/user/getUserById",
                    type: 'post',
                    data: {
                        userId : $("#userId").val()
                    },
                    success: function (data) {
                        console.log(data.user.sex);

                        layer.msg('用户查询成功', {icon: 1});
                        $("#unit").val(data.user.userName);
                        $("#age").val(data.user.age);
                        $("#phone").val(data.user.phone);
                        $('input:radio').eq(data.user.sex).attr('checked', 'true');
                        form.render();
                    }
                });
            });


            form.on('submit(formDemo)', function (data) {

                $.ajax({
                    url: "/disciplineOfHonor/addDisciplineOfHonor",
                    type: 'post',
                    data: {
                        id: $("#id").val(),
                        userId: $("#userId").val(),
                        unit: $("#unit").val(),
                        content: $("#content").val(),
                        type: $("#type").val(),
                        passive_unit: $("#passive_unit").val(),
                        certificate: $("#certificate").val(),
                        amount: $("#amount").val(),
                        note: $("#note").val(),
                        name: $("#name").val(),
                        time: $("#time").val()
                    },
                    success: function () {
                        layer.msg('保存成功', {icon: 1});
                        setTimeout(function () {
                            window.location.href = '/frame/honorList.ftl'
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