<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>layout 后台大布局 - Layui</title>
    <link rel="stylesheet" href="/js/layui/css/layui.css">
    <link rel="stylesheet" href="/css/public.css">

    <script src="/js/layui/layui.js"></script>
    <script src="/js/jquery/jquery-3.3.1.min.js"></script>

</head>

<body>
<form class="layui-form" style="margin-top: 10px">
    <div class="layui-form-item">
        <label class="layui-form-label label_width_100">账号</label>
        <div class="layui-input-inline">
            <input type="text" id="account_search"  placeholder="请输入管理员账号" autocomplete="off" class="layui-input">
        </div>
        <label class="layui-form-label ">昵称</label>
        <div class="layui-input-inline">
            <input type="text" id="name_search"  placeholder="请输入管理员昵称" autocomplete="off" class="layui-input">
        </div>
        <label class="layui-form-label ">类型</label>
        <div class="layui-input-inline">
            <select id="type_search" name="type_search" lay-verify="">
                <option value="">全部</option>
                <option value="1">超级管理员</option>
                <option value="2">普通管理员</option>
            </select>
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
    <a class="layui-btn layui-btn-xs" lay-event="edit">权限</a>
</script>
<style>
    .layui_open_fail{
        text-align: center;
        border-radius: 5px;
    }
</style>
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
                ,element = layui.element //元素操作

        element.on('tab(demo)', function(data){
            layer.tips('切换了 '+ data.index +'：'+ this.innerHTML, this, {
                tips: 1
            });
        });

        //执行一个 table 实例
        table.render({
            elem: '#demo'
            ,height: 563
            ,url: '/admin/list' //数据接口
            ,title: '用户表'
            ,page: true //开启分页
            ,toolbar: 'default'  //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            ,cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'id', title: 'ID', width:100, sort: true, fixed: 'left'}
                ,{field: 'account', title: '账号', width:200}
                ,{field: 'username', title: '昵称', width: 200}
                ,{field: 'admin_type', title: '类型', width:200}
                // ,{field: 'authority',title:'权限',width:200}
                ,{field: 'name',title:'支部',width:200}
                ,{field: 'id',title:'操作',width:200,fixed: 'right',toolbar: '#barDemo'}
            ]]
        });
        var $ = layui.$, active = {
            reload:function () {
                var account = $("#account_search").val();
                var userName=$("#name_search").val();
                var adminType=$("#type_search").val();
                table.reload('demo',{
                    method:'get',
                    where:{
                        account:account,
                        userName:userName,
                        adminType:adminType
                    }
                });
            }
        };

        $('.search_div .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                    ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'detail'){
                layer.msg('查看操作');
            } else if(layEvent === 'del'){
                layer.confirm('真的删除行么', function(index){
                    obj.del(); //删除对应行（tr）的DOM结构
                    layer.close(index);
                    //向服务端发送删除指令
                });
            } else if(layEvent === 'edit'){
                layer.msg('编辑操作');
            }
        });

        //监听头工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id)
                    ,data = checkStatus.data; //获取选中的数据
            switch(obj.event){
                case 'add':
                    window.location.href='/admin/goto';
                    break;
                case 'update':
                    if(data.length === 0){
                        layer.msg('请选择一行');
                    } else if(data.length > 1){
                        layer.msg('只能同时编辑一个');
                    } else {
                        window.location.href='/admin/goto?id='+data[0].id;
                    }
                    break;
                case 'delete':
                    if(data.length === 0){
                        layer.msg('请选择一行');
                    } else {
                        layer.confirm("确认要删除吗", {
                            icon:0
                        }, function () {
                            var idArr = [];
                            $.each(data,function(index,value){
                                idArr.push(value.id)
                            });
                            $.ajax({
                                url:"/admin/delete",
                                data:{
                                    deleteArray:JSON.stringify(idArr)
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
