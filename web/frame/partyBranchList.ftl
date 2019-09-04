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
        <label class="layui-form-label ">党支部编号</label>
        <div class="layui-input-inline">
            <input type="text" id="partyNo"  placeholder="党支部编号" autocomplete="off" class="layui-input">
        </div>
    </div>

</form>
<div class="layui-input-inline search_div" style="margin-left: 110px">
    <button class="layui-btn" data-type="reload">提交</button>
    <button onclick="reset_search()" class="layui-btn layui-btn-primary">重置</button>
</div>


<table class="layui-hide" id="demo" lay-filter="test"></table>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
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
            ,toolbar: true  //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            ,totalRow: true //开启合计行
            ,cols: [[ //表头
                 {type: 'checkbox', fixed: 'left'}
                ,{field: 'id', title: 'ID', width:100, sort: true, fixed: 'left'}
                ,{field: 'name', title: '党支部名称', width:200}
                ,{field: 'party_member_count', title: '党员总数', width: 100}
                ,{field: 'duty', title: '党支部职责', width:150,sort: true}
                ,{field: 'party_no',title:'党支部编号',width:200}
                ,{field: 'party_info',title:'党支部简介',width:200,sort: true}
                ,{field: 'founding_time',title:'成立时间',width:150,sort: true}
                ,{field: 'change_time',title:'换届时间',width:150,sort: true}
                ,{field: 'activity_area',title:'活动面积',width:100,sort: true}
                ,{field: 'party_img',title:'党支部图片',width:150}
                ,{field: 'edit',title:'编辑',width:200,templet: '#barDemo'}
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

        //监听行工具事件
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                    ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'detail'){
                layer.msg('查看操作');
            } else if(layEvent === 'edit'){
                    window.location.href='/partyBranch/setPartBranch?id='+data.id;
            }else if(layEvent === 'add'){
                layer.msg('add');
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
