<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>会议表管理</title>
    <link rel="stylesheet" href="/js/layui/css/layui.css">
    <script src="/js/layui/layui.js"></script>
    <script src="../js/jquery/jquery-3.3.1.min.js"></script>

</head>

<body>
<form class="layui-form" style="margin-top: 10px">
    <div class="layui-form-item">
        <label class="layui-form-label label_width_100">会议名称</label>
        <div class="layui-input-inline">
            <input type="text" id="name_search"  placeholder="会议名称" autocomplete="off" class="layui-input">
        </div>
        <label class="layui-form-label">会议类型</label>
        <div class="layui-input-inline">
            <select name="meeting_type_id" id="meeting_type_id">
                <option value="">全部</option>
            </select>
        </div>
    </div>
</form>

<form  id="lookDetail" class="layui-form" style="margin-top: 10px">
    <div class="layui-form-item">
        <label class="layui-form-label label_width_100">会议名称</label>
        <div class="layui-input-inline">
            <input type="text" id="name_search"  placeholder="会议名称" autocomplete="off" class="layui-input">
        </div>
        <label class="layui-form-label">会议类型</label>
        <div class="layui-input-inline">
            <select name="meeting_type_id" id="meeting_type_id">
                <option value="">全部</option>
            </select>
        </div>
    </div>
</form>

<div  style="display: none;padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">

</div>

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
    <a class="layui-btn layui-btn-sm layui-btn-danger"  lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-sm layui-btn-danger" lay-event="signIn">添加签到</a>
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


    function onAddBtn(data){

        //iframe窗
        layer.open({
            type: 2,
            title: '添加签到 ',
            shadeClose: true,
            shade: false,
            maxmin: true, //开启最大化最小化按钮
            area: ['893px', '600px'],
            content:  $("#lookDetail")//iframe的url，no代表不显示滚动条
        });

    }

    layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'element','form', ], function(){
        var laydate = layui.laydate //日期
                ,laypage = layui.laypage //分页
                ,layer = layui.layer //弹层
                ,table = layui.table //表格
                ,element = layui.element //元素操作

        var form = layui.form;
        var $ = jQuery;


        element.on('tab(demo)', function(data){
            layer.tips('切换了 '+ data.index +'：'+ this.innerHTML, this, {
                tips: 1
            });
        });

        laydate.render({
            elem: '#start_time_search' //指定元素
        });
        laydate.render({
            elem: '#end_time_search' //指定元素
        });

        //查询计划种类
        $.ajax({
            url: "/meetingType/allCategory",
            async: false,
            success: function (data) {
                $.each(data.list, function (i, item) {
                    $("#meeting_type_id").append("<option  value='" + item.id + "'>" + item.type_name + "</option>");
                });
                form.render('select');
            }
        });



        //执行一个 table 实例
        table.render({
            elem: '#demo'
            ,height: 563
            ,url: '/meeting/list' //数据接口
            ,title: '会议表'
            ,page: true //开启分页
            ,toolbar: 'default'  //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            ,totalRow: true //开启合计行
            ,cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'id', title: 'ID', width:100, sort: true, fixed: 'left'}
                ,{field: 'name', title: '会议名称'}
                ,{field: 'meeting_type_id',title:'会议类型'}
                ,{field: 'compere', title: '主持人'}
                // ,{field: 'recorder', title: '记录人'}
                ,{field: 'people_counting', title: '应到人数'}
                ,{field: 'attendance', title: '实到人数'}
                ,{field: 'address', title: '地点'}
                ,{field: 'content',title:'会议内容'}
                // ,{field: 'guide',title:'会议指导',width:350}
                // ,{field: 'images_a',title:'会议图片1',width:150}
                // ,{field: 'images_b',title:'会议图片2',width:200,hide:true}
                ,{field: 'start_time',title:'开始时间',sort: true}
                ,{field: 'end_time',title:'结束时间',sort: true}
                ,{field: 'edit',title:'编辑',width:200,templet: '#barDemo1'}

            ]]
        });
        var $ = layui.$, active = {
            reload:function () {
                var name = $("#name_search").val();
                var meeting_type_id=$("#meeting_type_id").val();

                table.reload('demo',{
                    method:'get',
                    where:{
                        name:name,
                        meeting_type_id:meeting_type_id
                    }
                });
            }
        };
        $('.search_div .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });


        //监听头工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id)
                    ,data = checkStatus.data; //获取选中的数据
            switch(obj.event){
                case 'add':
                     window.location.href='/meeting/gotoAdd';
                    break;
                case 'update':
                    console.log(data[0])
                    if(data.length === 0){
                        layer.msg('请选择一行');
                    } else if(data.length > 1){
                        layer.msg('只能同时编辑一个');
                    } else {
                        layer.msg('data[0].id');
                    window.location.href='/meeting/setMeeting?Id='+data[0].id;
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
                                a.push(value.id)
                            });

                            $.ajax({
                                url:"/meeting/logicDelete",
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

        //监听行工具事件
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                    ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'detail'){
                layer.msg('查看操作');
            } else if(layEvent === 'edit'){
                 console.log(data);
                window.location.href='/meeting/setMeeting?Id='+data.id;
            }else if(layEvent === 'signIn'){
                layer.msg('add');
                onAddBtn(data);
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
