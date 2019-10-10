<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>入党申请管理</title>
    <link rel="stylesheet" href="/js/layui/css/layui.css">
    <script src="/js/layui/layui.js"></script>
    <script src="../js/jquery/jquery-3.3.1.min.js"></script>

</head>

<body>

<form class="layui-form" style="margin-top: 10px">
    <div class="layui-form-item">

        <label class="layui-form-label ">手机号码</label>
        <div class="layui-input-inline">
            <input type="text" id="phone_search"  placeholder="请输入手机号码" autocomplete="off" class="layui-input">
        </div>
        <label class="layui-form-label ">用户名</label>
        <div class="layui-input-inline">
            <input type="text" id="userName_search"  placeholder="请输入用户名" autocomplete="off" class="layui-input">
        </div>

    </div>
    <div class="layui-form-item">
        <label class="layui-form-label ">审核状态</label>
        <div class="layui-input-inline">
            <select name="status_search" id="status_search">
                <option value=""></option>
                <option value="0">待审核</option>
                <option value="1">已通过</option>
                <option value="2">已拒绝</option>
            </select>
        </div>

        <label class="layui-form-label ">日期</label>
        <div class="layui-input-inline"> <!-- 注意：这一层元素并不是必须的 -->
            <input type="text" class="layui-input"  placeholder="起始日期" id="start_time_search">
        </div>
        <div class="layui-form-mid layui-word-aux">至</div>
        <div class="layui-input-inline" style="margin-left: 15px"> <!-- 注意：这一层元素并不是必须的 -->
            <input type="text" class="layui-input"  placeholder="结束日期" id="end_time_search">
        </div>
    </div>



</form>

<div class="layui-input-inline search_div" style="margin-left: 110px">
    <button class="layui-btn" data-type="reload">提交</button>
    <button onclick="reset_search()" class="layui-btn layui-btn-primary">重置</button>
</div>

<div id="audit" style="display: none; line-height: 22px; font-weight: 300;">
    <form class="layui-form" id="add-form" action="">
        <input type="hidden" id="set_credit_id" required value="" style="width: 240px" lay-verify="required" readonly
               autocomplete="off" class="layui-input">
        <div class="layui-form-item center" style="margin-top: 12px">
            <label class="layui-form-label" style="width: 100px">用户名</label>
            <div class="layui-input-block">
                <input type="text" id="set_credit_userName" required value="" style="width: 240px" lay-verify="required"
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
            <label class="layui-form-label" style="width: 100px">拒绝理由</label>
            <div class="layui-input-block">
                <textarea id="set_credit_reason" style="width:240px;height:100px;" placeholder="请输入拒绝理由"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" type="button" onclick="set_status(1)">通过</button>
                <button type="reset" class="layui-btn layui-btn-primary" onclick="set_status(2)" id="closeBtn">拒绝</button>
                <button type="reset" class="layui-btn layui-btn-primary" onclick="close_self(0)" id="closeBtn">取消</button>
            </div>
        </div>
    </form>
</div>

<table class="layui-hide" id="demo" lay-filter="test"></table>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="pass">通过</a>
    <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="end">拒绝</a>
</script>

<style>
    .layui_open_fail {
        text-align: center;
        border-radius: 5px;
    }
</style>


<script src="../js/layui/layui.js"></script>

<!--<div id="layer-photos-demo" class="layer-photos-demo">
    <img layer-pid="图片id，可以不写" layer-src="大图地址" src="缩略图" alt="图片名">
    <img layer-pid="图片id，可以不写" layer-src="大图地址" src="缩略图" alt="图片名">
</div>-->

<script type="text/html" id="img" class="layer-photos-demo">
    <div id="layer-photos-demo" class="layer-photos-demo"><img id="headUrlImg" style="height:35px;width:35px;border-radius:50%;line-height:50px!important;" layer-src="{{d.head_img}}" src="{{d.head_img}}" alt="{{d.head_img}}"></div>
</script>

<script type="text/html" id="app_form">
    {{# var srr=d.app_form.split(",");
    for(var j in srr) { srr[j] }}
    <div style="margin:0 10px; display:inline-block !important; display:inline;  max-width:70px; max-height:50px;">
        <img style=" max-width:70px; max-height:50px;" src="{{srr[j]}}" />
    </div>
    {{# } }}

</script>

<script type="text/html" id="main" >
</script>

<script>
    layui.config({
        version: '1551352891272' //为了更新 js 缓存，可忽略
    });

    function reset_search() {
        window.location.reload();
    }




    function onPassBtn(data) {
        $.ajax({
            url: "/applicationForm/changeStatus",//请求地址
            type: "POST",//请求方式
            dataType: "JSON",//返回数据类型
            data: {
                id:data.id,
                status: 1
            },//发送的参数
            success: function (data) {
                if(data.result=="success"){
                    // layer.msg('保存成功', {icon: 1});
                    setTimeout(function(){
                        window.location.reload();
                    }, 1500);
                }
            }
        });

        layer.msg('已通过审核', {icon: 6});

    }



    function onEndBtn(data) {

        layer.prompt({
            formType: 2,
            value: '请输入拒绝理由',
            title: '拒绝理由',
            area: ['350px', '200px'] //自定义文本域宽高
        }, function(value, index, elem){
            // alert(data.id); //得到value

            $.ajax({
                url: "/applicationForm/changeStatus",//请求地址
                type: "POST",//请求方式
                dataType: "JSON",//返回数据类型
                data: {
                    id:data.id,
                    refuse_reason:value,
                    status: 2
                },//发送的参数
                success: function (data) {
                    if(data.result=="success"){
                        layer.msg('保存成功', {icon: 1});
                        setTimeout(function(){
                            window.location.reload();
                        }, 1500);
                    }
                }

            });
            // layer.close(index);
        });
    }

    //#3
   /* function onAddBtn(id, name, phone, refuse_reason,set_appReason,adrress) {
        $("#set_credit_id").val(id);
        $("#set_credit_phone").val(phone);
        $("#set_credit_userName").val(name);
        $("#set_credit_reason").val(refuse_reason);
        $("#set_appReason").val(set_appReason);
        $("#set_credit_address").val(adrress);


        //页面层-自定义
        layer.open({
            type: 1,
            title: "审核",
            shadeClose: true,
            shade: false,
            offset: 'default',
            maxmin: true, //开启最大化最小化按钮
            area: ['741px', '400px'],
            content: $("#audit"),
            success: function (layero, index) {
            },
            yes: function (index, layero) {

            }
        });
    }*/

    layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'element',], function () {
        var laydate = layui.laydate //日期
                , laypage = layui.laypage //分页
                , layer = layui.layer //弹层
                , table = layui.table //表格
                , element = layui.element;//元素操作


        element.on('tab(demo)', function (data) {
            layer.tips('切换了 ' + data.index + '：' + this.innerHTML, this, {
                tips: 1
            });
        });

        //执行一个 table 实例
        table.render({
            elem: '#demo'
            ,height: 563
            ,url: '/applicationForm/getApplicationFormList' //数据接口
            ,title: '申请表'
            ,page: true //开启分页
            ,toolbar: 'default' //开启工具栏，此处显示默认图标,可以自定义模板,详见文档
            ,totalRow: true //开启合计行
            //表头
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
                ,{field: 'user_name', title: '用户名称'}
                ,{field: 'head_img', title: '用户头像',width:100,templet:'#img'}
                ,{field: 'phone',title:'号码'}
                ,{field: 'review_time',title:'审核时间'}
                ,{field: 'app_form',title:'申请表',templet:'#app_form'}
                ,{field: 'refuse_reason',title:'拒绝理由'}
                ,{field: 'type_name',title:'政治面貌'}
                ,{field: 'ReviewStatus',title:'审核状态'}
                ,{field: '',title:'审核',width:200,templet: '#barDemo'}
            ]]
        });

        var $ = layui.$, active = {
            reload: function () {
                var status = $("#status_search").val();
                var user_name = $("#userName_search").val();
                var start_time = $("#start_time_search").val();
                var end_time = $("#end_time_search").val();
                var phone = $("#phone_search").val();
                table.reload('demo', {
                    method: 'get',
                    where: {
                        status: status,
                        user_name: user_name,
                        start_time: start_time,
                        end_time: end_time,
                        phone: phone
                    }
                });
            }
        };
        $('.search_div .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });



        //监听工具栏事件4
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id)
                    ,data = checkStatus.data; //获取选中的数据
            switch(obj.event){
                case 'add':
                     // window.location.href='/frame/applicationForm_Add.ftl';
                    break;
                case 'update':
                    /*if(data.length === 0){
                        layer.msg('请选择一行');
                    } else if(data.length > 1){
                        layer.msg('只能同时编辑一个');
                    } else {
                        window.location.href='/applicationForm/setApplicationForm?id='+data[0].id;
                    }
                    break;*/
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
                                url:"/applicationForm/delete",
                                data:{
                                    deleteArray:JSON.stringify(a)
                                },
                                success:function (data) {
                                    console.warn(data);
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

        //监听行工具事件4
        table.on('tool(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                    , layEvent = obj.event; //获得 lay-event 对应的值
            if (layEvent === 'edit') {
                onAddBtn(data.id, data.user_name,data.phone,data.refuse_reason);
            }
            if (layEvent === 'detail') {
                onDetailBtn(data);
            }

            if (layEvent === 'pass') {
                onPassBtn(data);
            }

            if (layEvent === 'end') {
                onEndBtn(data);
            }
        });


        //分页
        laypage.render({
            elem: 'pageDemo' //分页容器的id
            , count: 100 //总页数
            , skin: '#1E9FFF' //自定义选中色值
            //,skip: true //开启跳页
            , jump: function (obj, first) {
                if (!first) {
                    layer.msg('第' + obj.curr + '页', {offset: 'b'});
                }
            }
        });

        function onDetailBtn(data) {
            $("#main").html("");
            var srr=data.app_form.split(",");

            for(var i=0;i<srr.length;i++){
                if(srr[i] != ""){
                    $("#main").append('<img style="margin-bottom:10px;max-width: 240px;" class="list" id="list'+i+'" src='+srr[i]+' />')
                }
            }

            layer.open({
                type: 1,
                title: false,
                closeBtn: 0,
                area: '240px',
                skin: 'layui-layer-nobg', //没有背景色
                shadeClose: true,
                content: $('#main')
            });

            //调用示例
            layer.photos({
                photos: '#main'
                ,anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
            });
        }

    });
</script>
</body>
</html>
