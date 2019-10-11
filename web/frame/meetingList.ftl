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
<form class="layui-form" style="margin-top:10px">
    <div class="layui-form-item">
        <label class="layui-form-label label_width_100">会议标题</label>
        <div class="layui-input-inline">
            <input type="text" id="name_search"  placeholder="会议标题" autocomplete="off" class="layui-input">
        </div>

        <!--<label class="layui-form-label">会议类型</label>-->
        <div class="layui-input-inline">
            <select name="meeting_type_id" id="meeting_type_id">
                <option value="">会议类型</option>
            </select>
        </div>

        <!--<label class="layui-form-label ">会议ID</label>-->
        <div class="layui-input-inline">
            <input type="text" id="meeting_id_search"  placeholder="会议ID" autocomplete="off" class="layui-input">
        </div>
    </div>
</form>

<div class="layui-input-inline search_div" style="margin-left: 110px">
    <button class="layui-btn " data-type="reload">提交</button>
    <button onclick="reset_search()" class="layui-btn layui-btn-primary">重置</button>
</div>



<div id="lookDetail" style="display: none;padding: 50px; line-height: 22px; color: #56aa17; font-weight: 300;">
    <form  class="layui-form" name="fileForm" style="margin-top: 10px">

        <div style="display: none">
            <label class="layui-form-label ">会议ID</label>
            <div class="layui-input-inline">
                <input type="text" id="meetingId" name="meetingId"  placeholder="会议ID" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label ">会议标题</label>
            <div class="layui-input-inline">
                <input type="text" id="name" name="name" readonly  placeholder="会议标题" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label ">用户ID</label>
            <div class="layui-input-inline">
                <input type="text" id="userId" name="userId"  placeholder="输入用户ID" autocomplete="off" class="layui-input">
            </div>
        </div>

    </form>
    <div class="layui-input-inline search_div" style="margin-left: 110px">
        <button class="layui-btn layui-btn-normal" onclick="sign_in()">添加</button>
    </div>

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
    <a class="layui-btn layui-btn-xs layui-btn-danger"  lay-event="edit">编辑</a>
<!--
    <a class="layui-btn layui-btn-sm layui-btn-normal" lay-event="signIn">添加签到</a>
-->
</script>
<script src="/js/layui/layui.js"></script>

<script type="text/html" id="images_a">

    {{# var srr=d.images_a.split(",");
    for(var j in srr) { srr[j] }}
    <div style="margin:0 10px; display:inline-block !important; display:inline;  max-width:70px; max-height:50px;">
        <img style=" max-width:70px; max-height:50px;" src="{{srr[j]}}" />
    </div>
    {{# } }}

</script>

<script>
    layui.config({
        version: '1551352891272' //为了更新 js 缓存，可忽略
    });
    function reset_search(){
        window.location.reload();
    }


    function onAddBtn(data){
        document.fileForm.name.value = data.name;
        document.fileForm.meetingId.value = data.id;

        layer.open({
            type: 1
            ,title: '添加签到 ' //不显示标题栏
            ,area:['500px', '300px']
            ,shadeClose: true
            ,shade: false
            ,offset: 'r'
            ,maxmin: true //开启最大化最小化按钮
            ,content: $("#lookDetail")
        });

        layer.open({
            type: 2,
            title: '用户列表页面',
            shadeClose: true,
            shade: false,
            offset: 'lt',
            maxmin: true, //开启最大化最小化按钮
            area: ['1200px', '800px'],
            content: ['/frame/userList.ftl']
        });
    }

    function sign_in(){
       var meeting_id = document.fileForm.meetingId.value;
       var user_id =  document.fileForm.userId.value;
       console.log(meeting_id);

        $.ajax({
            url: "/meeting/signIn",
            type : 'post',
            data :{
                meeting_id : meeting_id,
                user_id : user_id
            },
            success : function(data){
                if(data.result == "fail"){
                    layer.open({
                        icon: 2,
                        title: '消息提醒',
                        content: '添加失败',
                        skin:'layui_open_fail'
                    });
                } else if(data.msg == "用户已签到"){
                    layer.open({
                        icon: 2,
                        title: '消息提醒',
                        content: data.msg,
                        skin:'layui_open_fail'
                    });
                } else {
                    layer.msg('添加成功', {icon: 1});
                    setTimeout(function () {
                        location.reload()
                    },1000)
                }
            }
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
                ,{field: 'name', title: '会议标题'}
                ,{field: 'type_name',title:'会议类型'}
                ,{field: 'compere', title: '主持人'}
                // ,{field: 'recorder', title: '记录人'}
                ,{field: 'people_counting', title: '应到人数'}
                ,{field: 'attendance', title: '实到人数'}
                ,{field: 'address', title: '会议地点'}
                ,{field: 'content',title:'会议内容'}
                ,{field: 'guide',title:'会议指导',hide:true}
                ,{field: 'images_a',title:'会议图片',templet:'#images_a'}
                ,{field: 'start_time',title:'开始时间',sort: true}
                ,{field: 'end_time',title:'结束时间',sort: true}
                ,{field: 'edit',title:'操作',width:200,templet: '#barDemo1'}

            ]]
        });
        var $ = layui.$, active = {
            reload:function () {
                var name = $("#name_search").val();
                var meeting_type_id=$("#meeting_type_id").val();
                var meeting_id_search=$("#meeting_id_search").val();

                table.reload('demo',{
                    method:'get',
                    where:{
                        name:name,
                        meeting_id_search:meeting_id_search,
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
            }
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                    ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'detail'){
                layer.msg('查看操作');
            } else if(layEvent === 'edit'){
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
