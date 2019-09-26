<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title> 会议表后台</title>

    <link rel="stylesheet" href="../../js/layui/css/layui.css">
    <link rel="stylesheet" href="../../css/public.css">
    <script src="../../js/layui/layui.js"></script>
    <script src="../../js/jquery/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../../js/layui/css/layui.css" media="all">
    <style>
        .layui-upload .mark_button {
            position: absolute;
            right: 15px;
        }

        .upload-img {
            position: relative;
            display: inline-block;
            width: 230px;
            height: 140px;
            margin: 0 10px 10px 0;
            transition: box-shadow .25s;
            border-radius: 4px;
            box-shadow: 0px 10px 10px -5px rgba(0, 0, 0, 0.5);
            transition: 0.25s;
            -webkit-transition: 0.25s;
            margin-top: 15px;
        }

        .layui-upload-img {
            width: 230px;
            height: 140px;
            border-radius: 4px;
        }

        .upload-img:hover {
            cursor: pointer;
            box-shadow: 0 0 4px rgba(0,0,0,1);
            transform: scale(1.2);
            -webkit-transform: scale(1.05);
        }

        .upload-img input {
            position: absolute;
            left: 0px;
            top: 0px;
        }

        .upload-img button {
            position: absolute;
            right: 0px;
            top: 0px;
            border-radius: 6px;
        }
    </style>

</head>
<body>
<div style="width:1100px; position: relative; left:17%;">

<form class="layui-form" action="">
    <div>

    <br>
    <div class="layui-form-item" style="display:none">
        <label class="layui-form-label" style="margin-left: 85px">会议ID
            <div class="layui-input-inline"><input id="id" name="id" type=""  value="<#if id??>${id}<#else></#if>"/></div>

        <label class="layui-form-label" style="margin-left: 85px">会议图片</label>
            <div class="layui-input-inline"><input type="" id="imgPath" name="imgPath" value="" style="width: 400px;"/></div>
    </div>

    <div class="layui-input-inline" style="display:none ">
        <label class="layui-form-label"  style="margin-left: 85px">会议分类id</label>
        <input id="meeting_type_id" name="meeting_type_id" maxlength="20" value="<#if meeting_type_id??>${meeting_type_id}<#else></#if>"/>
    </div>


    <div class="layui-form-item input_row_margin_top">

        <label class="layui-form-label ">会议名称</label>
        <div class="layui-input-inline">
            <input id="name" name="name" lay-verify="required" placeholder="请输入名称" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if name??>${name}<#else></#if>">
        </div>

        <label class="layui-form-label">组织生活记录</label>
        <div class="layui-input-inline">
            <select name="meeting_type" id="meeting_type">
                <option value="">全部</option>
            </select>
        </div>

    </div>

    <div class="layui-form-item input_row_margin_top">

        <label class="layui-form-label ">主持人</label>
        <div class="layui-input-inline">
            <input id="compere" name="compere" lay-verify="required" placeholder="请输入主持人" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if compere??>${compere}<#else></#if>">
        </div>

        <label class="layui-form-label ">记录人</label>
        <div class="layui-input-inline">
            <input id="recorder" name="recorder" lay-verify="required" placeholder="请输入记录人" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if recorder??>${recorder}<#else></#if>">
        </div>

    </div>

    <div class="layui-form-item input_row_margin_top">

        <label class="layui-form-label ">应到人数</label>
        <div class="layui-input-inline">
            <input id="people_counting" name="people_counting" lay-verify="required" placeholder="请输入应到人数" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if people_counting??>${people_counting}<#else></#if>">
        </div>

        <label class="layui-form-label ">实到人数</label>
        <div class="layui-input-inline">
            <input id="attendance" name="attendance" lay-verify="required" placeholder="请输入实到人数" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if attendance ??>${attendance}<#else></#if>">
        </div>

    </div>

    <div class="layui-form-item input_row_margin_top">

        <label class="layui-form-label " style="margin-left: 1px">开始时间</label>
        <div class="layui-input-inline">
            <input id="start_time" name="start_time" lay-verify="required" placeholder="请输入开始时间" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if start_time??>${start_time}<#else></#if>">
        </div>
        <div class="layui-form-mid layui-word-aux"></div>
        <label class="layui-form-label " style="margin-left: -10px">结束时间</label>
        <div class="layui-input-inline">
            <input id="end_time" name="end_time" lay-verify="required" placeholder="请输入结束时间" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if end_time??>${end_time}<#else></#if>">
        </div>

        <label class="layui-form-label ">地点</label>
        <div class="layui-input-inline">
            <input id="address" name="address" lay-verify="required" placeholder="请输入地点" maxlength="150"
                   autocomplete="off" style="display: block; width: 150%; padding-left: 8px;height: 38px;border-color: #D2D2D2!important; line-height: 1.3; line-height: 38px\9; border-width: 1px; border-style: solid; background-color: #fff; border-radius: 2px;" value="<#if address ??>${address}<#else></#if>">
        </div>

    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">会议内容</label>
        <div class="layui-input-block">
            <textarea name="content" id="content" placeholder="请输入会议内容"
                      style="width: 928px; border:1px solid #e6e6e6; font-size: 13px; line-height: 23px;color: #56aa17;
                              max-width: 1500px; height: 160px; max-height: 1000px; outline: 0;"><#if content??>${content}<#else></#if></textarea>
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">会议指导</label>
        <div class="layui-input-block">
                <textarea name="guide" id="guide" placeholder="请输入会议指导"
                          style="width: 928px; border:1px solid #e6e6e6; font-size: 13px; line-height: 23px;color: #56aa17;
                              max-width: 1500px; height: 50px; max-height: 1000px; outline: 0;"><#if guide??>${guide}<#else></#if></textarea>
        </div>
    </div>

    <#if id??>
        <div class="layui-form-item layui-form-text" >
            <label class="layui-form-label">会议签到</label>
            <div class="layui-input-inline">
                    <textarea name="userName" id="userName" placeholder="会议签到" readonly
                              style="width:928px; border:1px solid #e6e6e6; font-size: 13px; line-height: 23px;color: #56aa17;
                                  max-width: 1500px; height: 40px; max-height: 1000px; outline: 0;"><#if userName??>${userName}<#else></#if></textarea>
            </div>
            <div class="layui-input-inline">
                <a class="layui-btn layui-btn-sm layui-btn-normal" style="margin-left: 116px;" onclick="addSignIn()">签到管理</a>
            </div>
        </div>
    <#else>
    </#if>

        <div class="layui-upload ">

            <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: -40px;border: none">

                <div class="layui-upload-list" id="imgs" style="margin-left: 100px;" >

                </div>
            </blockquote>

            <div class="mark_button" style="    margin-top: -12px; width: 88%;">
                <button type="button" class="layui-btn layui-btn-normal" id="sele_imgs">选择会议图片</button>
<!--
                <button type="button" class="layui-btn" id="upload_imgs" disabled>开始上传</button>
-->
                <button type="button" class="layui-btn layui-btn-danger" id="dele_imgs">删除选中图片</button>
            </div>

        </div>

<br><br><br>

        <div class="layui-form-item input_row_margin_top">
            <div class="layui-input-block">
                <button  class="layui-btn"  lay-submit lay-filter="formDemo">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>

    </div>
</form>

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
            <label class="layui-form-label ">会议名称</label>
            <div class="layui-input-inline">
                <input type="text" id="name" name="name" readonly  placeholder="会议名称" autocomplete="off" class="layui-input">
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
        <button class="layui-btn layui-btn-normal" onclick="sign_in()">添加签到</button>
        <button class="layui-btn layui-btn-danger" onclick="cancelSignIn()">取消签到</button>
    </div>

</div>


<script type="text/javascript" src="/js/layui/layui.all.js"></script>

<script id="img_template" type="text/html">
    <div class="upload-img" filename="{{ d.index }}">
        <input type="checkbox" name="" lay-skin="primary">
        <img src="{{  d.result }}" alt="{{ d.name }}" class="layui-upload-img">
    </div>
</script>

<script>
    var meetingTypeId = <#if meeting_type_id??>"${meeting_type_id}"<#else>""</#if>;
    var images = <#if images_a??>${images_a}<#else>""</#if>;
    var deletedPathArr = [];
    var resImgPathArr = [];
    var indexArr = [];
    var finishImgPathArr = [];



    function addSignIn(){
        document.fileForm.name.value = $("#name").val();
        document.fileForm.meetingId.value = $("#id").val();

        layer.open({
            type: 1
            ,title: '签到管理 ' //不显示标题栏
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


    function cancelSignIn(){
        var meeting_id = document.fileForm.meetingId.value;
        var user_id =  document.fileForm.userId.value;

        $.ajax({
            url: "/meeting/cancelSignIn",
            type : 'post',
            data :{
                meeting_id : meeting_id,
                user_id : user_id
            },
            success : function(data){
                if(data.msg == "该用户没有签到"){
                    layer.open({
                        icon: 2,
                        title: '消息提醒',
                        content: data.msg,
                        skin:'layui_open_fail'
                    });
                } else if(data.result == "fail"){
                    layer.open({
                        icon: 2,
                        title: '消息提醒',
                        content: data.msg,
                        skin:'layui_open_fail'
                    });
                } else {
                    layer.msg('取消签到成功', {icon: 1});
                    setTimeout(function () {
                        location.reload()
                    },1000)
                }
            }
        });
    }

function sign_in(){
        var meeting_id = document.fileForm.meetingId.value;
        var user_id =  document.fileForm.userId.value;

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
                        content: '添加签到失败',
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
                    layer.msg('添加签到成功', {icon: 1});
                    setTimeout(function () {
                        location.reload()
                    },1000)
                }
            }
        });
    }


        layui.use(['laydate', 'laytpl','form','upload'], function () {
            var form = layui.form
                    , laydate = layui.laydate //日期
                    ,layer = layui.layer //弹层
                    , laytpl = layui.laytpl
                    ,$ = layui.jquery
                    , upload = layui.upload;

            laydate.render({
                elem: '#start_time' //指定元素
            });
            laydate.render({
                elem: '#end_time' //指定元素
            });





            var imgFiles;

            //多图片上传
            var uploadInst = upload.render({
                elem: '#sele_imgs'  //开始
                , acceptMime: 'image/*'
                , url: '/upload/uploadImage'
                , auto: true
                , bindAction: '#upload_imgs'
                , multiple: true
                , size: 1024 * 12
                , choose: function (obj) {  //选择图片后事件
                    var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
                    imgFiles = files;

                    $('#upload_imgs').prop('disabled', false);

                    //预读本地文件示例，不支持ie8
                    obj.preview(function (index, file, result) {
                        var data = {
                            index: index,
                            name: file.name,
                            result: result
                        };

                        indexArr.push(data.index);
                        console.log(indexArr);

                        //将预览html 追加
                        laytpl(img_template.innerHTML).render(data, function (html) {
                            $('#imgs').append(html);
                        });

                        //绑定单击事件
                        $('#imgs div:last-child>img').click(function () {
                            var isChecked = $(this).siblings("input").prop("checked");
                            $(this).siblings("input").prop("checked", !isChecked);
                            return false;
                        });

                    });
                }
                , before: function (obj) { //上传前回函数
                    layer.load(); //上传loading
                }
                , done: function (res, index, upload) {    //上传完毕后事件

                    resImgPathArr.push(res.src);

                    layer.closeAll('loading'); //关闭loading

                    //上传完毕
                    //  $('#imgs').html("");//清空操作

                    layer.msg("上传成功！");

                    return delete imgFiles[index]; //删除文件队列已经上传成功的文件

                }
                , error: function (index, upload) {

                    layer.closeAll('loading'); //关闭loading

                    top.layer.msg("上传失败！");

                }
            });




            $(function () {
                for (let i = 0; i < images.length ; i++) {
                    var data = {
                        index: images[i].index,
                        name: images[i].url,
                        result: images[i].url
                    };

                    if(data.name != ""){
                        //将预览html 追加
                        laytpl(img_template.innerHTML).render(data, function (html) {
                            $('#imgs').append(html);
                        });
                    }
                }

                //绑定单击事件
                $('#imgs div img').click(function () {
                    var isChecked = $(this).siblings("input").prop("checked");
                    $(this).siblings("input").prop("checked", !isChecked);
                    return false;
                });

            });

            //查询计划种类
            $.ajax({
                url: "/meetingType/allCategory",
                async: false,
                success: function (data) {
                    $.each(data.list, function (i, item) {
                        if(item.id == meetingTypeId){
                            $("#meeting_type").append("<option selected='selected' value='" + item.id + "'>" + item.type_name + "</option>");
                        }else{
                            $("#meeting_type").append("<option  value='" + item.id + "'>" + item.type_name + "</option>");
                        }
                    });
                    form.render('select');
                }
            });


                //批量删除 单击事件
                $('#dele_imgs').click(function () {
                    $('input:checked').each(function (index, value) {
                        var filename=$(this).parent().attr("filename");
                        if(indexArr.includes(filename)){

                        }else{
                            if(imgFiles != undefined || imgFiles != null ){
                                delete imgFiles[filename];
                            }
                            $(this).parent().remove()
                        }

                    });

                });



            form.on('submit(formDemo)', function () {
                $("div[class=upload-img] img").each(function() {
                    var src=$(this).attr("src");
                    var patrn=/^([/0-9a-zA-Z_.]+)?$/;

                    if(patrn.exec(src)){
                        deletedPathArr.push(src);
                    }
                });

                    finishImgPathArr.push(resImgPathArr);
                    finishImgPathArr.push(deletedPathArr);
/*
                    alert("resImgPathArr: "+resImgPathArr);
                    alert("finishImgPathArr: "+finishImgPathArr);*/

                $.ajax({
                    url: "/meeting/addMeeting",
                    type:'post',
                    data: {
                        id: $("#id").val(),
                        compere: $("#compere").val(),
                        name: $("#name").val(),
                        recorder: $("#recorder").val(),
                        people_counting: $("#people_counting").val(),
                        attendance: $("#attendance").val(),
                        address: $("#address").val(),
                        meeting_type_id: $("#meeting_type").val(),
                        content: $("#content").val(),
                        guide: $("#guide").val(),
                        imgPath: finishImgPathArr.toString(),
                        start_time: $("#start_time").val(),
                        end_time: $("#end_time").val()
                    },
                    success: function () {
                        layer.msg('保存成功', {icon: 1});
                        setTimeout(function () {
                            window.location.href = '/frame/meetingList.ftl'
                        }, 1500);
                    }
                });
                return false;
            });

        });


</script>

</body>
</html>