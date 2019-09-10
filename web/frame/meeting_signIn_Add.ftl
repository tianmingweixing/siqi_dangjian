<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title> 会议表后台</title>
    <link rel="stylesheet" href="../../js/layui/css/layui.css">
    <script src="../../js/layui/layui.js"></script>
    <script src="../../js/jquery/jquery-3.3.1.min.js"></script>
</head>
<body>

<form class="layui-form" action="">

<br>
    <div class="layui-form-item input_row_margin_top" style="display:">
        <label class="layui-form-label" style="margin-left: 85px">会议ID
            <input id="id" name="id"  value=""/>
    </div>


    <div class="layui-form-item input_row_margin_top">

        <label class="layui-form-label ">会议名称</label>
        <div class="layui-input-inline">
            <input id="name" name="name" lay-verify="required" placeholder="请输入名称" maxlength="20"
                   autocomplete="off" class="layui-input" value="">
        </div>

    </div>


    <div class="layui-form-item">
        <div class="layui-form-item input_row_margin_top">
            <div class="layui-input-block">
                <button  class="layui-btn layui-btn-normal"  lay-submit lay-filter="formDemo">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </div>

</form>

<script>



    $(function() {

        layui.use(['laydate','form','upload'], function () {
            var form = layui.form;
            var laydate = layui.laydate //日期
                    ,layer = layui.layer; //弹层
            var upload = layui.upload;


            laydate.render({
                elem: '#start_time' //指定元素
            });
            laydate.render({
                elem: '#end_time' //指定元素
            });



            form.on('submit(formDemo)', function () {

                $.ajax({
                    url: "/meeting/addMeeting",
                    type:'post',
                    data: {
                        id: $("#id").val()
                    },
                    success: function () {
                        layer.msg('保存成功', {icon: 1});
                        setTimeout(function () {
                            window.location.href = '/frame/meetingList.ftl'
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