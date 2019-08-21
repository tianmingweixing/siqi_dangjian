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

<div style="width: 90%">
    <blockquote class="layui-elem-quote layui-quote-nm" id="footer"
                style="margin-top: 50px;margin-left: 5%;padding-left: 45px;border-color: #009688;color: #009688;font-weight: bold">
        设置轮播
    </blockquote>
</div>
<input type="hidden" id="img_path" name="img_path" value="" style="width: 800px;"/>


    <div style="width: 90%">
        <div class="container_div">
            <div class="layui-upload">
                <div class="layui-upload-list">
                    <table class="layui-table">
                        <thead>
                        <tr>
                            <th>文件名</th>
                            <th>序号</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr></thead>
                        <tbody id="demoList"></tbody>
                    </table>
                </div>
                <button type="button" class="layui-btn layui-btn-normal" id="testList">选择多文件</button>
                <button type="button" class="layui-btn" id="testListAction">开始上传</button>
            </div>
        </div>
    </div>




<script>


    $().ready(function () {
        //初始化信息//
        initComInfo();

    })

    function deleteImg(obj,index) {
        console.log(obj);
        console.log(obj.parents('tr'));
        var tr = obj.parents('tr')
                ,tds = tr.children();
        var delurl = tds.eq(0).html();
        var index = tds.eq(1).html();
        console.log(delurl);
        console.log(index);
        //删除数据库图片地址
        $.ajax({
            url: "/config/deleteImage",
            method: "GET",
            data: {
                index: index
            },
            success: function (data) {
                if (data.result == 'success') {
                    $("#demoList").html("");
                    initComInfo();
                } else {
                    layer.msg(data.result, {icon: 2});
                }
            }
        })


    }

    function initComInfo (){

        var imgObjPreview = $("#demoList");
        $.ajax({
            url: "/config/getConfig",
            method: "GET",
            data: {
            },
            success: function (data) {
                if (data.result == 'success') {

                    var urlArr = data.lunbo;

                    // $("#demoList").html(data.comInfo.lunbo);
                    if (urlArr) {
                        var urlArrStr = "";

                        for (var i = 0; i < urlArr.length; i++) {

                            if (urlArr[i] != null && urlArr[i] != "") {
                                urlArrStr = urlArrStr + "&" + urlArr[i];
                                var numb = urlArr[i].lastIndexOf("/")+1;
                                var length = urlArr[i].length;
                                var fildName = urlArr[i].substring(numb,length);
                                var tr = $(['<tr id="upload-'+ i +'">'
                                    ,'<td>'+ fildName +'</td>'
                                    ,'<td>'+ i + '</td>'
                                    ,'<td><span style="color: #5FB878;">上传成功</span></td>'
                                    ,'<td>'
                                    ,'<button class="layui-btn layui-btn-xs layui-btn-danger img-delete" onclick="deleteImg($(this),'+ i + ')">删除</button>'
                                    ,'</td>'
                                    ,'</tr>'].join(''));

                                imgObjPreview.append(tr);
                            }
                        }
                        $("#img_path").val(urlArrStr);
                    }

                } else {
                    layer.msg(data.result, {icon: 2});
                }
            }
        })
    }


    layui.use(['layer', 'element', 'upload', 'form' ], function(){
        var $ = layui.jquery;
        var layer = layui.layer //弹层
            ,element = layui.element //元素操作
            ,upload = layui.upload;



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
                    var tr = $(['<tr id="upload-'+ index +'">'
                        ,'<td>'+ file.name +'</td>'
                        ,'<td>'+ index +'</td>'
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
                    });

                    demoListView.append(tr);

                });
            }
            ,done: function(res, index, upload){
                if(res.code == 0){ //上传成功
                    var img_path = $("#img_path").val();
                    img_path = img_path+"&"+res.src;
                    $("#img_path").val(img_path);
                    console.log(res);

                    /*var tr = demoListView.find('tr#upload-'+ index)
                            ,tds = tr.children();
                    tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                    tds.eq(3).html('<button class="layui-btn layui-btn-xs layui-btn-danger img-delete" onclick="deleteImg($(this))">删除</button>'); //清空操作*/
                    return delete this.files[index]; //删除文件队列已经上传成功的文件
                }
                this.error(index, upload);
            }
            ,allDone: function(obj){ //当文件全部被提交后，才触发
                // console.log(obj.total); //得到总文件数
                // console.log(obj.successful); //请求成功的文件数
                // console.log(obj.aborted); //请求失败的文件数
                var path = $("#img_path").val();
                var pathArr = [];
                if (path.length > 1) {
                    path = path.substring(1,path.length);
                    pathArr = path.split("&");
                }
                console.log(pathArr);
                //保存路径

                $.ajax({
                    url: "/config/saveLunbo",
                    method: "POST",
                    data: {
                        pathArr: pathArr
                    },
                    success: function (data) {
                        if (data.result=="success") {
                            layer.msg('保存成功', {icon: 1});
                            setTimeout(function () {
                                location.reload();
                                // window.location.href = '../frame/sysAdSet.ftl'
                            }, 1000);
                        }else {
                            layer.msg('保存失败', {icon: 1});
                        }

                    }
                });


            }
            ,error: function(index, upload){
                var tr = demoListView.find('tr#upload-'+ index)
                        ,tds = tr.children();
                tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
                tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
            }
        });



    });









   /* var filds = [];
    $().ready(function () {
        //初始化信息//
        initComInfo();
    })

    function reset_search(){
        window.location.reload();
    }

    function initComInfo (){

        var imgObjPreview = $("#localImag");
        $.ajax({
            url: "/sysset/getConfig",
            method: "GET",
            data: {
            },
            success: function (data) {
                if (data.result == 'success') {
                    // console.log(data.comInfo);
                    var $li;
                    var urlArr = [];

                    if (data.lunbo) {
                        /!*var urlArrStr = data.comInfo.lunbo;
                        if (urlArrStr.length > 1) {
                            urlArrStr = urlArrStr.substring(1, urlArrStr.length - 1);
                            urlArr = urlArrStr.split(',');
                        }*!/
                        urlArr = data.lunbo;
                        imgObjPreview.html("");
                        for (var i = 0; i < urlArr.length; i++) {
                            var uri = urlArr[i].toString();
                            var index = uri.lastIndexOf('/');
                            var fname = uri.substring(index+1,uri.length);
                            $li = $(
                                    '<div class="img'+i+'" style="display:inline-block;margin-top: 5px; margin-left: 5px;">' +
                                    '<img src="'+uri+'" style="width:100px; height: auto;"/>' +
                                    '<div  style="font-size: 14px;text-align: center;width: 100px;white-space: nowrap;overflow: hidden;text-overflow: ellipsis;">' + fname + '</div></div>'
                            );
                            imgObjPreview.append($li);
                        }

                    }

                } else {
                    layer.msg(data.result, {icon: 2});
                }
            }
        })
    }

    function showImage(evt,show) {

        var docObj = evt;
        var imgObjPreview = show;
        // var file = $this[0].files;
        // var docObj = document.getElementById("input_file");
        // var imgObjPreview = $("#localImag");
        var $li;
        var reader = new FileReader();

        if (docObj.files) {

            var html = "";
            imgObjPreview.html(html);
            for (var i=0; i < docObj.files.length; i++){
                var path = window.URL.createObjectURL(docObj.files[i]);
                var file = docObj.files[i];
                filds.push(file.name);
                console.log(filds);
                $li = $(
                        '<div id="' + docObj.files[i].name + '" style="display:inline-block;margin-top: 5px; margin-left: 5px;">' +
                        '<img src="' + path + '" style="width:100px; height: auto;"/>' +
                        '<div  style="font-size: 14px;text-align: center;width: 100px;white-space: nowrap;overflow: hidden;text-overflow: ellipsis;">' + docObj.files[i].name + '</div></div>'
                );
                // var $img = $li.find('img');
                // var $btns = $('<div style="width: 100px;text-align: center;margin-top: 7px"><img id="delete_icon" onclick="removeFile($(\'#input_file\'))" style="width: 25px;height: 25px;"/></div>').appendTo($li);
                // $li.find('#delete_icon').attr("src", "/images/delete_icon.png");
                imgObjPreview.append($li);
            }

            //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
            // imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
        } else {
            alert("文件不能为空！");
        }
        return true;
    }


    layui.use(['form','laydate', 'laypage', 'layer', 'element'], function () {
        var form = layui.form;
        var laydate = layui.laydate; //日期

        laydate.render({
            elem: '#valid_time', //指定元素
            type: 'datetime'
        });


        form.on('submit(formDemo)', function (data) {
            var formData = new FormData();

            var fildlsize = $('#input_file')[0].files.length;
            if (fildlsize <= 0){
                return false;
            }

            for(var i=0; i<$('#input_file')[0].files.length; i++) {
                formData.append('files',$('#input_file')[0].files[i]);  //添加其他参数
            }
            $.ajax({
                url: "/sysset/filesUpload",
                type: "POST",//请求方式
                enctype: "multipart/form-data",
                processData: false, // 告诉jQuery不要去处理发送的数据
                contentType: false, // 告诉jQuery不要去设置Content-Type请求头
                data: formData,
                success: function (data) {
                    if (data.result=="success") {
                        layer.msg('保存成功', {icon: 1});
                        setTimeout(function () {
                            window.location.href = '../frame/sysAdSet.ftl'
                        }, 1500);
                    }else {
                        layer.msg('保存失败', {icon: 1});
                    }

                }
            });
            return false;
        });

    });*/


</script>

</body>
</html>