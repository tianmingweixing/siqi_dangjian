<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>用户列表管理</title>
    <link rel="stylesheet" href="/js/layui/css/layui.css">

    <script src="/js/layui/layui.js"></script>
    <script src="../js/jquery/jquery-3.3.1.min.js"></script>

</head>

<body>
<form class="layui-form" style="margin-top: 10px">
    <div class="layui-form-item">
        <label class="layui-form-label label_width_100">活动标题</label>
        <div class="layui-input-inline">
            <input type="text" id="title"  placeholder="活动标题" autocomplete="off" class="layui-input">
        </div>
        <label class="layui-form-label ">活动类型</label>
        <div class="layui-input-inline">
            <input type="text" id="type"  placeholder="活动类型" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">活动日期</label>
        <div class="layui-input-inline">
            <input type="text" class="layui-input" placeholder="起始日期" id="start_time_search">
        </div>
        <div class="layui-form-mid layui-word-aux">至</div>
        <div class="layui-input-inline" style="margin-left: 15px">
            <input type="text" class="layui-input" placeholder="结束日期" id="end_time_search">
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
    <!--<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>-->
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

    layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'element','form'], function() {
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

        //执行一个 table 实例
        table.render({
            elem: '#demo'
            , height: 563
            , url: '/activities/list' //数据接口
            , title: '用户表'
            , page: true //开启分页
            , toolbar: 'default'  //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            , totalRow: true //开启合计行
            , cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                , {field: 'id', title: 'ID', width: 100, sort: true, fixed: 'left'}
                , {field: 'title', title: '活动标题'}
                , {field: 'content', title: '活动内容'}
                , {field: 'type_id', title: '活动类型'}
                , {field: 'start_time', title: '开始时间'}
                , {field: 'end_time', title: '结束时间'}
                , {field: 'review', title: '点评'}
                , {field: 'image_path_a', title: '图片1'}
                , {field: 'image_path_b', title: '图片2'}
                // ,{field: 'address',title:'地址',width:200,sort: true}
                // ,{field: 'birth', title: '出生日期', width:200}
                // ,{field: 'userno',title:'用户编号',width:200,sort: true}
            ]]
        });

        var $ = layui.$, active = {
            reload: function () {
                var title = $("#title").val();
                var type = $("#type").val();
                var start_time = $("#start_time_search").val();
                var end_time = $("#end_time_search").val();
                table.reload('demo', {
                    method: 'get',
                    where: {
                        title: title,
                        type: type,
                        start_time: start_time,
                        end_time: end_time
                    }
                });
            }
        };

        $('.search_div .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });


        //监听头工具栏事件
        table.on('toolbar(test)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id)
                    , data = checkStatus.data; //获取选中的数据
            switch (obj.event) {
                case 'add':
                    window.location.href = '/activities/goto';
                    break;
                case 'update':
                    if (data.length === 0) {
                        layer.msg('请选择一行');
                    } else if (data.length > 1) {
                        layer.msg('只能同时编辑一个');
                    } else {
                        window.location.href = '/activities/goto?id=' + data[0].id;
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
                                url: "/activities/delete",
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
