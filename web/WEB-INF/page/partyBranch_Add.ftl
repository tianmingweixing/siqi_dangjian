<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>思齐党建管理系统</title>
    <link rel="stylesheet" href="../../js/layui/css/layui.css">
    <link rel="stylesheet" href="/css/public.css">
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

        #btn,#btn2 {
            padding: 5px 10px;
            background: #00b0f0;
            color: #FFF;
            border: none;
            border-radius: 5px;
        }

        label {
            position: relative;
        }

        #fileinp,#structure_img {
            position: absolute;
            left: 0;
            top: 0;
            opacity: 0;
        }

        #btn,#btn2 {
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
        修改党支部信息
    </blockquote>
</div>

<div style="width:90%">
    <div class="container_div">
        <form class="layui-form " name="fileForm" action="">

            <br>
            <div class="layui-input-inline" style="display: none">
                <label class="layui-form-label" style="margin-left: 85px">党支部Id</label>
                <input id="id" name="id" type="hidden" maxlength="20" value="<#if id??>${id}<#else></#if>"/>
            </div>
            <div class="layui-form-item input_row_margin_top">
                <label class="layui-form-label ">党支部名称</label>
                <div class="layui-input-inline">
                    <input id="name" name="name" lay-verify="required" placeholder="请输入党支部名称" maxlength="20"
                           autocomplete="off" class="layui-input" value="<#if name??>${name}<#else></#if>">
                </div>

        <label class="layui-form-label " >活动面积</label>
        <div class="layui-input-inline">
            <input id="activityArea" name="activityArea" lay-verify="number" placeholder="请输入活动面积/㎡" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if activityArea??>${activityArea}<#else></#if>">
        </div>

            </div>

            <div class="layui-form-item input_row_margin_top">
                <label class="layui-form-label ">成立时间</label>
                <div class="layui-input-inline">
                    <input id="foundingTime" name="foundingTime" placeholder="请输入成立时间" maxlength="20"
                           autocomplete="off" class="layui-input"
                           value="<#if foundingTime??>${foundingTime}<#else></#if>">
                </div>

                <label class="layui-form-label ">换届时间</label>
                <div class="layui-input-inline">
                    <input id="changeTime" name="changeTime" lay-verify="required" placeholder="请输入换届时间" maxlength="20"
                           autocomplete="off" class="layui-input" value="<#if changeTime??>${changeTime}<#else></#if>">
                </div>
            </div>

            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">党支部职责</label>
                <div class="layui-input-block">
            <textarea name="duty" id="duty" placeholder="请输入党支部职责"
                      style="margin-top:15px;width: 600px; border:1px solid #e6e6e6; font-size: 13px; line-height: 23px;color: #0c060f;
                              max-width: 1500px; height: 180px; max-height: 1000px; outline: 0;"><#if duty??>${duty}<#else></#if></textarea>
                </div>

                <label class="layui-form-label">党支部简介</label>
                <div class="layui-input-inline">
            <textarea name="partyInfo" id="partyInfo" placeholder="请输入支部简介"
                      style="margin-top:15px; width: 600px; border:1px solid #e6e6e6; font-size: 13px; line-height: 23px;color: #0c060f;
                             max-width: 1500px; height: 180px; max-height: 1000px; outline: 0;"><#if partyInfo??>${partyInfo}<#else></#if></textarea>
                </div>
            </div>

            <div class="layui-form-item input_row_margin_top">

                <label class="layui-form-label " style="margin-left: 1px; margin-top: 10px">支部图片</label>
                <div class="layui-input-inline" style="padding-top: 10px; margin-top: 10px">
                    <label for="fileinp" id="btn">选择图片</label>
                    <input type="file" id="fileinp" name="file" onchange="reads(this)">
                    <img id="backimg" name="backimg"
                         src="<#if party_img??>${party_img}<#else>\images\defaultImg.jpg</#if>" height="200" width="300"
                         alt="" style="margin-top: 10px">
                </div>
            </div>
            <div class="layui-form-item input_row_margin_top">

                <label class="layui-form-label " style="margin-left: 1px; margin-top: 10px">组织结构图</label>
                <div class="layui-input-inline" style="padding-top: 10px; margin-top: 10px">
                    <label for="structure_img" id="btn2">选择图片</label>
                    <input type="file" id="structure_img" name="structure_img" onchange="reads2(this)">
                    <img id="imgbox" name="imgbox"
                         src="<#if structure_img??>${structure_img}<#else>\images\defaultImg.jpg</#if>" height="200" width="300"
                         alt="" style="margin-top: 10px">
                </div>
            </div>


            <div class="layui-form-item input_row_margin_top">
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                </div>
            </div>


        </form>
    </div>
</div>

<script>

    $(function () {
        layui.use(['element', 'laydate', 'form'], function () {
            var form = layui.form;
            var laydate = layui.laydate //日期
                    , layer = layui.layer; //弹层
            var element = layui.element;

            laydate.render({
                type: 'date',
                elem: '#changeTime' //指定元素
                ,type: 'date'
            });

            laydate.render({
                type: 'date',
                elem: '#foundingTime'
                ,type: 'date'
            });

            form.on('submit(formDemo)', function () {

                var formData = new FormData();//这里需要实例化一个FormData来进行文件上传
                var file = document.fileForm.file.files[0];
                var file2 = document.fileForm.structure_img.files[0];

                if (file != null) {
                    formData.append("file", file);
                }
                if (file2 != null) {
                    formData.append("file2", file2);
                }


                formData.append("img_path", document.fileForm.backimg.src);
                formData.append("structure_img", document.fileForm.imgbox.src);
                formData.append("id", document.fileForm.id.value);
                formData.append("name", document.fileForm.name.value);
                formData.append("duty", document.fileForm.duty.value);
                formData.append("partyInfo", document.fileForm.partyInfo.value);
                formData.append("activityArea", document.fileForm.activityArea.value);
                formData.append("foundingTime", document.fileForm.foundingTime.value);
                formData.append("changeTime", document.fileForm.changeTime.value);

                $.ajax({
                    url: "/partyBranch/addPartBranch",
                    type: 'post',
                    data: formData,
                    cache: false,
                    contentType: false,
                    processData: false
                }).done(function(res) {
                    layer.msg('保存成功', {icon: 1});
                    setTimeout(function () {
                        window.location.href = '/frame/partyBranchList.ftl'
                    }, 1500);
                }).fail(function(res) {
                    alert('上传文件失败');
                });

                return false;
            });
        });
    });

    function reads(obj) {
        var file = obj.files[0];
        if (file.size > 1024 * 1024 * 5) {
            alert('图片大小不能超过 2MB!');
            return false;
        }
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function (ev) {
            $("#backimg").attr("src", ev.target.result);
        }
    }
    function reads2(obj) {
        var file = obj.files[0];
        if (file.size > 1024 * 1024 * 5) {
            alert('图片大小不能超过 2MB!');
            return false;
        }
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function (ev) {
            $("#imgbox").attr("src", ev.target.result);
        }
    }

</script>

</body>
</html>