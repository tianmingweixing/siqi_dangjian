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

</head>
<body>

<form class="layui-form" action="">

    <div style="width: 90%">
        <blockquote class="layui-elem-quote layui-quote-nm" id="footer"
                    style="margin-top: 50px;margin-left: 5%;padding-left: 45px;border-color: #009688;color: #009688;font-weight: bold">
            添加活动
        </blockquote>
    </div>

    <div style="width: 90%">
        <div class="container_div">

            <div class="layui-form-item input_row_margin_top">
                <label class="layui-form-label ">活动标题</label>
                <div class="layui-input-inline">
                    <input id="title" name="title" lay-verify="required" placeholder="请输入活动标题" maxlength="20"
                           autocomplete="off" class="layui-input" value="<#if title??>${title}<#else></#if>">
                </div>
                <label class="layui-form-label" >活动类型</label>
                <div class="layui-input-inline">
                    <select id="type_search" name="type_search" lay-verify="">
                        <option value="">请选择</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item input_row_margin_top">
                <label class="layui-form-label ">开始时间</label>
                <div class="layui-input-inline">
                    <input id="start_time" name="start_time" lay-verify="text" placeholder="开始时间"
                           autocomplete="off" class="layui-input" value="<#if start_time??>${start_time}<#else></#if>">
                </div>
                <label class="layui-form-label ">结束时间</label>
                <div class="layui-input-inline">
                    <input id="end_time" name="end_time" lay-verify="text" placeholder="结束时间"
                           autocomplete="off" class="layui-input" value="<#if end_time??>${end_time}<#else></#if>">
                </div>
            </div>

            <div class="layui-form-item input_row_margin_top">
                <label class="layui-form-label">活动状态</label>
                <div class="layui-input-inline">
                    <select id="is_end" name="is_end" lay-filter="select_first_category">
                        <option value='0'>进行中</option>
                        <option value='1'>已结束</option>
                    </select>
                </div>

                <label class="layui-form-label" >活动品牌</label>
                <div class="layui-input-inline">
                    <select id="brand_search" name="brand_search" lay-verify="">
                        <option value="">请选择</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item layui-form-text input_row_margin_top">
                <label class="layui-form-label">活动内容</label>
                <div class="layui-input-block">
            <textarea name="content" id="content" placeholder="请输入活动内容" style="width: 500px; height: 200px;" maxlength="250"
                      class="layui-textarea"><#if content??>${content}<#else></#if></textarea>
                </div>
            </div>



            <div class="layui-form-item input_row_margin_top">
                <div class="layui-upload" style="width: 620px;">
                    <button type="button" class="layui-btn layui-btn-normal" id="testList">选择多文件</button>
                    <button type="button" class="layui-btn" id="testListAction">开始上传</button>
                    <div class="layui-upload-list">
                        <table class="layui-table">
                            <thead>
                            <tr>
                                <th>图片</th>
                                <th>文件名</th>
                                <th>地址</th>
                                <th>状态</th>
                                <th>操作</th>
                            </tr></thead>
                            <tbody id="demoList"></tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div class="layui-form-item input_row_margin_top">
                <div class="layui-input-block" style="margin-left: 0;">
                    <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>


        </div>
    </div>
</form>

<script>
    var id =<#if id??>"${id}"<#else>""</#if>;
    var category =<#if typeId??>${typeId}<#else>0</#if>;
    var count = <#if imgarr??>${imgarr?size}<#else >0</#if>;
    var pathArr = [];

    /*$().ready(function () {
        //初始化信息//
        if (id != "")
            //initComInfo(id);

    })*/

    function initComInfo (id){
        var $ = jQuery;
        $.ajax({
            url: "/activities/getActivities",
            method: "GET",
            data: {
                id:id
            },
            success: function (data) {
                if (data.result == 'success') {
                    console.log(data.list);
                    var urlArr = data.list;

                    if (urlArr) {
                        imgArr = urlArr;
                        $("#headImg").val(imgArr);
                    }

                } else {
                    layer.msg(data.result, {icon: 2});
                }
            }
        })
    }

   /* function delectImg(e){
        var $ = jQuery;
        var $li = e.parent();
        imgArr.splice($($li).index(),1);
        $li.remove();
        $("#headImg").val(imgArr);
    }*/

    function initSelectOfType(form){
        //初始化下活动类型下拉框
        $.ajax({
            url: "/activityType/selectTypeList",//请求地址
            type: "GET",//请求方式
            dataType: "JSON",//返回数据类型
            data: {},//发送的参数
            success: function (data) {
                console.log(data)
                $.each(data.list, function (index, item) {
                    if (item.id == category)
                        $("#type_search").append("<option selected='selected' value='" + item.id + "'>" + item.typeName + "</option>")
                    else
                        $("#type_search").append("<option  value='" + item.id + "'>" + item.typeName + "</option>")
                });
                form.render('select');
            },
            error: function () {
                //失败执行的方法
                layer.msg("查询出错");
            }
        });
    }

    function initSelectOfBrand(form){
        //初始化下活动品牌下拉框
        $.ajax({
            url: "/activtiesBrand/selectBrandList",//请求地址
            type: "GET",//请求方式
            dataType: "JSON",//返回数据类型
            data: {},//发送的参数
            success: function (data) {
                console.log(data)
                $.each(data.list, function (index, item) {
                    if (item.id == category)
                        $("#brand_search").append("<option selected='selected' value='" + item.id + "'>" + item.brandName + "</option>")
                    else
                        $("#brand_search").append("<option  value='" + item.id + "'>" + item.brandName + "</option>")
                });
                form.render('select');
            },
            error: function () {
                //失败执行的方法
                layer.msg("查询出错");
            }
        });
    }

    function removeByValue(arr, val) {
        for(var i = 0; i < arr.length; i++) {
            if(arr[i] == val) {
                arr.splice(i, 1);
                break;
            }
        }
    }

    layui.use(['laydate', 'laypage', 'layer', 'table', 'form', 'element','upload'], function () {
        var $ = jQuery;
        var form = layui.form;
        var laydate = layui.laydate //日期
                , layer = layui.layer //弹层
                , element = layui.element//元素操作
                , upload = layui.upload;

        laydate.render({
            elem: '#start_time' //指定元素
            ,type: 'datetime'
        });
        laydate.render({
            elem: '#end_time' //指定元素
            ,type: 'datetime'
        });

        //初始化下活动类型下拉框
        initSelectOfType(form);

        //初始化下活动品牌下拉框
        initSelectOfBrand(form);

        /*//上传图片
        var uploadListIns = upload.render({
            elem: '#test1'
            ,accept: 'file'
            ,accept: 'images'
            ,multiple: true
            ,url: '/upload/uploadImage'
            ,auto: true
            ,choose: function(obj){
                //将每次选择的文件追加到文件队列
                //var files = obj.pushFile();

                //预读本地文件，如果是多文件，则会遍历。(不支持ie8/9)
                obj.preview(function(index, file, result){
                    console.log(file)
                    var $li = $(['<li> <img class="layui-upload-img" src="'+result+'"><p class="demoText">'+file.name+'</p>' +
                    '<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button></li>'].join(''));

                    //删除
                    $li.find('.demo-delete').on('click', function(){
                        //delete files[index]; //删除对应的文件
                        // console.log($($li).index())
                        imgArr.splice($($li).index(), 1);
                        $("#headImg").val(imgArr);
                        $li.remove();
                        uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                    });

                    $('#image_list').append($li);
                });
            }
            ,done: function(res){
                //如果上传
                if(res.code == 0){
                    imgArr.push(res.src)
                    $("#headImg").val(imgArr);
                    layer.msg('上传成功');
                }
            }
            ,error: function(index, upload){
                //演示失败状态，并实现重传
                layer.msg(index+'上传失败');
                // var btn = $('#'+ index);
                btn.removeClass('layui-hide'); //显示重传
            }
        });*/

        //多文件列表示例
        var demoListView = $('#demoList')
                ,uploadListIns = upload.render({
            elem: '#testList'
            ,url: '/upload/uploadImage'
            ,accept: 'images'
            ,multiple: true
            ,auto: false
            ,bindAction: '#testListAction'
            ,choose: function(obj){
                var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
                //读取本地文件
                obj.preview(function(index, file, result){
                    var tr = $(['<tr id="'+ index +'">'
                        ,'<td><img src="'+result+'"/></td>'
                        ,'<td>'+ file.name +'</td>'
                        ,'<td><input type="text" value="'+file.name+'"/></td>'
                        ,'<td>等待上传</td>'
                        ,'<td>'
                        ,'<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>'
                        ,'<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>'
                        ,'</td>'
                        ,'</tr>'].join(''));

                    //单个重传
                    tr.find('.demo-reload').on('click', function(){
                        obj.upload(index, file);
                    });

                    //删除
                    tr.find('.demo-delete').on('click', function(){
                        delete files[index]; //删除对应的文件
                        tr.remove();
                        uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                        //var url = $("#"+index).find('input').val();
                        var urlStr = tr.find('input').val();
                        removeByValue(pathArr, urlStr);
                        console.log(pathArr);
                    });

                    demoListView.append(tr);

                });
            }
            ,before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
                var count = pathArr.length;
                var count2 = 0;
                obj.preview(function(index, file, result){count2++;}
                console.log(count+count2);
                if (count+count2 > 2) {
                    uploadListIns.stop();
                }

            }
            ,done: function(res, index, upload){
                if(res.code == 0){ //上传成功
                    var tr = $("#"+index);
                    tr.find('input').val(res.src).addClass("ok");
                    var tds = tr.children();
                    tds.eq(3).html('<span style="color: #5FB878;">上传成功</span>');
                    return delete this.files[index]; //删除文件队列已经上传成功的文件
                }
                //this.error(index, upload);
            }
            ,allDone: function(obj) {
                //当文件全部被提交后，才触发
                // console.log(obj.total); //得到总文件数
                // console.log(obj.successful); //请求成功的文件数
                // console.log(obj.aborted); //请求失败的文件数
                pathArr = [];
                $(".ok").each(function(index, element){
                    // console.log($(element).val());
                    pathArr.push($(this).val());
                });
                console.log(pathArr);
            }
            ,error: function(index, upload){
                var tr = demoListView.find('tr#'+ index)
                        ,tds = tr.children();
                tds.eq(3).html('<span style="color: #FF5722;">上传失败</span>');
                tds.eq(4).find('.demo-reload').removeClass('layui-hide'); //显示重传
            }
        });



        form.on('submit(formDemo)', function (data) {
            var formData = new FormData();

            if(pathArr.length > 2){
                layer.msg("上传数量不能大于2张");
                return false;
            }

            $.ajax({
                url: "/activities/add",
                type: "POST",
                data: {
                    // party_branch_id: $("#party_branch_id").val(),
                    party_branch_id:1,
                    title: $("#title").val(),
                    content: $("#content").val(),
                    type_id: $("#type_search").val(),
                    brand_id: $("#brand_search").val(),
                    start_time: $("#start_time").val(),
                    end_time: $("#end_time").val(),
                    is_end: $("#is_end").val(),
                    image_path_a: pathArr[0],
                    image_path_b: pathArr[1],
                    id: id
                },
                success: function (data) {
                    if (data.code == 0){
                        layer.msg('保存成功', {icon: 1});
                        setTimeout(function () {
                            window.location.href = '/frame/activityList.ftl'
                        }, 1500);
                    } else {
                        layer.msg('保存失败', {icon: 2});
                    }
                }
            });

        });

    });


</script>

</body>
</html>