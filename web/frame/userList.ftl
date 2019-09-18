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
        <label class="layui-form-label label_width_100">昵称</label>
        <div class="layui-input-inline">
            <input type="text" id="name_search"  placeholder="昵称" autocomplete="off" class="layui-input">
        </div>
        <label class="layui-form-label ">单位</label>
        <div class="layui-input-inline">
            <input type="text" id="company_search"  placeholder="单位" autocomplete="off" class="layui-input">
        </div>

    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">政治面貌</label>
        <div class="layui-input-inline">
            <select name="type_name" id="type_name">
                <option value="">全部</option>
            </select>
        </div>
    </div>

</form>

<div id="lookDetail" style="display: none;>
    <form class="layui-form" id="add-form" action="">
        <input type="hidden" id="id"  value="" style="width: 240px">

        <div class="layui-form-item center" style=" margin-top: 3px; padding: -145px;">
            <label class="layui-form-label" style="width: 60px;margin-top: 34px;">用户名</label>
            <div class="layui-form-label" id="username" style="color: #00b0f0;margin-top: 34px;margin-left: -27px;"></div>

            <label class="layui-form-label " style="margin-left: -9px;margin-top: 34px;">用户头像</label>
            <div class="layui-input-inline" style="padding-top: 71px;margin-top: 11px;">
                <img id="head_img" name ="head_img" src="" height="auto" width="100" alt="" style="margin-top: -63px;">
            </div>
        </div>

        <div class="layui-form-item center">
            <label class="layui-form-label" style="width: 60px">学历</label>
            <div class="layui-form-label" id="education" style="color: #00b0f0;margin-left: -27px;"></div>

            <label class="layui-form-label" style="width: 100px">年龄</label>
            <div class="layui-form-label" id="age" style="color: #00b0f0;margin-left: -27px;"></div>

        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 60px">性别</label>
            <div class="layui-form-label" id="sex" style="color: #00b0f0;margin-left: -27px;"></div>

            <label class="layui-form-label" style="width: 100px">手机号码</label>
            <div class="layui-form-label" id="phone" style="color: #00b0f0;margin-left: -27px;"></div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 60px">党内职务</label>
            <div class="layui-form-label" id="dutyid" style="color: #00b0f0;margin-left: -27px;"></div>

            <label class="layui-form-label" style="width: 100px">困难状态</label>
            <div class="layui-form-label" id="difficulty_type" style="color: #00b0f0;margin-left: -27px;"></div>

        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 60px">身份证</label>
            <div class="layui-form-label" id="ID_cord" style="color: #00b0f0;margin-left: -27px;"></div>

            <label class="layui-form-label" style="width: 100px">所属党支部</label>
            <div class="layui-form-label" id="party_branch_name" style="color: #a9f981;margin-left: -27px;"></div>

        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 60px">单位</label>
            <div class="layui-form-label" id="company" style="color: #00b0f0;margin-left: -27px;"></div>

            <label class="layui-form-label" style="width: 100px">入党时间</label>
            <div class="layui-form-label" id="join_time" style="color: #00b0f0;margin-left: -27px;"></div>

        </div>



    </form>
</div>


<div class="layui-input-inline search_div" style="margin-left: 440px">
    <button class="layui-btn" data-type="reload">提交</button>
    <button onclick="reset_search()" class="layui-btn layui-btn-primary">重置</button>
</div>


<div style="margin: 0 10px;">
    <table class="layui-hide" id="demo" lay-filter="test"></table>
</div>

<script type="text/html" id="barDemo1">
    <a class="layui-btn layui-btn-primary layui-btn-xs"  lay-event="add">添加</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-xs" lay-event="delete">删除</a>
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-sm layui-btn-danger" lay-event="detail">查看</a>
    <a class="layui-btn  layui-btn-sm layui-btn-normal" lay-event="addSympathy"><i class="layui-icon"></i>添加慰问</a>
</script>
<style>
    .layui_open_fail{
        text-align: center;
        border-radius: 5px;
    }
</style>


<script type="text/html" id="img">
    <div><img id="headUrlImg" style="height:35px;width:35px;border-radius:50%;line-height:50px!important;" src="{{d.head_img}}"></div>
</script>




<script src="/js/layui/layui.js"></script>
<script>
    layui.config({
        version: '1551352891272' //为了更新 js 缓存，可忽略
    });

    function reset_search(){
        window.location.reload();
    }
    


    function onAddUserBtn(data){

        layer.open({
            type: 2,
            title: '添加用户页面',
            shadeClose: true,
            shade: false,
            offset: 'default',
            maxmin: true, //开启最大化最小化按钮
            area: ['800px', '500px'],
            content: ['/frame/user_Add.ftl']
        });
    }

    function onAddBtn(data){
        $("#username").html(data.username);
        $("#age").html(data.age);
        $("#company").html(data.company);
        $("#education").html(data.education);
        $("#phone").html(data.phone);
        $("#sex").html(data.sex);
        $("#join_time").html(data.join_time);
        $("#head_img").attr('src',data.head_img);
        $("#dutyid").html(data.dutyid);
        $("#nation").html(data.nation);
        $("#ID_cord").html(data.ID_cord);
        $("#difficulty_type").html(data.difficulty_type);
        $("#party_branch_name").html(data.party_branch_name);



        layer.open({
            type: 1,
            title: '用户详情',
            shadeClose: true,
            shade: false,
            offset: 'default',
            maxmin: true, //开启最大化最小化按钮
            area: ['500px', '500px'],
            content: $("#lookDetail")
        });

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

        //查询计划种类
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
            ,url: '/user/list' //数据接口
            ,title: '用户表'
            ,page: true //开启分页
            ,toolbar: 'default'  //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            ,totalRow: true //开启合计行
            ,cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'id', title: 'ID', width:50, sort: true, fixed: 'left'}
                ,{field: 'username', title: '用户名'}
                ,{field: 'head_img',title:'头像',width:80,templet:'#img'}
                ,{field: 'nick_name', title: '昵称',hide:true}
                ,{field: 'sex',title:'性别'}
                ,{field: 'age',title:'年龄'}
                ,{field: 'education',title:'学历'}
                ,{field: 'company',title:'单位'}
                ,{field: 'phone',title:'手机号码'}
                ,{field: 'ID_cord',title:'身份证'}
                // ,{field: 'dutyid',title:'职务ID'}
                // ,{field: 'sympathyId',title:'慰问ID'}
                // ,{field: 'party_duty',title:'党内职务',width:125}
                ,{field: 'type_name',title:'政治面貌'}
                ,{field: 'difficulty_type',title:'困难情况'}
                ,{field: 'join_time',title:'入党时间'}
                ,{field: 'look',fixed: 'right',title:'编辑',width:200,templet:'#barDemo'}
            ]]
        });
        var $ = layui.$, active = {
            reload:function () {
                var username = $("#name_search").val();
                var dutyid = $("#type_name").val();
                var company=$("#company_search").val();

                table.reload('demo',{
                    method:'get',
                    where:{
                        username:username,
                        company:company,
                        dutyid: dutyid
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
                    onAddUserBtn(data);
                    // window.location.href='/user/gotoAdd';
                    break;
                case 'update':
                    if(data.length === 0){
                        layer.msg('请选择一行');
                    } else if(data.length > 1){
                        layer.msg('只能同时编辑一个');
                    } else {
                        console.log(data[0]);


                        if (data[0].party_groups_id != undefined){
                            var parameter = + data[0].id  + '&partyGroupsId='+data[0].party_groups_id;
                        }
                        if(data[0].party_team_id != undefined && data[0].party_groups_id != undefined){
                            var parameter =  + data[0].id  + '&partyGroupsId='+data[0].party_groups_id +'&partyTeamId='+data[0].party_team_id;
                        }
                        if(data[0].party_team_id == undefined && data[0].party_groups_id == undefined){
                            var parameter = + data[0].id;
                        }
                        window.location.href='/user/setUser?id=' + parameter;
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
                                url:"/user/logicDelete",
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
            if (layEvent === 'detail') {
                layer.msg('查看操作');
                onAddBtn(data);

            } else if (layEvent === 'addSympathy') {
                //如果没有sympathyId 则不传
                if (data.sympathyId == undefined){
                    var parameter = + data.id  + '&addSympathy=' + 1 ;
                }else {
                    var parameter = + data.id + '&sympathyId=' + data.sympathyId + '&addSympathy=' + 1 ;
                }
            window.location.href = '/user/setUser?id=' + parameter;
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
