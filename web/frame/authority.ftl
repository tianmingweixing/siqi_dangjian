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
<body style="">

<form class="layui-form" action="" lay-filter="example">

    <div style="width: 90%">
        <blockquote class="layui-elem-quote layui-quote-nm" id="footer"
                    style="margin-top: 50px;margin-left: 5%;padding-left: 45px; border-color: #009688; color: #009688;font-weight: bold">
            权限管理
        </blockquote>
    </div>

    <div style="width: 90%">
        <div class="container_div">

            <div class="layui-form-item" pane="">

                <fieldset class="layui-elem-field">
                    <legend>支部管理</legend>
                    <div class="layui-field-box">
                        <input type="checkbox" name="authority[play]" value="true" lay-skin="primary" title="支部信息">
                        <input type="checkbox" name="authority[group]" value="true" lay-skin="primary" title="班子管理">
                        <input type="checkbox" name="authority[teams]" value="true" lay-skin="primary" title="党组管理">
                    </div>
                </fieldset>
                <fieldset class="layui-elem-field">
                    <legend>用户管理</legend>
                    <div class="layui-field-box">
                        <input type="checkbox" name="authority[user]" value="true" lay-skin="primary" title="用户列表">
                        <input type="checkbox" name="authority[usertype]" value="true" lay-skin="primary" title="人员分类">
                    </div>
                </fieldset>
                <fieldset class="layui-elem-field">
                    <legend>活动管理</legend>
                    <div class="layui-field-box">
                        <input type="checkbox" name="authority[activity]" value="true" lay-skin="primary" title="活动列表">
                        <input type="checkbox" name="authority[acttype]" value="true" lay-skin="primary" title="活动分类">
                        <input type="checkbox" name="authority[brand]" value="true" lay-skin="primary" title="活动品牌">
                        <input type="checkbox" name="authority[tips]" value="true" lay-skin="primary" title="活动心得">
                    </div>
                </fieldset>
                <fieldset class="layui-elem-field">
                    <legend>会议管理</legend>
                    <div class="layui-field-box">
                        <input type="checkbox" name="authority[meeting]" value="true" lay-skin="primary" title="会议列表">
                        <input type="checkbox" name="authority[meettype]" value="true" lay-skin="primary" title="会议分类">
                    </div>
                </fieldset>
                <fieldset class="layui-elem-field">
                    <legend>文档管理</legend>
                    <div class="layui-field-box">
                        <input type="checkbox" name="authority[work]" value="true" lay-skin="primary" title="工作计划">
                        <input type="checkbox" name="authority[summary]" value="true" lay-skin="primary" title="工作总结">
                        <input type="checkbox" name="authority[wordtype]" value="true" lay-skin="primary" title="文档分类">
                    </div>
                </fieldset>
                <fieldset class="layui-elem-field">
                    <legend>党务管理</legend>
                    <div class="layui-field-box">
                        <input type="checkbox" name="authority[honor]" value="true" lay-skin="primary" title="表彰奖励列表">
                        <input type="checkbox" name="authority[discipline]" value="true" lay-skin="primary" title="违纪违法列表">
                        <input type="checkbox" name="authority[condole]" value="true" lay-skin="primary" title="慰问情况列表">
                        <input type="checkbox" name="authority[notice]" value="true" lay-skin="primary" title="党内公示公告">
                    </div>
                </fieldset>
                <fieldset class="layui-elem-field">
                    <legend>系统设置</legend>
                    <div class="layui-field-box">
                        <input type="checkbox" name="authority[admin]" value="true" lay-skin="primary" title="管理员列表">
                        <input type="checkbox" name="authority[pwd]" value="true" lay-skin="primary" title="修改密码">
                        <input type="checkbox" name="authority[com]" value="true" lay-skin="primary" title="公司信息">
                        <input type="checkbox" name="authority[lunbo]" value="true" lay-skin="primary" title="轮播设置">
                    </div>
                </fieldset>

            </div>

            <div class="layui-form-item input_row_margin_top">
                <div class="layui-input-block" style="margin-left: 0;">
                    <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>


        </div>
    </div>
</form>


<script>

    var adminId = 0;
    if (parent.PartitionData) {
        adminId = parent.PartitionData.id;
    }


    $().ready(function () {
        //初始化信息//
        //if (id != ""){
            //initComInfo(id);
        //}
    });


    layui.use(['laydate', 'laypage', 'layer', 'table', 'form', 'element','upload'], function () {
        var $ = jQuery;
        var form = layui.form;
        var laydate = layui.laydate //日期
                , layer = layui.layer //弹层
                , element = layui.element//元素操作
                , upload = layui.upload;


        $.ajax({
            url: "/admin/getAuthority",
            type: "POST",
            data: {
                // party_branch_id: $("#party_branch_id").val(),
                party_branch_id:1,
                adminId: adminId
            },
            success: function (data) {
                if (data.code == 0){
                    var str = data.authority;
                    var obj = eval('('+str+')');
                    /*var obj1 = eval('('+str+')'); //使用eval函数
                    var obj2 = jQuery.parseJSON(str); //jq对象方法，由json字符串转换为json对象
                    var obj3 = JSON.parse(str); //由json字符串转换为json对象*/
                    form.val("example", obj);
                }
            }
        });




        form.on('submit(formDemo)', function (data) {
            var formData = new FormData();
            var authority = JSON.stringify(data.field);
            console.log(data.field);

            /*layer.alert(JSON.stringify(data.field.authority[write]), {
                title: '最终的提交信息'
            })*/

            $.ajax({
                url: "/admin/setAuthority",
                type: "POST",
                data: {
                    // party_branch_id: $("#party_branch_id").val(),
                    party_branch_id:1,
                    adminId: adminId,
                    authority: authority
                },
                success: function (data) {
                    /*alert(data.code);*/
                    if (data.code == 0){
                        layer.msg('保存成功', {icon: 1});
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