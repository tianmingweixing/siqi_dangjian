<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>慰问表后台</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css">
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jquery/jquery-3.3.1.min.js"></script>
</head>
<body>

<form class="layui-form" action="">

    <br>
    <div class="layui-form-item input_row_margin_top" style="display:none ">
        <label class="layui-form-label" style="margin-left: 85px">慰问ID</label>
        <input id="sympathyId" name="sympathyId" type="hidden" maxlength="20" value=""/>
    </div>

    <div class="layui-form-item input_row_margin_top" style="display:none ">
        <label class="layui-form-label">用户ID</label>
        <div class="layui-input-inline">
            <input id="userId" name="userId" class="layui-input" placeholder="请输入用户ID" value=""/>
        </div>
    </div>

    <div class="layui-form-item input_row_margin_top">
        <div class="layui-inline">
            <label class="layui-form-label ">用户姓名</label>
            <div class="layui-input-inline">
                <input id="username" readonly name="username" lay-verify="required" placeholder="请输入姓名" maxlength="20"
                       autocomplete="off" class="layui-input" value="">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">困难情况</label>
            <div class="layui-input-inline">
                <select name="difficult" id="difficult">
                    <option value="">全部</option>
                    <option value="0">非困难</option>
                    <option value="1">困难</option>
                    <option value="2">非常困难</option>
                </select>
            </div>
        </div>

    </div>

    <!--    <div class="layui-form-item input_row_margin_top" style="display:none ">
            <label class="layui-form-label" style="margin-left: -7px">性别</label>
            <div class="layui-input-inline">
                <input type="radio" name="sex" value="1" title="男" >
                <input type="radio" name="sex" value="2" title="女" >
            </div>

            <label class="layui-form-label" >手机号码</label>
            <div class="layui-input-inline">
                <input id="phone" name="phone" lay-verify="number" placeholder="请输入手机号码" maxlength="20"
                       autocomplete="off" class="layui-input" value="">
            </div>

        </div>-->

    <div class="layui-form-item input_row_margin_top">
        <div class="layui-inline">
            <label class="layui-form-label">单位及职务</label>
            <div class="layui-input-inline">
                <input id="unit_and_position" name="unit_and_position" lay-verify="required" placeholder="单位及职务"
                       maxlength="20"
                       autocomplete="off" class="layui-input" value="">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label ">慰问时间</label>
            <div class="layui-input-inline">
                <input id="sympathy_time" name="sympathy_time" lay-verify="required" placeholder="请输入入党时间"
                       maxlength="20"
                       autocomplete="off" class="layui-input" value="">
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
                              max-width: 1500px; height: 100px; max-height: 1000px; outline: 0;"></textarea>
                </div>
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <div class="layui-inline">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                        <textarea name="note" id="note" placeholder="请输入备注"
                                  style="width: 500px; border:1px solid #e6e6e6; font-size: 10px; line-height: 23px;color: #0c060f;
                              max-width: 1500px; height: 100px; max-height: 1000px; outline: 0;"></textarea>
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

<script>

    $(function () {

        if (parent.PartitionData) {
            $("#userId").val(parent.PartitionData.id);
            $("#username").val(parent.PartitionData.username);

            /*   $("input:radio][value='"+parent.PartitionData.sex+"']").prop("checked", "checked");
               $("#nation").val(parent.PartitionData.nation);
                $("#party_branch_name").html(parent.PartitionData.party_branch_name);*/

        }

        layui.use(['laydate', 'form'], function () {
            var form = layui.form;
            var laydate = layui.laydate //日期
                    , layer = layui.layer //弹层
                    , element = layui.element;//元素操作

            laydate.render({
                elem: '#sympathy_time' //指定元素
            });

            if (parent.PartitionData) {
                if (parent.PartitionData.difficulty_type == '非困难') {
                    var difficulty_type = 0;
                } else if (parent.PartitionData.difficulty_type == '困难') {
                    var difficulty_type = 1;
                } else if (parent.PartitionData.difficulty_type == '非常困难') {
                    var difficulty_type = 2;
                }
                $("#difficult").val(difficulty_type);
                form.render();
            }

            form.on('submit(formDemo)', function () {
                $.ajax({
                    url: "/sympathy/addSympathy",
                    data: {
                        sympathyId: $("#sympathyId").val(),
                        userId: $("#userId").val(),
                        sympathy_product: $("#sympathy_product").val(),
                        unit_and_position: $("#unit_and_position").val(),
                        sympathy_time: $("#sympathy_time").val(),
                        note: $("#note").val()
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