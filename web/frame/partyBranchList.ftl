<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>支部信息</title>
    <link rel="stylesheet" href="/js/layui/css/layui.css">
    <script src="/js/layui/layui.js"></script>
    <script src="../js/jquery/jquery-3.3.1.min.js"></script>

</head>

<body>
<form class="layui-form" style="margin-top: 10px">
    <div class="layui-form-item">
        <label class="layui-form-label label_width_100">党支部名称</label>
        <div class="layui-input-inline">
            <input type="text" id="name_search"  placeholder="党支部名称" autocomplete="off" class="layui-input">
        </div>
      <!--  <label class="layui-form-label ">党支部编号</label>
        <div class="layui-input-inline">
            <input type="text" id="partyNo"  placeholder="党支部编号" autocomplete="off" class="layui-input">
        </div>-->
    </div>

</form>
<div class="layui-input-inline search_div" style="margin-left: 110px">
    <button class="layui-btn" data-type="reload">提交</button>
    <button onclick="reset_search()" class="layui-btn layui-btn-primary">重置</button>
</div>


<div style="margin: 0 10px;">
    <table class="layui-hide" id="demo" lay-filter="test"></table>
</div>

<script type="text/html" id="barDemo1">
    <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="edit">编辑</a>
</script>
<style>

    .party_img{
        height: 100%;
        width: 100%;
    }

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
            ,url: '/partyBranch/list' //数据接口
            ,title: '用户表'
            ,page: true //开启分页
            ,toolbar: 'default'  //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            ,totalRow: true //开启合计行
            ,cols: [[ //表头
                 {type: 'checkbox', fixed: 'left'}
                ,{field: 'id', title: 'ID', width:100, sort: true, fixed: 'left'}
                ,{field: 'name', title: '党支部名称'}
                ,{field: 'party_img',title:'党支部图片',templet: function(d){return '<div><img src="'+d.party_img+'" alt="" width="50%" height="50%"></div>';}}
                ,{field: 'UserCount', title: '成员总数'}
                ,{field: 'duty', title: '党支部职责'}
                ,{field: 'party_no',title:'党支部编号',hide:true}
                ,{field: 'party_info',title:'党支部简介'}
                ,{field: 'founding_time',title:'成立时间',}
                ,{field: 'change_time',title:'换届时间',}
                ,{field: 'activity_area',title:'活动面积(m²)',}
                ,{field: 'edit',fixed:'right',title:'操作',width:200,templet: '#barDemo1'}
            ]]
        });




        var $ = layui.$, active = {
            reload:function () {
                var name = $("#name_search").val();
                var partyNo=$("#partyNo").val();
                var start_time_search=$("#start_time_search").val();
                var end_time_search=$("#end_time_search").val();
                table.reload('demo',{
                    method:'get',
                    where:{
                        name:name,
                        partyNo:partyNo,
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


        //监听头工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id)
                ,data = checkStatus.data; //获取选中的数据
            switch(obj.event){
                case 'add':
                    window.location.href='/partyBranch/setPartyBranch';
                    break;
                case 'update':
                    if(data.length === 0){
                        layer.msg('请选择一行');
                    } else if(data.length > 1){
                        layer.msg('只能同时编辑一个');
                    } else {
                        layer.msg('data[0].id');
                        window.location.href='/partyBranch/setPartyBranch?id='+data[0].id;
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
                                url:"/partyBranch/logicDelete",
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
                    window.location.href='/partyBranch/setPartyBranch?id='+data.id;
            }else if(layEvent === 'add'){
                layer.msg('add');
            }
        });

        //自定义样式
        laypage.render({
            elem: 'demo2'
            ,count: 100
            ,theme: '#1E9FFF'
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
