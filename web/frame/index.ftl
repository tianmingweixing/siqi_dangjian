<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>思齐党建后台</title>
    <link rel="stylesheet" href="/js/layui/css/layui.css">
    <link rel="stylesheet" href="/css/public.css">

    <script src="/js/layui/layui.js"></script>
    <script src="/js/jquery/jquery-3.3.1.min.js"></script>
    <script src="/js/echarts.js"></script>

</head>
<body>
<div class="container">

    <div style="width: 90%">
        <blockquote class="layui-elem-quote layui-quote-nm" id="footer"
                    style="margin-top: 50px;margin-left: 5%;padding-left: 45px;border-color: #009688;color: #009688;font-weight: bold">

            <div class="info">支部名称 : <span id="name">暂无数据</span></div>
            <div class="info">成立时间 : <span id="founding_time">暂无数据</span></div>
            <div class="info">换届时间 : <span id="change_time">暂无数据</span></div>
            <div style="clear: left;"></div>
        </blockquote>
    </div>
    <div style="width: 85%; margin-left: 5%; margin-top: 20px;">

        <div class="layui-row layui-col-space5">
            <div class="layui-col-md6">
                <div id="total" style="width: 94%;height:400px;"></div>
            </div>
            <div class="layui-col-md6">
                <div id="teams" style="width: 94%;height:400px;"></div>
            </div>
        </div>

        <div class="layui-row layui-col-space5" style=" margin-top: 20px;">
            <div class="layui-col-md6">
                <div id="activity" style="width: 94%;height:400px;"></div>
            </div>
            <div class="layui-col-md6">
                <div id="meeting" style="width: 94%;height:400px;"></div>
            </div>
        </div>

    </div>




</div>

<script>


   /* $().ready(function () {


    })*/

   var option = {};

    // 基于准备好的dom，初始化echarts实例
    var total = echarts.init(document.getElementById('total'));
    var teams = echarts.init(document.getElementById('teams'));

   var activity = echarts.init(document.getElementById('activity'));
   var meeting = echarts.init(document.getElementById('meeting'));

   layui.use(['layer', 'element' ], function() {
       var layer = layui.layer //弹层
               , element = layui.element; //元素操作

       $.ajax({
           url: "/index/total",
           data: {
           },
           success: function (data) {

               if (data.code == 0) {
                   var dataArr = data.coum,info = data.data;

                   option = {
                       title : {
                           text: '党员统计',
                           x:'center'
                       },
                       xAxis: {
                           type: 'category',
                           data: dataArr
                       },
                       yAxis: {
                           type: 'value'
                       },
                       series: [{
                           data: info,
                           type: 'bar'
                       }]
                   };

                   total.setOption(option);
               } else {
                   layer.msg('获取党员数据失败', {icon: 2});
               }
           }
       })

       $.ajax({
           url: "/index/totalOfTeam",
           data: {
           },
           success: function (data) {
               // console.log(data);
               if (data.code == 0) {
                   var dataArr = data.coum, info = data.data;

                   option = {
                       title : {
                           text: '党小组人员统计',
                           x:'center'
                       },
                       xAxis: {
                           type: 'category',
                           data: dataArr
                       },
                       yAxis: {
                           type: 'value'
                       },
                       series: [{
                           data: info,
                           type: 'bar'
                       }]
                   };

                   teams.setOption(option);
               } else {
                   layer.msg('获取党小组党员数据失败', {icon: 2});
               }
           }
       })

       $.ajax({
           url: "/index/getActivityCountInfo",
           data: {
           },
           success: function (data) {
               // console.log(data);
               if (data.code == 0) {
                   var dataArr = data.coum, info = data.data;

                   // 指定图表的配置项和数据
                   option = {
                       title : {
                           text: '今年活动统计',
                           // subtext: '纯属虚构',
                           x:'center'
                       },
                       tooltip : {
                           trigger: 'item',
                           formatter: "{a} <br/>{b} : ({c}次) ({d}%)"
                       },
                       legend: {
                           orient: 'vertical',
                           left: 'left',
                           data: dataArr
                       },
                       series : [
                           {
                               name: '活动占比',
                               type: 'pie',
                               radius : '55%',
                               center: ['50%', '60%'],
                               /*data:[
                                   {value:335, name:'正式党员'},
                                   {value:310, name:'积极分子'},
                                   {value:234, name:'发展对象'},
                                   {value:135, name:'视频广告'},
                                   {value:1548, name:'搜索引擎'}
                               ],*/
                               data:info,
                               itemStyle: {
                                   emphasis: {
                                       shadowBlur: 10,
                                       shadowOffsetX: 0,
                                       shadowColor: 'rgba(0, 0, 0, 0.5)'
                                   }
                               }
                           }
                       ]
                   };

                   // 使用刚指定的配置项和数据显示图表。
                   activity.setOption(option);
               } else {
                   layer.msg('获取活动数据失败', {icon: 2});
               }
           }
       })

       $.ajax({
           url: "/index/getMeetingCountInfo",
           data: {
           },
           success: function (data) {
               // console.log(data);
               if (data.code == 0) {
                   var dataArr = data.coum, info = data.data;

                   // 指定图表的配置项和数据
                   option = {
                       title : {
                           text: '今年会议统计',
                           // subtext: '纯属虚构',
                           x:'center'
                       },
                       tooltip : {
                           trigger: 'item',
                           formatter: "{a} <br/>{b} : {c}次 ({d}%)"
                       },
                       legend: {
                           orient: 'vertical',
                           left: 'left',
                           data: dataArr
                       },
                       series : [
                           {
                               name: '会议占比',
                               type: 'pie',
                               radius : '55%',
                               center: ['50%', '60%'],
                               data:info,
                               itemStyle: {
                                   emphasis: {
                                       shadowBlur: 10,
                                       shadowOffsetX: 0,
                                       shadowColor: 'rgba(0, 0, 0, 0.5)'
                                   }
                               }
                           }
                       ]
                   };

                   // 使用刚指定的配置项和数据显示图表。
                   meeting.setOption(option);
               } else {
                   layer.msg('获取会议数据失败', {icon: 2});
               }
           }
       })

       $.ajax({
           url: "/index/getPartyInfo",
           data: {
           },
           success: function (data) {
               console.log(data);
               if (data.code == 0) {
                   console.log(data.info);
                   let ft = new Date(data.info.foundingTime);
                   let ct = new Date(data.info.changeTime);
                   var ftStr = ft.getFullYear() + "年"+(ft.getMonth()+1)+"月"+ft.getDate()+"日";
                   var ctStr = ct.getFullYear() + "年"+(ft.getMonth()+1)+"月"+ft.getDate()+"日";

                   $("#name").html(data.info.name);
                   $("#founding_time").html(ftStr);
                   $("#change_time").html(ctStr);

               } else {
                   layer.msg(data.msg, {icon: 2});
               }
           }
       })




   });












</script>

</body>
</html>