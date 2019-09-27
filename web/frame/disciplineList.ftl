<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>违纪表管理</title>
    <link rel="stylesheet" href="/js/layui/css/layui.css">
    <script src="/js/layui/layui.js"></script>
    <script src="../js/jquery/jquery-3.3.1.min.js"></script>

</head>

<body>
<form class="layui-form" style="margin-top: 10px">

    <div class="layui-form-item">
        <label class="layui-form-label label_width_100">违纪名称</label>
        <div class="layui-input-inline">
            <input type="text" id="name"  placeholder="违纪名称" autocomplete="off" class="layui-input">
        </div>

        <label class="layui-form-label label_width_100">用户ID</label>
        <div class="layui-input-inline">
            <input type="text" id="start_user_id"  placeholder="用户ID" autocomplete="off" class="layui-input">
        </div>

    </div>

    <div class="layui-form-item">
        <label class="layui-form-label " style="margin-left: 1px">开始时间</label>
        <div class="layui-input-inline">
            <input id="start_time_search" name="start_time_search" lay-verify="required" placeholder="开始时间" maxlength="20"
                   autocomplete="off" class="layui-input" >
        </div>

        <label class="layui-form-label " style="margin-left: 1px">结束时间</label>
        <div class="layui-input-inline">
            <input id="end_time_search" name="end_time_search" lay-verify="required" placeholder="结束时间" maxlength="20"
                   autocomplete="off" class="layui-input" >
        </div>
    </div>

</form>
<div class="layui-input-inline search_div" style="margin-left: 110px">
    <button class="layui-btn" data-type="reload">提交</button>
    <button onclick="reset_search()" class="layui-btn layui-btn-primary">重置</button>
</div>


<div style="margin: 0 10px;">
    <table class="layui-hide" id="demo" lay-filter="test"></table>
</div>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs"  lay-event="add">添加</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-xs" lay-event="delete">删除</a>
</script>
<script type="text/html" id="barDemo1">
    <a class="layui-btn layui-btn-sm layui-btn-danger" lay-event="edit">编辑</a>
</script>
<style>
    .layui_open_fail{
        text-align: center;
        border-radius: 5px;
    }
</style>

<script src="/js/layui/layui.js"></script>
<script>
    layui.config({
        version: '1551352891272' //为了更新 js 缓存，可忽略
    });
    function reset_search(){
        window.location.reload();
    }

    layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'element',], function () {
        var laydate = layui.laydate //日期
                , laypage = layui.laypage //分页
                , layer = layui.layer //弹层
                , table = layui.table //表格
                , element = layui.element; //元素操作

        element.on('tab(demo)', function (data) {
            layer.tips('切换了 ' + data.index + '：' + this.innerHTML, this, {
                tips: 1
            });
        });

        laydate.render({
            elem: '#start_time_search' //指定元素
        });
        laydate.render({
            elem: '#end_time_search' //指定元素
        });

        //执行一个 table 实例
        table.render({
            elem: '#demo'
            , height: 563
            , url: '/disciplineOfHonor/list?type='+1 //数据接口
            , title: '违纪表'
            , page: true //开启分页
            , toolbar: 'default'  //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            , totalRow: true //开启合计行
            , cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                , {field: 'id', title: '违纪ID', sort: true, fixed: 'left'}
                , {field: 'user_id', title: '用户ID'}
                , {field: 'unit', title: '违纪个人或单位'}
                , {field: 'name', title: '违纪名称'}
                , {field: 'type', title: '类型' ,hide:true}
                , {field: 'certificate', title: '违纪凭证',hide:true}
                , {field: 'time', title: '时间',sort:true}
                , {field: 'passive_unit', title: '奖惩单位', width: 200,sort:true,hide:true}
                , {field: 'amount', title: '金额',sort:true,hide:true}
                , {field: 'content', title: '违纪详情',sort:true}
                , {field: 'note', title: '备注',sort:true}
                , {field: 'party_branch_id', title: '支部ID', width: 80,sort:true,hide:true}
                ,{field: 'edit',title:'编辑',templet: '#barDemo1'}
            ]]
        });
        var $ = layui.$, active = {
            reload:function () {
                var name = $("#name").val();
                var user_id=$("#start_user_id").val();
                var start_time_search=$("#start_time_search").val();
                var end_time_search=$("#end_time_search").val();

                table.reload('demo',{
                    method:'get',
                    where:{
                        name:name,
                        user_id:user_id,
                        start_time_search:start_time_search,
                        end_time_search:end_time_search
                    }
                });
            }
        };
        //??
        $('.search_div .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });


        //头:工具栏事件
        table.on('toolbar(test)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id)
                    , data = checkStatus.data; //获取选中的数据
            switch (obj.event) {
                case 'add':
                    window.location.href = '/disciplineOfHonor/gotoAdd?type='+1;
                    break;
                case 'update':
                    console.log(data[0])
                    if (data.length === 0) {
                        layer.msg('请选择一行');
                    } else if (data.length > 1) {
                        layer.msg('只能同时编辑一个');
                    } else {
                        layer.msg('正在编辑中..');
                        window.location.href = '/disciplineOfHonor/setDisciplineOfHonor?Id=' + data[0].id+'&type='+1;
                    }
                    break;
                case 'delete':
                    if (data.length === 0) {
                        layer.msg('请选择一行');
                    } else {
                        layer.confirm("确认要删除吗", {
                            icon: 0
                        }, function () {
                            var a = [];
                            $.each(data, function (index, value) {
                                a.push(value.id)
                            });

                            $.ajax({
                                url: "/disciplineOfHonor/logicDelete",
                                data: {
                                    deleteArray: JSON.stringify(a)
                                },
                                success: function (data) {
                                    if (data.result == "fail") {
                                        layer.open({
                                            icon: 2,
                                            title: '消息提醒',
                                            content: '删除失败',
                                            skin: 'layui_open_fail'
                                        });
                                    } else {
                                        layer.msg('删除成功', {icon: 1});
                                        setTimeout(function () {
                                            location.reload()
                                        }, 1000)
                                    }
                                }
                            })
                        })

                    }
                    break;
            }
        });

        //行:工具事件
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                    ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'detail'){
                layer.msg('查看操作');
            } else if(layEvent === 'edit'){
                 layer.msg('edit');
                 console.log(data);
                window.location.href = '/disciplineOfHonor/setDisciplineOfHonor?Id=' + data.id+'&type='+1+'&userId='+data.user_id;
            }else if(layEvent === 'add'){
                layer.msg('add');
            }else if(layEvent === 'delete'){
                layer.msg('del');
            }
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
