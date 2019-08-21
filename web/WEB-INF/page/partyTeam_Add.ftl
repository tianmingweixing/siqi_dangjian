<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title> 党支部后台</title>
    <link rel="stylesheet" href="../../js/layui/css/layui.css">
    <script src="../../js/layui/layui.js"></script>
    <script src="../../js/jquery/jquery-3.3.1.min.js"></script>
    <!--<link rel="stylesheet" type="text/css" href="/WImageUpload/webuploader.css">-->
    <!--<script type="text/javascript" src="/WImageUpload/webuploader.js"></script>-->
</head>
<body>

<form class="layui-form" action="">

<br>
    <!--<input type="hidden" id="shareCount" value="<#if share_count??>${share_count}<#else></#if>">-->
    <div class="layui-input-inline" style="display: none">
        <label class="layui-form-label" style="margin-left: 85px">党支部Id</label>
        <input id="id" name="id" type="hidden"  maxlength="20" value="<#if id??>${id}<#else></#if>"/>
    </div>
    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label ">党支部名称</label>
        <div class="layui-input-inline">
            <input id="name" name="name" lay-verify="required" placeholder="请输入党支部名称" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if name??>${name}<#else></#if>">
        </div>
        <!--<label class="layui-form-label" style="margin-left: 85px">成员数量</label>
        <div class="layui-input-inline">
            <input id="partyMemberCount" name="partyMemberCount" lay-verify="required" placeholder="请输入成员数量" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if partyMemberCount??>${partyMemberCount}<#else></#if>">
        </div>-->

    </div>

    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label ">党小组职责</label>
        <div class="layui-input-inline">
            <input id="duty" name="duty" lay-verify="required" placeholder="请输入党小组职责" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if duty??>${duty}<#else></#if>">
        </div>
        <div class="layui-form-mid layui-word-aux"></div>
       <!-- <label class="layui-form-label " style="margin-left: 60px">活动面积</label>
        <div class="layui-input-inline">
            <input id="activityArea" name="activityArea" lay-verify="number" placeholder="请输入活动面积" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if activityArea??>${activityArea}<#else></#if>">
        </div>-->
    </div>

    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label ">党支部编号</label>
        <!--<label class="layui-form-label " style="margin-left: 85px">党支部编号</label>-->
        <div class="layui-input-inline">
            <input id="partyNo" name="partyNo" lay-verify="number" placeholder="请输入编号" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if partyNo??>${partyNo}<#else></#if>">
        </div>
    </div>

    <div class="layui-form-item input_row_margin_top">
    <label class="layui-form-label ">党小组编号</label>
    <!--<label class="layui-form-label " style="margin-left: 85px">党小组编号</label>-->
    <div class="layui-input-inline">
        <input id="partyGroupNo" name="partyGroupNo" lay-verify="number" placeholder="请输入编号" maxlength="20"
               autocomplete="off" class="layui-input" value="<#if partyGroupNo??>${partyGroupNo}<#else></#if>">
    </div>
    </div>

    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label ">成立时间</label>
        <div class="layui-input-inline">
            <input id="foundingTime" name="foundingTime" placeholder="请输入成立时间" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if foundingTime??>${foundingTime}<#else></#if>">
        </div>
        <label class="layui-form-label " style="margin-left: 84px">换届时间</label>
        <div class="layui-input-inline">
            <input id="changeTime" name="changeTime" lay-verify="required" placeholder="请输入换届时间" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if changeTime??>${changeTime}<#else></#if>">
        </div>
        <div class="layui-form-mid layui-word-aux"></div>
    </div>


<!--    <div class="layui-form-item layui-form-text input_row_margin_top" style="padding-right: 1000px">
        <label class="layui-form-label">党支部简介</label>
        <div class="layui-input-block">
            <textarea name="partyInfo" id="partyInfo" placeholder="请输入支部简介"
                      class="layui-textarea"><#if partyInfo??>${partyInfo}<#else></#if></textarea>
        </div>
    </div>-->

    <div class="layui-form-item">
        <div class="input_row_margin_top">
            <label class="layui-form-label">党支部图片</label>
            <div id="uploader">
                <!--用来存放文件信息-->
                <div id="thelist"  class="uploader-list layui-input-inline" style="width: 1192px">
                    <#if  imageList??>
                        <#list imageList as image>
                            <#if image_index==0>
                              <div style="display:inline-block;float: left;margin-top: 5px;">
                            <#else >
                              <div style="display:inline-block;float: left;margin-top: 5px;margin-left:40px">
                            </#if>
                            <img src="<#if partyImg??>${partyImg.src}<#else >''</#if>" style="width: 100px;height: 100px;">
                            <div style="font-size:14px;text-align:center;width: 100px;">${image.name}</div>
                        <div style="width:100px;text-align: center;margin-top:7px">
                            <img id="delete_icon" onclick="deleteImg(${image.id})" style="width: 25px;height: 25px;"
                                 src="/images/delete_icon.png"/>
                        </div>
                        </div>
                        </#list>
                    </#if>
                </div>

                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <div id="picker" style="display: inline-block;">选择文件</div>
            </div>
        </div>

        <div class="layui-form-item input_row_margin_top">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>

</form>

<script>

    $(function() {
       /* var $ = jQuery;
        var $list = $("#thelist");
        var $btn = $("#ctlBtn");
        var state = 'pending'; // 上传文件初始化
        // var uploader = WebUploader.create({
        //     swf: '/WImageUpload/Uploader.swf',
        //     server: 'https://wx.ankelife.com/upload/uploadImage',
        //     pick: '#picker',
        //     resize: false,
        //     accept: {
        //         title: 'Images',
        //         extensions: 'gif,jpg,jpeg,bmp,png',
        //         mimeTypes: 'image/!*'
        //     }
        // });


        uploader.on('fileQueued', function (file) {
            count++;
            var $li;
            if (count == 1 || count % 9 == 1) {
                $li = $(
                        '<div id="' + file.id + '" style="display:inline-block;float: left;margin-top: 5px">' +
                        '<img>' +
                        '<div  style="font-size: 14px;text-align: center;width: 100px;">' + file.name.substr(0, file.name.length - 4) + '</div></div>'
                );
            } else {
                $li = $(
                        '<div id="' + file.id + '" style="display:inline-block;float: left; margin-left: 40px;margin-top:5px">' +
                        '<img >' +
                        '<div  style="font-size: 14px;text-align: center;width: 100px;">' + file.name.substr(0, file.name.length - 4) + '</div></div>'
                );
            }
            var $img = $li.find('img');
            var $btns = $('<div style="width: 100px;text-align: center;margin-top: 7px"><  img id="delete_icon" style="width: 25px;height: 25px;"/><text style="font-size: 14px;margin-left: 3px">待上传</text></div>').appendTo($li);
            $li.find('#delete_icon').attr("src", "/images/delete_icon.png");

            // $list为容器jQuery实例
            $list.append($li);
            $btns.on('click', 'img', function () {
                var index = $(this).index();
                switch (index) {
                    case 0:
                        uploader.removeFile(file);
                        removeFile(file);
                        return;
                }
            });


            //创建缩略图
            //如果为非图片文件，可以不用调用此方法。
            //thumbnailWidth x thumbnailHeight 为 100 x 100
            uploader.makeThumb(file, function (error, src) {
                if (error) {
                    $img.replaceWith('<span>不能预览</span>');
                    return;
                }
                $img.attr('src', src);
            }, 100, 100);
        });


        uploader.on('uploadProgress',
                function (file, percentage) {
                    var $li = $('#' + file.id), $percent = $li
                            .find('.progress .progress-bar');

                    // 避免重复创建
                    if (!$percent.length) {
                        $percent = $(
                                '<div class="progress progress-striped active" style="width: 100px;height: 15px;border-radius: 5px;background-color: #ee232a">'
                                + '<div class="progress-bar" role="progressbar" style="width: 0%;background-color: #111111">'
                                + '</div>' + '</div>')
                                .appendTo($li).find('.progress-bar');
                    }

                    $li.find('text').text('上传中...');

                    $percent.css('width', percentage * 100 + '%');
                });

        uploader.on('uploadBeforeSend', function (obj, data) {
            //传入表单参数
            data = $.extend(data, {
                uuid: uuid
            });
        });
        uploader.on('uploadSuccess', function (file) {
            $('#' + file.id).find('text').text('已上传');
        });

        uploader.on('uploadError', function (file) {
            $('#' + file.id).find('text').text('上传出错');
        });

        uploader.on('uploadComplete', function (file) {
            $('#' + file.id).find('.progress').fadeOut();
        });
        $btn.on('click', function () {

        });*/

        // function removeFile(file) {
        //     var $li = $('#' + file.id);
        //     $li.off().find('.file-panel').off().end().remove();
        //     count--;
        // }

        layui.use('form', function () {
            var form = layui.form;

            form.on('submit(formDemo)', function (data) {

                // if (state === 'uploading') {
                //     uploader.stop();
                // } else {
                //     uploader.upload();
                // }

                $.ajax({
                    url: "/partyTeam/addPartyTeam",
                    data: {
                        id: $("#id").val(),
                        name: $("#name").val(),
                        partyGroupNo: $("#partyGroupNo").val(),
                        duty: $("#duty").val(),
                        partyNo: $("#partyNo").val(),
                        foundingTime: $("#foundingTime").val(),
                        changeTime: $("#changeTime").val()
                    },
                    success: function () {
                        layer.msg('保存成功', {icon: 1});
                        setTimeout(function () {
                            window.location.href = '/frame/partyTeamList.ftl'
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