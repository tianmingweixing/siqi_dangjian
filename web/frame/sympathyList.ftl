<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>慰问表管理</title>
    <link rel="stylesheet" href="/js/layui/css/layui.css">

    <script src="/js/layui/layui.js"></script>
    <script src="../js/jquery/jquery-3.3.1.min.js"></script>

</head>

<body>
<form class="layui-form" style="margin-top: 10px">
    <div class="layui-form-item">
        <label class="layui-form-label label_width_100">姓名</label>
        <div class="layui-input-inline">
            <input type="text" id="name_search"  placeholder="姓名" autocomplete="off" class="layui-input">
        </div>
        <label class="layui-form-label">困难情况</label>
        <div class="layui-input-inline">
            <select name="difficult" id="difficult">
                <option value="">全部</option>
                <option value="0" >非困难</option>
                <option value="1" >困难</option>
                <option value="2" >非常困难</option>
            </select>
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
   <!-- <a class="layui-btn layui-btn-primary layui-btn-xs"  lay-event="add">添加</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-xs" lay-event="delete">删除</a>-->
    <a class="layui-btn layui-btn-sm layui-btn-primary"><i class="layui-icon" lay-event="delete">&#xe640;</i></a>
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

    layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'element', ], function(){
        var laydate = layui.laydate //日期
                ,laypage = layui.laypage //分页
                ,layer = layui.layer //弹层
                ,table = layui.table //表格
                ,element = layui.element; //元素操作

        laydate.render({
            elem: '#start_time_search' //指定元素
        });
        laydate.render({
            elem: '#end_time_search' //指定元素
        });

        element.on('tab(demo)', function(data){
            layer.tips('切换了 '+ data.index +'：'+ this.innerHTML, this, {
                tips: 1
            });
        });

        //执行一个 table 实例
        table.render({
            elem: '#demo'
            ,height: 563
            ,url: '/sympathy/list' //数据接口
            ,title: '慰问表'
            ,page: true //开启分页
            ,toolbar: '#barDemo'  //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            ,totalRow: true //开启合计行
            ,cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'sympathyId', title: '慰问ID', width:100, sort: true, fixed: 'left'}
                ,{field: 'userId', title: '用户ID', width:100,  fixed: 'left'}
                ,{field: 'username', title: '姓名'}
                ,{field: 'sex',title:'性别'}
                ,{field: 'age',title:'年龄'}
                ,{field: 'difficult',title:'困难情况',sort: true}
                ,{field: 'sympathy_time',title:'慰问时间',sort: true}
                ,{field: 'unit_and_position',title:'慰问人单位及职务'}
                ,{field: 'sympathy_product',title:'慰问品及信息'}
                ,{field: 'note',title:'备注'}
                ,{field: 'edit',title:'编辑',width:200,templet: '#barDemo1'}
            ]]
        });
        var $ = layui.$, active = {
            reload:function () {
                var username = $("#name_search").val();
                var difficult=$("#difficult").val();
                var start_time_search=$("#start_time_search").val();
                var end_time_search=$("#end_time_search").val();

                table.reload('demo',{
                    method:'get',
                    where:{
                        username:username,
                        difficult:difficult,
                        start_time_search:start_time_search,
                        end_time_search:end_time_search
                    }
                });
            }
        };
        $('.search_div .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });


        //监听头事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id)
                    ,data = checkStatus.data; //获取选中的数据
            switch(obj.event){
                case 'add':
                     window.location.href='/sympathy/gotoAdd';
                    break;
                case 'update':
                    console.log(data[0])
                    if(data.length === 0){
                        layer.msg('请选择一行');
                    } else if(data.length > 1){
                        layer.msg('只能同时编辑一个');
                    } else {
                        //layer.msg('data[0].id');
                        window.location.href='/sympathy/setSympathy?sympathyId='+data[0].sympathyId +'&userId='+data[0].userId;
                    }
                    break;
                case 'delete':
                    if(data.length === 0){
                        layer.msg('请选择一行');
                    } else {
                        layer.confirm("确认要删除吗", {
                            icon:0
                        }, function () {
                            var a = [];
                            $.each(data,function(index,value){
                                a.push(value.sympathyId)
                            });

                            $.ajax({
                                url:"/sympathy/logicDelete",
                                data:{
                                    deleteArray:JSON.stringify(a)
                                },
                                success:function (data) {
                                    if(data.result == "fail"){
                                        layer.open({
                                            icon: 2,
                                            title: '消息提醒',
                                            content: '删除失败',
                                            skin:'layui_open_fail'
                                        });
                                    } else {
                                        layer.msg('删除成功', {icon: 1});
                                        setTimeout(function () {
                                            location.reload()
                                        },1000)
                                    }
                                }
                            })
                        })

                    }
                    break;
            };
        });

        //监听行事件
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                    ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'detail'){
                layer.msg('查看操作');
            } else if(layEvent === 'edit'){
                window.location.href='/sympathy/setSympathy?sympathyId='+data.sympathyId +'&userId='+data.userId;
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
