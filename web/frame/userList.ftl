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
            <input type="text" id="company"  placeholder="单位" autocomplete="off" class="layui-input">
        </div>
    </div>
    <!--<div class="layui-form-item">
        <label class="layui-form-label label_width_100">联系电话</label>
        <div class="layui-input-inline">
            <input type="text" id="phone_search"  placeholder="联系电话" autocomplete="off" class="layui-input">
        </div>
    </div>-->
</form>

<div id="lookDetail" style="display: none;padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">
    <form class="layui-form" id="add-form" action="">
        <input type="hidden" id="id" required value="" style="width: 240px" lay-verify="required" readonly
               autocomplete="off" class="layui-input">

        <div class="layui-form-item center">
            <label class="layui-form-label " style="margin-left: 1px; margin-top: 10px">用户头像</label>
            <div class="layui-input-inline" style="padding-top: 10px; margin-top: 10px">
                <img id="headImg" name ="headImg" src="" height="auto" width="100" alt="" style="margin-top: 10px">
            </div>
        </div>

        <div class="layui-form-item center">
            <label class="layui-form-label" style="width: 100px">用户名</label>
            <div class="layui-input-block">
                <input type="text" id="username" required  style="width: 240px" lay-verify="required"
                       readonly  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">手机号码</label>
            <div class="layui-input-block">
                <input type="text" id="set_credit_phone" required style="width: 240px" lay-verify="required"
                       readonly   autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">地址</label>
            <div class="layui-input-block">
                <input type="text" id="set_credit_address" required style="width: 240px" lay-verify="required"
                       readonly autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">申请理由</label>
            <div class="layui-input-block">
                <textarea id="set_appReason" readonly style="width:240px;height:100px;" ></textarea>
            </div>
        </div>

    </form>
</div>


<div class="layui-input-inline search_div" style="margin-left: 110px">
    <button class="layui-btn" data-type="reload">提交</button>
    <button onclick="reset_search()" class="layui-btn layui-btn-primary">重置</button>
</div>


<table class="layui-hide" id="demo" lay-filter="test"></table>

<script type="text/html" id="barDemo1">
    <a class="layui-btn layui-btn-primary layui-btn-xs"  lay-event="add">添加</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-xs" lay-event="delete">删除</a>
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
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
//'<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">username</div>'
    function onAddBtn(data){

        $("#username").val(data.username);
        $("#age").val(data.age);
        $("#sex").val(data.sex);
        $("#address").val(data.address);
        $("#unit").val(data.unit);
        $("#phone").val(data.phone);
        $("#foundingTime").val(data.foundingTime);
        $("#head_img").attr(src,data.headImg);


        //示范一个公告层
        layer.open({
            type: 1
            ,title: false //不显示标题栏
            ,closeBtn: false
            ,area: '1200px;'
            ,shade: 0.8
            ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
            ,btn: ['返回页面']
            ,btnAlign: 'c'
            ,moveType: 1 //拖拽模式，0或者1
            ,content: $("#lookDetail")
            ,success: function(layero){
                var btn = layero.find('.layui-layer-btn');
                btn.find('.layui-layer-btn0').attr({
                    target: '_blank'
                });
            }
        });
    }

    layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'element', ], function(){
        var laydate = layui.laydate //日期
                ,laypage = layui.laypage //分页
                ,layer = layui.layer //弹层
                ,table = layui.table //表格
                ,element = layui.element;//元素操作

        element.on('tab(demo)', function(data){
            layer.tips('切换了 '+ data.index +'：'+ this.innerHTML, this, {
                tips: 1
            });
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
                ,{field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
                ,{field: 'username', title: '昵称', width:80}
                ,{field: 'sex',title:'性别',width:80}
                ,{field: 'age',title:'年龄',width:80}
                ,{field: 'education',title:'学历',width:100,sort: true}
                ,{field: 'company',title:'单位',width:200,sort: true}
                ,{field: 'phone',title:'手机号码',width:200,sort: true}
                ,{field: 'ID_cord',title:'身份证',width:200,sort: true}
                ,{field: 'join_time',title:'入党时间',width:200,sort: true}
                // ,{field: 'address',title:'地址',width:200,sort: true}
                ,{field: 'head_img',title:'头像',width:200,sort: true}
                ,{field: 'look',title:'查看详情',width:150,templet:'#barDemo'}
                // ,{field: 'birth', title: '出生日期', width:200}
                // ,{field: 'userno',title:'用户编号',width:200,sort: true}
            ]]
        });
        var $ = layui.$, active = {
            reload:function () {
                var username = $("#name_search").val();
                var company=$("#company").val();

                table.reload('demo',{
                    method:'get',
                    where:{
                        username:username,
                        company:company
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
                     window.location.href='/user/gotoAdd';
                    break;
                case 'update':
                    if(data.length === 0){
                        layer.msg('请选择一行');
                    } else if(data.length > 1){
                        layer.msg('只能同时编辑一个');
                    } else {
                        layer.msg('data[0].id');
                        window.location.href='/user/setUser?id='+data[0].id;
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
            if(layEvent === 'detail'){
                layer.msg('查看操作');
                onAddBtn(data);

            } else if(layEvent === 'edit'){
                 layer.msg('edit');
                 console.log(data);
                     window.location.href='';
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
