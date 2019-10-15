<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title> 添加公示公告</title>
    <link rel="stylesheet" href="../../js/layui/css/layui.css">
    <script src="../../js/layui/layui.js"></script>
    <script src="../../js/jquery/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="/css/public.css">

    <!--上传图片样式-->
    <style type="text/css">
        .body {
            width: 800px;
            margin: 0 auto 0 auto;
        }

        .layui-unselect {
            width: 200px
        }

        #btn {
            padding: 5px 10px;
            background: #00b0f0;
            color: #FFF;
            border: none;
            border-radius: 5px;
        }

        label {
            position: relative;
        }

        #fileinp {
            position: absolute;
            left: 0;
            top: 0;
            opacity: 0;
        }

        #btn {
            margin-right: 5px;
        }

        #text {
            color: #009688;
        }

        /* table 样式 */
        table {
            border-top: 1px solid #ccc;
            border-left: 1px solid #ccc;
        }

        table td,
        table th {
            border-bottom: 1px solid #ccc;
            border-right: 1px solid #ccc;
            padding: 3px 5px;
        }

        table th {
            border-bottom: 2px solid #ccc;
            text-align: center;
        }

        /* blockquote 样式 */
        blockquote {
            display: block;
            border-left: 8px solid #d0e5f2;
            padding: 5px 10px;
            margin: 10px 0;
            line-height: 1.4;
            font-size: 100%;
            background-color: #f1f1f1;
        }

        /* code 样式 */
        code {
            display: inline-block;
            *display: inline;
            *zoom: 1;
            background-color: #f1f1f1;
            border-radius: 3px;
            padding: 3px 5px;
            margin: 0 3px;
        }

        pre code {
            display: block;
        }

        /* ul ol 样式 */
        ul, ol {
            margin: 10px 0 10px 20px;
        }

    </style>

</head>
<body>
    <div style="width: 90%">
        <blockquote class="layui-elem-quote layui-quote-nm" id="footer"
                    style="margin-top: 50px;margin-left: 5%;padding-left: 45px;border-color: #009688;color: #009688;font-weight: bold">
            添加公示公告
        </blockquote>
    </div>
    <div style="width:90%">
        <div class="container_div">
            <form class="layui-form" name="fileForm" action="">

        <br>
        <div class="layui-form-item input_row_margin_top" style="display:none ">
            <label class="layui-form-label" style="margin-left: 85px">公示公告ID</label>
            <input id="id" name="id" type="hidden" maxlength="20" value="<#if id??>${id}<#else></#if>"/>
        </div>


        <div class="layui-form-item input_row_margin_top">

            <label class="layui-form-label " style="margin-left: 1px">公示标题</label>
            <div class="layui-input-inline">
                <input id="title" name="title" lay-verify="required" style="width: 800px;" placeholder="请输入标题" maxlength="200"
                       autocomplete="off" class="layui-input" value="<#if title??>${title}<#else></#if>">
            </div>

        </div>

        <div class="layui-form-item input_row_margin_top" style="display:none ">

            <label class="layui-form-label">支部ID</label>
            <div class="layui-input-inline">
                <input id="party_branch_id" name="party_branch_id" lay-verify="" placeholder="支部ID" maxlength="20"
                       autocomplete="off" class="layui-input"
                       value="<#if party_branch_id??>${party_branch_id}<#else></#if>">
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">公示信息</label>
            <div class="layui-input-block">
                <textarea name="content" id="content" placeholder="请输入公示信息"
                          style="width: 800px; border:1px solid #e6e6e6; font-size: 13px; line-height: 23px;color: #0c060f;
                                  max-width: 1500px; height: 250px; max-height: 1000px; outline: 0;"><#if content??>${content}<#else></#if></textarea>
            </div>

        </div>

        <div class="layui-form-item input_row_margin_top">

            <label class="layui-form-label " style="margin-left: 1px">公示图片</label>
            <div class="layui-input-inline" style="padding-top: 10px">
                <label for="fileinp" id="btn" >选择图片</label>
                <input type="file" id="fileinp" name="file" onchange="reads(this)">
                <img id="backimg" name="backimg" src="<#if image_path??>${image_path}<#else>/images/defaultImg.jpg</#if>" height="250"
                     width="400" alt="" style="margin-top: 10px">
            </div>

        </div>

        <div class="layui-form-item">
            <div class="layui-form-item input_row_margin_top">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-normal" lay-submit lay-filter="formDemo">立即提交</button>
                    <#if id??><#else>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button></#if>

                </div>
            </div>
        </div>

    </form>
        </div>
    </div>

    <script type="text/javascript" src="/js/util/compressImg.js"></script>
<script>
    // 构建FormData
    var formData = new FormData();

    /*图片上传辅助*/
    function reads(obj) {
        var file = obj.files[0];
        if (file.size > 1024 * 1024 * 2) {
            alert('图片大小不能超过 2MB!');
            return false;
        }

        var files = obj.files;

        if (files && files.length > 0) {
            file = files[0];

            resizeImage(file).then(function (result) {
                return typeof result === 'string' ? convertToBlob(result, file.type) : result;
            }).then(function (blob) {

                //注意：此处第3个参数最好传入一个带后缀名的文件名，否则很有可能被后台认为不是有效的图片文件
                formData.append("file", blob, file.name);
            });
        }

        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function (ev) {
            $("#backimg").attr("src", ev.target.result);
        }
    }

    /**
     * 压缩裁剪图片
     */
    function resizeImage(file) {
        return new Promise(function (resolve, reject) {
            var reader = new FileReader();

            reader.onload = function () {
                var img = new Image();

                img.onload = function () {
                    var w = this.naturalWidth;
                    var h = this.naturalHeight;
                    var maxW = 500;
                    var maxH = 500;

                    // 如果图片尺寸小于最大限制，则不压缩直接上传
                    if (w <= maxW && h <= maxH) {
                        resolve(file);
                        return;
                    }

                    var level = 0.8;
                    var multiple = Math.max(w / maxW, h / maxH);
                    var resizeW = w / multiple;
                    var resizeH = h / multiple;

                    var canvas = document.createElement("canvas");

                    canvas.width = resizeW;
                    canvas.height = resizeH;

                    var ctx = canvas.getContext("2d");

                    ctx.drawImage(img, 0, 0, resizeW, resizeH);

                    var base64Img = canvas.toDataURL(file.type, level);
                    var arr = base64Img.split(",");

                    resolve(arr[1]);
                };

                img.src = this.result;
            };

            reader.readAsDataURL(file);
        });
    }

    /**
     * 将图片的base64字符串转换为Blob对象
     */
    function convertToBlob(base64Str, fileType) {
        var base64 = window.atob(base64Str);
        var len = base64.length;
        var buff = new ArrayBuffer(len);
        var uarr = new Uint8Array(buff);

        for (var i = 0; i < len; i++) {
            uarr[i] = base64.charCodeAt(i);
        }

        var blob = null;

        try {
            blob = new Blob([buff], { type: fileType });
        } catch (e) {
            var BlobBuilder = window.BlobBuilder = (
                    window.BlobBuilder ||
                    window.WebKitBlobBuilder ||
                    window.MozBlobBuilder ||
                    window.MSBlobBuilder
            );

            if (e.name === "TypeError" && BlobBuilder) {
                var builder = new BlobBuilder();
                builder.append(buff);
                blob = builder.getBlob(fileType);
            }
        }

        return blob;
    }


    $(function () {
        /*layiu基础配件*/
        layui.use(['element', 'laydate', 'form'], function () {
            var form = layui.form;
            var laydate = layui.laydate //日期
                    , layer = layui.layer; //弹层
            var element = layui.element;

            laydate.render({
                elem: '#year_limit' //指定元素
            });


            form.on('submit(formDemo)', function (data) {
               /* var formData = new FormData();//这里需要实例化一个FormData来进行文件上传
                var file = document.fileForm.file.files[0];
                if (file != null ) {
                    canvasDataURL(file, function (blob) {
                        var aafile = new File([blob], file.name, {
                            type: file.type
                        })
                        if (file != null) {
                            formData.append("file", blob,file.name);
                        }
                    })
                }*/



                var img_path = document.fileForm.backimg.src;
                // 图片正则
                var patrn = /\.(png|jpe?g|gif|svg)(\?.*)?$/
                // const picReg = new RegExp (PICTURE_EXPRESSION)
                // var patrn = /^([/0-9a-zA-Z_.]+)?$/;
                if (patrn.exec(img_path)) {
                    formData.append("img_path", img_path);
                }

                formData.append("content", document.fileForm.content.value);
                formData.append("title", document.fileForm.title.value);
                formData.append("id", document.fileForm.id.value);

                $.ajax({
                    url: "/notice/addNotice",
                    type: 'post',
                    data: formData,
                    cache: false,
                    contentType: false,
                    processData: false,
                    success: function () {
                        layer.msg('保存成功', {icon: 1});
                        setTimeout(function () {
                            window.location.href = '/frame/noticeList.ftl'
                        }, 1500);
                    }
                });
                return false;
            });
        });

    });




</script>

</body>
</html>