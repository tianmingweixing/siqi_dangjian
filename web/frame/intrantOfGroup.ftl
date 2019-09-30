<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>成员列表</title>
    <link rel="stylesheet" href="/js/layui/css/layui.css">

    <script src="/js/layui/layui.js"></script>
    <script src="../js/jquery/jquery-3.3.1.min.js"></script>

</head>

<body>
<form class="layui-form" style="margin-top: 10px">
    <div class="layui-form-item">
        <label class="layui-form-label label_width_100">用户名</label>
        <div class="layui-input-inline">
            <input type="text" id="name_search"  placeholder="用户名" autocomplete="off" class="layui-input">
        </div>

        <label class="layui-form-label ">用户ID</label>
        <div class="layui-input-inline">
            <input type="text" id="user_id_search"  placeholder="用户ID" autocomplete="off" class="layui-input">
        </div>

    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">政治面貌</label>
        <div class="layui-input-inline">
            <select name="type_name" id="type_name">
                <option value="">全部</option>
            </select>
        </div>

        <label class="layui-form-label ">单位</label>
        <div class="layui-input-inline">
            <input type="text" id="company_search"  placeholder="单位" autocomplete="off" class="layui-input">
        </div>
    </div>

</form>

<div id="lookDetail" style="display: none;">
    <form class="layui-form" id="add-form" action="">
        <input type="hidden" id="id"  value="" style="width: 240px">

        <div class="layui-form-item center" style=" margin-top: 3px; padding: -145px;">
            <label class="layui-form-label" style="width: 60px;margin-top: 34px;">姓名</label>
            <div class="layui-form-label" id="username" style="color: #00b0f0;margin-top: 34px;margin-left: -27px;"></div>

            <label class="layui-form-label " style="margin-left: -9px;margin-top: 34px;">头像</label>
            <div class="layui-input-inline" style="padding-top: 71px;margin-top: 11px;">
                <img id="head_img" name ="head_img" src="" height="auto" width="100" alt="" style="margin-top: -63px;">
            </div>
        </div>

        <div class="layui-form-item center">
            <label class="layui-form-label" style="width: 60px">学历</label>
            <div class="layui-form-label" id="education" style="color: #00b0f0;margin-left: -27px;"></div>

            <label class="layui-form-label" style="width: 100px">用户年龄</label>
            <div class="layui-form-label" id="age" style="color: #00b0f0;margin-left: -27px;"></div>

        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 60px">性别</label>
            <div class="layui-form-label" id="sex" style="color: #00b0f0;margin-left: -27px;"></div>

            <label class="layui-form-label" style="width: 100px">手机号码</label>
            <div class="layui-form-label" id="phone" style="color: #00b0f0;margin-left: -27px;"></div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 60px">职务</label>
            <div class="layui-form-label" id="look_type_name" style="color: #00b0f0;margin-left: -27px;"></div>

            <label class="layui-form-label" style="width: 100px">困难状态</label>
            <div class="layui-form-label" id="difficulty_type" style="color: #00b0f0;margin-left: -27px;"></div>

        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 60px">身份证</label>
            <div class="layui-form-label" id="ID_cord" style="color: #00b0f0;margin-left: -27px;"></div>

            <label class="layui-form-label" style="width: 100px">所属支部</label>
            <div class="layui-form-label" id="look_party_branch_name" style="color: #00b0f0;margin-left: -27px;"></div>

        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 60px">单位</label>
            <div class="layui-form-label" id="company" style="color: #00b0f0;margin-left: -27px;"></div>

            <label class="layui-form-label" style="width: 100px">入党时间</label>
            <div class="layui-form-label" id="join_time" style="color: #00b0f0;margin-left: -27px;"></div>

        </div>



    </form>
</div>


<div class="layui-input-inline search_div" style="margin-left: 115px">
    <button class="layui-btn" data-type="reload">提交</button>
    <button onclick="reset_search()" class="layui-btn layui-btn-primary">重置</button>
</div>


<div style="margin: 0 10px;">
    <table class="layui-hide" id="demo" lay-filter="test"></table>
</div>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-sm layui-btn-danger" lay-event="edit">修改</a>
    <a class="layui-btn layui-btn-sm layui-btn-danger" lay-event="detail">查看</a>
    <a class="layui-btn  layui-btn-sm layui-btn-normal" lay-event="addSympathy">添加慰问</a>
</script>


<script type="text/html" id="img">
    <div><img id="headUrlImg" style="height:35px;width:35px;border-radius:50%;line-height:50px!important;" src="{{d.head_img}}"></div>
</script>

<script src="/js/layui/layui.js"></script>
<script>
    layui.config({
        version: '1551352891272' //为了更新 js 缓存，可忽略
    });



    var groupid = 0;
    if (parent.PartitionData) {
        groupid =  parent.PartitionData;
    }
    console.log(groupid);

    function reset_search(){
        window.location.reload();
    }


    layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'element', ], function(){
        var laydate = layui.laydate //日期
                ,laypage = layui.laypage //分页
                ,layer = layui.layer //弹层
                ,table = layui.table //表格
                ,element = layui.element;//元素操作

        var $ = jQuery;
        var form = layui.form;


        laydate.render({
            elem: '#join_time' //指定元素
        });

        element.on('tab(demo)', function(data){
            layer.tips('切换了 '+ data.index +'：'+ this.innerHTML, this, {
                tips: 1
            });
        });

        //初始化政治面貌下拉框
        $.ajax({
            url: "/duty/allCategory",
            async: false,
            success: function (data) {
                $.each(data.list, function (i, item) {
                    $("#type_name").append("<option  value='" + item.id + "'>" + item.type_name + "</option>");
                });
                form.render('select');
            }
        });

        //执行一个 table 实例
        table.render({
            elem: '#demo'
            ,height: 563
            ,url: '/user/intrant?groupid='+groupid //数据接口
            ,title: '用户表'
            ,page: true //开启分页
            ,toolbar: false
            ,totalRow: true //开启合计行
            ,cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'id', title: 'ID', width:50, sort: true, fixed: 'left'}
                ,{field: 'username', title: '用户名'}
                ,{field: 'head_img',title:'头像',width:80,templet:'#img'}
                ,{field: 'nick_name', title: '昵称',hide:true}
                ,{field: 'sex',title:'性别',width:80}
                ,{field: 'education',title:'学历',hide:true}
                ,{field: 'company',title:'单位'}
                ,{field: 'phone',title:'手机号码'}
                ,{field: 'ID_cord',title:'身份证',hide:true}
                ,{field: 'type_name',title:'政治面貌'}
                ,{field: 'profiles',title:'用户简介',hide:true}
                ,{field: 'company_office',title:'单位职务',hide:true}
                ,{field: 'party_posts',title:'党内职务',hide:true}
                ,{field: 'dutyid',title:'职务ID',hide:true}
                ,{field: 'party_branch_id',title:'党支部ID',hide:true}
                ,{field: 'party_branch_name',title:'党支部名称',hide:true}
                ,{field: 'party_groups_id',title:'班子ID'}
                ,{field: 'party_groups_name',title:'班子名称',hide:true}
                ,{field: 'party_team_id',title:'党小组ID'}
                ,{field: 'party_groups_name',title:'党小组名称',hide:true}
                ,{field: 'join_time',title:'入党时间'}
            ]]
        });

        var $ = layui.$, active = {
            reload:function () {
                var username = $("#name_search").val();
                var dutyid = $("#type_name").val();
                var company =$("#company_search").val();
                var userId = $("#user_id_search").val();

                table.reload('demo',{
                    method:'get',
                    where:{
                        username:username,
                        company:company,
                        userId:userId,
                        dutyid: dutyid
                    }
                });
            }
        };
        $('.search_div .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });


        //分页
        laypage.render({
            elem: 'pageDemo' //分页容器的id
            ,count: 100 //总页数
            ,skin: '#1E9FFF' //自定义选中色值
            //,skip: true //开启跳页
            ,jump: function(obj, first){
                if(!first){
                    layer.msg('第'+ obj.curr +'页', {offset: 'b'});
                }
            }
        });


    });
</script>
</body>
</html>
