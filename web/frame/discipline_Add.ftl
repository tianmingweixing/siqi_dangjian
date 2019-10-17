<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title> 违纪表</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css">
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jquery/jquery-3.3.1.min.js"></script>
</head>
<body>
<form class="layui-form" action="">
    <br>
    <div class="layui-form-item input_row_margin_top" style="display:none ">
        <label class="layui-form-label" style="margin-left: 85px">违纪ID</label>
        <input id="id" name="id" type="hidden"  maxlength="20" value=""/>
        <label class="layui-form-label" style="margin-left: 85px">违纪凭证</label>
        <div class="layui-input-inline"><input type="" id="imgPath" name="imgPath" value="" style="width: 400px;"/></div>
    </div>


    <div class="layui-form-item input_row_margin_top" style="display:none ">
        <label class="layui-form-label">用户ID</label>
        <div class="layui-input-inline">
            <input id="userId" name="userId" class="layui-input" placeholder="请输入用户ID" onchange="function()"
                   value=""/>
        </div>
    </div>

    <div class="layui-form-item input_row_margin_top" style="display:none ">
        <label class="layui-form-label">类型</label>
        <div class="layui-input-inline" >
            <select name="type" id="type">
                <option value="1" >违纪</option>
                <option value="0" >荣誉</option>
            </select>
        </div>
    </div>

  <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label ">姓名</label>
        <div class="layui-input-inline">
            <input id="userName" name="userName" readonly lay-verify="required" placeholder="请输入姓名" maxlength="20"
                   autocomplete="off" class="layui-input" value="">
        </div>

      <label class="layui-form-label " style="margin-left: 1px">时间</label>
      <div class="layui-input-inline">
          <input id="time" name="time" lay-verify="required" placeholder="请输入违纪时间" maxlength="20"
                 autocomplete="off" class="layui-input" value="">
      </div>
    </div>


    <div class="layui-form-item input_row_margin_top">

        <label class="layui-form-label " style="margin-left: 1px">违纪名称</label>
        <div class="layui-input-inline">
            <input id="name" name="name" lay-verify="required" placeholder="请输入违纪名称" maxlength="20"
                   autocomplete="off" class="layui-input" value="">
        </div>

        <label class="layui-form-label " style="margin-left: 1px">罚款</label>
        <div class="layui-input-inline">
            <input id="amount" name="amount"  placeholder="没有则不填" maxlength="20"
                   autocomplete="off" class="layui-input" value="">
        </div>
    </div>



    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">详情</label>
        <div class="layui-input-inline">
            <textarea name="content" id="content" placeholder="请输入违纪详情"
                      style="width: 500px; border:1px solid #e6e6e6; font-size: 10px; line-height: 23px;color: #0c060f;
                              max-width: 1500px; height: 100px; max-height: 1000px; outline: 0;"></textarea>
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-inline">
            <textarea name="note" id="note" placeholder="请输入备注"
                      style="width: 500px; border:1px solid #e6e6e6; font-size: 10px; line-height: 23px;color: #0c060f;
                              max-width: 1500px; height: 100px; max-height: 1000px; outline: 0;"></textarea>
        </div>
    </div>

    <div class="layui-form-item input_row_margin_top">
        <div class="layui-upload" style="margin-left: 60px;">
            <button type="button" class="layui-btn" id="uploadImg">上传违纪凭证</button>
            <span class="error-tips" style="color: #ff3100; font-size:13px; padding-left:10px;">
                        图片大小不超过200kb,尺寸为650 * 300。
                    </span>
            <div class="layui-upload-list">
                <img class="layui-upload-img" id="certificate" src="/images/defaultImg.jpg" style="width: 300px; height: 200px; border: 1px solid #CCCCCC;">
                <p id="demoText"></p>
            </div>
        </div>
    </div>



    <div class="layui-form-item">
        <div class="layui-form-item input_row_margin_top">
            <div class="layui-input-block">
                <button  class="layui-btn layui-btn-normal"  lay-submit lay-filter="formDemo">立即提交</button>
                <!--<button type="reset" class="layui-btn layui-btn-primary">重置</button>-->
            </div>
        </div>
    </div>

</form>
<script type="text/javascript" src="/js/util/compressImg.js"></script>

<script>

    $(function() {

        if (parent.PartitionData) {
            $("#userName").val(parent.PartitionData[0].username);
            $("#userId").val(parent.PartitionData[0].id);
        }

        layui.use(['laydate','form','upload'], function () {
            var form = layui.form;
            var laydate = layui.laydate //日期
                    ,layer = layui.layer; //弹层
            var upload = layui.upload;


            laydate.render({
                elem: '#time' //指定元素
            });

            $("#userId").change(function(){
                $.ajax({
                    url: "/user/getUserById",
                    type: 'post',
                    data: {
                        userId : $("#userId").val()
                    },
                    success: function (data) {
                        console.log(data.user.sex);

                        layer.msg('用户查询成功', {icon: 1});
                        $("#unit").val(data.user.userName);
                        $("#age").val(data.user.age);
                        $("#phone").val(data.user.phone);
                        $('input:radio').eq(data.user.sex).attr('checked', 'true');
                        form.render();
                    }
                });
            });

            //上传凭证
            upload.render({
                elem: '#uploadImg'
                ,accept: 'file'
                ,url: '/upload/uploadImage'
                ,auto: false
                ,choose: function(obj){
                    //将每次选择的文件追加到文件队列
                    var files = obj.pushFile();

                    var imgFiles = files;
                    var filesArry = [];
                    for (var key in imgFiles) { //将上传的文件转为数组形式
                        filesArry.push(imgFiles[key])
                    }

                    for (let i = filesArry.length-1; i >= 0; i--) {
                        var file = filesArry[i]; //获取最后选择的图片,即处理多选情况

                        if (navigator.appName == "Microsoft Internet Explorer" && parseInt(navigator.appVersion.split(";")[1]
                                .replace(/[ ]/g, "").replace("MSIE", "")) < 9) {
                            return obj.upload(index, file)
                        }
                        canvasDataURL(file, function (blob) {
                            var aafile = new File([blob], file.name, {
                                type: file.type
                            })
                            var isLt1M;
                            if (file.size < aafile.size) {
                                isLt1M = file.size
                            } else {
                                isLt1M = aafile.size
                            }

                            if (isLt1M / 1024 / 1024 > 2) {
                                return layer.alert('上传图片过大！')
                            } else {
                                if (file.size < aafile.size) {
                                    return obj.upload(i, file)
                                }
                                obj.upload(i, aafile)
                            }
                        })
                    }


                    //预读本地文件，如果是多文件，则会遍历。(不支持ie8/9)
                    obj.preview(function(index, file, result){
                        //???????
                        $('#certificate').attr('src', result); //图片链接（base64）
                    });
                }
                ,done: function(res){
                    //如果上传
                    if(res.code == 0){

                        //???? 图片路径数组
                        $("#imgPath").val(res.src);
                        layer.msg('上传成功');
                    }
                }
                ,error: function(){
                    //演示失败状态，并实现重传
                    var demoText = $('#demoText');
                    demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                    demoText.find('.demo-reload').on('click', function(){
                        uploadInst.upload();
                    });
                }
            });


            form.on('submit(formDemo)', function (data) {
                var defalutImg = "/images/defaultImg.jpg";

                if($("#imgPath").val() == ""){
                    $('#imgPath').val(defalutImg);
                }
                $.ajax({
                    url: "/disciplineOfHonor/addDisciplineOfHonor",
                    type: 'post',
                    data: {
                        id: $("#id").val(),
                        userId: $("#userId").val(),
                        unit: $("#userName").val(),
                        content: $("#content").val(),
                        type: $("#type").val(),
                        passive_unit: $("#passive_unit").val(),
                        certificate: $("#imgPath").val(),
                        amount: $("#amount").val(),
                        note: $("#note").val(),
                        name: $("#name").val(),
                        time: $("#time").val()
                    },
                    success: function () {
                        layer.msg('保存成功', {icon: 1});
                        setTimeout(function () {
                            window.location.href = '/frame/disciplineList.ftl'
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