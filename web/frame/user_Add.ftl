<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title> 党支部后台</title>
    <link rel="stylesheet" href="../../js/layui/css/layui.css">
    <script src="../../js/layui/layui.js"></script>
    <script src="../../js/jquery/jquery-3.3.1.min.js"></script>
</head>
<body>

<form class="layui-form" action="">

<br>
    <div class="layui-input-inline" style="display:none ">
        <label class="layui-form-label" style="margin-left: 85px">Id</label>
        <input id="id" name="id"   maxlength="20" value=""/>
    </div>
    <div class="layui-input-inline" style="display:none ">
        <label class="layui-form-label"  style="margin-left: 85px">职务id</label>
        <input id="dutyid" name="dutyid" maxlength="20" value=""/>
    </div>

    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label ">用户名称</label>
        <div class="layui-input-inline">
            <input id="username" name="username" lay-verify="required" placeholder="请输入用户名称" maxlength="20"
                   autocomplete="off" class="layui-input" value="">
        </div>

        <label class="layui-form-label" style="margin-left: 85px">性别</label>
        <div class="layui-input-inline">
            <input type="radio" name="sex" value="1" title="男">
            <input type="radio" name="sex" value="2" title="女">
        </div>
    </div>

    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label" >身份证</label>
        <div class="layui-input-inline">
            <input id="ID_cord" name="ID_cord" lay-verify="required" placeholder="请输入身份证" maxlength="20"
                   autocomplete="off" class="layui-input" value="">
        </div>

        <label class="layui-form-label" style="margin-left: 85px">政治面貌</label>
        <div class="layui-input-inline">
            <select name="type_name" id="type_name">
                <option value="">全部</option>
            </select>
        </div>
    </div>

    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label ">年龄</label>
        <div class="layui-input-inline">
            <input id="age" name="age" lay-verify="number" placeholder="请输入年龄" maxlength="20"
                   autocomplete="off" class="layui-input" value="">
        </div>

        <label class="layui-form-label" style="margin-left: 85px">学历</label>
        <div class="layui-input-inline">
            <select name="education" id="education">
                <option value="">请选择</option>
                <option value="1">初中</option>
                <option value="2">高中</option>
                <option value="3">中专</option>
                <option value="4">大专</option>
                <option value="5">本科</option>
                <option value="5">硕士</option>
                <option value="5">博士</option>
            </select>
            <!--<input id="education" name="education" lay-verify="required" placeholder="请输入学历" maxlength="20"
                   autocomplete="off" class="layui-input" value="">-->
        </div>
    </div>

    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label" >手机号码</label>
        <div class="layui-input-inline">
            <input id="phone" name="phone" lay-verify="number" placeholder="请输入手机号码" maxlength="20"
                   autocomplete="off" class="layui-input" value="">
        </div>

        <label class="layui-form-label" style="margin-left: 85px">入党时间</label>
        <div class="layui-input-inline">
            <input id="join_time" name="join_time" lay-verify="" placeholder="请输入入党时间" maxlength="20"
                   autocomplete="off" class="layui-input" value="">
        </div>
    </div>

    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label" >单位</label>
        <div class="layui-input-inline">
            <input id="company" name="company" lay-verify="required" placeholder="请输入单位" maxlength="20"
                   autocomplete="off" class="layui-input" value="">
        </div>

        <label class="layui-form-label" style="margin-left: 85px">所属支部班子</label>
        <div class="layui-input-inline">
            <select name="partyGroups_name" id="partyGroups_name">
                <option value="">没有则不选</option>
            </select>
        </div>
    </div>

    <div class="layui-form-item input_row_margin_top">
            <label class="layui-form-label" >所属党小组</label>
            <div class="layui-input-inline">
                <select name="partyTeam_name" id="partyTeam_name">
                    <option value="">没有则不选</option>
                </select>
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
        //从父层获取值，json是父层的全局js变量。eval是将该string类型的json串变为标准的json串
        /* var parent_json = eval('('+parent.json+')');
         console.log(parent_json);*/
        console.log(parent.PartitionData.id);

        $("#username").val(parent.PartitionData.username);
        $("#age").val(parent.PartitionData.age);
        $("#company").val(parent.PartitionData.company);
        $("#education").val(parent.PartitionData.education);
        $("#phone").val(parent.PartitionData.phone);
        $("#sex").val(parent.PartitionData.sex);
        $("#join_time").val(parent.PartitionData.join_time);
        // $("#head_img").attr('src',parent.PartitionData.head_img);
        // $("#dutyid").val(parent.PartitionData.dutyid);
        // $("#nation").val(parent.PartitionData.nation);
        $("#ID_cord").val(parent.PartitionData.ID_cord);
       /* $("#difficulty_type").val(parent.PartitionData.difficulty_type);
        $("#party_branch_name").html(parent.PartitionData.party_branch_name);*/

        layui.use(['laydate', 'layer', 'table', 'carousel', 'element', 'form'], function () {
            var form = layui.form;

            var laydate = layui.laydate //日期
                    ,layer = layui.layer //弹层
                    ,element = layui.element;//元素操作

            laydate.render({
                elem: '#join_time'
                ,btns: ['confirm']
                ,theme: 'grid'
                ,trigger: 'click'
            });



            //查询政治面貌种类
            $.ajax({
                url: "/duty/allCategory",
                async: false,
                success: function (data) {
                    $.each(data.list, function (i, item) {
                        $("#type_name").append("<option  value='" + item.id + "'>" + item.type_name + "</option>");

                        /* if(item.id == dutyid) {
                             $("#type_name").append("<option selected='selected'  value='" + item.id + "'>" + item.type_name + "</option>");
                         }else{
                             $("#type_name").append("<option  value='" + item.id + "'>" + item.type_name + "</option>");
                         }*/
                    });
                    form.render('select');
                }
            });

            //查询支部班子种类
            $.ajax({
                url: "/partyGroup/allCategory",
                async: false,
                success: function (data) {
                    $.each(data.list, function (i, item) {
                        $("#partyGroups_name").append("<option  value='" + item.id + "'>" + item.name + "</option>");

                        /* if(item.id == partyGroupsId) {
                             $("#partyGroups_name").append("<option selected='selected'  value='" + item.id + "'>" + item.name + "</option>");
                         }else{
                             $("#partyGroups_name").append("<option  value='" + item.id + "'>" + item.name + "</option>");

                         }*/
                    });
                    form.render('select');
                }
            });

            //查询党小组种类
            $.ajax({
                url: "/partyTeam/allCategory",
                async: false,
                success: function (data) {
                    $.each(data.list, function (i, item) {
                        $("#partyTeam_name").append("<option  value='" + item.id + "'>" + item.name + "</option>");

                        /*if(item.id == partyTeamId) {
                            $("#partyTeam_name").append("<option selected='selected'  value='" + item.id + "'>" + item.name + "</option>");
                        }else{
                            $("#partyTeam_name").append("<option  value='" + item.id + "'>" + item.name + "</option>");
                        }*/
                    });
                    form.render('select');
                }
            });


            form.on('submit(formDemo)', function () {partyGroups_name

                        $.ajax({
                            url: "/user/addUser",
                            data: {
                                id: $("#id").val(),
                                username: $("#username").val(),
                                age: $("#age").val(),
                                education: $("#education").val(),
                                age: $("#age").val(),
                                dutyid : $("#type_name").val(),
                                partyGroupsId : $("#partyGroups_name").val(),
                                partyTeamId : $("#partyTeam_name").val(),
                                join_time: $("#join_time").val(),
                                sex: $('input[name="sex"]:checked').val(),
                                ID_cord: $("#ID_cord").val(),
                                company: $("#company").val(),
                                phone: $("#phone").val()
                            },
                            success: function () {
                                layer.msg('保存成功', {icon: 1});
                                setTimeout(function () {
                                     window.parent.location.reload();
                                    // window.location.href = '/frame/userList.ftl'
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