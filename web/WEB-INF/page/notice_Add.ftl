<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title> 添加公示公告</title>
    <link rel="stylesheet" href="../../js/layui/css/layui.css">
    <script src="../../js/layui/layui.js"></script>
    <script src="../../js/jquery/jquery-3.3.1.min.js"></script>

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
<div class="layui-progress">
    <div class="layui-progress-bar " lay-percent="100%"></div>
</div>
<form class="layui-form" name="fileForm" action="">

    <br>
    <div class="layui-form-item input_row_margin_top" style="display:none ">
        <label class="layui-form-label" style="margin-left: 85px">公示公告ID</label>
        <input id="id" name="id" type="hidden" maxlength="20" value="<#if id??>${id}<#else></#if>"/>
    </div>


    <div class="layui-form-item input_row_margin_top">

        <label class="layui-form-label " style="margin-left: 1px">公示标题</label>
        <div class="layui-input-inline">
            <input id="title" name="title" lay-verify="required" placeholder="请输入标题" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if title??>${title}<#else></#if>">
        </div>

    </div>

    <div class="layui-form-item input_row_margin_top">

        <label class="layui-form-label " style="margin-left: 1px">公示图片</label>
        <div class="layui-input-inline" style="padding-top: 10px">
            <label for="fileinp" id="btn">选择图片</label>
            <input type="file" id="fileinp" name="file" onchange="reads(this)">
            <img id="backimg" name="backimg" src="<#if image_path??>${image_path}<#else></#if>" height="auto"
                 width="150" alt="" style="margin-top: 10px">
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
                      style="width: 1000px; border:1px solid #e6e6e6; font-size: 16px; line-height: 23px; font-family: 微软雅黑;
                              max-width: 1500px; height: 400px; max-height: 1000px; outline: 0;"><#if content??>${content}<#else></#if></textarea>
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

<script>

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
                var formData = new FormData();//这里需要实例化一个FormData来进行文件上传
                var file = document.fileForm.file.files[0];
                if (file != null) {
                    formData.append("file", file);
                }
                formData.append("img_path", document.fileForm.backimg.src);
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

    /*图片上传辅助*/
    function reads(obj) {
        var file = obj.files[0];
        if (file.size > 1024 * 1024 * 2) {
            alert('图片大小不能超过 2MB!');
            return false;
        }
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function (ev) {
            $("#backimg").attr("src", ev.target.result);
        }
    }


</script>

</body>
</html>